package cn.edu.hbpu.news2022.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cn.edu.hbpu.news2022.entity.Comment;
import cn.edu.hbpu.news2022.service.CommentService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	@GetMapping("/getByNewsid")
	List<Comment> getByNewsid(Integer newsId){
		return commentService.getByNewsId(newsId);
	}
	@PostMapping("/add")
	String add(@RequestBody Comment c) {
		c.setTime(LocalDateTime.now());
		commentService.save(c);
		return "";
	}
}

