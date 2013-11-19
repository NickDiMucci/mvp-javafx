package com.madballneek.github.mvpboostrap.service.message;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;

import java.util.concurrent.ExecutionException;

/**
 * A, somewhat rude, <code>MessageService</code> implementation.
 */
public class RudeMessageServiceImpl implements MessageService {
	private LoadingCache<String, String> rudeHelloMessages;

	public RudeMessageServiceImpl() {
		rudeHelloMessages = CacheBuilder.newBuilder()
				.maximumSize(10)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						// We are just demonstrating a long running, expensive service process.
						// Since we are running our service tasks on background threads, this won't
						// hang up the application at all. You may execute other tasks to this service while this one runs!
						return createRudeHelloMessage(key);
					}
				});
	}

	@Override
	public MessageResponseData sayHello(final MessageRequestData requestData) {
		String message = null;
		try {
			// Performing a get on a CacheBuilder will also automatically put the value if it's not already present.
			message = rudeHelloMessages.get(requestData.name);
		} catch (ExecutionException e) {
			// In a real app, you'll want to handle exceptions a little bit better than this.
			e.printStackTrace();
		}

		return new MessageResponseData(message);
	}

	@Override
	public MessageResponseData sayGoodbye(final MessageRequestData requestData) {
		// Humor me once more, saying goodbye isn't expensive (though, it's always the hardest),
		// so we don't need a CacheBuilder.
		return new MessageResponseData("Don't let the door hit you, " + requestData.name);
	}

	private String createRudeHelloMessage(final String name) throws InterruptedException {
		// Again, just simulating a long running process.
		Thread.sleep(5000);
		return "What the hell do you want, " + name + "?";
	}
}
