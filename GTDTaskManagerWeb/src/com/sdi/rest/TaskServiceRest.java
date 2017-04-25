package com.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.business.exception.BusinessException;
import com.sdi.dto.Category;
import com.sdi.dto.Task;

@Path("/TaskServiceRs")
public interface TaskServiceRest {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Category> findCategoriesByUserId() throws BusinessException;
	//TODO Param (id) removed for the moment
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Task> findTasksByCategoryId(Long catId) throws BusinessException;
	//TODO Suponiendo que se pidan este tipo de tareas ^
	
	@POST 
	@Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void markTaskAsFinished(Long id) throws BusinessException;
	
	@PUT 
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Long createTask(Task task) throws BusinessException;
	
}
