package cn.edu.hbpu.news2022.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@TableName("news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻编号
     */
    @TableId(value = "newsId", type = IdType.AUTO)
    private Integer newsId;

    /**
     * 分类编号
     */
    @TableField("kindId")
    private Integer kindId;

    /**
     * 新闻标题
     */
    @TableField("title")
    private String title;

    /**
     * 新闻内容
     */
    @TableField("content")
    private String content;

    /**
     * 编辑
     */
    @TableField("editor")
    private String editor;

    /**
     * 新闻来源
     */
    @TableField("source")
    private String source;

    /**
     * 发表时间
     */
    @TableField("time")
    private LocalDateTime time;

    /**
     * 类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 图片
     */
    @TableField("pictures")
    private String pictures;

    /**
     * 视频
     */
    @TableField("video")
    private String video;

    /**
     * 用户编号
     */
    @TableField("uid")
    private Integer uid;

    private transient List<Picture> picList;
    private transient String kindName;
    private transient String userName;
    private transient String image;
    private transient Integer page;
}
