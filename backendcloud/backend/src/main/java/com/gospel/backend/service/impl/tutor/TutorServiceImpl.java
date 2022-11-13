package com.gospel.backend.service.impl.tutor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.UserMapper;
import com.gospel.backend.mapper.tutor.TutorMapper;
import com.gospel.backend.mapper.tutor.TutorRequestMapper;
import com.gospel.backend.pojo.User;
import com.gospel.backend.pojo.tutor.Tutor;
import com.gospel.backend.pojo.tutor.TutorRequest;
import com.gospel.backend.pojo.vo.UpdateRequestVo;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import com.gospel.backend.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: 选导师相关服务
 * @author: lzp
 * @created: 2022/11/13 13:14
 */

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorMapper tutorMapper;

    @Autowired
    private TutorRequestMapper tutorRequestMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public R getTutorRequestList() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        QueryWrapper<TutorRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("tutor_id",user.getId());
        List<TutorRequest> list=tutorRequestMapper.selectList(queryWrapper);
        List<JSONObject> list1=new ArrayList<>();
        for(TutorRequest tutorRequest:list){
            QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("id",tutorRequest.getStudentId());
            User user1=userMapper.selectOne(queryWrapper1);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",user1.getId().toString());
            jsonObject.put("number",user1.getNumber());
            jsonObject.put("name",user1.getName());
            jsonObject.put("flag",user1.getFlag().toString());
            jsonObject.put("photo",user1.getPhoto());
            jsonObject.put("profile",user1.getProfile());
            jsonObject.put("status",tutorRequest.getStatus().toString());
            jsonObject.put("send_time",tutorRequest.getSendTime());
            list1.add(jsonObject);
        }
        return R.ok().data("requestList",list1);
    }

    @Override
    public R sendTutorRequest(Integer tutorId) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        if(!userMapper.selectById(tutorId).getMajor().equals(user.getMajor())){
            return R.error().data("error_message","该导师与你专业不同，请选择相同专业导师");
        }
        QueryWrapper<Tutor> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("tutor_id",tutorId);
        if(tutorMapper.selectList(queryWrapper).size()==8){
            return R.error().data("error_message","该导师已有8名学生，请选择其他导师");
        }

        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",user.getId());
        if(!tutorMapper.selectList(queryWrapper).isEmpty()){
            return R.error().data("error_message","你已有导师，请勿再申请导师");
        }

        QueryWrapper<TutorRequest> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("tutor_id",tutorId)
                .eq("student_id",user.getId())
                .eq("status",0);

        if(!tutorRequestMapper.selectList(queryWrapper1).isEmpty()){
            return R.error().data("error_message","已向该导师发送请求，请勿多次操作");
        }

        queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("student_id",user.getId()).eq("status",0);
        if(!tutorRequestMapper.selectList(queryWrapper1).isEmpty()){
            return R.error().data("error_message","向其他导师发送的申请尚未处理，请勿同时给多个导师发送申请");
        }

        queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("tutor_id",tutorId)
                .eq("student_id",user.getId())
                .eq("status",1);
        if(!tutorRequestMapper.selectList(queryWrapper1).isEmpty()){
            return R.error().data("error_message","你已经是他的学生了");
        }

        queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("student_id",user.getId()).eq("status",1);
        if(!tutorRequestMapper.selectList(queryWrapper1).isEmpty()){
            return R.error().data("error_message","你已经是其他导师的学生了");
        }

        queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("tutor_id",tutorId)
                .eq("student_id",user.getId())
                .eq("status",2);
        if(!tutorRequestMapper.selectList(queryWrapper1).isEmpty()){
            return R.error().data("error_message","你已经被拒绝了，请选择其他导师");
        }

        Date date=new Date();
        TutorRequest tutorRequest=new TutorRequest(null, user.getId(), tutorId,0,date);
        tutorRequestMapper.insert(tutorRequest);

        return R.ok();
    }

    @Override
    public R updateTutorRequest(UpdateRequestVo updateRequestVo) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        QueryWrapper<TutorRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("student_id",updateRequestVo.getUserFrom()).eq("tutor_id",user.getId());
        TutorRequest tutorRequest=tutorRequestMapper.selectOne(queryWrapper);
        tutorRequest.setStatus(updateRequestVo.getFlag());
        tutorRequestMapper.updateById(tutorRequest);

        if(tutorRequest.getStatus()==1){
            Date date=new Date();
            Tutor tutor=new Tutor(null, user.getId(), updateRequestVo.getUserFrom(),date);
            tutorMapper.insert(tutor);
            return R.ok().data("result","成功接受学生");
        }

        if(tutorRequest.getStatus()==2){
            return R.ok().data("result","成功拒绝对方");
        }

        return R.error();
    }

    @Override
    public R getStudentList() {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser=(UserDetailsImpl) authentication.getPrincipal();
        User user=loginUser.getUser();

        if(user.getFlag()!=1){
            return R.error().data("getError","你没有权限查看");
        }

        QueryWrapper<Tutor> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("tutor_id",user.getId());
        List<Tutor> list=tutorMapper.selectList(queryWrapper);
        List<User> userList=new ArrayList<>();
        for(Tutor tutor:list){
            QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("id",tutor.getStudentId());
            User user1=userMapper.selectOne(queryWrapper1);
            userList.add(user1);
        }

        return R.ok().data("student_list",userList);
    }
}
