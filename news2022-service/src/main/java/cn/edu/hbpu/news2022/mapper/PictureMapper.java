package cn.edu.hbpu.news2022.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.edu.hbpu.news2022.entity.Picture;
@Mapper
public interface PictureMapper extends BaseMapper<Picture>{
	@Select("select pid,pic from pic where newsid=#{newsid}")
	public List<Picture> getPicListByNewsid(int newsid);
}
