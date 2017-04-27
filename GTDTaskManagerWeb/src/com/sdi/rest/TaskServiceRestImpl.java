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
		return service.findCategoriesByUserId(272L);	//TODO placeholder
	}

	@Override
	public List<Task> findTasksByCategoryId(Long catId)
			throws BusinessException {
		return service.findTasksByCategoryId(catId);
	}

	@Override
	public void markTaskAsFinished(Long id) throws BusinessException {
		service.markTaskAsFinished(id);
	}

	@Override
	public Long createTask(Task task) throws BusinessException {
		return service.createTask(task);
	}

	
	
}
