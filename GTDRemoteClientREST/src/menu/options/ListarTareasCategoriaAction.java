package menu.options;

import java.util.Collections;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.PlannedTaskComparator;
import com.sdi.client.RestServiceFactory;
import com.sdi.client.Task;

public class ListarTareasCategoriaAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Long catId = Console.readLong("ID de la categoría");
		
		List<Task> taskList = RestServiceFactory.getClient().findTasksByCategoryId(catId);
	
		//Ordenamos las tareas de fecha planeda más antigua a más reciente
		Collections.sort(taskList, new PlannedTaskComparator());
			
		printHeader();
		printTasks(taskList);
		
		System.out.print("------------------------------------------------------");
		
	}

	private void printHeader() {
		System.out.printf("%s %s %s %s %s %s\n",
				"_ID____",
				"_TITULO_____________",
				"_FECHA_CREACIÓN____________________",
				"_FECHA_PLANEADA____________________",
				"_IDCAT_",
				"_COMENTARIOS__________________"
			);
	}

	private void printTasks(List<Task> taskList) {
		
		for(Task task : taskList){
			System.out.printf("%-7d %-20s %-35s %-35s %-7d %-30s\n",
					task.getId(),
					task.getTitle(),
					task.getCreated(),
					task.getPlanned(),
					task.getCategoryId(),
					task.getComments()
				);
		}
	}

}
