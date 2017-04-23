package com.sdi.business.impl.user;


import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.user.command.FindLoggableUSerCommand;
import com.sdi.business.impl.user.command.RegisterUserCommand;
import com.sdi.business.impl.user.command.UpdateUserDetailsCommand;
import com.sdi.business.services.UserService;
import com.sdi.dto.User;

/**
 * Session Bean implementation class EjbUserService
 */
@Stateless
@WebService(name="UserService")
public class EjbUserService implements UserService,RemoteUserService, LocalUserService {

	@Override
	public Long registerUser(User user) throws BusinessException {
				return new RegisterUserCommand( user ).execute();
	}

	@Override
	public void updateUserDetails(User user) throws BusinessException {
				new UpdateUserDetailsCommand( user ).execute();
	}

	@Override
	public User findLoggableUser(final String login, final String password) 
			throws BusinessException {
		
		return new FindLoggableUSerCommand<User>(login, password).execute();
	}

	

}
