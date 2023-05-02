package cn.edu.hbpu.news2022.controller;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.edu.hbpu.news2022.entity.User;
import cn.edu.hbpu.news2022.service.UserService;
import cn.edu.hbpu.news2022.serviceImpl.MailService;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	private String uploadPath;
	@GetMapping("/list")
	List<User> list(){
		return userService.list();
	}
	@PostMapping("/checkUsername")
	String checkUsername(@RequestBody User u){
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("userName").eq("userName",u.getUsername());
		if (userService.getOne(queryWrapper)!=null) {
			return "exist";
		}
		return "error";
	}
	@RequestMapping("/regist")
	String regist(User u,MultipartFile imgFile) {
		String checkcode = u.getCheckcode();
		String code = (String)redisTemplate.opsForValue().get(u.getEmail());
		if(checkcode.equals(code)) {
			String fileName=imgFile.getOriginalFilename();
			String newsFileName=UUID.fastUUID().toString(true)
				+"."+FileNameUtil.extName(fileName);
			File saveFile=new File(uploadPath+newsFileName);
			try {
				imgFile.transferTo(saveFile);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			u.setRegTime(LocalDateTime.now());
			u.setImage(newsFileName);
			u.setType(1);
			userService.save(u);
			return "ok";
		}
		return "fail";
	}
//	@Autowired
//	private RocketMQTemplate rocketMQTemplate;
	@PostMapping("/login")
	public User login(@RequestBody User u) {
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("uid,userName,image").eq("userName",u.getUsername())
		.eq("password", u.getPassword());
		User user = userService.getOne(queryWrapper);
		if (user!=null) {
//			rocketMQTemplate.convertAndSend("login-log",user);
			if(user.getType()==0) {
				return null;
			}
		}
		return user;
	}
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	MailService mailService;
	@GetMapping("/sendMail")
	void sendMail(String email) {
		String codes="23456789qazwsxedcrfvtgbyhnujmkp";
		Random r=new Random();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<6;i++) {
			int index = r.nextInt(codes.length());
			char c = codes.charAt(index);
			sb.append(c);
		}
		redisTemplate.opsForValue().set(email, sb.toString(),Duration.ofMinutes(3));
		mailService.sendMail(email, "头条新闻注册验证码", sb.toString());
	}
	@GetMapping("/disable")
	public void disable(User u) {
		 userService.disable(u);
		 return ;
	}
	@GetMapping("/page")
	public IPage<User> getPage(Page<User> page){
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.select("uid","userName","regTime","image","email","type").orderByAsc("uid");
		return userService.page(page,queryWrapper);
	}
}

