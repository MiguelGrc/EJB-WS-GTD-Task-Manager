package menu.options;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import menu.ClientInfo;
import alb.util.menu.Action;

public class listarTareasHoyAtrasadasAction extends MessageTemplateAction implements Action {


	@Override
	public MapMessage createMessage() throws JMSException {
		MapMessage msg = session.createMapMessage();
		
		msg.setString("command", "listTasks");
		msg.setString("nameuser", ClientInfo.name);
		msg.setString("password", ClientInfo.password);
		
		return msg;
	}


			
	

}
