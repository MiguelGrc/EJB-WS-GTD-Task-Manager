package com.sdi.rest;

import java.util.List;

import com.sdi.business.BusinessFactory;
import com.sdi.business.exception.BusinessException;
import com.sdi.business.services.TaskService;
import com.sdi.dto.Category;
import com.sdi.dto.Task;

public class TaskServiceRestImpl implements TaskServiceRest {

	TaskService service = BusinessFactory.businessService.getTaskService();
	
	@Override
	public List<Category> findCategoriesByUserId()
			throws BusinessException {
		return service.findCategoriesByUserId(ClientInfo.id);	//TODO placeholder
	}

	@Override
	public List<Task> findDelayedTasksByCategoryId(Long catId)
			throws BusinessException {
		return service.findDelayedTasksByCategoryId(catId);
	}

	@Override
	public void markTaskAsFinished(Long id) throws BusinessException {
		service.markTaskAsFinished(id);
	}

	@Override
	public void createTask(Task task) throws BusinessException {
		task.setUserId(ClientInfo.id);
		service.createTask(task);
	}

	
	
}
