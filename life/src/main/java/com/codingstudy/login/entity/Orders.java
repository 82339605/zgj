package com.codingstudy.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@TableName("orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Orders {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String productId;

    private String size;

    private String color;
    private Double price;
    private Integer num;
    @TableField(exist = false)
    private String productName;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private  String imgUrl;

}
