package com.gospel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gospel.backend.common.R;
import com.gospel.backend.mapper.*;
import com.gospel.backend.pojo.*;
import com.gospel.backend.pojo.vo.AddCourseVo;
import com.gospel.backend.service.CourseService;
import com.gospel.backend.service.impl.utils.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @program: backendcloud
 * @description: 与课程有关的服务
 * @author: zhw
 * @created: 2022/11/12 10:56
 */
@Service
public class CourseServiceImpl implements CourseService{
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private SelectCourseMapper selectCourseMapper;
    
    @Autowired
    private FzuGroupMapper fzuGroupMapper;
    
    @Autowired
    private GroupMemberMapper groupMemberMapper;
    
    @Override
    public R adminAddCourse(AddCourseVo addCourseVo) {
        String courseName = addCourseVo.getCourseName();
        Integer teacherId = addCourseVo.getTeacherId();
        Integer limitNum = addCourseVo.getLimitNum();
        String address = addCourseVo.getAddress();
        
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", teacherId).and(i -> i.eq("flag", 1));
        User teacher = userMapper.selectOne(queryWrapper);
        if(teacher == null) {
            return R.error();
        }
        
                
        Course course = new Course(null, courseName, teacherId, limitNum, null, address, 1);
        
        courseMapper.insert(course);
        return R.ok();
    }

    @Override
    public R studentSelect(String courseId) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        log.info("用户{}选择课程{}", user.getId(), courseId);
        
        
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", Integer.parseInt(courseId));
        Course course = courseMapper.selectOne(queryWrapper);
        
        if(course == null) {
            log.info("查询课程为空");
            return R.error();
        }
        
        /** 选课已关闭 */
        if(course.getStatus() == 0) {
            return R.error();
        }
         
        QueryWrapper<SelectCourse> selectCourseQueryWrapper = new QueryWrapper<>();
        selectCourseQueryWrapper.eq("course_id", Integer.parseInt(courseId));
        
        List<SelectCourse> selectCourses = selectCourseMapper.selectList(selectCourseQueryWrapper);
        
        if(selectCourses.size() < course.getLimitNum()) {
            selectCourseQueryWrapper = new QueryWrapper<>();
            selectCourseQueryWrapper.eq("course_id", Integer.parseInt(courseId))
                    .and(i -> i.eq("student_id",user.getId()));
            if(selectCourseMapper.selectOne(selectCourseQueryWrapper) != null) {
                return R.error();
            }
            SelectCourse selectCourse = new SelectCourse(null, Integer.parseInt(courseId), user.getId());
            selectCourseMapper.insert(selectCourse);
        } else {
            return R.error();
        }
        return R.ok();
    }

    @Override
    public R endSelect(String courseId) {
        UsernamePasswordAuthenticationToken authentication=
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser=(UserDetailsImpl)authentication.getPrincipal();
        User user = loginUser.getUser();
        
        /** 没有权限做此操作 */
        if(user.getFlag() != 0) {
            return R.error();
        }

        /** 没有此课程或该课程已结束选课 */
        Course course = courseMapper.selectById(Integer.parseInt(courseId));
        if(course == null || course.getStatus() == 0) {
            return R.error();
        }
        
        /** 关闭选课 */
        Course newCourse = new Course(
                null, 
                course.getCourseName(),
                course.getTeacherId(),
                course.getLimitNum(),
                course.getGroupId(),
                course.getAddress(),
                0
        );
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", Integer.parseInt(courseId));
        courseMapper.update(newCourse, updateWrapper);
        
        
        /** 建群 */
        Date createTime = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
        Random r = new Random();
        
        User teacher = userMapper.selectById(course.getTeacherId());
        
        String groupNumber = "G" + sdf.format(new Date()) + r.nextInt(9);
        String groupName = course.getCourseName() + " " + course.getAddress() + " " + teacher.getName();
        String photo = "https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png";
        String profile = "这个是"+teacher.getName()+"老师的"+course.getCourseName()+"教学群";

        FzuGroup newGroup = new FzuGroup(
                null,
                groupNumber,
                groupName,
                photo,
                createTime,
                profile
        );
        System.out.println(newGroup);
        fzuGroupMapper.insert(new FzuGroup(null, groupNumber, groupName, photo, createTime, profile));
        
        /** 取出创建的群 */
        QueryWrapper<FzuGroup> groupQueryWrapper = new QueryWrapper<>();
        groupQueryWrapper.eq("group_number", groupNumber);
        FzuGroup groupCreated = fzuGroupMapper.selectOne(groupQueryWrapper);
        
        /** 插入群成员--学生 */
        QueryWrapper<SelectCourse> selectCourseQueryWrapper = new QueryWrapper<>();
        selectCourseQueryWrapper.eq("course_id", Integer.parseInt(courseId));
        List<SelectCourse> selectStudents = selectCourseMapper.selectList(selectCourseQueryWrapper);
        
        for(SelectCourse sc : selectStudents) {
            GroupMember groupMember = new GroupMember(
                    null,
                    groupCreated.getId(),
                    sc.getStudentId(),
                    "common",
                    1
            );
            groupMemberMapper.insert(groupMember);
        }        
        /** 插入群成员--老师 */
        
        GroupMember teacherMember = new GroupMember(
            null,
            groupCreated.getId(),
            teacher.getId(),
            "admin",
            1    
        );
        groupMemberMapper.insert(teacherMember);
        
        return R.ok().data("Group", groupCreated);
    }
}
