package com.gospel.backend.controller.friend;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.UpdateRequestVo;
import com.gospel.backend.service.friend.UpdateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateRequestController {
    @Autowired
    private UpdateRequestService updateRequestService;

    @PostMapping("/friend/update")
    public R updateRequest(@RequestBody UpdateRequestVo updateRequestVo){
        return updateRequestService.updateRequest(updateRequestVo);
    }

}
