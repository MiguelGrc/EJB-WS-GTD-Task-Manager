package menu.options;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import menu.ClientInfo;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AnadirTareaAction extends MessageTemplateAction implements Action {

	@Override
	public MapMessage createMessage() throws JMSException {
		MapMessage msg = session.createMapMessage();

		String titulo = Console.readString("Título de la tarea a crear");
		String comentario = Console.readString("Comentarios de la tarea a crear");
		int plannedDay  = Console.readInt("Día en la que esta planeada");
		int plannedMonth = Console.readInt("Mes en la que esta planeada");
		int plannedYear = Console.readInt("Año en la que esta planeada");
		Long CategoryID = Console.readLong("ID de la categoría a la que se quiere asociar");

		
		
		msg.setString("command", "createTask");
		msg.setString("titulo", titulo);
		msg.setString("comentario", comentario);
		msg.setInt("diaPlaneado", plannedDay);
		msg.setInt("mesPlaneado", plannedMonth);
		msg.setInt("añoPlaneado", plannedYear);
		msg.setLong("categoryID", CategoryID);
		msg.setString("nameuser", ClientInfo.name);
		msg.setString("password", ClientInfo.password);

		return msg;
	}

}
