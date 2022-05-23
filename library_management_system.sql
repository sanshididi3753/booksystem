/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : library_management_system

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 23/05/2022 22:14:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int(0) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`, `admin_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456');
INSERT INTO `admin` VALUES (2, 'root', '123456');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(0) NOT NULL AUTO_INCREMENT,
  `book_isbn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ISDN号',
  `book_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书名',
  `book_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `book_time` date NULL DEFAULT NULL COMMENT '出版日期',
  `book_publish` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `book_type` int(0) NULL DEFAULT NULL COMMENT '图书类别',
  `book_num` int(0) NULL DEFAULT NULL COMMENT '总量',
  `book_inventory` int(0) NULL DEFAULT NULL COMMENT '库存量',
  `book_area` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '藏书区域',
  `book_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `book_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`book_id`, `book_isbn`) USING BTREE,
  INDEX `book_type`(`book_type`) USING BTREE,
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`book_type`) REFERENCES `book_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '', 'java编程思想', '埃克尔', NULL, '机械工业', 1, NULL, NULL, NULL, '无', NULL);
INSERT INTO `book` VALUES (3, '', 'java编程思想', '埃克尔', NULL, '机械工业', 1, NULL, NULL, NULL, '无', NULL);
INSERT INTO `book` VALUES (4, '', 'java编程思想', '埃克尔', NULL, '机械工业', 1, NULL, NULL, NULL, '无', NULL);
INSERT INTO `book` VALUES (5, '', 'C++ Primer Plus', '普拉塔', NULL, '人民邮电出版社', 1, NULL, NULL, NULL, '无', NULL);
INSERT INTO `book` VALUES (6, '', 'C++ Primer Plus', '普拉塔', NULL, '人民邮电出版社', 1, NULL, NULL, NULL, '无', NULL);
INSERT INTO `book` VALUES (7, '', '新概念英语', '外语教学与研究出版社', NULL, '亚历山大', 6, NULL, NULL, NULL, '无', NULL);
INSERT INTO `book` VALUES (8, '', '纯粹理性批判', '康德', NULL, '人民出版社', 7, NULL, NULL, NULL, '无', NULL);
INSERT INTO `book` VALUES (9, '', '毛泽东选集', '毛泽东', NULL, '人民出版社', 7, NULL, NULL, NULL, '无', NULL);

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `type_id` int(0) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES (1, '计算机');
INSERT INTO `book_type` VALUES (6, '英语');
INSERT INTO `book_type` VALUES (7, '哲学');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `book_id` int(0) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `return_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `book_id`(`book_id`) USING BTREE,
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------

-- ----------------------------
-- Table structure for reader_info
-- ----------------------------
DROP TABLE IF EXISTS `reader_info`;
CREATE TABLE `reader_info`  (
  `user_id` int(0) NOT NULL COMMENT '读者id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader_info
-- ----------------------------

-- ----------------------------
-- Table structure for superadmin
-- ----------------------------
DROP TABLE IF EXISTS `superadmin`;
CREATE TABLE `superadmin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of superadmin
-- ----------------------------
INSERT INTO `superadmin` VALUES (1, '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_email` varchar(320) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `confirmCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '确认码',
  `activationTime` datetime(0) NULL DEFAULT NULL COMMENT '激活失效时间',
  `is_valid` tinyint(1) NULL DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (27, '1', '@1234', '1526569889144377344', '2022-05-18 22:26:39', 1);
INSERT INTO `user` VALUES (29, '1', '123', '1526916071142068224', '2022-05-19 21:22:15', 1);
INSERT INTO `user` VALUES (30, '1', '@123456', '1526933251384545280', '2022-05-19 22:30:31', 1);
INSERT INTO `user` VALUES (31, '1', '123456', '1526948765297152000', '2022-05-19 23:32:10', 1);
INSERT INTO `user` VALUES (32, '1', '123456', '1526948772121284608', '2022-05-19 23:32:11', 1);
INSERT INTO `user` VALUES (33, '1', '123456', '1526948778689564672', '2022-05-19 23:32:13', 0);
INSERT INTO `user` VALUES (47, '1', '1@1234', '1527615798984708096', '2022-05-21 19:42:43', 0);
INSERT INTO `user` VALUES (48, '1', '12@1234', '1527617290193997824', '2022-05-21 19:48:38', 0);

SET FOREIGN_KEY_CHECKS = 1;
