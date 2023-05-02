package cn.edu.hbpu.news2022.controller;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.edu.hbpu.news2022.entity.News;
import cn.edu.hbpu.news2022.service.NewsService;
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
public class NewsController {
	@Autowired
	private NewsService newsService;
	@GetMapping("/getSwipe")
	public IPage<News> getSwipe(){
		Page<News> page = new Page<News>(1,5);
		QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
		queryWrapper.select("newsId","pictures").orderByAsc("newsId");
		return newsService.page(page,queryWrapper);
	}
	@GetMapping("/getPage")
	public IPage<News> getPage(Page<News> page){
		QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
		queryWrapper.select("newsId","pictures","title","time","source").orderByAsc("newsId");
		return newsService.page(page,queryWrapper);
	}
	@GetMapping("/getBykindid")
	List<News> getBykindid(int kindId){
		QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
		queryWrapper.select("newsId","pictures","title","time","source").
		eq("kindId",kindId).orderByAsc("newsId");
		return newsService.list(queryWrapper);
	}
	@Value("${web.uploadPath}")
	private String uploadPath;
	@RequestMapping("/add")
	public String add(News news,MultipartFile imgFile) {
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
			news.setPictures(newsFileName);
			news.setTime(LocalDateTime.now());
			newsService.save(news);
			return "ok";
		}
	@GetMapping("/getById")
	public News getById(int newsId) {
		return newsService.selectNewsById(newsId);
	}
	@GetMapping("/download")
	public byte[] download(String filename) {
		File file = new File(uploadPath+filename);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		try {
			return FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage().getBytes();
		}
		
	}
	@GetMapping("/getByAuthorId")
	public IPage<News> getByAuthorId(Page<News> page,int uid) {
		QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
		queryWrapper.select("newsId","pictures","title","time","source")
		.eq("uid", uid)
		.orderByDesc("newsId");
		return newsService.page(page,queryWrapper);
	}
	@GetMapping("/getVideoBykindId")
	List<News> getVideoByKindId(int kindId) {
		return newsService.getVideoByKindId(kindId);
	}
	@PostMapping("/getByTitle")
	public IPage<News> getByTitle(@RequestBody News news){
		Page<News> page = new Page<>(news.getPage(),10);
		QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
		queryWrapper.select("newsId","pictures","title","time","source")
		.like("title", news.getTitle()).orderByDesc("newsId");
		return newsService.page(page,queryWrapper);
	}
	
}

