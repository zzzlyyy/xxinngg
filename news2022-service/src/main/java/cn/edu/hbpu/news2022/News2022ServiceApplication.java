package cn.edu.hbpu.news2022;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients //开启 OpenFeign 功能
@MapperScan("cn.edu.hbpu.news2022.mapper")
public class News2022ServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(News2022ServiceApplication.class, args);
	}
}
