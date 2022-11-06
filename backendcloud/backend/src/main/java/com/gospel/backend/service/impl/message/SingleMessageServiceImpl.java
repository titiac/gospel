package com.gospel.backend.service.impl.message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.mapper.SingleMessageMapper;
import com.gospel.backend.pojo.SingleMessage;
import com.gospel.backend.service.mesage.SingleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gospel
 * @description: 私聊消息
 * @author: zhw
 * @created: 2022/11/06 17:20
 */
@Service
public class SingleMessageServiceImpl implements SingleMessageService {
    
    @Autowired
    private SingleMessageMapper singleMessageMapper;
    
    @Override
    public List<SingleMessage> getSingleMessage(Integer userFrom, Integer userTo) {

        QueryWrapper<SingleMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_from", userFrom).and(w -> w.eq("user_to", userTo)).orderByAsc("send_time");
        List<SingleMessage> resp = singleMessageMapper.selectList(queryWrapper);
        
        return resp;
    }
}

