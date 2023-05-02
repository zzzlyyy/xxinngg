package cn.edu.hbpu.news2022.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import cn.edu.hbpu.news2022.entity.Kind;
import cn.edu.hbpu.news2022.service.KindService;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/kind")
public class KindController {
	@Autowired
	KindService kindService;
	@Autowired
	RedisTemplate redisTemplate;
	private static String Kind_Redis_Key="kindList";
	@GetMapping("/list")
	List<Kind> list(){
		ListOperations opsForList = redisTemplate.opsForList();
		Long size = opsForList.size(this.Kind_Redis_Key);
		if (size>0) {
			System.out.println("redis");
			return opsForList.range(this.Kind_Redis_Key, 0, size-1);
		} else {
			List<Kind> list = kindService.list();
			opsForList.rightPushAll(this.Kind_Redis_Key, list);
			return list;
		}
	}
	@GetMapping("/getNewsNumsByKind")
	List<Kind> getNewsNumsByKind(){
		return kindService.getNewsNumsByKind();
	}
	
}

