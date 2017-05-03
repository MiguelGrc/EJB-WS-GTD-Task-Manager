package menu.options;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.client.RestServiceFactory;

public class MarcarTareaComoFinalizadaAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("ID de la tarea a completar");
		
		try{
			RestServiceFactory.getClient().markTaskAsFinished(id);
		} catch (NotAuthorizedException | InternalServerErrorException e){
			System.err.println("[ERROR] " + e.getMessage().split("\n")[0]);
			return;
		}
		
		System.out.println("La tarea se ha marcado como finalizada satisfactoriamente");
		
		
	}

}
