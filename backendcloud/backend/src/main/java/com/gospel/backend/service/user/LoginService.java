package com.gospel.backend.service.user;

import com.gospel.backend.common.R;

import java.util.Map;

public interface LoginService {
    public R getToken(String username, String password);
}
