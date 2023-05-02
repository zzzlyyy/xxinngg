package cn.edu.hbpu.news2022.serviceImpl;

import cn.edu.hbpu.news2022.entity.Kind;
import cn.edu.hbpu.news2022.mapper.KindMapper;
import cn.edu.hbpu.news2022.service.KindService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

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
public class KindServiceImpl extends ServiceImpl<KindMapper, Kind> implements KindService {
	@Autowired
	KindMapper kindMapper;
	@Override
	public List<Kind> getNewsNumsByKind() {
		// TODO Auto-generated method stub
		return kindMapper.getNewsNumsByKind();
	}

}
