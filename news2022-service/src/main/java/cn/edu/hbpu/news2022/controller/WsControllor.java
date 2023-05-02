package cn.edu.hbpu.news2022.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.hbpu.news2022.entity.Chatmsg;
import cn.edu.hbpu.news2022.service.ChatmsgService;

@RestController
@RequestMapping("/ws")
public class WsControllor {
	@Autowired
	private SimpMessagingTemplate template;
	@Autowired
	ChatmsgService chatmsgService;
	@MessageMapping("/chat")
	public void handleMsg(Chatmsg msg) {
		msg.setDate(LocalDateTime.now());
		chatmsgService.save(msg);
		template.convertAndSendToUser(msg.getToUser(),"/queue/chat",msg);
	}
}
