package cn.edu.hbpu.news2022.controller;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController{
	//@RequestMapping("/hello")
	@GetMapping("/hello")
	//使用GET方法访问
	//@RequestMapping(value = "/hello",method = RequestMethod.GET)
	//使用post访问
	//@PostMapping("/hello")
	public String helloSpring() {
		return "hello,HBPU,springboot!201940407134,网工班张宇辰";
	}
	@GetMapping("/hello1")
	public String hello1() {
		return "hello,HBPU湖北理工学院,springboot!";
	}
}