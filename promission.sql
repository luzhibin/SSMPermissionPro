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

 Date: 05/12/2019 18:23:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'lzb', '2017-01-09 19:49:12', '18027634208', '125586362@qq.com', 1, 1, NULL);
INSERT INTO `employee` VALUES (2, 'admin', '2019-11-11 19:02:30', '12345678910', 'admin@163.com', 1, 1, NULL);
INSERT INTO `employee` VALUES (3, '小明', '2018-02-01 08:03:18', '13666666666', 'xiaiming@qq.com', 0, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
