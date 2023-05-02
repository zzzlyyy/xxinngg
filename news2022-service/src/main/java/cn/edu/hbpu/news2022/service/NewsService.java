package cn.edu.hbpu.news2022.service;

import cn.edu.hbpu.news2022.entity.News;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
public interface NewsService extends IService<News> {
	News selectNewsById(int newsId);
	List<News> getVideoByKindId(int kindId);
}
