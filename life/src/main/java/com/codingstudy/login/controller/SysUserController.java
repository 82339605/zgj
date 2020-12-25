package com.codingstudy.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.codingstudy.login.components.BCryptPasswordEncoderUtil;
import com.codingstudy.login.entity.SysRoleTable;
import com.codingstudy.login.entity.SysRoleUserTable;
import com.codingstudy.login.entity.SysUserEntity;
import com.codingstudy.login.service.SysRoleUserTableService;
import com.codingstudy.login.service.SysUserService;
import com.codingstudy.login.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 登录接口
 */
@RestController
@RequestMapping("user")
public class SysUserController extends ApiController {

    @Autowired
    SysUserService userService;
    @Autowired
    BCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
    @Autowired
    SysRoleUserTableService sysRoleUserTableService;

    /**
     * 查询所有数据集
     * @return
     */
    @GetMapping("search")
    public R getList() {
        List<SysUserEntity> list =  userService.list();
        return  success(list);
    }

    /**
     * 添加用户、用户自行注册。
     * @param userVo
     * @return
     */
    @PostMapping("register")
    public R register(@RequestBody(required = false) SysUserVo userVo) {
       try {
           System.out.println("registerVo = " + userVo);
           userService.register(userVo);
           //给用户分配默认权限;
           SysRoleUserTable sysRoleUserTable = new SysRoleUserTable();
           sysRoleUserTable.setRoleId("2f412c59e5077e0cd53d21fac3eee402");
           sysRoleUserTable.setUserId(userService.getOne(new LambdaQueryWrapper<SysUserEntity>()
                   .eq(SysUserEntity::getUsername,userVo.getUsername())).getUserId());
           sysRoleUserTableService.save(sysRoleUserTable);
           return success("ok");
       }catch (Exception e){
           return failed(e.getMessage());
       }
    }

    /**
     * 修改数据
     *
     * @param sysUserVo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysUserVo sysUserVo) {
        SysUserEntity po = this.userService.voToPo(sysUserVo);
        System.out.println("po = " + po);
        return success(this.userService.updateById(po));
    }
    @PutMapping("update")
    public R update(@RequestBody SysUserEntity sysUserEntity){
        sysUserEntity.setPassword(bCryptPasswordEncoderUtil.encode(sysUserEntity.getPassword()));
        //获取当前用户，并且修改当前用户的名字;
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return success(userService.update(sysUserEntity,new LambdaUpdateWrapper<SysUserEntity>().eq(SysUserEntity::getUsername,name)));
    }
    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<String> idList) {
        return success(this.userService.removeByIds(idList));
    }
}
