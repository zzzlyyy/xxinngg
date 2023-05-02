package cn.edu.hbpu.news2022.serviceImpl;

import cn.edu.hbpu.news2022.entity.Log;
import cn.edu.hbpu.news2022.mapper.LogMapper;
import cn.edu.hbpu.news2022.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-30
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
