package cn.edu.hbpu.news2022.service;

import cn.edu.hbpu.news2022.entity.Kind;

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
public interface KindService extends IService<Kind> {
	List<Kind> getNewsNumsByKind();
}
