package cn.edu.hbpu.news2022.serviceImpl;

import cn.edu.hbpu.news2022.entity.Comment;
import cn.edu.hbpu.news2022.mapper.CommentMapper;
import cn.edu.hbpu.news2022.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
	@Autowired
	CommentMapper commentMapper;
	@Override
	public List<Comment> getByNewsId(Integer newsId) {
		// TODO Auto-generated method stub
		return commentMapper.getByNewsId(newsId);
	}

}
