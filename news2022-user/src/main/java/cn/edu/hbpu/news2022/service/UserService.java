package cn.edu.hbpu.news2022.service;


import com.baomidou.mybatisplus.extension.service.IService;

import cn.edu.hbpu.news2022.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
public interface UserService extends IService<User> {
	void disable(User u);

	
}
