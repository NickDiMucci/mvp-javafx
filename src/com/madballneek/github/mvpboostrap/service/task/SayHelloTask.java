package com.madballneek.github.mvpboostrap.service.task;

import com.google.common.eventbus.EventBus;
import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;
import com.madballneek.github.mvpboostrap.model.response.ResponseData;
import com.madballneek.github.mvpboostrap.service.message.MessageService;

/**
 * A task for getting a hello message from the <code>MessageService</code>.
 * This nicely encapsulates creating a background worker thread and allows you to
 * define pre and post service actions.
 */
public class SayHelloTask extends ServiceTask {
	private EventBus eventBus;
	private MessageService messageService;
	private MessageRequestData requestData;

	public SayHelloTask(EventBus eventBus, MessageService messageService, MessageRequestData requestData) {
		super();
		this.eventBus = eventBus;
		this.messageService = messageService;
		this.requestData = requestData;
	}

	@Override
	public void processPreService() {
		super.processPreService();
		// Perhaps there's some additional initialization you want to do before calling the service,
		// hook into a ProgressBar to update the status, or post an event that we're about to make a call to a service?
		// You could handle that here.
	}

	@Override
	protected ResponseData call() throws Exception {
		return messageService.sayHello(requestData);
	}

	@Override
	protected void processPostServiceResponse(ResponseData response) {
		MessageResponseData responseData = (MessageResponseData) response;
		eventBus.post(responseData);
	}
}
