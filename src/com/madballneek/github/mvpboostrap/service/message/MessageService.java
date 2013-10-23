package com.madballneek.github.mvpboostrap.service.message;

import com.madballneek.github.mvpboostrap.model.request.MessageRequestData;
import com.madballneek.github.mvpboostrap.model.response.MessageResponseData;

public interface MessageService {
	public MessageResponseData sayHello(MessageRequestData requestDto);

	public MessageResponseData sayGoodbye(MessageRequestData requestDto);
}
