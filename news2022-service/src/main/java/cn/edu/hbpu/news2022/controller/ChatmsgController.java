package cn.edu.hbpu.news2022.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.edu.hbpu.news2022.entity.Chatmsg;
import cn.edu.hbpu.news2022.service.ChatmsgService;

@RestController
@RequestMapping("/chatmsg")
public class ChatmsgController {
	@Autowired
	ChatmsgService chatmsgService;
	@GetMapping("/list")
	IPage<Chatmsg> list(Page<Chatmsg> page,Chatmsg msg){
		QueryWrapper<Chatmsg> wrapper = new QueryWrapper<Chatmsg>();
		wrapper.eq("fromUser", msg.getFromUser())
		.eq("toUser", msg.getToUser())
		.or()
		.eq("fromUser", msg.getToUser())
		.eq("toUser", msg.getFromUser());
		return chatmsgService.page(page,wrapper);
	}
}

