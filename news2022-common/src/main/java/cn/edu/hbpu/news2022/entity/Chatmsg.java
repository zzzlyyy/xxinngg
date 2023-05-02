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
 * @since 2022-03-30
 */
@Getter
@Setter
@TableName("chatmsg")
public class Chatmsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    @TableField("fromUser")
    private String fromUser;

    @TableField("toUser")
    private String toUser;

    @TableField("content")
    private String content;

    @TableField("date")
    private LocalDateTime date;


}
