package com.codingstudy.login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingstudy.login.dao.ReplyDao;
import com.codingstudy.login.entity.Reply;
import com.codingstudy.login.service.ReplyService;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyDao, Reply> implements ReplyService {

}
