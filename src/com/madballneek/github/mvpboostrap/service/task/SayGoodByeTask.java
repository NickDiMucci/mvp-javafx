package com.madballneek.github.mvpboostrap.service.task;

import com.google.common.eventbus.EventBus;
import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;
import com.madballneek.github.mvpboostrap.model.response.ResponseData;
import com.madballneek.github.mvpboostrap.service.TaskManager;
import com.madballneek.github.mvpboostrap.service.message.MessageService;

/**
 * A task for getting a good bye message from the <code>MessageService</code>.
 * This nicely encapsulates creating a background worker thread and allows you to
 * define pre and post service actions.
 */
public class SayGoodByeTask extends ServiceTask {
	private EventBus eventBus;
	private MessageService messageService;
	private MessageRequestData requestData;

	public SayGoodByeTask(TaskManager taskManager, EventBus eventBus, MessageService messageService, MessageRequestData requestData) {
		super(taskManager);
		this.eventBus = eventBus;
		this.messageService = messageService;
		this.requestData = requestData;
	}

	@Override
	protected ResponseData call() throws Exception {
		return messageService.sayGoodbye(requestData);
	}

	@Override
	public void start() {
		new Thread(this).start();
	}

	@Override
	protected void processPostServiceResponse(ResponseData response) {
		MessageResponseData responseData = (MessageResponseData) response;
		eventBus.post(responseData);
	}
}
