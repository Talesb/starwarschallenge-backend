package br.com.starwarschallenge.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String details;
	private String trace;

	public ExceptionResponse() {
		super();
	}

	public ExceptionResponse(Date timestamp, String message, String details, String trace) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.trace = trace;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [timestamp=" + timestamp + ", message=" + message + ", details=" + details
				+ ", trace=" + trace + "]";
	}
}