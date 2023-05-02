package cn.edu.hbpu.news2022.service;

import java.util.List;

import cn.edu.hbpu.news2022.entity.User;

public interface FollowUserService {
	boolean followUser(Long userId,Long followUserId);
	boolean disFollowUser(Long userId,Long followUserId);
	boolean isFollowUser(Long userId,Long followUserId);
	List<User> getFollowUsersByUid(Long userId);
	List<User> getUsersByFollowUid(Long followUserId);
}
