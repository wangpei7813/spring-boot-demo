package com.wang.netty.marshalling;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class Response implements Serializable {

	private static final long serialVersionUID = -4639347903121316828L;
	
	private String id;
	
	private String name;
	
	private String responseMessage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	
	
}
