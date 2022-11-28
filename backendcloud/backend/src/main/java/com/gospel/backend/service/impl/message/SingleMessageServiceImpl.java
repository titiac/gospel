package com.gospel.backend.service.impl.message;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.SingleMessageMapper;
import com.gospel.backend.pojo.SingleMessage;
import com.gospel.backend.pojo.vo.IsReadSingleMessageRequestVo;
import com.gospel.backend.service.mesage.SingleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: backendcloud
 * @description: 单聊消息
 * @author: zhw
 * @created: 2022/11/10 19:18
 */
@Service
public class SingleMessageServiceImpl implements SingleMessageService {

    @Autowired
    private SingleMessageMapper singleMessageMapper;
    
    
    @Override
    public R getSingleMessage(IsReadSingleMessageRequestVo iVo) {
        Integer myselfId = iVo.getMyselfId();
        Integer friendId = iVo.getFriendId();

        QueryWrapper<SingleMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(i -> i.eq("user_from", myselfId).eq("user_to", friendId))
                .or(i -> i.eq("user_from", friendId).eq("user_to", myselfId))
                .orderByAsc("send_time");
        List<SingleMessage> resp = singleMessageMapper.selectList(queryWrapper);
        return R.ok().data("singleMessage",resp);
        
    }

    @Override
    public void setIsRead(IsReadSingleMessageRequestVo iVo) {
        Integer myselfId = iVo.getMyselfId();
        Integer friendId = iVo.getFriendId();
        System.out.println(iVo);
        singleMessageMapper.setIsRead(myselfId, friendId);
    }
}

