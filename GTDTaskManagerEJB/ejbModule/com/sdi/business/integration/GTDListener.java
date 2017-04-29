package com.sdi.business.integration;

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
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.ejb.ActivationConfigProperty;

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
						propertyValue = "queue/GTDQueue")
})
public class GTDListener implements MessageListener {
	
	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
//	@Resource(mappedName = "java:/queue/GTDQueue")
//	private Destination requestQueue;
	
	@EJB(beanInterface = LocalUserService.class)
	private UserService userServ;
	@EJB(beanInterface = LocalTaskService.class)
	private TaskService taskServ;
	
	
	private Connection con;
	private Session session;
	
	@PostConstruct
	private void initialize() throws JMSException{
		
		con = factory.createConnection("sdi","password");
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

		con.start();
	}

	@Override
	public void onMessage(Message msg) {
		System.out.println("GOTCHA!!");
		try{
			System.out.println(msg);
			TextMessage tmsg = process(msg);
			reply(tmsg, msg.getJMSReplyTo(),msg);
			
		}
		catch(JMSException jex){
			//TODO log the exception
			System.out.println("ERROR!!");
		}
		
		
	}

	private void reply(TextMessage tmsg, Destination destination, Message msg) throws JMSException {
		MessageProducer replyProducer = session.createProducer(msg.getJMSDestination());
		tmsg.setJMSCorrelationID(msg.getJMSCorrelationID());
		replyProducer.send(tmsg);
		
	}

	private TextMessage process(Message msg) throws JMSException {
//		if(!messageOfExceptedType(msg)){
//			System.out.print("Wrong message type.");
//			return session.createTextMessage("Wrong message type.");
//		}
		
		MapMessage m = (MapMessage) msg;
		
		String cmd = m.getString("command");
		TextMessage replyMessage = session.createTextMessage();
		
		try {
			User user = userServ.findLoggableUser(m.getString("nameuser"), "password");
			if(user==null){
				replyMessage = session.createTextMessage("Usuario no válido");
				System.out.println("ITS HEREEE!!!");
			}
			else{
				if(cmd.equals("listTasks"))
					listTasks(replyMessage, user);
				else if(cmd.equals("finishTask"))
					finishTask(m,replyMessage);
				 else if(cmd.equals("createTask"))
					 createTask(m, replyMessage, user);
				 else
					 System.out.println("wrong command");
				 //TODO aqui con else con que el formato está mal
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			replyMessage = session.createTextMessage("Se ha producido un error procesando la petición"); 
			
		}
		
		return replyMessage;
		
		
	}
	
	
	private void listTasks(TextMessage replyMessage, User user) throws BusinessException, JMSException {
		List<Task> tasks = taskServ.findTodayTasksByUserId(user.getId());
		
		StringBuilder sb = new StringBuilder();
		sb.append("--------------- List of requested Tasks -----------------");
		sb.append("ID \t");
		sb.append("Title \t");
		sb.append("Created \t");
		sb.append("Planned \t");
		sb.append("Comments \n");
		
		for(Task task : tasks){
			sb.append(task.getId()+" \t");
			sb.append(task.getTitle()+ "\t");
			sb.append(task.getCreated()+ "\t");
			sb.append(task.getPlanned()==null? "Not planned \t" : task.getPlanned()+ "\t" );
			sb.append(task.getComments()+ "\t");
		}
		
		sb.append("----------------------------------------------------------");
		
		replyMessage=session.createTextMessage(sb.toString());
	}
	
	private void finishTask(MapMessage m, TextMessage replyMessage) throws BusinessException, JMSException {
		taskServ.markTaskAsFinished(m.getLong("taskId"));
		replyMessage = session.createTextMessage("La tarea ha sido finalizada correctamente.");
		
	}

	private void createTask(MapMessage m, TextMessage replyMessage, User user) throws JMSException, BusinessException {
		Task task = new Task();
		Date plannedDate = new Date(m.getInt("diaPlaneado"),m.getInt("mesPlaneado")
				, m.getInt("añoPlaneado"));
		task.setTitle(m.getString("titulo")).setComments(m.getString("comentario"))
		.setPlanned(plannedDate).setCategoryId(m.getLong("categoryID")).setUserId(user.getId());
		
		
		taskServ.createTask(task);
		
		replyMessage= session.createTextMessage("La tarea ha sido creada correctamente.");
		
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
	
	
	
	

}
