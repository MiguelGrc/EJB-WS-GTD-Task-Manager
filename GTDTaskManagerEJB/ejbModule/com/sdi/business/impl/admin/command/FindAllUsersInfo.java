package com.sdi.business.impl.admin.command;

import java.util.ArrayList;
import java.util.List;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.command.Command;
import com.sdi.dto.User;
import com.sdi.dto.UserInfo;
import com.sdi.persistence.Persistence;

public class FindAllUsersInfo implements Command<List<UserInfo>> {

	@Override
	public List<UserInfo> execute() throws BusinessException {
		List<UserInfo> userInf = new ArrayList<UserInfo>();
		List<User> users =  Persistence.getUserDao().findAll();
		
		for (User user: users){
			int numeroTareasCompletadas = Persistence.getTaskDao()
									.countCompleatedTasksByUserId(user.getId());
			int numeroTareasComplRetrasadas = Persistence.getTaskDao()
									.countCompleatedAndDelayedTasksByUserId(user.getId());
			int numeroTareasPlanificadas = Persistence.getTaskDao()
									.countPlannedTasksByUserId(user.getId());
			int numeroTareasNoPlanificadas = Persistence.getTaskDao()
									.countNotPlannedTasksByUserId(user.getId());
			
			userInf.add(new UserInfo(user,numeroTareasCompletadas, numeroTareasComplRetrasadas,
										numeroTareasPlanificadas, numeroTareasNoPlanificadas));
		}
		
		return userInf;
	}

}
