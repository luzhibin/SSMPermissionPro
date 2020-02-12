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

 Date: 12/02/2020 23:24:00
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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (58, 'admin', '2010-06-15 00:00:00', '15009665652', 'admin@qq.com', 1, 1, 1, '3ef7164d1f6167cb9f2658c07d3c2f0a');
INSERT INTO `employee` VALUES (59, 'admin1', '2014-07-16 00:00:00', '18741563253', 'admin1@163.com', 1, 1, 2, '86572f34cab41b2993c86485b04d0a1a');
INSERT INTO `employee` VALUES (60, '1255863672', '2011-01-20 00:00:00', '1255863672', '1255863672@qq.com', 1, 0, 3, '7636301488c9171f6400e0115f995349');
INSERT INTO `employee` VALUES (61, '总经理A', '2010-01-01 00:00:00', '16315947568', '16315947568@gmail.com', 1, 1, 1, 'a2d2bb1ebe9373d6381f38006fd7d2ce');
INSERT INTO `employee` VALUES (62, '副总经理A', '2010-07-07 00:00:00', '12574779535 ', '12574779535 @163.com', 1, 0, 1, 'c8afc045601177f7f8d4d661974ea203');
INSERT INTO `employee` VALUES (63, '副总经理B', '2011-04-28 00:00:00', '18461861633 ', '18461861633 @email.com', 0, 0, 1, 'd970cade373fde9f55e071775bdccddc');
INSERT INTO `employee` VALUES (64, '总监A', '2013-03-27 00:00:00', '13863850178 ', '13863850178 @email.com', 1, 0, 2, '9754ce16c44ae900801ad347b4c435f6');
INSERT INTO `employee` VALUES (65, '技术研发部总监C', '2015-05-14 00:00:00', '17469659497 ', '17469659497@email.com', 1, 0, 3, '295fd97fae81e159a9553cfe0c041418');
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
INSERT INTO `employee` VALUES (87, '8', '2019-05-02 00:00:00', '88489456', '8489456', 1, 0, 6, '8');
INSERT INTO `employee` VALUES (88, '实习生A', '2019-12-03 00:00:00', '15842120928', '15842120928@qq.com', 1, 0, 9, '1');
INSERT INTO `employee` VALUES (89, '临时工1', '2020-01-17 00:00:00', '13156481651', '156s1d65@email.com', 1, 0, 9, '1111');
INSERT INTO `employee` VALUES (90, '测试1', '2020-01-21 00:00:00', 'ceshi1', 'ceshi1', 1, 0, 9, 'ceshi1');
INSERT INTO `employee` VALUES (91, '人力资源部主管B', '2018-10-24 00:00:00', '56489489', '495156465@qq.com', 1, 0, 4, '123456');
INSERT INTO `employee` VALUES (92, 'test12315', '2019-08-08 00:00:00', '18741563253', '1733918267@gmail.com', 1, 0, 8, '0576bacb4248d415728f99cd73586fef');
INSERT INTO `employee` VALUES (93, 'test12316', '2020-01-01 00:00:00', 'test12316', 'test12316', 1, 0, 5, 'e263112cba4a2b1339c98ccbee8e2e1c');
INSERT INTO `employee` VALUES (94, '中文名', '2020-01-22 00:00:00', '18741563253', '16953694552@email.com', 1, 0, 10, '903f504d12d485756eac5917a8e91eac');

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
INSERT INTO `employee_role_rel` VALUES (60, 2);
INSERT INTO `employee_role_rel` VALUES (61, 2);
INSERT INTO `employee_role_rel` VALUES (62, 2);
INSERT INTO `employee_role_rel` VALUES (63, 2);
INSERT INTO `employee_role_rel` VALUES (68, 28);
INSERT INTO `employee_role_rel` VALUES (69, 28);
INSERT INTO `employee_role_rel` VALUES (71, 28);
INSERT INTO `employee_role_rel` VALUES (72, 28);
INSERT INTO `employee_role_rel` VALUES (74, 28);
INSERT INTO `employee_role_rel` VALUES (75, 28);
INSERT INTO `employee_role_rel` VALUES (76, 28);
INSERT INTO `employee_role_rel` VALUES (78, 28);
INSERT INTO `employee_role_rel` VALUES (79, 28);
INSERT INTO `employee_role_rel` VALUES (80, 28);
INSERT INTO `employee_role_rel` VALUES (81, 28);
INSERT INTO `employee_role_rel` VALUES (82, 28);
INSERT INTO `employee_role_rel` VALUES (83, 28);
INSERT INTO `employee_role_rel` VALUES (84, 28);
INSERT INTO `employee_role_rel` VALUES (85, 28);
INSERT INTO `employee_role_rel` VALUES (87, 28);
INSERT INTO `employee_role_rel` VALUES (90, 28);
INSERT INTO `employee_role_rel` VALUES (92, 28);
INSERT INTO `employee_role_rel` VALUES (93, 28);
INSERT INTO `employee_role_rel` VALUES (94, 28);
INSERT INTO `employee_role_rel` VALUES (89, 29);
INSERT INTO `employee_role_rel` VALUES (77, 30);
INSERT INTO `employee_role_rel` VALUES (86, 30);
INSERT INTO `employee_role_rel` VALUES (88, 30);
INSERT INTO `employee_role_rel` VALUES (91, 31);
INSERT INTO `employee_role_rel` VALUES (64, 32);
INSERT INTO `employee_role_rel` VALUES (65, 32);
INSERT INTO `employee_role_rel` VALUES (66, 32);
INSERT INTO `employee_role_rel` VALUES (67, 32);
INSERT INTO `employee_role_rel` VALUES (70, 32);
INSERT INTO `employee_role_rel` VALUES (73, 32);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `menu_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '系统管理', NULL, NULL, NULL);
INSERT INTO `menu` VALUES (2, '员工管理', '/employee', 1, 4);
INSERT INTO `menu` VALUES (3, '角色权限管理', '/role', 1, 5);
INSERT INTO `menu` VALUES (4, '菜单管理', '/menu', 1, 6);
INSERT INTO `menu` VALUES (6, '系统管理2', '', NULL, NULL);
INSERT INTO `menu` VALUES (7, 'aa', '/aa', 6, NULL);
INSERT INTO `menu` VALUES (8, 'bb', '/bb', 7, NULL);
INSERT INTO `menu` VALUES (10, '菜单管理子菜单1', '/menu1', 4, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `presource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '添加员工', 'employee:add');
INSERT INTO `permission` VALUES (2, '删除员工', 'employee:delete');
INSERT INTO `permission` VALUES (3, '编辑员工', 'employee:edit');
INSERT INTO `permission` VALUES (4, '员工主页', 'employee:index');
INSERT INTO `permission` VALUES (5, '角色权限主页', 'roleindex');
INSERT INTO `permission` VALUES (6, '菜单主页', 'menuindex');

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
INSERT INTO `role` VALUES (1, 'CEO', '总裁');
INSERT INTO `role` VALUES (2, 'Manager', '经理');
INSERT INTO `role` VALUES (28, '普通员工', '普通员工');
INSERT INTO `role` VALUES (29, '临时工', '临时工');
INSERT INTO `role` VALUES (30, '实习生', '实习生');
INSERT INTO `role` VALUES (31, 'HR', '人事');
INSERT INTO `role` VALUES (32, '高级员工', '高级员工(总监、主管、部长等)');

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
INSERT INTO `role_permission_rel` VALUES (1, 2);
INSERT INTO `role_permission_rel` VALUES (2, 2);
INSERT INTO `role_permission_rel` VALUES (1, 3);
INSERT INTO `role_permission_rel` VALUES (31, 3);
INSERT INTO `role_permission_rel` VALUES (1, 4);
INSERT INTO `role_permission_rel` VALUES (2, 4);
INSERT INTO `role_permission_rel` VALUES (31, 4);
INSERT INTO `role_permission_rel` VALUES (32, 4);
INSERT INTO `role_permission_rel` VALUES (2, 5);

SET FOREIGN_KEY_CHECKS = 1;
