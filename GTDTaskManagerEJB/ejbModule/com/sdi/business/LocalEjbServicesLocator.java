package com.sdi.business;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.services.AdminService;
import com.sdi.business.services.Services;
import com.sdi.business.services.TaskService;
import com.sdi.business.services.UserService;

public class LocalEjbServicesLocator implements Services {

	private static final String BASE_JNDI_KEY = "java:global/GTDTaskManager/GTDTaskManagerEJB/";
	private static final String ADMIN_SERVICE_JNDI_KEY = BASE_JNDI_KEY
			+ "EjbAdminService!com.sdi.business.impl.admin.LocalAdminService";
	private static final String TASK_SERVICE_JNDI_KEY = BASE_JNDI_KEY
			+ "EjbTaskService!com.sdi.business.impl.task.LocalTaskService";
	private static final String USER_SERVICE_JNDI_KEY = BASE_JNDI_KEY
			+ "EjbUserService!com.sdi.business.impl.user.LocalUserService";

	@Override
	public AdminService getAdminService() {
		try {
			// Instanciamos al contexto
			Context ctx = new InitialContext();
			return (AdminService) ctx.lookup(ADMIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UserService getUserService() {
		try {
			// Instanciamos al contexto
			Context ctx = new InitialContext();
			return (UserService) ctx.lookup(USER_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public TaskService getTaskService() {
		try {
			// Instanciamos al contexto
			Context ctx = new InitialContext();
			return (TaskService) ctx.lookup(TASK_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}
