package com.xianlei.springBoot.model;

/*
 *  服务端向浏览器发送的此类的消息
 */
public class UserResponse {

	private String responseMessage;

	public UserResponse(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

}
