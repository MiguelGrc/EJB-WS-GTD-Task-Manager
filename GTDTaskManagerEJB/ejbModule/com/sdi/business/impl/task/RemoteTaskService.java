package com.sdi.business.impl.task;

import javax.ejb.Remote;

import com.sdi.business.services.TaskService;

@Remote
public interface RemoteTaskService extends TaskService {

}
