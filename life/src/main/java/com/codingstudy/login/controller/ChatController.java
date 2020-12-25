package com.codingstudy.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.codingstudy.login.entity.Chat;
import com.codingstudy.login.entity.Reply;
import com.codingstudy.login.entity.SysUserEntity;
import com.codingstudy.login.service.ChatService;
import com.codingstudy.login.service.ReplyService;
import com.codingstudy.login.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("chat")
public class ChatController extends ApiController {
    @Autowired
    ChatService chatService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    ReplyService replyService;

    @PostMapping
    public R insert(@RequestBody Chat chat) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        chat.setUserId(sysUserService.getOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, name))
                .getUserId());
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        chat.setTime(new Date());// new Date()为获取当前系统时间
        return success(chatService.save(chat));
    }

    @GetMapping
    public R get() {
        List<Chat> list = chatService.list();
        list.forEach(i -> {
            i.setReplyList(replyService.list(
                    new LambdaQueryWrapper<Reply>().eq(Reply::getChatId, i.getId()
                    )))
                    .setUsername(sysUserService.getOne(
                            new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUserId, i.getUserId())).getUsername());
        });
        return success(list);
    }

    @DeleteMapping
    public R delete(String id) {
        return success(chatService.removeById(id));
    }
}
