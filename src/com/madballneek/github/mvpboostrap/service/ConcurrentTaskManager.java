package com.madballneek.github.mvpboostrap.service;

import com.madballneek.github.mvpboostrap.service.task.ServiceTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manager class that wraps a {@link ExecutorService}.
 * When a new task is submitted, it will be executed immediately, if there are available threads in the pool to handle
 * the request, and doesn't block for the results.
 */
public class ConcurrentTaskManager implements TaskManager<Void> {
	private ExecutorService service;

	public ConcurrentTaskManager() {
		int cpus = Runtime.getRuntime().availableProcessors();
		int maxThreads = cpus + 1;
		maxThreads = (maxThreads > 0 ? maxThreads : 1);
		service = Executors.newFixedThreadPool(maxThreads);
	}

	/**
	 * Submit a new {@link ServiceTask} to the {@link ExecutorService}. The {@link ExecutorService}
	 * will execute the thread immediately if there are available threads in the pool to handle the request.
	 *
	 * @param newTask The {@link ServiceTask} to submit to the {@link ExecutorService}.
	 */
	@Override
	public Void submitNewTask(ServiceTask newTask) {
		if (newTask != null) {
			newTask.processPreService();
			service.submit(newTask);
		}
		return null;
	}
}