package com.madballneek.github.mvpboostrap.service;

import com.madballneek.github.mvpboostrap.model.response.ResponseData;
import com.madballneek.github.mvpboostrap.service.task.ServiceTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manager class that wraps a {@link ExecutorService}.
 * When a new task is submitted, it will block and wait for the results before returning. As a result,
 * this implementation use a single thread executor.
 */
public class BlockingTaskManager implements TaskManager<ResponseData> {
	private ExecutorService service;

	public BlockingTaskManager() {
		service = Executors.newSingleThreadExecutor();
	}

	/**
	 * Submit a new {@link ServiceTask} to the {@link ExecutorService}. The {@link ExecutorService}
	 * will execute the thread immediately and will block to wait for the results to return.
	 *
	 * @param newTask the {@link ServiceTask} to submit.
	 * @return the {@link ResponseData} containing the results of the service call.
	 */
	@Override
	public ResponseData submitNewTask(ServiceTask newTask) {
		ResponseData responseData = null;
		if (newTask != null) {
			newTask.processPreService();
			service.submit(newTask);
			try {
				responseData = newTask.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO: Do something a bit more gracefully than this!
				e.printStackTrace();
			}
		}
		return responseData;
	}
}
