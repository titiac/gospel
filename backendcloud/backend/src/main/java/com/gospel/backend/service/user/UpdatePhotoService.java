package com.gospel.backend.service.user;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UpdatePhotoService {
    public Map<String ,String > updatePhoto(MultipartFile multipartFile);
}
