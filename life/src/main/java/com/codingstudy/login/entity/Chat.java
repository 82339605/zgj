package com.codingstudy.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@TableName("chat")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Chat {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String title;
    private String text;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
    @TableField(exist = false)
    private List<Reply> replyList;
    @TableField(exist = false)
    private String username;
}
