package cn.edu.hbpu.news2022.mapper;

import cn.edu.hbpu.news2022.entity.Kind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
@Mapper
public interface KindMapper extends BaseMapper<Kind> {
@Select("SELECT COUNT(n.newsid) AS nums,k.kindId,k.content\n"
		+ "FROM news n \n"
		+ "LEFT JOIN kind k ON n.kindId=k.kindId\n"
		+ "GROUP BY  n.kindId")
List<Kind> getNewsNumsByKind();
}
