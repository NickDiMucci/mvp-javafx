package com.madballneek.github.mvpboostrap.service.task;

import com.madballneek.github.mvpboostrap.model.response.ResponseData;
import com.madballneek.github.mvpboostrap.service.TaskManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

public abstract class ServiceTask extends Task<ResponseData> {
	private TaskManager taskManager;

	public ServiceTask(TaskManager taskManager) {
		this.taskManager = taskManager;
	}

	/**
	 * Implementors of this method should instantiate a new thread and pass it the <code>serviceTask</code> to run.
	 */
	public abstract void start();

	/**
	 * This method is called once the service has responded back to the client.
	 * Handle success and failure here.
	 *
	 * @param response the response data transfer object with information about
	 *                 the status of the service call, any error messages, and the data
	 *                 payload, if any coming back to the client.
	 */
	protected abstract void processPostServiceResponse(ResponseData response);

	/**
	 * Performs any pre-sending tasks. Will also add a state change listener to the <code>Task</code> object.
	 * If you override this method, be sure to call <code>super</code>.
	 * <p>
	 * <b>NOTE: </b>Specific implementations should be sure to call this method
	 * before performing additional custom tasks.
	 * </p>
	 */
	public void prepareToStart() {
		// Add a ChangeListener to listen for change in state, and check if the Task was successful.
		// http://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
		this.stateProperty().addListener(new ChangeListener<Worker.State>() {

			@Override
			public void changed(ObservableValue<? extends Worker.State> source, Worker.State oldState, Worker.State newState) {
				if (newState.equals(Worker.State.SUCCEEDED)) {
					onSuccess(getValue());
				} else if (newState.equals(Worker.State.FAILED)) {
					onFailure(getException());
				}
			}
		});
	}

	/**
	 * Process any tasks that need to be handled client side, before going
	 * to the service. Should only be called by TaskManager.
	 *
	 * @return boolean to indicate whether pre-server tasks have been
	 *         successfully processed, and if we can proceed to processing
	 *         server side tasks.
	 */
	public boolean processPreServiceResponse() {
		return true;
	}

	public void onFailure(Throwable exception) {
		// TODO: Handle task failure robustly.
		exception.printStackTrace();
		taskManager.executeNextTask();
	}

	public void onSuccess(ResponseData response) {
		if (response.getResponseStatus() == ResponseData.ResponseStatus.SUCCESS) {
			processPostServiceResponse(response);
		}
		taskManager.executeNextTask();
	}
}
