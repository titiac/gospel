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

 Date: 09/11/2022 19:26:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `belong_user` int NULL DEFAULT NULL,
  `friend` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '好友表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contact
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (1, 'kkk', 'https://w.wallhaven.cc/full/7p/wallhaven-7p6jp3.png', '2022-11-06 12:06:05', '这个群主很懒什么也没留下');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '群成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_member
-- ----------------------------
INSERT INTO `group_member` VALUES (1, 1, 11, NULL, 1);
INSERT INTO `group_member` VALUES (2, 1, 12, NULL, 1);
INSERT INTO `group_member` VALUES (3, 1, 13, 'admin', 1);

-- ----------------------------
-- Table structure for single_message
-- ----------------------------
DROP TABLE IF EXISTS `single_message`;
CREATE TABLE `single_message`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` int NULL DEFAULT NULL,
  `user_from` int NULL DEFAULT NULL,
  `user_to` int NULL DEFAULT NULL,
  `send_time` datetime NULL DEFAULT NULL,
  `file_raw_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `message` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `message_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flag` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_read` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '私聊消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of single_message
-- ----------------------------
INSERT INTO `single_message` VALUES (7, NULL, 11, 12, '2022-09-01 12:13:34', NULL, 'hello', 'text', 'single', 1);
INSERT INTO `single_message` VALUES (8, NULL, 11, 12, '2022-10-01 12:13:34', NULL, 'hello', 'text', 'single', 0);
INSERT INTO `single_message` VALUES (9, NULL, 11, 12, '2022-10-01 12:13:34', NULL, 'hello', 'text', 'single', 0);
INSERT INTO `single_message` VALUES (10, 1, 11, NULL, '2022-10-01 12:13:34', NULL, 'hello', 'text', 'group', 0);
INSERT INTO `single_message` VALUES (11, 1, 11, NULL, '2022-10-01 12:13:34', NULL, 'hello', 'text', 'group', 0);
INSERT INTO `single_message` VALUES (12, 1, 11, NULL, '2022-10-01 12:13:34', NULL, 'hello', 'text', 'group', 0);
INSERT INTO `single_message` VALUES (13, 1, 11, NULL, '2022-10-01 12:13:34', NULL, 'hello', 'text', 'group', 0);

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
  `profile` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `status` int NULL DEFAULT NULL COMMENT '登录状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '张三', '$2a$10$IcYbYb0GSaL.WS17fbvDEegjMwC5qywGzL608HGUE8BZY5WaNHl.y', 0, 'https://cdn.acwing.com/media/user/profile/photo/149957_lg_de39db8b80.jpg', ' 我是超级管理员', NULL);
INSERT INTO `user` VALUES (11, 'S2211032345231', '赵谦', '$2a$10$uE2dnXZZf4PdsAy7rI4JpeEf1kROYlos6WfOC6lDNt07OIU03BCg6', 2, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (12, 'T2211032345497', '钱牧', '$2a$10$wnWgUZQo7uY3q6raeZ0RlO6hWRgq9eXLlMJym5eh5F7Y6jdG6fnKe', 1, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '这个用户很懒，什么也没留下', 0);
INSERT INTO `user` VALUES (13, 'T2211061442486', '吴阶', '$2a$10$wT/iugV3OVIBmNhbc8DZtOBIF5IqArAUcRhIbDV.opIGjO77Dk2De', 1, 'https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png', '这个用户很懒，什么也没留下', 0);

SET FOREIGN_KEY_CHECKS = 1;
