package com.gospel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gospel
 * @description: 群成员表
 * @author: zhw
 * @created: 2022/11/06 12:09
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupMember {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer groupId;
    private Integer userId;
    private String  memberType;
    private Integer memberStatus;
}

