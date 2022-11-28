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

 Date: 28/11/2022 21:06:22
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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '操作系统', 12, 80, 2, '东三', 0);
INSERT INTO `course` VALUES (2, '离散数学', 13, 80, 5, '西一', 0);
INSERT INTO `course` VALUES (3, '模拟电路', 14, 80, NULL, '文楼', 1);
INSERT INTO `course` VALUES (4, '计算机网络技术', 15, 80, NULL, '西一', 1);
INSERT INTO `course` VALUES (5, 'web应用实践', 15, 80, NULL, '数计', 1);
INSERT INTO `course` VALUES (6, '局域网应用实践', 15, 80, NULL, '数计', 1);
INSERT INTO `course` VALUES (7, '算法与数据结构', 13, 100, NULL, '西二', 1);
INSERT INTO `course` VALUES (8, '计算机', 12, 10, NULL, '东三', 1);

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_from` int NULL DEFAULT NULL,
  `friend_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `friend_type` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 11, 12, '2022-11-11 23:53:24', 1);
INSERT INTO `friend` VALUES (2, 11, 13, '2022-11-11 23:53:36', 1);
INSERT INTO `friend` VALUES (3, 12, 11, '2022-11-11 23:54:00', 1);
INSERT INTO `friend` VALUES (4, 13, 11, '2022-11-11 23:54:01', 1);
INSERT INTO `friend` VALUES (5, 12, 14, '2022-11-11 23:54:30', 1);
INSERT INTO `friend` VALUES (6, 14, 12, '2022-11-11 23:54:31', 1);
INSERT INTO `friend` VALUES (7, 16, 12, '2022-11-18 17:40:15', 1);
INSERT INTO `friend` VALUES (8, 12, 16, '2022-11-18 17:40:15', 1);
INSERT INTO `friend` VALUES (9, 11, 14, '2022-11-18 17:43:38', 1);
INSERT INTO `friend` VALUES (10, 14, 11, '2022-11-18 17:43:38', 1);
INSERT INTO `friend` VALUES (11, 18, 11, '2022-11-18 17:44:54', 1);
INSERT INTO `friend` VALUES (12, 11, 18, '2022-11-18 17:44:54', 1);
INSERT INTO `friend` VALUES (13, 16, 11, '2022-11-18 17:46:46', 1);
INSERT INTO `friend` VALUES (14, 11, 16, '2022-11-18 17:46:46', 1);
INSERT INTO `friend` VALUES (19, 11, 21, '2022-11-19 15:00:37', 1);
INSERT INTO `friend` VALUES (20, 21, 11, '2022-11-19 15:00:37', 1);
INSERT INTO `friend` VALUES (21, 11, 15, '2022-11-28 20:53:18', 0);
INSERT INTO `friend` VALUES (22, 15, 11, '2022-11-28 20:53:18', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of friend_request
-- ----------------------------
INSERT INTO `friend_request` VALUES (1, 11, 12, 1, '2022-11-11 23:53:46');
INSERT INTO `friend_request` VALUES (2, 13, 11, 1, '2022-11-11 23:53:07');
INSERT INTO `friend_request` VALUES (3, 14, 11, 1, '2022-10-12 00:03:26');
INSERT INTO `friend_request` VALUES (4, 15, 11, 2, '2022-10-12 00:03:41');
INSERT INTO `friend_request` VALUES (5, 12, 16, 1, '2022-11-18 16:56:00');
INSERT INTO `friend_request` VALUES (6, 14, 12, 1, '2022-11-18 17:39:00');
INSERT INTO `friend_request` VALUES (7, 16, 14, 0, '2022-11-18 17:40:00');
INSERT INTO `friend_request` VALUES (8, 16, 13, 0, '2022-11-18 17:40:00');
INSERT INTO `friend_request` VALUES (9, 11, 16, 1, '2022-11-18 17:43:00');
INSERT INTO `friend_request` VALUES (10, 11, 17, 0, '2022-11-18 17:44:00');
INSERT INTO `friend_request` VALUES (11, 11, 18, 1, '2022-11-18 17:44:00');
INSERT INTO `friend_request` VALUES (12, 21, 11, 1, '2022-11-18 18:29:00');

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
  `status` int NULL DEFAULT 1,
  `drop_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of fzu_group
-- ----------------------------
INSERT INTO `fzu_group` VALUES (1, 'G2211031923482', 'kkk', 'https://w.wallhaven.cc/full/7p/wallhaven-7p6jp3.png', '2022-11-06 12:06:05', '这个群主很懒什么也没留下', 1, NULL);
INSERT INTO `fzu_group` VALUES (2, 'G2211121923482', '操作系统 东三 钱牧', 'https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png', '2022-11-12 19:23:48', '这个是钱牧老师的操作系统教学群', 1, NULL);
INSERT INTO `fzu_group` VALUES (3, 'G2211141531474', '测试普通用户建群', 'https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png', '2022-11-14 15:31:47', '这个群主很懒什么也没留下', 1, NULL);
INSERT INTO `fzu_group` VALUES (4, 'G2211141800407', '添加删除群聊字段测试', 'https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png', '2022-11-14 18:00:41', '这个群主很懒什么也没留下', 1, NULL);
INSERT INTO `fzu_group` VALUES (5, 'G2211191503255', '离散数学 西一 吴阶', 'https://cdn.acwing.com/media/article/image/2022/11/12/87795_68611bda62-QQ%E5%9B%BE%E7%89%8720221112165243.png', '2022-11-19 15:03:26', '这个是吴阶老师的离散数学教学群', 1, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '发送加群请求表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_enter_request
-- ----------------------------
INSERT INTO `group_enter_request` VALUES (1, 18, 3, 1, '2022-11-14 16:23:05', '2022-11-15 10:50:26', 11);
INSERT INTO `group_enter_request` VALUES (2, 13, 2, 1, '2022-11-14 22:10:53', '2022-11-14 22:13:32', 12);
INSERT INTO `group_enter_request` VALUES (3, 14, 2, 1, '2022-11-14 22:10:54', '2022-11-14 22:13:32', 12);
INSERT INTO `group_enter_request` VALUES (4, 15, 2, 1, '2022-11-14 22:10:54', '2022-11-14 22:13:32', 12);
INSERT INTO `group_enter_request` VALUES (5, 12, 3, 0, '2022-11-15 14:02:16', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_member
-- ----------------------------
INSERT INTO `group_member` VALUES (1, 1, 11, 'common', 0);
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
INSERT INTO `group_member` VALUES (14, 4, 12, 'common', 1);
INSERT INTO `group_member` VALUES (15, 4, 13, 'common', 1);
INSERT INTO `group_member` VALUES (16, 4, 14, 'common', 1);
INSERT INTO `group_member` VALUES (17, 4, 15, 'common', 1);
INSERT INTO `group_member` VALUES (18, 4, 16, 'admin', 1);
INSERT INTO `group_member` VALUES (19, 2, 13, 'common', 1);
INSERT INTO `group_member` VALUES (20, 2, 14, 'common', 1);
INSERT INTO `group_member` VALUES (21, 2, 15, 'common', 1);
INSERT INTO `group_member` VALUES (22, 3, 18, 'common', 1);
INSERT INTO `group_member` VALUES (23, 5, 11, 'common', 1);
INSERT INTO `group_member` VALUES (24, 5, 13, 'admin', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group_message
-- ----------------------------
INSERT INTO `group_message` VALUES (6, 11, 1, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-11 12:59:43', '', 'text', '我是赵谦，在发群消息', '{\"isRead\":[11,12,13]}');
INSERT INTO `group_message` VALUES (7, 13, 1, '吴阶', 'https://tupian.qqw21.com/article/UploadPic/2020-1/202012921334864995.jpg', '2022-11-11 13:17:26', '', 'text', '我是吴阶，在发群消息', '{\"isRead\":[11,12,13]}');
INSERT INTO `group_message` VALUES (8, 12, 2, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 17:29:32', '', 'text', '同学们，因为我个人原因，下周的操作系统课需要和模拟电路课调换一下顺序，请同学们注意通知，收到请回复！', '{\"isRead\":[12]}');
INSERT INTO `group_message` VALUES (9, 14, 2, '张二', 'https://tupian.qqw21.com/article/UploadPic/2020-1/202011121502998047.jpg', '2022-11-18 17:38:49', '', 'text', '同学们，注意书本不要带错', '{\"isRead\":[12]}');
INSERT INTO `group_message` VALUES (10, 16, 2, '张大彪', 'https://cdn.acwing.com/media/user/profile/photo/111430_lg_73934914fc.jpg', '2022-11-18 17:40:02', '', 'text', '收到', '{\"isRead\":[12]}');
INSERT INTO `group_message` VALUES (11, 11, 2, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-18 17:42:02', '', 'text', '收到', '{\"isRead\":[12]}');
INSERT INTO `group_message` VALUES (12, 11, 2, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-18 19:19:42', '', 'text', '大家好', '{\"isRead\":[11,12]}');
INSERT INTO `group_message` VALUES (13, 12, 2, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 19:19:46', '', 'text', '大家好', '{\"isRead\":[11,12]}');
INSERT INTO `group_message` VALUES (14, 11, 2, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-19 14:59:58', '', 'text', '好', '{\"isRead\":[11,12]}');

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
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of select_course
-- ----------------------------
INSERT INTO `select_course` VALUES (1, 1, 11);
INSERT INTO `select_course` VALUES (2, 2, 11);
INSERT INTO `select_course` VALUES (3, 1, 16);
INSERT INTO `select_course` VALUES (4, 1, 17);
INSERT INTO `select_course` VALUES (5, 1, 18);
INSERT INTO `select_course` VALUES (6, 4, 16);
INSERT INTO `select_course` VALUES (7, 6, 16);
INSERT INTO `select_course` VALUES (8, 4, 17);
INSERT INTO `select_course` VALUES (9, 6, 17);
INSERT INTO `select_course` VALUES (10, 7, 16);
INSERT INTO `select_course` VALUES (11, 7, 17);
INSERT INTO `select_course` VALUES (12, 7, 18);
INSERT INTO `select_course` VALUES (13, 5, 21);
INSERT INTO `select_course` VALUES (14, 5, 20);

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
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of single_message
-- ----------------------------
INSERT INTO `single_message` VALUES (31, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:32:38', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (32, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:33:46', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (33, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:36:54', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (34, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:37:19', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (37, 12, 11, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-10 17:38:45', '', 'text', '我是钱牧，正在发私聊消息', 1);
INSERT INTO `single_message` VALUES (38, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:38:59', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (39, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:39:02', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (40, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:40:52', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (46, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 17:43:26', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (47, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 18:17:08', '', 'text', '我是赵谦', 1);
INSERT INTO `single_message` VALUES (48, 12, 11, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-10 18:22:29', '', 'text', '我是钱牧，正在发私聊消息', 1);
INSERT INTO `single_message` VALUES (49, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 18:51:39', '', 'text', '我是赵谦', 0);
INSERT INTO `single_message` VALUES (50, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 18:53:00', '', 'text', '我是赵谦', 0);
INSERT INTO `single_message` VALUES (51, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-10 18:53:30', '', 'text', '我是赵谦', 0);
INSERT INTO `single_message` VALUES (52, 12, 18, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-10 17:38:45', '', 'text', '同学，这周操作系统有实验课吗？', 0);
INSERT INTO `single_message` VALUES (53, 11, 18, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-15 18:53:30', '', 'text', '同学，这周操作系统有实验课吗？', 0);
INSERT INTO `single_message` VALUES (55, 12, 14, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 17:27:12', '', 'text', '张老师好，我是操作系统的钱牧', 0);
INSERT INTO `single_message` VALUES (56, 12, 14, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 17:28:01', '', 'text', '想和你商量一下下周的课和你的模电课调换一下可以吗', 0);
INSERT INTO `single_message` VALUES (57, 12, 14, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 17:28:14', '', 'text', '我有点事情不是很方便', 0);
INSERT INTO `single_message` VALUES (58, 14, 12, '张二', 'https://tupian.qqw21.com/article/UploadPic/2020-1/202011121502998047.jpg', '2022-11-18 17:38:00', '', 'text', '当然可以啊，钱老师', 1);
INSERT INTO `single_message` VALUES (59, 16, 12, '张大彪', 'https://cdn.acwing.com/media/user/profile/photo/111430_lg_73934914fc.jpg', '2022-11-18 17:40:38', '', 'text', '老师好，我想问下这周操作系统有实验吗？', 1);
INSERT INTO `single_message` VALUES (60, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-11 17:43:30', '', 'text', '老师好，很高兴能成为老师的学生', 0);
INSERT INTO `single_message` VALUES (61, 18, 11, '孙正义', 'https://tupian.qqw21.com/article/UploadPic/2020-3/20203162351455618.jpg', '2022-11-18 17:46:09', '', 'text', '我也不知道，不好意思', 0);
INSERT INTO `single_message` VALUES (62, 11, 16, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-15 17:47:31', '', 'text', '同学，这周操作系统有实验课吗，我上课时没注意听？', 0);
INSERT INTO `single_message` VALUES (63, 12, 11, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 17:49:29', '', 'text', '赵同学，共勉！', 0);
INSERT INTO `single_message` VALUES (64, 12, 16, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 17:50:08', '', 'text', '没有的，因为实验环境的问题，实验课放到下周了', 0);
INSERT INTO `single_message` VALUES (65, 16, 12, '张大彪', 'https://cdn.acwing.com/media/user/profile/photo/111430_lg_73934914fc.jpg', '2022-11-18 17:53:20', '', 'text', '好的，谢谢老师', 1);
INSERT INTO `single_message` VALUES (66, 16, 11, '张大彪', 'https://cdn.acwing.com/media/user/profile/photo/111430_lg_73934914fc.jpg', '2022-11-18 17:54:37', '', 'text', '老师说没有，下周才上', 0);
INSERT INTO `single_message` VALUES (67, 16, 11, '张大彪', 'https://cdn.acwing.com/media/user/profile/photo/111430_lg_73934914fc.jpg', '2022-11-18 17:55:02', '', 'text', '不好意思，同学之前没看消息，现在才回', 0);
INSERT INTO `single_message` VALUES (68, 16, 11, '张大彪', 'https://cdn.acwing.com/media/user/profile/photo/111430_lg_73934914fc.jpg', '2022-11-18 17:55:09', '', 'text', '老师说没有的', 0);
INSERT INTO `single_message` VALUES (69, 11, 16, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-18 17:58:10', '', 'text', '没事的，谢谢同学', 0);
INSERT INTO `single_message` VALUES (71, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-18 19:18:23', '', 'text', '老师好', 1);
INSERT INTO `single_message` VALUES (72, 12, 11, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-18 19:18:29', '', 'text', '同学好', 1);
INSERT INTO `single_message` VALUES (74, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-19 10:15:01', '', 'text', '你好', 1);
INSERT INTO `single_message` VALUES (75, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-19 14:46:12', '', 'text', '你好', 1);
INSERT INTO `single_message` VALUES (76, 12, 11, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-19 14:46:15', '', 'text', '你好', 1);
INSERT INTO `single_message` VALUES (77, 11, 12, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-19 14:59:34', '', 'text', '老师好', 1);
INSERT INTO `single_message` VALUES (78, 12, 11, '钱牧', 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '2022-11-19 14:59:40', '', 'text', '同学好', 1);
INSERT INTO `single_message` VALUES (79, 11, 21, '赵谦', 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '2022-11-19 15:00:44', '', 'text', '你好', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tutor
-- ----------------------------
INSERT INTO `tutor` VALUES (1, 13, 17, '2022-11-13 15:47:09');
INSERT INTO `tutor` VALUES (3, 12, 18, '2022-11-15 22:11:52');
INSERT INTO `tutor` VALUES (4, 12, 11, '2022-11-19 15:01:40');


DROP TABLE IF EXISTS `group`;

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tutor_request
-- ----------------------------
INSERT INTO `tutor_request` VALUES (2, 16, 14, 0, '2022-11-11 23:53:46');
INSERT INTO `tutor_request` VALUES (3, 18, 15, 2, '2022-11-13 15:48:29');
INSERT INTO `tutor_request` VALUES (4, 17, 13, 1, '2022-11-15 22:10:44');
INSERT INTO `tutor_request` VALUES (5, 18, 12, 1, '2022-11-15 22:12:11');
INSERT INTO `tutor_request` VALUES (6, 21, 22, 0, '2022-11-18 18:49:29');
INSERT INTO `tutor_request` VALUES (9, 11, 12, 1, '2022-11-19 15:01:23');

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '张三', '$2a$10$IcYbYb0GSaL.WS17fbvDEegjMwC5qywGzL608HGUE8BZY5WaNHl.y', 0, 'https://cdn.acwing.com/media/user/profile/photo/149957_lg_de39db8b80.jpg', NULL, NULL, ' 我是超级管理员', 0);
INSERT INTO `user` VALUES (11, 'S2211032345231', '赵谦', '$2a$10$uE2dnXZZf4PdsAy7rI4JpeEf1kROYlos6WfOC6lDNt07OIU03BCg6', 2, 'https://tupian.qqw21.com/article/UploadPic/2021-4/202141120403391814.jpg', '电气工程与自动化学院', '电气工程与自动化', 'https://bbs.csdn.net/topics/608312514', 0);
INSERT INTO `user` VALUES (12, 'T2211032345497', '钱牧', '$2a$10$wnWgUZQo7uY3q6raeZ0RlO6hWRgq9eXLlMJym5eh5F7Y6jdG6fnKe', 1, 'http://5b0988e595225.cdn.sohucs.com/images/20190626/7af6aa2e8eaa4671a21a5872be093198.jpeg', '电气工程与自动化学院', '电气工程与自动化', 'https://csip.fzu.edu.cn/?page_id=636', 0);
INSERT INTO `user` VALUES (13, 'T2211061442486', '吴阶', '$2a$10$wT/iugV3OVIBmNhbc8DZtOBIF5IqArAUcRhIbDV.opIGjO77Dk2De', 1, 'https://tupian.qqw21.com/article/UploadPic/2020-1/202012921334864995.jpg', '电气工程与自动化学院', '人工智能', 'https://csip.fzu.edu.cn/?page_id=636', 0);
INSERT INTO `user` VALUES (14, 'T2211111512524', '张二', '$2a$10$0OkSj5vsGLV8Eez18iq4zeCPL3YYwUTspKpSN9cfv2tPwG5RVFdHe', 1, 'https://tupian.qqw21.com/article/UploadPic/2020-1/202011121502998047.jpg', '电气工程与自动化学院', '', 'https://csip.fzu.edu.cn/?page_id=636', 0);
INSERT INTO `user` VALUES (15, 'T2211111633052', '张三丰', '$2a$10$kPieo3eI/v8JVXYj1mjg/uRPO5tt2mi4IKkcttvi8sFwglEcPRCMe', 1, 'https://tupian.qqw21.com/article/UploadPic/2020-1/202012921334896237.jpg', '计算机与大数据学院', '', 'https://csip.fzu.edu.cn/?page_id=636', 0);
INSERT INTO `user` VALUES (16, 'S2211121150592', '张大彪', '$2a$10$nhECOxbCRWbq.lu7cOm9X.2SIvuzkSSVhTD6nCdqt0bvqOMmXE2s.', 2, 'https://cdn.acwing.com/media/user/profile/photo/111430_lg_73934914fc.jpg', '计算机与大数据学院', '人工智能', 'https://bbs.csdn.net/topics/608312514', 0);
INSERT INTO `user` VALUES (17, 'S2211121151460', '柱子', '$2a$10$BEKwZnDcnvto8Q8rG2M2fOgYCBY1Q44EXxK77GgHxk06I3Sae1rni', 2, 'https://tupian.qqw21.com/article/UploadPic/2019-5/201951113193417891.jpeg', '计算机与大数据学院', '计算机科学与技术', 'https://bbs.csdn.net/topics/608312514', 0);
INSERT INTO `user` VALUES (18, 'S2211121152050', '孙正义', '$2a$10$qMfNt.UNwV3XaS97gAqu2OLEj/ehG8ldfeRI0CkEkHPACAoT8oV4G', 2, 'https://tupian.qqw21.com/article/UploadPic/2020-3/20203162351455618.jpg', '计算机与大数据学院', '大数据科学', 'https://bbs.csdn.net/topics/608312514', 0);
INSERT INTO `user` VALUES (19, 'S2211181633544', '石征', '$2a$10$KEgJfie8hzwHyljoW4i3N.5Pscj6f1Pc7x.iC7FCuMyNoHRqxWfGu', 2, 'http://tupian.qqw21.com/article/UploadPic/2013-4/20134178101368214.jpg', '计算机与大数据学院', '计算机科学与技术', 'https://www.cnblogs.com/xuanyuan/', 0);
INSERT INTO `user` VALUES (20, 'S2211181635211', '齐兰', '$2a$10$u/AzU.eoiYrEmGxf2EWnFODT9//NMosgBcUumJA6nYqHbFpUNO6l.', 2, 'https://www.nitutu.com/uploads/allimg/200407/110GL230-14.jpg', '计算机与大数据学院', '软件理论', 'https://www.cnblogs.com/xuanyuan/', 0);
INSERT INTO `user` VALUES (21, 'S2211181636431', '金哲', '$2a$10$h6wfs04cgEL.L0M22CPicO.Jj2cl4UsrGlTR3yXy10ZpPfBPGaCiy', 2, 'https://tupian.qqw21.com/article/UploadPic/2020-3/202031522292042080.jpg', '计算机与大数据学院', '网络工程', 'https://www.cnblogs.com/xuanyuan/', 0);
INSERT INTO `user` VALUES (22, 'T2211181839023', '蒋一强', '$2a$10$qJsnjBd9hjwZ/KlHdvuL6O3Di8j6NyyI8qorGzmVBXtaYDsbSHSja', 1, 'http://www.gx8899.com/uploads/allimg/2018041608/1ogdijgczzi.jpg', '计算机与大数据学院', '网络工程', 'https://csip.fzu.edu.cn/?page_id=636', 0);
INSERT INTO `user` VALUES (23, 'T2211181912013', '王强', '$2a$10$SX0ivqrpo/vIqGElaTN7TeQnAXs2YnAc62jT6ousCJ6/afMTU9h72', 1, 'https://cdn.acwing.com/media/user/profile/photo/232787_lg_b913a93e45.jpg', '电气工程与自动化学院', '电气工程与自动化', 'https://csip.fzu.edu.cn/?page_id=636', 0);
INSERT INTO `user` VALUES (24, 'T2211181912128', '刘红', '$2a$10$p5LedKX32a9lxI6AmwwTyeUJ2T2qMfN5ZvLTc8LP9gJ4Bgvoq/.Jm', 1, 'https://cdn.acwing.com/media/user/profile/photo/244990_lg_dce68bf764.jpg', '电气工程与自动化学院', '电气工程与自动化', 'https://csip.fzu.edu.cn/?page_id=636', 0);
INSERT INTO `user` VALUES (25, 'S2211191504141', '康凯', '$2a$10$vpsMWszjsYuoSoJJaxyHQO9WDdQTUfxRbD2gITakt3r.BzUsQCYSS', 2, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '计算机与大数据学院', '计算机类', 'https://www.cnblogs.com/xuanyuan/', 0);

SET FOREIGN_KEY_CHECKS = 1;
