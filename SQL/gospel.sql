/*
 Navicat Premium Data Transfer

 Source Server         : MySQL80
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : gospel

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 14/11/2022 16:34:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_id` int NULL DEFAULT NULL,
  `limit_num` int NULL DEFAULT NULL COMMENT '限制选课人数',
  `group_id` int NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '操作系统', 12, 80, NULL, '东三', 0);
INSERT INTO `course` VALUES (2, '离散数学', 13, 80, NULL, '西一', 1);
INSERT INTO `course` VALUES (3, '模拟电路', 14, 80, NULL, '文楼', 1);

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_from` int NULL DEFAULT NULL,
  `friend_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 11, 12, '2022-11-11 23:53:24');
INSERT INTO `friend` VALUES (2, 11, 13, '2022-11-11 23:53:36');
INSERT INTO `friend` VALUES (3, 12, 11, '2022-11-11 23:54:00');
INSERT INTO `friend` VALUES (4, 13, 11, '2022-11-11 23:54:01');
INSERT INTO `friend` VALUES (5, 12, 14, '2022-11-11 23:54:30');
INSERT INTO `friend` VALUES (6, 14, 12, '2022-11-11 23:54:31');

-- ----------------------------
-- Table structure for friend_request
-- ----------------------------
DROP TABLE IF EXISTS `friend_request`;
CREATE TABLE `friend_request`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_from` int NULL DEFAULT NULL,
  `user_to` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `send_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend_request
-- ----------------------------
INSERT INTO `friend_request` VALUES (1, 11, 12, 1, '2022-11-11 23:53:46');
INSERT INTO `friend_request` VALUES (2, 13, 11, 1, '2022-11-11 23:53:07');
INSERT INTO `friend_request` VALUES (3, 14, 11, 0, '2022-10-12 00:03:26');
INSERT INTO `friend_request` VALUES (4, 15, 11, 2, '2022-10-12 00:03:41');

-- ----------------------------
-- Table structure for fzu_group
-- ----------------------------
DROP TABLE IF EXISTS `fzu_group`;
CREATE TABLE `fzu_group`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_number` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `group_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `photo` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `group_create_time` datetime NULL DEFAULT NULL,
  `group_profile` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fzu_group
-- ----------------------------
INSERT INTO `fzu_group` VALUES (1, 'G2211031923482', 'kkk', 'https://w.wallhaven.cc/full/7p/wallhaven-7p6jp3.png', '2022-11-06 12:06:05', '这个群主很懒什么也没留下');
INSERT INTO `fzu_group` VALUES (2, 'G2211121923482', '操作系统 东三 钱牧', 'https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png', '2022-11-12 19:23:48', '这个是钱牧老师的操作系统教学群');
INSERT INTO `fzu_group` VALUES (3, 'G2211141531474', '测试普通用户建群', 'https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png', '2022-11-14 15:31:47', '这个群主很懒什么也没留下');

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `photo` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `profile` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (1, 'kkk', 'https://w.wallhaven.cc/full/7p/wallhaven-7p6jp3.png', '2022-11-06 12:06:05', '这个群主很懒什么也没留下');

-- ----------------------------
-- Table structure for group_enter_request
-- ----------------------------
DROP TABLE IF EXISTS `group_enter_request`;
CREATE TABLE `group_enter_request`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_from` int NULL DEFAULT NULL,
  `group_id` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT 0,
  `send_time` datetime NULL DEFAULT NULL,
  `deal_time` datetime NULL DEFAULT NULL,
  `deal_admin_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发送加群请求表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_enter_request
-- ----------------------------
INSERT INTO `group_enter_request` VALUES (1, 18, 13, 0, '2022-11-14 16:23:05', NULL, NULL);

-- ----------------------------
-- Table structure for group_member
-- ----------------------------
DROP TABLE IF EXISTS `group_member`;
CREATE TABLE `group_member`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `member_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `member_status` int NULL DEFAULT 1 COMMENT '是否被移除群聊',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_member
-- ----------------------------
INSERT INTO `group_member` VALUES (1, 1, 11, 'common', 1);
INSERT INTO `group_member` VALUES (2, 1, 12, 'common', 1);
INSERT INTO `group_member` VALUES (3, 1, 13, 'admin', 1);
INSERT INTO `group_member` VALUES (4, 1, 1, 'common', 1);
INSERT INTO `group_member` VALUES (5, 2, 11, 'common', 1);
INSERT INTO `group_member` VALUES (6, 2, 16, 'common', 1);
INSERT INTO `group_member` VALUES (7, 2, 17, 'common', 1);
INSERT INTO `group_member` VALUES (8, 2, 18, 'common', 1);
INSERT INTO `group_member` VALUES (9, 2, 12, 'admin', 1);
INSERT INTO `group_member` VALUES (10, 3, 14, 'common', 1);
INSERT INTO `group_member` VALUES (11, 3, 15, 'common', 1);
INSERT INTO `group_member` VALUES (12, 3, 16, 'common', 1);
INSERT INTO `group_member` VALUES (13, 3, 11, 'admin', 1);

-- ----------------------------
-- Table structure for group_message
-- ----------------------------
DROP TABLE IF EXISTS `group_message`;
CREATE TABLE `group_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_from` int NULL DEFAULT NULL,
  `group_id` int NULL DEFAULT NULL,
  `sender_nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sender_photo` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `send_time` datetime NULL DEFAULT NULL,
  `file_raw_name` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `message_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `message` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_read` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_message
-- ----------------------------
INSERT INTO `group_message` VALUES (6, 11, 1, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-11 12:59:43', '', 'text', '我是赵谦，在发群消息', '{\"isRead\":[11,12,13]}');
INSERT INTO `group_message` VALUES (7, 13, 1, '吴阶', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-11 13:17:26', '', 'text', '我是吴阶，在发群消息', '{\"isRead\":[11,12,13]}');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `college` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, '计算机与大数据学院', '计算机科学与技术');
INSERT INTO `major` VALUES (2, '计算机与大数据学院', '人工智能');
INSERT INTO `major` VALUES (3, '计算机与大数据学院', '大数据科学');
INSERT INTO `major` VALUES (4, '电气工程与自动化学院', '电气工程与自动化');
INSERT INTO `major` VALUES (5, '电气工程与自动化学院', '智能电网信息工程');
INSERT INTO `major` VALUES (6, '电气工程与自动化学院', '自动化');
INSERT INTO `major` VALUES (7, '数学与统计学院', '数学类');
INSERT INTO `major` VALUES (8, '数学与统计学院', '应用统计学');
INSERT INTO `major` VALUES (9, '化学学院', '化学');
INSERT INTO `major` VALUES (10, '化学学院', '制药工程');

-- ----------------------------
-- Table structure for select_course
-- ----------------------------
DROP TABLE IF EXISTS `select_course`;
CREATE TABLE `select_course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NULL DEFAULT NULL,
  `student_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of select_course
-- ----------------------------
INSERT INTO `select_course` VALUES (1, 1, 11);
INSERT INTO `select_course` VALUES (2, 2, 11);
INSERT INTO `select_course` VALUES (3, 1, 16);
INSERT INTO `select_course` VALUES (4, 1, 17);
INSERT INTO `select_course` VALUES (5, 1, 18);

-- ----------------------------
-- Table structure for single_message
-- ----------------------------
DROP TABLE IF EXISTS `single_message`;
CREATE TABLE `single_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_from` int NULL DEFAULT NULL,
  `user_to` int NULL DEFAULT NULL,
  `sender_nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sender_photo` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `send_time` datetime NULL DEFAULT NULL,
  `file_raw_name` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `message_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `message` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_read` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of single_message
-- ----------------------------
INSERT INTO `single_message` VALUES (31, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:32:38', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (32, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:33:46', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (33, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:36:54', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (34, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:37:19', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (37, 12, 11, '钱牧', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:38:45', '', 'text', '我是钱牧，正在发私聊消息', 1);
INSERT INTO `single_message` VALUES (38, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:38:59', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (39, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:39:02', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (40, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:40:52', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (46, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 17:43:26', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (47, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 18:17:08', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (48, 12, 11, '钱牧', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 18:22:29', '', 'text', '我是钱牧，正在发私聊消息', 1);
INSERT INTO `single_message` VALUES (49, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 18:51:39', '', 'text', '我是赵谦', 0);
INSERT INTO `single_message` VALUES (50, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 18:53:00', '', 'text', '我是赵谦', 0);
INSERT INTO `single_message` VALUES (51, 11, 12, '赵谦', 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '2022-11-10 18:53:30', '', 'text', '我是赵谦', 0);

-- ----------------------------
-- Table structure for tutor
-- ----------------------------
DROP TABLE IF EXISTS `tutor`;
CREATE TABLE `tutor`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `tutor_id` int NULL DEFAULT NULL,
  `student_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tutor
-- ----------------------------
INSERT INTO `tutor` VALUES (1, 13, 17, '2022-11-13 15:47:09');
INSERT INTO `tutor` VALUES (2, 12, 11, '2022-11-13 16:04:18');

-- ----------------------------
-- Table structure for tutor_request
-- ----------------------------
DROP TABLE IF EXISTS `tutor_request`;
CREATE TABLE `tutor_request`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NULL DEFAULT NULL,
  `tutor_id` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `send_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tutor_request
-- ----------------------------
INSERT INTO `tutor_request` VALUES (1, 11, 12, 1, '2022-11-11 23:53:46');
INSERT INTO `tutor_request` VALUES (2, 16, 14, 0, '2022-11-11 23:53:46');
INSERT INTO `tutor_request` VALUES (3, 18, 15, 0, '2022-11-13 15:48:29');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学号/教师工号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `flag` int NULL DEFAULT NULL COMMENT '管理员/老师/学生',
  `photo` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `college` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `major` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `profile` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `status` int NULL DEFAULT NULL COMMENT '登录状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '张三', '$2a$10$IcYbYb0GSaL.WS17fbvDEegjMwC5qywGzL608HGUE8BZY5WaNHl.y', 0, 'https://cdn.acwing.com/media/user/profile/photo/149957_lg_de39db8b80.jpg', NULL, NULL, ' 我是超级管理员', 0);
INSERT INTO `user` VALUES (11, 'S2211032345231', '赵谦', '$2a$10$uE2dnXZZf4PdsAy7rI4JpeEf1kROYlos6WfOC6lDNt07OIU03BCg6', 2, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '电气工程与自动化学院', '电气工程与自动化', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (12, 'T2211032345497', '钱牧', '$2a$10$wnWgUZQo7uY3q6raeZ0RlO6hWRgq9eXLlMJym5eh5F7Y6jdG6fnKe', 1, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '电气工程与自动化学院', '电气工程与自动化', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (13, 'T2211061442486', '吴阶', '$2a$10$wT/iugV3OVIBmNhbc8DZtOBIF5IqArAUcRhIbDV.opIGjO77Dk2De', 1, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '电气工程与自动化学院', '人工智能', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (14, 'T2211111512524', '张二', '$2a$10$0OkSj5vsGLV8Eez18iq4zeCPL3YYwUTspKpSN9cfv2tPwG5RVFdHe', 1, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '电气工程与自动化学院', '', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (15, 'T2211111633052', '张三丰', '$2a$10$kPieo3eI/v8JVXYj1mjg/uRPO5tt2mi4IKkcttvi8sFwglEcPRCMe', 1, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '计算机与大数据学院', '', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (16, 'S2211121150592', '张大彪', '$2a$10$nhECOxbCRWbq.lu7cOm9X.2SIvuzkSSVhTD6nCdqt0bvqOMmXE2s.', 2, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '计算机与大数据学院', '人工智能', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (17, 'S2211121151460', '柱子', '$2a$10$BEKwZnDcnvto8Q8rG2M2fOgYCBY1Q44EXxK77GgHxk06I3Sae1rni', 2, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '计算机与大数据学院', '计算机科学与技术', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (18, 'S2211121152050', '孙正义', '$2a$10$qMfNt.UNwV3XaS97gAqu2OLEj/ehG8ldfeRI0CkEkHPACAoT8oV4G', 2, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '计算机与大数据学院', '大数据科学', '这个用户很懒，什么也没留下', 0);

SET FOREIGN_KEY_CHECKS = 1;
