package com.madballneek.github.mvpboostrap.service;

import com.madballneek.github.mvpboostrap.service.task.ServiceTask;

public interface TaskManager {
	public void queueNewTask(ServiceTask newTask);

	public void executeNextTask();
}
