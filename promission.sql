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

 Date: 14/12/2019 20:53:29
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '董事会');
INSERT INTO `department` VALUES (2, '技术部');
INSERT INTO `department` VALUES (3, '研发部');
INSERT INTO `department` VALUES (4, '财务部');
INSERT INTO `department` VALUES (5, '人力部');
INSERT INTO `department` VALUES (6, '法务部');
INSERT INTO `department` VALUES (7, '监管部');

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
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'lzb', '2017-01-09 19:49:12', '18027634208', '125586362@qq.com', 1, 1, 1, NULL);
INSERT INTO `employee` VALUES (2, 'admin', '2019-11-11 00:00:00', '12345678910', 'admin@163.com', 1, 1, 1, NULL);
INSERT INTO `employee` VALUES (3, '小明', '2018-02-01 08:03:18', '13666666666', 'xiaiming@qq.com', 1, 0, 2, NULL);
INSERT INTO `employee` VALUES (4, 'test1', '2019-12-05 21:39:56', '13244646666', 'test1@gmail.com', 1, 0, 3, NULL);
INSERT INTO `employee` VALUES (5, '1255863672', '2019-11-28 00:00:00', '16845987885', '1255863672@qq.com', 1, 0, 2, '1255863672');
INSERT INTO `employee` VALUES (6, '1255863672', '2019-11-28 00:00:00', '16845987885', '1255863672@qq.com', 1, 0, 2, '1255863672');
INSERT INTO `employee` VALUES (7, 'test1', '2018-02-14 00:00:00', '1', '2', 0, 0, 4, 'test1');
INSERT INTO `employee` VALUES (8, 'linjinhao', '2019-12-11 00:00:00', '1', '2', 1, 0, 2, 'linjinhaoshisb');
INSERT INTO `employee` VALUES (35, '1', '2019-12-14 00:00:00', '1', '1', 1, 0, 4, '1');
INSERT INTO `employee` VALUES (36, '2', '2019-12-05 00:00:00', '2', '2', 1, 0, 3, '2');
INSERT INTO `employee` VALUES (43, 'admin1', '2019-12-05 00:00:00', '123', '123', 1, 1, 1, 'admin1');
INSERT INTO `employee` VALUES (44, '3', '2019-11-30 00:00:00', '3', '3', 1, 0, 6, '3');
INSERT INTO `employee` VALUES (45, '4', '2019-11-27 00:00:00', '4', '4', 0, 0, 5, '4');
INSERT INTO `employee` VALUES (46, '5', '2019-12-01 00:00:00', '5', '5', 1, 0, 7, '5');
INSERT INTO `employee` VALUES (47, '6', '2019-12-14 00:00:00', '6', '6', 1, 0, 6, '6');
INSERT INTO `employee` VALUES (48, '7', '2019-12-03 00:00:00', '7', '7', 1, 0, 6, '7');
INSERT INTO `employee` VALUES (49, '8', '2019-12-14 00:00:00', '8', '8', 1, 0, 5, '8');
INSERT INTO `employee` VALUES (50, '9', '2019-03-13 00:00:00', '9', '9', 1, 0, 5, '9');
INSERT INTO `employee` VALUES (51, '10', '2018-11-08 00:00:00', '10', '10', 1, 0, 3, '10');
INSERT INTO `employee` VALUES (52, '11', '2019-12-18 00:00:00', '11', '11', 1, 0, 2, '11');
INSERT INTO `employee` VALUES (53, '12', '2019-12-11 00:00:00', '12', '12', 1, 0, 4, '12');
INSERT INTO `employee` VALUES (54, '13', '2019-01-02 00:00:00', '13', '13', 1, 0, 4, '12');
INSERT INTO `employee` VALUES (55, '14', '2019-12-05 00:00:00', '14', '14', 1, 0, 5, '14');

SET FOREIGN_KEY_CHECKS = 1;
