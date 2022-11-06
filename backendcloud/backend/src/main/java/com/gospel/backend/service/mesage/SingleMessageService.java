package com.gospel.backend.service.mesage;

import com.gospel.backend.pojo.SingleMessage;

import java.util.List;

public interface SingleMessageService {
    List<SingleMessage> getSingleMessage(Integer userFrom, Integer userTo);
}
