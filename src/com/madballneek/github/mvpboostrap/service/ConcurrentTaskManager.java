package com.madballneek.github.mvpboostrap.service;

import com.madballneek.github.mvpboostrap.service.task.ServiceTask;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manager class that manages a queue of service tasks.
 * This will start tasks as they are received. It does not wait for any currently processing tasks to finish.
 * When you have a new <code>ServiceTask</code> to call, use this class to queue it.
 */
public class ConcurrentTaskManager implements TaskManager {
	private Queue<ServiceTask> taskQueue = new LinkedList<>();

	/**
	 * Add a new task to the queue. If we are not currently processing, attempt
	 * to execute it right away.
	 *
	 * @param newTask The task to add to the queue.
	 */
	@Override
	public void queueNewTask(ServiceTask newTask) {
		taskQueue.add(newTask);
		ServiceTask currentTask = taskQueue.poll();
		if ((currentTask != null) && currentTask.processPreServiceResponse()) {
			currentTask.prepareToStart();
			currentTask.start();
		} else {
			executeNextTask();
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
		}
	}
}