package menu.options;

import javax.naming.Context;
import javax.naming.InitialContext;

import util.JndiData;

import com.sdi.business.services.AdminService;

import alb.util.console.Console;
import alb.util.menu.Action;

public class EliminarUsuariosAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("ID del usuario a eliminar");
		
		Context ctx = new InitialContext();
		AdminService service = (AdminService) ctx.lookup(JndiData.getInstance().getJndiKey());
		service.deepDeleteUser(id);
		
		System.out.println("El usuario ha sido borrado satisfactoriamente");
		
	}

	
}
