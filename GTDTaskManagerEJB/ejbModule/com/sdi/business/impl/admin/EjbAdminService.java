package com.sdi.business.impl.admin;

import java.util.List;

import javax.ejb.Stateless;

import com.sdi.business.exception.BusinessException;
import com.sdi.business.impl.admin.command.DeepDeleteUserCommand;
import com.sdi.business.impl.admin.command.DisableUserCommand;
import com.sdi.business.impl.admin.command.EnableUserCommand;
import com.sdi.business.impl.command.Command;
import com.sdi.business.services.AdminService;
import com.sdi.dto.User;
import com.sdi.persistence.Persistence;

/**
 * Session Bean implementation class EjbAdminService
 */
@Stateless
public class EjbAdminService implements AdminService,RemoteAdminService, LocalAdminService {

	@Override
	public void deepDeleteUser(Long id) throws BusinessException {
		new DeepDeleteUserCommand( id ).execute();
	}

	@Override
	public void disableUser(Long id) throws BusinessException {
		new DisableUserCommand( id ).execute();
	}

	@Override
	public void enableUser(Long id) throws BusinessException {
		new EnableUserCommand( id ).execute();
	}

	@Override
	public List<User> findAllUsers() throws BusinessException {
		 return new Command<List<User>>() {
			@Override public List<User> execute() throws BusinessException {
				return Persistence.getUserDao().findAll();
			}
		}.execute();
	}

	
	public User findUserById(final Long id) throws BusinessException {
		return new Command<User>() {
			@Override public User execute() throws BusinessException {
				return Persistence.getUserDao().findById(id);
			}
		}.execute();
	}

}
