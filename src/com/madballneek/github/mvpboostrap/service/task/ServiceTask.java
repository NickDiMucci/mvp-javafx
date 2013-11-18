package com.madballneek.github.mvpboostrap.service.task;

import com.madballneek.github.mvpboostrap.model.response.ResponseData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;

/**
 * A wrapper for a {@link Task}, providing methods to perform any pre and/or post service call operations,
 * as well as allowing you to define <code>onSuccess</code> and <code>onFailure</code> methods.
 */
public abstract class ServiceTask extends Task<ResponseData> {
	public ServiceTask() {
	}

	/**
	 * This method is called once the service has responded back to the main thread.
	 *
	 * @param response the {@link ResponseData} object with information about
	 *                 the status of the service call, any error messages, and the data
	 *                 payload.
	 */
	protected abstract void processPostServiceResponse(ResponseData response);

	/**
	 * Performs any processing you want to occur before calling the service. Will also add a state change listener to
	 * the {@link Task} object.
	 * If you override this method, be sure to call <code>super</code> to attach the state change listener.
	 */
	public void processPreService() {
		// Add a ChangeListener to listen for change in state, and check if the Task was successful.
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

	protected void onFailure(Throwable exception) {
		// TODO: Handle task failure gracefully.
		exception.printStackTrace();
	}

	protected void onSuccess(ResponseData response) {
		if (response.getResponseStatus() == ResponseData.ResponseStatus.SUCCESS ||
				response.getResponseStatus() == ResponseData.ResponseStatus.PARTIAL) {
			processPostServiceResponse(response);
		}
	}
}
