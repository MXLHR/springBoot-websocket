package com.xianlei.springBoot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.xianlei.springBoot.model.UserMessage;
import com.xianlei.springBoot.model.UserResponse;

@Controller
public class UserController {

	@MessageMapping("/welcome")//1. 浏览器想服务端发送请求，类似于@RequestMapping
	@SendTo("/topic/getResponse")// 2. 服务端有消息时，对订阅了@SendTo中的路径的浏览器发送消息
	public UserResponse say(UserMessage message){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UserResponse response = new UserResponse("hello websocket -->" + message.getName());
		return response;
	}
}
