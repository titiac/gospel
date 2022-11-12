package com.gospel.backend.service;

import com.gospel.backend.common.R;

public interface MajorService {
    
    R getCollege();
    
    R getMajorByCollege(String college);
}
