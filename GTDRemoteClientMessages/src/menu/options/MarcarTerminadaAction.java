package menu.options;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import menu.ClientInfo;
import alb.util.console.Console;
import alb.util.menu.Action;

public class MarcarTerminadaAction extends MessageTemplateAction implements Action {

	@Override
	public MapMessage createMessage() throws JMSException {
		MapMessage msg = session.createMapMessage();
		
		Long id = Console.readLong("ID de la tarea a terminar");
		
		msg.setString("command", "finishTask");
		msg.setLong("taskId", id);
		msg.setString("nameuser", ClientInfo.name);
		msg.setString("password", ClientInfo.password);
	
		return msg;
	}

}
