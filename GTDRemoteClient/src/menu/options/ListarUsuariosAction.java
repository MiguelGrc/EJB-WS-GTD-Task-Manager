package menu.options;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import util.JndiData;

import com.sdi.business.services.AdminService;
import com.sdi.dto.UserInfo;
import com.sdi.dto.types.UserStatus;

import alb.util.menu.Action;

public class ListarUsuariosAction implements Action{

	@Override
	public void execute() throws Exception {
		
		Context ctx = new InitialContext();
		AdminService service = (AdminService) ctx.lookup(JndiData.getInstance().getJndiKey());
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
			
			sb.append(userInf.getUser().getId() +" \t");
			sb.append(userInf.getUser().getLogin()+"   \t");
			sb.append(userInf.getUser().getEmail()+"  \t");
			sb.append((userInf.getUser().getIsAdmin()? "yes" : "no") + "    \t \t");
			sb.append((userInf.getUser().getStatus()==UserStatus.ENABLED? "ENABLED" : "DISABLED") + " \t");
			sb.append(userInf.getNumTareasCompletadas() + "   \t \t");
			sb.append(userInf.getNumTareasCompletadasRetrasadas() + "      \t \t \t");
			sb.append(userInf.getNumTareasPlanificadas() + "       \t \t");
			sb.append(userInf.getNumTareasNoPlanificadas() + "      \t \t");
			sb.append("\n");
			
			System.out.print(sb.toString());
		}
		
		
		
		
	}

}
