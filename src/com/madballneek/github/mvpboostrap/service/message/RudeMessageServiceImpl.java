package com.madballneek.github.mvpboostrap.service.message;

import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;

/**
 * A, somewhat rude, <code>MessageService</code> implementation.
 */
public class RudeMessageServiceImpl implements MessageService {

	public RudeMessageServiceImpl() {
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
		return new MessageResponseData("What the hell do you want, " + requestData.name + "?");
	}

	@Override
	public MessageResponseData sayGoodbye(MessageRequestData requestData) {
		return new MessageResponseData("Don't let the door hit you, " + requestData.name);
	}
}
