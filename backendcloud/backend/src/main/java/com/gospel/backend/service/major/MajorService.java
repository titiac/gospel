package com.gospel.backend.service.major;

import com.gospel.backend.common.R;

public interface MajorService {
    
    R getCollege();
    
    R getMajorByCollege(String college);
}
