package com.madballneek.github.mvpboostrap.service;

import com.madballneek.github.mvpboostrap.service.task.ServiceTask;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manager class that manages a queue of service tasks.
 * This will run tasks asynchronously, that is,
 * it will wait for the currently running task to finish before starting  a new one.
 * When you have a new <code>ServiceTask</code> to call, use this class to queue it.
 */
public class AsyncTaskManager implements TaskManager {
	private Queue<ServiceTask> taskQueue = new LinkedList<>();
	private boolean currentlyProcessing = false;

	@Override
	public void queueNewTask(ServiceTask newTask) {
		taskQueue.add(newTask);
		if (!currentlyProcessing) {
			currentlyProcessing = true;
			ServiceTask currentTask = taskQueue.poll();
			if ((currentTask != null) && currentTask.processPreServiceResponse()) {
				currentTask.prepareToStart();
				currentTask.start();
			} else {
				executeNextTask();
			}
		}
	}

	/**
	 * Execute the next task in the queue. If there is nothing left in the queue
	 * to process, stop processing.
	 */
	@Override
	public void executeNextTask() {
		ServiceTask nextTask = taskQueue.poll();
		if ((nextTask != null) && nextTask.processPreServiceResponse()) {
			nextTask.prepareToStart();
			nextTask.start();
		} else {
			currentlyProcessing = false;
		}
	}
}
