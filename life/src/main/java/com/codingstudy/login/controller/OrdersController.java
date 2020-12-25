package com.codingstudy.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.codingstudy.login.dao.ProductTableDao;
import com.codingstudy.login.entity.Orders;
import com.codingstudy.login.entity.ProductTable;
import com.codingstudy.login.entity.SysUserEntity;
import com.codingstudy.login.service.OrdersService;
import com.codingstudy.login.service.ProductTableService;
import com.codingstudy.login.service.SysUserService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrdersController extends ApiController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    ProductTableService productTableService;
    @Autowired
    private ProductTableDao productTableDao;

    @PostMapping
    public R insert(@RequestBody Orders orders) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        orders.setUserId(sysUserService.getOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, name))
                .getUserId());
        return success(ordersService.save(orders));
    }

    @PutMapping
    public R update(@RequestBody Orders orders) {
        return success(ordersService.update(orders, new LambdaUpdateWrapper<Orders>()
                .eq(Orders::getId, orders.getId())));
    }

    @GetMapping
    public R select() {
        List<Orders> list = ordersService.list();
        list.forEach(i -> {
            SysUserEntity one = sysUserService.getOne(
                    new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUserId, i.getUserId()));
            i.setUsername(one != null ? one.getUsername() : null);
            i.setProductName(productTableDao.selectName(i.getProductId()));
        });
        return success(list);
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable("id") String id) {
        return success(ordersService.removeById(id));
    }
}
