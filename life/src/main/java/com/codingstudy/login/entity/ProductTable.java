package com.codingstudy.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (ProductTable)表实体类
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-10 15:49:54
 */
@SuppressWarnings("serial")
@TableName("product_table")
@Data
public class ProductTable extends Model<ProductTable> {

    @TableId(type = IdType.UUID)
    private String productId;
    
    private String productName;
    
    private String imgUrl;
    
    private Double price;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.productId;
    }
    }