package com.madballneek.github.mvpboostrap.service;

import com.madballneek.github.mvpboostrap.model.response.ResponseData;
import com.madballneek.github.mvpboostrap.service.task.ServiceTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manager class that wraps a {@link ExecutorService}.
 * When a new task is submitted, it will block and wait for the results before returning.
 */
public class BlockingTaskManager implements TaskManager<ResponseData> {
	private ExecutorService service;

	public BlockingTaskManager() {
		service = Executors.newSingleThreadExecutor();
	}

	/**
	 * Submit a new {@link ServiceTask} to the {@link ExecutorService}. The {@link ExecutorService}
	 * will execute the thread immediately if there are available threads in the pool to handle the request,
	 * and will call {@link java.util.concurrent.Future}'s <code>get()</code> method to block and wait for the
	 * results to return.
	 *
	 * @param newTask The {@link ServiceTask} to submit to the {@link ExecutorService}.
	 */
	@Override
	public ResponseData submitNewTask(ServiceTask newTask) {
		ResponseData responseData = null;
		if (newTask != null) {
			newTask.processPreService();
			try {
				responseData = (ResponseData) service.submit(newTask).get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO: Do something a bit more gracefully than this!
				e.printStackTrace();
			}
		}
		return responseData;
	}
}
