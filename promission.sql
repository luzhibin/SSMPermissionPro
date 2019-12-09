/*
 Navicat Premium Data Transfer

 Source Server         : connection
 Source Server Type    : MySQL
 Source Server Version : 80014
 Source Host           : localhost:3306
 Source Schema         : promission

 Target Server Type    : MySQL
 Target Server Version : 80014
 File Encoding         : 65001

 Date: 09/12/2019 20:19:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '管理员');
INSERT INTO `department` VALUES (2, '技术部');
INSERT INTO `department` VALUES (3, '研发部');
INSERT INTO `department` VALUES (4, '财务部');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inputtime` datetime(0) NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(1) NULL DEFAULT NULL,
  `admin` tinyint(1) NULL DEFAULT NULL,
  `dep_id` bigint(20) NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `employee_department`(`dep_id`) USING BTREE,
  CONSTRAINT `employee_department` FOREIGN KEY (`dep_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'lzb', '2017-01-09 19:49:12', '18027634208', '125586362@qq.com', 1, 1, 1, NULL);
INSERT INTO `employee` VALUES (2, 'admin', '2019-11-11 19:02:30', '12345678910', 'admin@163.com', 1, 1, 1, NULL);
INSERT INTO `employee` VALUES (3, '小明', '2018-02-01 08:03:18', '13666666666', 'xiaiming@qq.com', 0, 0, 2, NULL);
INSERT INTO `employee` VALUES (4, 'test1', '2019-12-05 21:39:56', '13244646666', 'test1@gmail.com', 1, 0, 3, NULL);
INSERT INTO `employee` VALUES (5, '1255863672', '2019-11-28 00:00:00', '16845987885', '1255863672@qq.com', NULL, 0, 2, '1255863672');
INSERT INTO `employee` VALUES (6, '1255863672', '2019-11-28 00:00:00', '16845987885', '1255863672@qq.com', NULL, 0, 2, '1255863672');

SET FOREIGN_KEY_CHECKS = 1;
