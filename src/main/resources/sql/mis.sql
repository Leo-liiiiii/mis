/*
 Navicat Premium Data Transfer

 Source Server         : loca
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : mis

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/09/2020 15:53:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `deptno` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `local` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门位置',
  PRIMARY KEY (`deptno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '开发部', '北京');
INSERT INTO `dept` VALUES (2, '研发部', '上海');
INSERT INTO `dept` VALUES (3, '公关部', '深圳');
INSERT INTO `dept` VALUES (4, '市场部', '广州');
INSERT INTO `dept` VALUES (5, '总裁办', '天山上');

-- ----------------------------
-- Table structure for emp_role
-- ----------------------------
DROP TABLE IF EXISTS `emp_role`;
CREATE TABLE `emp_role`  (
  `erid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_fk` int(11) NULL DEFAULT NULL COMMENT '角色表的关联外键',
  `emp_fk` int(11) NULL DEFAULT NULL COMMENT '员工表的关联外键',
  `erdis` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段没有实际意义',
  PRIMARY KEY (`erid`) USING BTREE,
  INDEX `emp_fk`(`emp_fk`) USING BTREE,
  INDEX `role_fk`(`role_fk`) USING BTREE,
  CONSTRAINT `emp_role_ibfk_1` FOREIGN KEY (`emp_fk`) REFERENCES `employee` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `emp_role_ibfk_2` FOREIGN KEY (`role_fk`) REFERENCES `role` (`roleid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_role
-- ----------------------------
INSERT INTO `emp_role` VALUES (1, 7, 1, '大佬配置');
INSERT INTO `emp_role` VALUES (4, 6, 4, '郭海藻是6号角色');
INSERT INTO `emp_role` VALUES (5, 8, 5, '小贝是8号角色');
INSERT INTO `emp_role` VALUES (6, NULL, 13, 'dsadad');
INSERT INTO `emp_role` VALUES (7, NULL, 14, '大萨达大大所');
INSERT INTO `emp_role` VALUES (8, NULL, 15, '撒发发发发');
INSERT INTO `emp_role` VALUES (9, 5, 16, 'adad');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `eid` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号（主键）',
  `ename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `esex` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `eage` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `telephone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `hiredate` date NULL DEFAULT NULL COMMENT '入职日期',
  `pnum` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `dfk` int(11) NULL DEFAULT NULL COMMENT '部门表的外键',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`eid`) USING BTREE,
  INDEX `dfk`(`dfk`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dfk`) REFERENCES `dept` (`deptno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '宋思明', '男', 43, '110', '2020-03-02', '0011', 'ssm', '111', '权利操纵者', 5, NULL);
INSERT INTO `employee` VALUES (4, '郭海藻', '女', 23, '119', '2020-03-06', '111', 'ghz', '222', '风华绝代...', 3, NULL);
INSERT INTO `employee` VALUES (5, '小贝', '男', 24, '120', '2020-03-06', '111', 'xb', '333', '当前员工在试用期，多关注', 4, NULL);
INSERT INTO `employee` VALUES (6, '张三', '女', 33, '1514321', NULL, '1232467697', 'ad34', '111', '山大啊', NULL, NULL);
INSERT INTO `employee` VALUES (7, '', '男', NULL, '', NULL, '', '', '', '', NULL, NULL);
INSERT INTO `employee` VALUES (8, '', '男', NULL, '', NULL, '', '', '', '', NULL, NULL);
INSERT INTO `employee` VALUES (9, '', '男', NULL, '', NULL, '', '', '', '', NULL, NULL);
INSERT INTO `employee` VALUES (10, '', '男', NULL, '', NULL, '', '', '', '', NULL, NULL);
INSERT INTO `employee` VALUES (11, '', '男', NULL, '', NULL, '', '', '', '', NULL, NULL);
INSERT INTO `employee` VALUES (12, '张三达大厦', '男', 33, '1514321', '2020-09-01', '1232467697', 'ad34', 'saaa', 'dasdsaadada', NULL, NULL);
INSERT INTO `employee` VALUES (13, '李四', '男', 31, '1244325432', '2020-09-01', '1232467697', 'lll', '321', 'dsadad', NULL, NULL);
INSERT INTO `employee` VALUES (14, '王五', '男', 22, '', '2020-09-03', '1232467697', 'www', '555', '大萨达大大所', NULL, NULL);
INSERT INTO `employee` VALUES (15, '游戏', '男', 2222, '1514321', NULL, '1232467697', 'yyy', '666', '撒发发发发', NULL, NULL);
INSERT INTO `employee` VALUES (16, '张三达大厦', '女', 2222, '1514321', NULL, '1232467697', 'admin', '123', 'adad', NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rolename` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `roledis` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` int(11) NULL DEFAULT 0 COMMENT '是否启用(0 禁用 1启用)',
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (5, 'Customer', '普通客户没有特殊权限', 1);
INSERT INTO `role` VALUES (6, 'VIP', '享有一定的特权', 1);
INSERT INTO `role` VALUES (7, 'VVIP', '啥都能干', 1);
INSERT INTO `role` VALUES (8, '太上皇', '销售人员的角色', 1);
INSERT INTO `role` VALUES (11, '王五', '很牛逼', 1);
INSERT INTO `role` VALUES (12, '马利克', '六岁儿', 1);
INSERT INTO `role` VALUES (17, '模量', '可以的', 1);
INSERT INTO `role` VALUES (18, '可儿', '可爱不1', 0);
INSERT INTO `role` VALUES (19, '兵长', '牛笔', 0);

-- ----------------------------
-- Table structure for role_sources
-- ----------------------------
DROP TABLE IF EXISTS `role_sources`;
CREATE TABLE `role_sources`  (
  `rsid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键字段',
  `rsdis` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段无实际意义',
  `resources_fk` int(11) NULL DEFAULT NULL COMMENT '权限表的关联字段',
  `role_fk` int(11) NULL DEFAULT NULL COMMENT '角色表的关联字段',
  PRIMARY KEY (`rsid`) USING BTREE,
  INDEX `roleid`(`role_fk`) USING BTREE,
  INDEX `sid`(`resources_fk`) USING BTREE,
  CONSTRAINT `role_sources_ibfk_1` FOREIGN KEY (`resources_fk`) REFERENCES `sources` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_sources_ibfk_2` FOREIGN KEY (`role_fk`) REFERENCES `role` (`roleid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_sources
-- ----------------------------
INSERT INTO `role_sources` VALUES (11, '王五的权限', 1, 11);
INSERT INTO `role_sources` VALUES (12, '王五的权限', 2, 11);
INSERT INTO `role_sources` VALUES (13, '王五的权限', 9, 11);
INSERT INTO `role_sources` VALUES (14, '王五的权限', 10, 11);
INSERT INTO `role_sources` VALUES (15, '王五的权限', 11, 11);
INSERT INTO `role_sources` VALUES (16, '王五的权限', 12, 11);
INSERT INTO `role_sources` VALUES (17, '王五的权限', 13, 11);
INSERT INTO `role_sources` VALUES (18, '王五的权限', 51, 11);
INSERT INTO `role_sources` VALUES (19, '马利克的权限', 1, 12);
INSERT INTO `role_sources` VALUES (20, '马利克的权限', 4, 12);
INSERT INTO `role_sources` VALUES (21, '马利克的权限', 27, 12);
INSERT INTO `role_sources` VALUES (22, '马利克的权限', 31, 12);
INSERT INTO `role_sources` VALUES (23, '马利克的权限', 32, 12);
INSERT INTO `role_sources` VALUES (24, '马利克的权限', 33, 12);
INSERT INTO `role_sources` VALUES (47, '模量的权限', 1, 17);
INSERT INTO `role_sources` VALUES (48, '模量的权限', 35, 17);
INSERT INTO `role_sources` VALUES (49, '模量的权限', 42, 17);
INSERT INTO `role_sources` VALUES (50, '模量的权限', 43, 17);
INSERT INTO `role_sources` VALUES (51, '模量的权限', 44, 17);
INSERT INTO `role_sources` VALUES (64, '可儿的权限', 1, 18);
INSERT INTO `role_sources` VALUES (65, '可儿的权限', 4, 18);
INSERT INTO `role_sources` VALUES (66, '可儿的权限', 27, 18);
INSERT INTO `role_sources` VALUES (67, '可儿的权限', 31, 18);
INSERT INTO `role_sources` VALUES (68, '可儿的权限', 32, 18);
INSERT INTO `role_sources` VALUES (69, '可儿的权限', 33, 18);
INSERT INTO `role_sources` VALUES (100, '兵长的权限', 1, 19);
INSERT INTO `role_sources` VALUES (101, '兵长的权限', 34, 19);
INSERT INTO `role_sources` VALUES (102, '兵长的权限', 39, 19);
INSERT INTO `role_sources` VALUES (103, '兵长的权限', 40, 19);
INSERT INTO `role_sources` VALUES (104, '兵长的权限', 41, 19);

-- ----------------------------
-- Table structure for sources
-- ----------------------------
DROP TABLE IF EXISTS `sources`;
CREATE TABLE `sources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源备注',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父菜单id',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sources
-- ----------------------------
INSERT INTO `sources` VALUES (1, 'oa办公协同系统', NULL, '系统名称', 0, NULL);
INSERT INTO `sources` VALUES (2, '项目管理', NULL, '项目管理', 1, NULL);
INSERT INTO `sources` VALUES (3, '日常办公', NULL, '日常办公', 1, NULL);
INSERT INTO `sources` VALUES (4, '消息管理', NULL, '信息箱', 1, NULL);
INSERT INTO `sources` VALUES (9, '基本信息管理', 'project-base.jsp', '项目基本信息', 2, NULL);
INSERT INTO `sources` VALUES (10, '需求信息管理', 'project-need.jsp', '项目需求分析', 2, NULL);
INSERT INTO `sources` VALUES (11, '模块管理', 'project-model.jsp', '模块管理', 2, NULL);
INSERT INTO `sources` VALUES (12, '功能管理', 'project-function.jsp', '功能管理', 2, NULL);
INSERT INTO `sources` VALUES (13, '附件管理', 'project-file.jsp', '附件管理', 2, NULL);
INSERT INTO `sources` VALUES (14, '创建任务', 'task-add.jsp', '创建任务', 3, NULL);
INSERT INTO `sources` VALUES (15, '任务信息', 'task.jsp', '任务信息', 3, NULL);
INSERT INTO `sources` VALUES (16, '我的任务', 'task-my.jsp', '我的任务', 3, NULL);
INSERT INTO `sources` VALUES (17, '通知公告', 'notice.jsp', '通知公告', 3, NULL);
INSERT INTO `sources` VALUES (18, '档案管理', 'archives.jsp', '档案管理', 3, NULL);
INSERT INTO `sources` VALUES (19, '我的档案', 'myarchives.jsp', '我的档案', 3, NULL);
INSERT INTO `sources` VALUES (20, '报销审批', 'baoxiao-task.jsp', '报销审批', 3, NULL);
INSERT INTO `sources` VALUES (21, '我的报销', 'mybaoxiao-base.jsp', '我的报销', 3, NULL);
INSERT INTO `sources` VALUES (27, '发送邮件', 'email-send.jsp', '仅仅使用javamail发送邮件就ok啦', 4, NULL);
INSERT INTO `sources` VALUES (28, '客户信息管理', NULL, '管理客户信息', 1, NULL);
INSERT INTO `sources` VALUES (29, '客户信息', 'customer.jsp', '客户信息的统计', 28, NULL);
INSERT INTO `sources` VALUES (31, '发件箱', 'email.jsp', '发件箱', 4, NULL);
INSERT INTO `sources` VALUES (32, '消息推送', 'message-give.jsp', '消息推送', 4, NULL);
INSERT INTO `sources` VALUES (33, '论坛', 'forum.jsp', '论坛', 4, NULL);
INSERT INTO `sources` VALUES (34, '系统管理', NULL, '系统管理', 1, NULL);
INSERT INTO `sources` VALUES (35, '对标管理', NULL, '对标管理', 1, NULL);
INSERT INTO `sources` VALUES (36, '个人信息', NULL, '曾经沧海难为水,除却巫山不是云', 1, NULL);
INSERT INTO `sources` VALUES (39, '人员管理', 'user.jsp', '人员管理', 34, NULL);
INSERT INTO `sources` VALUES (40, '权限维护', 'pm.jsp', '权限维护', 34, NULL);
INSERT INTO `sources` VALUES (41, '角色管理', 'role.jsp', '角色管理', 34, NULL);
INSERT INTO `sources` VALUES (42, '数据采集', 'duibiao-base.jsp', '数据采集', 35, NULL);
INSERT INTO `sources` VALUES (43, '设定指标', 'indexvalue-base.jsp', '设定指标', 35, NULL);
INSERT INTO `sources` VALUES (44, '目标营业额分析', 'duibiao-result.jsp', '目标营业额分析', 35, NULL);
INSERT INTO `sources` VALUES (45, '信息检索', 'info.jsp', '信息查看', 36, NULL);
INSERT INTO `sources` VALUES (46, '修改密码', 'modpassword.jsp', '修改密码', 36, NULL);
INSERT INTO `sources` VALUES (48, '曾经的自己', 'history.jsp', '曾经沧海难为水,除却巫山不是云。', 36, NULL);
INSERT INTO `sources` VALUES (49, '张三事迹', '321', '法外狂徒', 36, NULL);
INSERT INTO `sources` VALUES (51, '迪丽热巴', '321', '美美哒', 2, NULL);

SET FOREIGN_KEY_CHECKS = 1;
