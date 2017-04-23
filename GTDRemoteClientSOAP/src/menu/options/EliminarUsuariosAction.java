package menu.options;

import alb.util.console.Console;
import alb.util.menu.Action;

import com.sdi.ws.admin.AdminService;
import com.sdi.ws.admin.EjbAdminServiceService;

public class EliminarUsuariosAction implements Action {

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("ID del usuario a eliminar");
		
		AdminService service = new EjbAdminServiceService().getAdminServicePort();
		service.deepDeleteUser(id);
		
		System.out.println("El usuario ha sido borrado satisfactoriamente");
		
	}

	
}
