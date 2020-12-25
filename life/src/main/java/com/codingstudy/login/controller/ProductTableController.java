package com.codingstudy.login.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingstudy.login.dao.ProductTableDao;
import com.codingstudy.login.entity.Orders;
import com.codingstudy.login.entity.ProductTable;
import com.codingstudy.login.entity.SysUserEntity;
import com.codingstudy.login.service.OrdersService;
import com.codingstudy.login.service.ProductTableService;
import com.codingstudy.login.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (ProductTable)表控制层
 * 该类由EasyCode工具生成
 * @author 小明哥
 * @since 2020-03-10 15:49:54
 */
@RestController
@RequestMapping("productTable")
public class ProductTableController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ProductTableService productTableService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private OrdersService ordersService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param productTable 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<ProductTable> page, ProductTable productTable) {
        return success(this.productTableService.page(page, new QueryWrapper<>(productTable)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        System.out.println(this.productTableService.getById(id));
        return success(this.productTableService.getById(id));
    }
    @GetMapping("getInformation")
    public R getInformation() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        SysUserEntity one = sysUserService.getOne(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUsername, name));
        List<Orders> list = ordersService.list(new LambdaQueryWrapper<Orders>().eq(Orders::getUserId, one.getUserId()));
        list.forEach(i->{
            ProductTable productTable = productTableService.getOne(new LambdaQueryWrapper<ProductTable>()
                    .eq(ProductTable::getProductId, i.getProductId()));
            i.setProductName(productTable.getProductName())
            .setImgUrl(productTable.getImgUrl());

        });
        //        System.out.println(this.productTableService.getById(id));
        return success(list);
    }

    /**
     * 新增数据
     *
     * @param productTable 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody ProductTable productTable) {
        return success(this.productTableService.save(productTable));
    }

    /**
     * 修改数据
     *
     * @param productTable 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody ProductTable productTable) {
        return success(this.productTableService.update(productTable,new LambdaUpdateWrapper<ProductTable>()
                .eq(ProductTable::getProductId,productTable.getProductId())));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.productTableService.removeByIds(idList));
    }
    @DeleteMapping("{id}")
    public R deleteById(@PathVariable("id") String id) {
        return success(this.productTableService.removeById(id));
    }
}