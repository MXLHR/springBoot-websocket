package com.xianlei.springBoot.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;//通过SimpMessagingTemplate向浏览器发送消息
	
	@MessageMapping("/chat")
	public void handleChat(Principal principl , String msg){//在SpringMvc中可以直接在参数中获得principl,包含当前用户信息
		String sendMessage = "New message :" + msg + ". From [" + principl.getName() + "] send message ";
		//此处硬编码指定的发送人
		//通过convertAndSendToUser 发送消息给用户，参数： 接受人，浏览器订阅的地址，消息
		if(principl.getName().equals("sam")){
			messagingTemplate.convertAndSendToUser("jack", "/queue/notifications",sendMessage);
		} else {
			messagingTemplate.convertAndSendToUser("sam", "/queue/notifications",sendMessage);
		}
	}
}
