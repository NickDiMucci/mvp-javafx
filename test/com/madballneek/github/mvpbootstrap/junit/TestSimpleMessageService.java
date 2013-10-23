package com.madballneek.github.mvpbootstrap.junit;

import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;
import com.madballneek.github.mvpboostrap.service.message.SimpleMessageServiceImpl;
import junit.framework.TestCase;

public class TestSimpleMessageService extends TestCase {
	SimpleMessageServiceImpl messageService;

	public void setUp() throws Exception {
		messageService = new SimpleMessageServiceImpl();
	}

	public void testSimpleMessage() throws Exception {
		// These are all such frivolous tests, but demonstarting JUnit.
		String name = "Jimmy";
		String expectedMessage = "Hey there, " + name;
		MessageResponseData actualMessage = messageService.sayHello(new MessageRequestData(name));
		assertEquals(expectedMessage, actualMessage.message);

		expectedMessage = "See yea, " + name;
		actualMessage = messageService.sayGoodbye(new MessageRequestData(name));
		assertEquals(expectedMessage, actualMessage.message);


	}

	public void tearDown() throws Exception {
		messageService = null;
	}
}
