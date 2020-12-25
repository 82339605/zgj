package com.codingstudy.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@TableName("sys_user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserEntity {

    @TableId(type = IdType.UUID)
    String userId;

    String username;

    String password;

    Integer state;

    String description;

    String phone;

}
