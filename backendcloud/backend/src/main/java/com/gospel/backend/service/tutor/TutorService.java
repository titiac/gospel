package com.gospel.backend.service.tutor;

import com.gospel.backend.common.R;
import com.gospel.backend.pojo.vo.UpdateRequestVo;

public interface TutorService {
    public R getStudentList();

    public R getTutorRequestList();

    public R sendTutorRequest(Integer tutorId);

    public R updateTutorRequest(UpdateRequestVo updateRequestVo);

    public R getRequestListForStudent();

}
