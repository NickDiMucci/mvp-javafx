package com.madballneek.github.mvpboostrap.service.message;

import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;

/**
 * MessageService implementation that simply replies back to the client with a hello or goodbye message.
 */
public class SimpleMessageServiceImpl implements MessageService {

	public SimpleMessageServiceImpl() {
	}

	@Override
	public MessageResponseData sayHello(MessageRequestData requestData) {
		// We are just demonstrating a long running service process.
		// Since we are running our service tasks on background threads, this won't
		// hang up the application at all. You may execute other tasks to this service while this one runs!
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new MessageResponseData("Hey there, " + requestData.name);
	}

	@Override
	public MessageResponseData sayGoodbye(MessageRequestData requestData) {
		return new MessageResponseData("See yea, " + requestData.name);
	}
}
