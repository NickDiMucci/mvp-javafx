package com.madballneek.github.mvpboostrap.model.response;

/**
 * Model object that wraps the response data from the <code>MessageService</code>.
 */
public class MessageResponseData extends ResponseData {
	public String message;

	public MessageResponseData(String message) {
		super(ResponseStatus.SUCCESS);
		this.message = message;
	}
}
