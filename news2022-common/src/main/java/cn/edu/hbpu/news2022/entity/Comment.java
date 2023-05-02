package cn.edu.hbpu.news2022.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "commentId", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 用户编号
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * 新闻编号
     */
    @TableField("newsId")
    private Integer newsId;

    private transient String userName;
    private transient String image;
}
