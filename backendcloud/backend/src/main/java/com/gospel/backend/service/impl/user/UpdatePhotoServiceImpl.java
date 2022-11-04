package com.gospel.backend.service.impl.user;

import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.user.UpdatePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: lzp
 * @Description: 更新用户头像
 * @DateTime: 2022/11/4 22:34
 */

@Service
public class UpdatePhotoServiceImpl implements UpdatePhotoService {

    @Autowired
    private UserMapper userMapper;

    private final static String photoUrl="http://127.0.0.1:3000/get/photo/";

    @Override
    public Map<String, String> updatePhoto(MultipartFile file) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        Map<String ,String > map=new HashMap<>();
        try {
            if (file.isEmpty()){
                map.put("error_message","文件为空");
                return map;
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            //String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "d:/gospel/src/";

            //避免重名
            UUID uuid=UUID.randomUUID();
            String uid=uuid.toString().substring(0,8);

            String path = filePath+uid+fileName;
            File dest = new File(path);
            //检测是否存在该目录
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            //写入文件
            file.transferTo(dest);
            //更改图片链接，图片获取下次再写
            user.setPhoto(photoUrl+uid+fileName);
            userMapper.updateById(user);

            map.put("error_message","success");
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error_message","头像上传失败");
        return map;

    }
}
