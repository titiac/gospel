package com.gospel.backend.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: backendcloud
 * @description:
 * @author: zhw
 * @created: 2022/11/11 11:41
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IsReadVo {
    private Set<Integer> isRead = new HashSet<>();
}

