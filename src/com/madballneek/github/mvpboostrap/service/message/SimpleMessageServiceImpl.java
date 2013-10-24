package com.madballneek.github.mvpboostrap.service.message;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;

import java.util.concurrent.ExecutionException;

/**
 * MessageService implementation that simply replies back to the client with a hello or goodbye message.
 */
public class SimpleMessageServiceImpl implements MessageService {
	private LoadingCache<String, String> helloMessages;

	public SimpleMessageServiceImpl() {
		// Let's pretend this service is expensive to execute (although in this example, it's not).
		// Taking advantage of CacheBuilder will allow us to cache expensive results and return them immediately
		// if they are ever requested again.
		helloMessages = CacheBuilder.newBuilder()
				.maximumSize(10)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						// We are just demonstrating a long running, expensive service process.
						// Since we are running our service tasks on background threads, this won't
						// hang up the application at all. You may execute other tasks to this service while this one runs!
						System.out.println("Loading cache value...");
						return createHelloMessage(key);
					}
				});
	}

	@Override
	public MessageResponseData sayHello(final MessageRequestData requestData) {
		MessageResponseData responseData;

		try {
			// Performing a get on a CacheBuilder will also automatically put the value if it's not already present.
			responseData = new MessageResponseData(helloMessages.get(requestData.name));
		} catch (ExecutionException e) {
			e.printStackTrace();
			// In a real app, you'll want to handle exceptions a little bit better than this.
			responseData = new MessageResponseData("");
		}

		return responseData;
	}

	@Override
	public MessageResponseData sayGoodbye(final MessageRequestData requestData) {
		// Humor me once more, saying goodbye isn't expensive (though, it's always the hardest),
		// so we don't need a CacheBuilder.
		return new MessageResponseData("See yea, " + requestData.name);
	}

	private String createHelloMessage(final String name) throws InterruptedException {
		// Again, just simulating a long running process.
		Thread.sleep(5000);
		return "Hey there, " + name;
	}
}
