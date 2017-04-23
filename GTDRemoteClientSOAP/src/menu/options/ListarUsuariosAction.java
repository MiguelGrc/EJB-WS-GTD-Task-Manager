package menu.options;

import java.util.List;

import alb.util.menu.Action;

import com.sdi.ws.admin.AdminService;
import com.sdi.ws.admin.EjbAdminServiceService;
import com.sdi.ws.admin.UserInfo;

public class ListarUsuariosAction implements Action{

	@Override
	public void execute() throws Exception {
		
		AdminService service = new EjbAdminServiceService().getAdminServicePort();
		
		List<UserInfo> usersInfo = service.findAllUsersInfo();		
		
		printHeader();
		printUsers(usersInfo);
		
		System.out.print("------------------------------------------------------");
		
	}

	private void printHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID \t");
		sb.append("Login   \t");
		sb.append("Email            \t");
		//TODO:La contraseña no deberia mostarse al Administrador
		sb.append("IsAdmin  \t");
		sb.append("Status  \t");
		sb.append("numTarCompl \t");
		sb.append("numTarComplRetrasadas \t");
		sb.append("numTarPlanificadas \t");
		sb.append("numTarNoPlanificadas \t");
		sb.append("\n");
		
		System.out.print(sb.toString());
	}

	private void printUsers(List<UserInfo> users) {
		
		for(UserInfo userInf : users){
			StringBuilder sb = new StringBuilder();
			
//			sb.append(userInf.getUser().getId() +" \t");
//			sb.append(userInf.getUser().getLogin()+"   \t");
//			sb.append(userInf.getUser().getEmail()+"  \t");
//			sb.append((userInf.getUser().getIsAdmin()? "yes" : "no") + "    \t \t");
//			sb.append((userInf.getUser().getStatus() == UserStatus.ENABLED ? "ENABLED" : "DISABLED") + " \t");
//			sb.append(userInf.getNumTareasCompletadas() + "   \t \t");
//			sb.append(userInf.getNumTareasCompletadasRetrasadas() + "      \t \t \t");
//			sb.append(userInf.getNumTareasPlanificadas() + "       \t \t");
//			sb.append(userInf.getNumTareasNoPlanificadas() + "      \t \t");
//			sb.append("\n");
			
			System.out.print(sb.toString());
		}
		
		
		
		
	}

}
