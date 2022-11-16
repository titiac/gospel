package com.gospel.backend.controller.tutor;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.SendTutorRequestVo;
import com.gospel.backend.pojo.vo.UpdateRequestVo;
import com.gospel.backend.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @GetMapping("/tutor/getStudentList")
    public R getStudentList(){
        return tutorService.getStudentList();
    }

    @GetMapping("/tutor/getRequestList")
    public R getTutorRequestList(){
        return tutorService.getTutorRequestList();
    }

    @PostMapping("/tutor/sendRequest")
    public R sendTutorRequest(@RequestBody SendTutorRequestVo sendTutorRequestVo){
        Integer tutorId= sendTutorRequestVo.getTutorId();
        return tutorService.sendTutorRequest(tutorId);
    }

    @PostMapping("/tutor/updateRequest")
    public R updateTutorRequest(@RequestBody UpdateRequestVo updateRequestVo){
        return tutorService.updateTutorRequest(updateRequestVo);
    }

    @GetMapping("/tutor/getRequestListForStudent")
    public R getRequestListForStudent(){
        return tutorService.getRequestListForStudent();
    }

    @GetMapping("/tutor/getTeacherAndPeers")
    public R getTeacherAndPeers(){
        return tutorService.getTeacherAndPeers();
    }

}
