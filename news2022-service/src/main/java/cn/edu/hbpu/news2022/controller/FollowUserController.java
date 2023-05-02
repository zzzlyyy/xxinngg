package cn.edu.hbpu.news2022.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.hbpu.news2022.entity.User;
import cn.edu.hbpu.news2022.service.FollowUserService;

@RestController
@RequestMapping("/followUser")
public class FollowUserController {
	@Autowired
	FollowUserService followUserService;
	
	@GetMapping("/followUser")
	boolean followUser(Long userId,Long followUserId) {
		return followUserService.followUser(userId, followUserId);
	}
	@GetMapping("/disFollowUser")
	boolean disFollowUser(Long userId,Long followUserId) {
		return followUserService.disFollowUser(userId, followUserId);
	}
	@GetMapping("/isFollowUser")
	boolean isFollowUser(Long userId,Long followUserId) {
		return followUserService.isFollowUser(userId, followUserId);
	}
	@GetMapping("/getFollowUsersByUid")
	List<User> getFollowUsersByUid(Long userId) {
		return followUserService.getFollowUsersByUid(userId);
	}
	@GetMapping("/getUsersByFollowUid")
	List<User> getUsersByFollowUid(Long followUserId) {
		return followUserService.getUsersByFollowUid(followUserId);
	}
}
