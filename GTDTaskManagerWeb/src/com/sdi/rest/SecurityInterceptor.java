package com.sdi.rest;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

import alb.util.log.Log;

import com.sdi.business.BusinessFactory;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.services.UserService;
import com.sdi.dto.User;
import com.sdi.dto.types.UserStatus;

@Provider
public class SecurityInterceptor implements javax.ws.rs.container.ContainerRequestFilter {

	private static final ServerResponse ACCESS_DENIED = new ServerResponse(
			"Acceso no permitido a este recurso", 401, new Headers<Object>());
	
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Log.debug("[REST] Petición recibida");
		
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		List<String> authorization = headers.get("Authorization");
		
		if(authorization == null || authorization.isEmpty()){
			requestContext.abortWith(ACCESS_DENIED);
			return;
		}
		
		String encodedLoginAndPass = authorization.get(0).replaceFirst("Basic ", "");
		
		String loginAndPass = null;
		try {
			loginAndPass = new String(Base64.decode(encodedLoginAndPass));
		} catch (IOException e) {
			requestContext.abortWith(ACCESS_DENIED);
			return;
		}
		
		StringTokenizer tokenizer = new StringTokenizer(loginAndPass, ":");
		String login = tokenizer.nextToken();
		String password = tokenizer.nextToken();
		
		if(!isUserAllowed(login, password)){
			requestContext.abortWith(ACCESS_DENIED);
			return;
		}
		
		
	}
	
	private boolean isUserAllowed(String login, String password){
		UserService service = BusinessFactory.businessService.getUserService();
		User user = null;
		try {
			user = service.findLoggableUser(login, password);			
		} catch (BusinessException e) {
			Log.error(e);
		}
		
		if(user != null && user.getStatus().equals(UserStatus.ENABLED)){
			ClientInfo.setInformation(user);
			Log.debug("[REST] Usuario identificado correctamente");
			return true;
		}
		return false;
	}
	
}
