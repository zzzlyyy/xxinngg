package cn.edu.hbpu.news2022.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.edu.hbpu.news2022.entity.News;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {
	@Select("SELECT n.*,k.content AS kindName,u.userName,u.image FROM news n LEFT JOIN kind k ON\r\n" + 
			"n.kindId=k.kindId LEFT JOIN user u ON n.uid=u.uid WHERE newsId=#{newsid}")
	@Results({
		@Result(property = "picList",column = "newsid",
				many = @Many(select = "cn.edu.hbpu.news2022.mapper.PictureMapper.getPicListByNewsid"))
	})
	News getById(int newsid);
	@Select("SELECT n.newsId,n.title,n.video,u.userName,u.image\n"
			+ "FROM news n\n"
			+ "LEFT JOIN user u\n"
			+ "ON n.uid=u.uid\n"
			+ "WHERE kindId=#{kindId} AND n.type=3")
	List<News> getVideoByKindId(int kindId);
}
