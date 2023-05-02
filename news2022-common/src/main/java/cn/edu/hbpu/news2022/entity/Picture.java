package cn.edu.hbpu.news2022.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hbpu
 * @since 2022-03-09
 */
@Getter
@Setter
@TableName("pic")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;

    /**
     * 图片
     */
    @TableField("pic")
    private String pic;

    /**
     * 新闻编号
     */
    @TableField("newsId")
    private Integer newsId;


}
