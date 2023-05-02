package cn.edu.hbpu.news2022.serviceImpl;

import cn.edu.hbpu.news2022.entity.User;
import cn.edu.hbpu.news2022.mapper.UserMapper;
import cn.edu.hbpu.news2022.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	UserMapper userMapper;
	@Override
	public void disable(User u) {
		userMapper.disable(u);
		
	}

}
