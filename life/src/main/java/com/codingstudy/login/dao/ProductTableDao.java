package com.codingstudy.login.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codingstudy.login.entity.ProductTable;
import org.apache.ibatis.annotations.Select;

/**
 * (ProductTable)表数据库访问层
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-10 15:49:54
 */
public interface ProductTableDao extends BaseMapper<ProductTable> {
@Select("select product_name from product_table where product_id = #{id}")
    public String selectName(String id);
}