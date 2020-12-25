package com.codingstudy.login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingstudy.login.dao.ChatDao;
import com.codingstudy.login.entity.Chat;
import com.codingstudy.login.service.ChatService;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatDao, Chat> implements ChatService {
}
