package com.madballneek.github.mvpboostrap.service;

import com.madballneek.github.mvpboostrap.service.task.ServiceTask;

public interface TaskManager<V> {
	public V submitNewTask(ServiceTask newTask);
}
