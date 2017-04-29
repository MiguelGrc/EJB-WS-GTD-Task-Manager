package menu.options;

import java.util.Date;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;

import com.sdi.client.RestServiceFactory;
import com.sdi.client.Task;

public class CrearTareaAction implements Action {

	@Override
	public void execute() throws Exception {
		
		String titulo = Console.readString("Título de la tarea a crear");
		String comentario = Console.readString("Comentarios de la tarea a crear");
		String plannedDate  = Console.readString("Fecha planeada (dd/MM/yyyy)");
		Date plannedDateFormatted = checkDateFormat(plannedDate);
		Long categoryID = Console.readLong("ID de la categoría a la que se quiere asociar");
		
		Task task = new Task();
		task.setTitle(titulo);
		task.setComments(comentario);
		task.setPlanned(plannedDateFormatted);
		task.setCreated(new Date());
		task.setCategoryId(categoryID);
		
		RestServiceFactory.getClient().createTask(task);
		
	}
		
	private Date checkDateFormat(String dateString){
		boolean success = false;
		String input = dateString;
		Date plannedDateFormatted = null;
		while(!success){
			try{
				plannedDateFormatted = DateUtil.fromString(input);
				success = true;
			}
			catch (NumberFormatException | IndexOutOfBoundsException e) {
				input = Console.readString("Fecha planeada (dd/MM/yyyy)");
			}
		}
		return plannedDateFormatted;
	}

}
