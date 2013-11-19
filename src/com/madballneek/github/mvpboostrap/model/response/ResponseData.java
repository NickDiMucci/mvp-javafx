package com.madballneek.github.mvpboostrap.model.response;

public abstract class ResponseData {
	public enum ResponseStatus {
		SUCCESS, FAILURE, PARTIAL, TIMEOUT
	}

	protected ResponseStatus responseStatus;

	protected ResponseData(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
}
