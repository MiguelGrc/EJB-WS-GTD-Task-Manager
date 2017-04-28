package menu.options;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.RestServiceFactory;

public class MarcarTareaComoFinalizadaAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("ID de la tarea a completar");
		
		RestServiceFactory.getClient().markTaskAsFinished(id);
		
		System.out.println("La tarea se ha marcado como finalizada satisfactoriamente");
		
		
	}

}
