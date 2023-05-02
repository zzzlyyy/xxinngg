package cn.edu.hbpu.news2022.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mongodb.client.result.DeleteResult;
import cn.edu.hbpu.news2022.entity.FollowUser;
import cn.edu.hbpu.news2022.entity.User;
import cn.edu.hbpu.news2022.mapper.UserMapper;
import cn.edu.hbpu.news2022.service.FollowUserService;

@Service
public class FollowUserServiceImpl implements FollowUserService {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private RedisTemplate redisTemplate;
	private String Follow_User_Redis_Key="FOLLOW_USER";
	@Override
	public boolean followUser(Long userId,Long followUserId) {
		FollowUser followUser = new FollowUser();
		followUser.setId(ObjectId.get());
		followUser.setUserId(userId);
		followUser.setFollowUserId(followUserId);
		followUser.setCreated(System.currentTimeMillis());
		mongoTemplate.save(followUser);
		this.redisTemplate.opsForHash().put(getFollowUserRedisKey(userId),
				followUserId+"",1);
		return true;
	}
	private String getFollowUserRedisKey(Long userId) {
		return this.Follow_User_Redis_Key+userId;
	}
	@Override
	public boolean disFollowUser(Long userId,Long followUserId) {
		Query query = new Query(Criteria.where("userId").is(userId)
				.and("followUserId").is(followUserId));
		DeleteResult result = mongoTemplate.remove(query,FollowUser.class);
		if (result.getDeletedCount()>0) {
			this.redisTemplate.opsForHash().delete(getFollowUserRedisKey(userId),
					followUserId+"");
			return true;
		} 
		return false;
	}
	@Override
	public boolean isFollowUser(Long userId,Long followUserId) {
		return this.redisTemplate.opsForHash().hasKey(getFollowUserRedisKey(userId),
				followUserId+"");
	}
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> getFollowUsersByUid(Long userId) {
		Query query=new Query(Criteria.where("userId").is(userId));
		List<FollowUser>  list = mongoTemplate.find(query,FollowUser.class);
		List<User> users = new ArrayList<User>();
		for (FollowUser followUser : list) {
			QueryWrapper<User>  wrapper=new QueryWrapper<>();
			wrapper.select("uid,username,image").eq("uid",followUser.getFollowUserId());
			User user = userMapper.selectOne(wrapper);
			users.add(user);
		}
		return users;
	}
	@Override
	public List<User> getUsersByFollowUid(Long followUserId) {
		Query query=new Query(Criteria.where("followUserId").is(followUserId));
		List<FollowUser>  list = mongoTemplate.find(query,FollowUser.class);
		List<User> users = new ArrayList<User>();
		for (FollowUser followUser : list) {
			QueryWrapper<User>  wrapper=new QueryWrapper<>();
			wrapper.select("uid,username,image").eq("uid",followUser.getUserId());
			User user = userMapper.selectOne(wrapper);
			users.add(user);
		}
		return users;
	}
	
}
