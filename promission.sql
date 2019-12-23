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

 Date: 23/12/2019 14:46:39
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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '董事会');
INSERT INTO `department` VALUES (2, '决策运营部');
INSERT INTO `department` VALUES (3, '技术研发部');
INSERT INTO `department` VALUES (4, '人力资源部');
INSERT INTO `department` VALUES (5, '财务管理部');
INSERT INTO `department` VALUES (6, '市场营销部');
INSERT INTO `department` VALUES (7, '法律事务部');
INSERT INTO `department` VALUES (8, '安全监管部');
INSERT INTO `department` VALUES (9, '生产制造部');
INSERT INTO `department` VALUES (10, '后勤保卫部');

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
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (58, 'admin', '2010-06-15 00:00:00', '15009665652', 'admin@qq.com', 1, 1, 2, 'admin');
INSERT INTO `employee` VALUES (59, 'admin1', '2014-07-17 00:00:00', '18741563253', 'admin1@163.com', 1, 1, 2, 'admin1');
INSERT INTO `employee` VALUES (60, '1255863672', '2011-01-21 00:00:00', '1255863672', '1255863672@qq.com', 1, 0, 3, '1255863672');
INSERT INTO `employee` VALUES (61, '总经理A', '2010-01-01 00:00:00', '16315947568', '16315947568@gmail.com', 1, 1, 1, 'jingli');
INSERT INTO `employee` VALUES (62, '副总经理A', '2010-07-07 00:00:00', '12574779535 ', '12574779535 @163.com', 1, 1, 1, '123456');
INSERT INTO `employee` VALUES (63, '副总经理B', '2011-04-28 00:00:00', '18461861633 ', '18461861633 @email.com', 1, 1, 1, '123456');
INSERT INTO `employee` VALUES (64, '总监A', '2013-03-27 00:00:00', '13863850178 ', '13863850178 @email.com', 1, 1, 2, 'zongjian1');
INSERT INTO `employee` VALUES (65, '技术研发部总监C', '2015-05-14 00:00:00', '17469659497 ', '17469659497@email.com', 1, 1, 3, 'zongjian2');
INSERT INTO `employee` VALUES (66, '人力资源部总监A', '2015-05-11 00:00:00', '16953694552 ', '16953694552@email.com', 1, 1, 4, '16953694552 ');
INSERT INTO `employee` VALUES (67, '市场营销部总监A', '2015-06-23 00:00:00', '13132699777', '13132699777@email.com', 1, 0, 6, '13132699777');
INSERT INTO `employee` VALUES (68, '小明', '2018-08-01 00:00:00', '14257106833', '14257106833email', 1, 0, 2, 'xiaoming');
INSERT INTO `employee` VALUES (69, '小明的爸爸', '2016-12-22 00:00:00', '13815521134', '13815521134@12.com', 1, 0, 2, '123456');
INSERT INTO `employee` VALUES (70, '小红的奶奶', '2011-10-22 00:00:00', '18166339044', '18166339044@emial.com', 0, 0, 4, 'xiaohong');
INSERT INTO `employee` VALUES (71, '小红的妈妈', '2014-04-26 00:00:00', '18893964684', '18893964684', 1, 0, 4, '18893964684');
INSERT INTO `employee` VALUES (72, '小红', '2018-12-14 00:00:00', '15757330437', '15757330437', 1, 0, 4, '15757330437');
INSERT INTO `employee` VALUES (73, '苦逼程序猿A1', '2017-11-08 00:00:00', '18856498891', '18856498891@email.com', 1, 0, 3, '18856498891');
INSERT INTO `employee` VALUES (74, '苦逼程序猿B2', '2017-11-25 00:00:00', '18329093494', '18329093494@email.com', 0, 0, 3, '18329093494');
INSERT INTO `employee` VALUES (75, '苦逼程序猿C3', '2017-03-14 00:00:00', '19515268726', '19515268726', 1, 0, 3, '19515268726');
INSERT INTO `employee` VALUES (76, '苦逼程序猿D4', '2018-05-31 00:00:00', '16937450316', '16937450316', 1, 0, 3, '16937450316');
INSERT INTO `employee` VALUES (77, '苦逼程序猿E5', '2019-10-15 00:00:00', '14019232040', '14019232040', 1, 0, 3, '14019232040');
INSERT INTO `employee` VALUES (78, '17665641761', '2017-06-14 00:00:00', '17665641761', '17665641761', 1, 0, 4, '117665641761');
INSERT INTO `employee` VALUES (79, '16522854429', '2017-05-16 00:00:00', '16522854429', '16522854429', 1, 0, 4, '16522854429');
INSERT INTO `employee` VALUES (80, '1', '2018-02-26 00:00:00', '1', '1', 1, 0, 9, '1');
INSERT INTO `employee` VALUES (81, '2', '2019-10-16 00:00:00', '2', '2', 1, 0, 5, '2');
INSERT INTO `employee` VALUES (82, '3', '2017-08-20 00:00:00', '17288518738', '17288518738@email', 1, 0, 5, '3');
INSERT INTO `employee` VALUES (83, '4', '2019-02-20 00:00:00', '4', '4', 1, 0, 6, '4');
INSERT INTO `employee` VALUES (84, '5', '2017-02-16 00:00:00', '5', '5', 1, 0, 6, '5');
INSERT INTO `employee` VALUES (85, '6', '2016-06-23 00:00:00', '19394901085', '19394901085 ', 1, 0, 7, '6');
INSERT INTO `employee` VALUES (86, '7', '2019-10-16 00:00:00', '15210836295', '15210836295', 1, 0, 5, '7');
INSERT INTO `employee` VALUES (87, '8', '2019-05-02 00:00:00', '88489456', '8489456', 1, 0, 6, '8489456');

-- ----------------------------
-- Table structure for employee_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `employee_role_rel`;
CREATE TABLE `employee_role_rel`  (
  `eid` bigint(20) NOT NULL,
  `rid` bigint(20) NOT NULL,
  PRIMARY KEY (`eid`, `rid`) USING BTREE,
  INDEX `rid`(`rid`) USING BTREE,
  CONSTRAINT `employee_role_rel_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_role_rel_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_role_rel
-- ----------------------------
INSERT INTO `employee_role_rel` VALUES (58, 1);
INSERT INTO `employee_role_rel` VALUES (59, 1);
INSERT INTO `employee_role_rel` VALUES (61, 1);
INSERT INTO `employee_role_rel` VALUES (62, 1);
INSERT INTO `employee_role_rel` VALUES (63, 1);
INSERT INTO `employee_role_rel` VALUES (64, 1);
INSERT INTO `employee_role_rel` VALUES (65, 1);
INSERT INTO `employee_role_rel` VALUES (66, 1);
INSERT INTO `employee_role_rel` VALUES (61, 2);
INSERT INTO `employee_role_rel` VALUES (62, 2);
INSERT INTO `employee_role_rel` VALUES (63, 2);
INSERT INTO `employee_role_rel` VALUES (60, 3);
INSERT INTO `employee_role_rel` VALUES (64, 3);
INSERT INTO `employee_role_rel` VALUES (65, 3);
INSERT INTO `employee_role_rel` VALUES (66, 3);
INSERT INTO `employee_role_rel` VALUES (67, 3);
INSERT INTO `employee_role_rel` VALUES (69, 4);
INSERT INTO `employee_role_rel` VALUES (70, 4);
INSERT INTO `employee_role_rel` VALUES (78, 4);
INSERT INTO `employee_role_rel` VALUES (84, 4);
INSERT INTO `employee_role_rel` VALUES (85, 4);
INSERT INTO `employee_role_rel` VALUES (68, 5);
INSERT INTO `employee_role_rel` VALUES (71, 5);
INSERT INTO `employee_role_rel` VALUES (83, 5);
INSERT INTO `employee_role_rel` VALUES (72, 27);
INSERT INTO `employee_role_rel` VALUES (73, 27);
INSERT INTO `employee_role_rel` VALUES (79, 27);
INSERT INTO `employee_role_rel` VALUES (81, 27);
INSERT INTO `employee_role_rel` VALUES (74, 28);
INSERT INTO `employee_role_rel` VALUES (75, 28);
INSERT INTO `employee_role_rel` VALUES (76, 28);
INSERT INTO `employee_role_rel` VALUES (80, 28);
INSERT INTO `employee_role_rel` VALUES (82, 28);
INSERT INTO `employee_role_rel` VALUES (86, 28);
INSERT INTO `employee_role_rel` VALUES (87, 28);
INSERT INTO `employee_role_rel` VALUES (77, 30);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `presource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '添加员工', 'employee:add');
INSERT INTO `permission` VALUES (2, '删除员工', 'employee:delete');
INSERT INTO `permission` VALUES (3, '编辑员工', 'employee:edit');
INSERT INTO `permission` VALUES (4, '员工主页', 'employee:index');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rnum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'Admin', '管理员');
INSERT INTO `role` VALUES (2, 'Manager', '经理');
INSERT INTO `role` VALUES (3, 'Director ', '总监');
INSERT INTO `role` VALUES (4, 'SUP', '主管');
INSERT INTO `role` VALUES (5, 'Minister', '部长');
INSERT INTO `role` VALUES (27, 'Group leader', '组长');
INSERT INTO `role` VALUES (28, '普通员工', '普通员工');
INSERT INTO `role` VALUES (29, '临时工', '临时工');
INSERT INTO `role` VALUES (30, '实习生', '实习生');
INSERT INTO `role` VALUES (31, 'HR', '人事');

-- ----------------------------
-- Table structure for role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel`  (
  `rid` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  PRIMARY KEY (`rid`, `pid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `role_permission_rel_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_rel_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `permission` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------
INSERT INTO `role_permission_rel` VALUES (1, 1);
INSERT INTO `role_permission_rel` VALUES (2, 1);
INSERT INTO `role_permission_rel` VALUES (3, 1);
INSERT INTO `role_permission_rel` VALUES (4, 1);
INSERT INTO `role_permission_rel` VALUES (31, 1);
INSERT INTO `role_permission_rel` VALUES (1, 2);
INSERT INTO `role_permission_rel` VALUES (2, 2);
INSERT INTO `role_permission_rel` VALUES (3, 2);
INSERT INTO `role_permission_rel` VALUES (31, 2);
INSERT INTO `role_permission_rel` VALUES (1, 3);
INSERT INTO `role_permission_rel` VALUES (2, 3);
INSERT INTO `role_permission_rel` VALUES (3, 3);
INSERT INTO `role_permission_rel` VALUES (4, 3);
INSERT INTO `role_permission_rel` VALUES (5, 3);
INSERT INTO `role_permission_rel` VALUES (27, 3);
INSERT INTO `role_permission_rel` VALUES (31, 3);
INSERT INTO `role_permission_rel` VALUES (1, 4);
INSERT INTO `role_permission_rel` VALUES (2, 4);
INSERT INTO `role_permission_rel` VALUES (3, 4);
INSERT INTO `role_permission_rel` VALUES (4, 4);
INSERT INTO `role_permission_rel` VALUES (5, 4);
INSERT INTO `role_permission_rel` VALUES (27, 4);
INSERT INTO `role_permission_rel` VALUES (28, 4);
INSERT INTO `role_permission_rel` VALUES (31, 4);

SET FOREIGN_KEY_CHECKS = 1;
