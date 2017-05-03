
package com.sdi.business.integration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.ejb.ActivationConfigProperty;

import alb.util.date.DateUtil;
import alb.util.log.Log;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.task.LocalTaskService;
import com.sdi.business.impl.user.LocalUserService;
import com.sdi.business.services.TaskService;
import com.sdi.business.services.UserService;
import com.sdi.dto.Task;
import com.sdi.dto.User;


@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(
						propertyName = "destination",
						propertyValue = "jms/queue/GTDQueue")
})
public class GTDListener implements MessageListener {
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
	@Resource(mappedName = "java:/jms/queue/ErrorQueue")
	private Destination errorQueue;
	
	@EJB(beanInterface = LocalUserService.class)
	private UserService userServ;
	@EJB(beanInterface = LocalTaskService.class)
	private TaskService taskServ;
	
	
	private Connection con;
	private Session session;
	private MessageProducer errorSender;
	
	@PostConstruct
	private void postConst() throws JMSException{
		
		con = factory.createConnection("sdi","password");
	}

	@Override
	public void onMessage(Message msg) {
		try{
			System.out.println(msg);
			TextMessage tmsg = process(msg);
			reply(tmsg, msg.getJMSReplyTo(),msg);
			
		}
		catch(JMSException jex){
			jex.printStackTrace();
			Log.error("JMS error");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
	}

	private void reply(TextMessage tmsg, Destination destination, Message msg) throws JMSException {
		MessageProducer replyProducer = session.createProducer(msg.getJMSReplyTo());
		tmsg.setJMSCorrelationID(msg.getJMSCorrelationID());
		replyProducer.send(tmsg);
		
	}

	private TextMessage process(Message msg) throws JMSException {
		
		initialize();
		
		if(!messageOfExceptedType(msg)){
			Log.error("Wrong message type.");
			return createIncidence("Wrong Message Type");
		}
		MapMessage m = (MapMessage) msg;
		
		String cmd = m.getString("command");
		TextMessage replyMessage = session.createTextMessage();
		
		try {
			User user = userServ.findLoggableUser(m.getString("nameuser"), m.getString("password"));
			if(user==null){
				replyMessage = createIncidence("El usuario no existe o está deshabilitado",
						"Usuario con login: " + m.getString("nameuser") + "ha intentado acceder a los servicios" );
			}
			else{
				Log.debug("Message received with command " + cmd);
				if(cmd.equals("listTasks")){
					replyMessage = listTasks(replyMessage, user);
				}
				else if(cmd.equals("finishTask"))
					replyMessage = finishTask(m,replyMessage);
				 else if(cmd.equals("createTask"))
					 replyMessage = createTask(m, replyMessage, user);
				 else
					 replyMessage = createIncidence("Command error", "Command error: " + cmd);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			replyMessage = createIncidence("Se ha producido un error procesando la petición", "Se ha producido un BusinessException");
			
		}
		
		return replyMessage;
		
		
	}
	
	
	private TextMessage listTasks(TextMessage replyMessage, User user) throws BusinessException, JMSException {
		List<Task> tasks = taskServ.findTodayTasksByUserId(user.getId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder sb = new StringBuilder();
		sb.append("--------------- List of requested Tasks ----------------- \n");
		sb.append("ID \t");
		sb.append("Title \t");
		sb.append("Created \t");
		sb.append("Planned \t");
		sb.append("Comments \n");
		
		for(Task task : tasks){
			sb.append(task.getId()+" \t");
			sb.append(task.getTitle()+ "\t");
			sb.append(sdf.format(task.getCreated())+ "\t");
			sb.append(task.getPlanned()==null? "Not planned \t" : sdf.format(task.getPlanned())+ "\t" );
			sb.append(task.getComments()==null? "\t" : task.getComments()+"\t");
			sb.append("\n");
		}
		
		sb.append("----------------------------------------------------------");
		
		return session.createTextMessage(sb.toString());
	}
	
	private TextMessage finishTask(MapMessage m, TextMessage replyMessage) throws BusinessException, JMSException {
		taskServ.markTaskAsFinished(m.getLong("taskId"));
		return session.createTextMessage("La tarea ha sido finalizada correctamente.");
		
	}

	private TextMessage createTask(MapMessage m, TextMessage replyMessage, User user) throws JMSException, BusinessException {
		Task task = new Task();
		Date plannedDate = null;
		try{
		plannedDate = DateUtil.fromString(m.getString("planned"));
		}
		catch(ArrayIndexOutOfBoundsException e){
		}
		if(plannedDate==null)
			return createIncidence("Wrong Date format");
		task.setTitle(m.getString("titulo")).setComments(m.getString("comentario"))
		.setPlanned(plannedDate).setCategoryId(m.getLong("categoryID")).setUserId(user.getId());
		
		
		taskServ.createTask(task);
		
		return session.createTextMessage("La tarea ha sido creada correctamente.");
		
	}


	private boolean messageOfExceptedType(Message msg) {
		return msg instanceof MapMessage;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	private void initialize() throws JMSException{
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		con.start();
		
		errorSender = session.createProducer(errorQueue);
	}
	
	private TextMessage createIncidence(String content) throws JMSException{
		TextMessage tmsg = session.createTextMessage(content +".");
		
		Date currentDate = new Date();
		
		TextMessage tsmgError = session.createTextMessage(content+ "\t" + currentDate);
		errorSender.send(tsmgError);
		return tmsg;
	}
	
	private TextMessage createIncidence(String contentUser, String contentAdmin) throws JMSException{
		
		
		Date currentDate = new Date();
		
		TextMessage tsmgError = session.createTextMessage(contentAdmin+ "\t" + currentDate);
		errorSender.send(tsmgError);
		TextMessage tmsg = session.createTextMessage(contentUser+".");
		return tmsg;
	}
	

}

