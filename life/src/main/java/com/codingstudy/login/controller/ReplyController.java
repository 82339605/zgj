package com.codingstudy.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.codingstudy.login.entity.Reply;
import com.codingstudy.login.entity.SysUserEntity;
import com.codingstudy.login.service.ReplyService;
import com.codingstudy.login.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("reply")
public class ReplyController extends ApiController {
    @Autowired
    ReplyService replyService;
    @Autowired
    SysUserService sysUserService;
    @PostMapping
    public R insert(@RequestBody Reply reply){
        System.out.println(reply);
        //获取当前登录用户
        reply.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        reply.setTime(new Date());
        return R.ok(replyService.save(reply));
    }

}
