/*
 Navicat Premium Data Transfer

 Source Server         : 119.27.167.77
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 119.27.167.77:3306
 Source Schema         : xyxsglxt

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 13/05/2020 23:12:54
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bj
-- ----------------------------
DROP TABLE IF EXISTS `bj`;
CREATE TABLE `bj`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NULL DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 4 COMMENT = '班级' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bj
-- ----------------------------
INSERT INTO `bj` VALUES (1, '一班');
INSERT INTO `bj` VALUES (2, '二班');
INSERT INTO `bj` VALUES (3, '三班');

-- ----------------------------
-- Table structure for gykq
-- ----------------------------
DROP TABLE IF EXISTS `gykq`;
CREATE TABLE `gykq`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user` bigint(255) NULL DEFAULT NULL COMMENT '学生',
  `time` datetime NULL DEFAULT NULL COMMENT '日期',
  `zt` varchar(255)  NULL DEFAULT NULL COMMENT '考勤状态',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 6 COMMENT = '公寓考勤数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of gykq
-- ----------------------------
INSERT INTO `gykq` VALUES (1, 9, '2020-05-29 20:41:30', '正常');
INSERT INTO `gykq` VALUES (2, 3, '2020-05-21 20:41:51', '正常');
INSERT INTO `gykq` VALUES (3, 9, '2020-05-05 20:41:55', '正常');
INSERT INTO `gykq` VALUES (4, 10, '2020-05-08 19:21:58', '正常');
INSERT INTO `gykq` VALUES (5, 10, '2020-05-08 19:22:02', '晚归');

-- ----------------------------
-- Table structure for kc
-- ----------------------------
DROP TABLE IF EXISTS `kc`;
CREATE TABLE `kc`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NULL DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 4 COMMENT = '课程' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of kc
-- ----------------------------
INSERT INTO `kc` VALUES (1, '体育');
INSERT INTO `kc` VALUES (2, '数学');
INSERT INTO `kc` VALUES (3, '计算机');

-- ----------------------------
-- Table structure for kckq
-- ----------------------------
DROP TABLE IF EXISTS `kckq`;
CREATE TABLE `kckq`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user` bigint(255) NULL DEFAULT NULL COMMENT '考勤学生',
  `kc` bigint(255) NULL DEFAULT NULL COMMENT '课程名称',
  `user` bigint(255) NULL DEFAULT NULL COMMENT '教师',
  `skdd` varchar(255)  NULL DEFAULT NULL COMMENT '上课地点',
  `time` datetime NULL DEFAULT NULL COMMENT '上课时间',
  `kqjg` varchar(255)  NULL DEFAULT NULL COMMENT '考勤结果',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 6 COMMENT = '课程考勤数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of kckq
-- ----------------------------
INSERT INTO `kckq` VALUES (1, 9, 2, 7, '实打实', '2020-05-14 20:37:03', '正常');
INSERT INTO `kckq` VALUES (2, 9, 2, 7, '大萨达所', '2020-05-08 20:37:10', '迟到');
INSERT INTO `kckq` VALUES (3, 3, 1, 7, '大萨达啊', '2020-05-14 20:37:20', '缺勤');
INSERT INTO `kckq` VALUES (4, 10, 3, 7, '撒大声地', '2020-05-08 19:21:18', '迟到');
INSERT INTO `kckq` VALUES (5, 10, 3, 7, '大萨达', '2020-05-08 19:21:38', '正常');

-- ----------------------------
-- Table structure for st
-- ----------------------------
DROP TABLE IF EXISTS `st`;
CREATE TABLE `st`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user` bigint(255) NULL DEFAULT NULL COMMENT '学生',
  `xfje` double(255, 0) NULL DEFAULT NULL COMMENT '消费金额',
  `time` datetime NULL DEFAULT NULL COMMENT '消费时间',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 8 COMMENT = '食堂刷卡记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of st
-- ----------------------------
INSERT INTO `st` VALUES (1, 9, 50, '2020-05-21 20:34:29');
INSERT INTO `st` VALUES (2, 3, 100, '2020-05-21 20:34:37');
INSERT INTO `st` VALUES (3, 3, 200, '2020-05-27 20:34:37');
INSERT INTO `st` VALUES (4, 9, 100, '2020-05-27 20:34:37');
INSERT INTO `st` VALUES (5, 3, 200, '2020-05-27 20:34:37');
INSERT INTO `st` VALUES (6, 10, 1, '2020-05-28 19:19:46');
INSERT INTO `st` VALUES (7, 10, 12, '2020-05-27 19:19:52');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
)  AUTO_INCREMENT = 107 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (2, 100, '用户管理', 'sys/user.html', NULL, 1, 'fa fa-user', 1);
INSERT INTO `sys_menu` VALUES (3, 0, '角色管理', 'sys/role.html', NULL, 1, 'fa fa-user-secret', 2);
INSERT INTO `sys_menu` VALUES (4, 0, '菜单管理', 'sys/menu.html', NULL, 1, 'fa fa-th-list', 3);
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:perms', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:perms', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (76, 100, '班级管理', 'admin/bj.html', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (77, 100, '专业管理', 'admin/zy.html', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (78, 100, '课程管理', 'admin/kc.html', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (79, 101, '图书馆刷卡记录', 'admin/tsg.html', NULL, 1, NULL, 4);
INSERT INTO `sys_menu` VALUES (80, 102, '食堂刷卡记录', 'admin/st.html', NULL, 1, NULL, 5);
INSERT INTO `sys_menu` VALUES (81, 103, '医院就医数据记录', 'admin/yy.html', NULL, 1, NULL, 6);
INSERT INTO `sys_menu` VALUES (83, 104, '课程考勤数据', 'admin/kckq.html', NULL, 1, NULL, 7);
INSERT INTO `sys_menu` VALUES (84, 105, '公寓考勤数据', 'admin/gykq.html', NULL, 1, NULL, 8);
INSERT INTO `sys_menu` VALUES (85, 101, '图书馆人员统计', 'admin/tsgtj.html', NULL, 1, NULL, 4);
INSERT INTO `sys_menu` VALUES (86, 102, '食堂消费人数统计', 'admin/sttj.html', NULL, 1, NULL, 5);
INSERT INTO `sys_menu` VALUES (87, 103, '医院就医人数统计', 'admin/yytj.html', NULL, 1, NULL, 6);
INSERT INTO `sys_menu` VALUES (88, 104, '课堂考勤查询', 'admin/kckqtj.html', NULL, 1, NULL, 7);
INSERT INTO `sys_menu` VALUES (89, 105, '公寓考勤查询', 'admin/gykqtj.html', NULL, 1, NULL, 8);
INSERT INTO `sys_menu` VALUES (90, 79, 's', NULL, 'sys:tsg:use', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (91, 79, 'c', NULL, NULL, 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (92, 80, 's', NULL, 'sys:st:use', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (93, 80, 'c', NULL, NULL, 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (94, 81, 's', NULL, 'sys:yy:use', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (95, 81, 'c', NULL, NULL, 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (96, 83, 's', NULL, 'sys:kckq:use', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (97, 83, 'c', NULL, NULL, 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (98, 84, 's', NULL, 'sys:gykq:use', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (99, 84, 'c', NULL, NULL, 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (100, 0, '系统管理', NULL, NULL, 0, NULL, 0);
INSERT INTO `sys_menu` VALUES (101, 0, '图书馆管理', NULL, NULL, 0, NULL, 0);
INSERT INTO `sys_menu` VALUES (102, 0, '食堂管理', NULL, NULL, 0, NULL, 0);
INSERT INTO `sys_menu` VALUES (103, 0, '校医院管理', NULL, NULL, 0, NULL, 0);
INSERT INTO `sys_menu` VALUES (104, 0, '课堂考勤', NULL, NULL, 0, NULL, 0);
INSERT INTO `sys_menu` VALUES (105, 0, '公寓考勤', NULL, NULL, 0, NULL, 0);
INSERT INTO `sys_menu` VALUES (106, 100, '管理员管理', 'admin/useradmin.html', NULL, 1, NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
)  AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '学生', NULL, 1, '2018-04-17 10:26:34');
INSERT INTO `sys_role` VALUES (2, '系统管理员', NULL, -1, '2018-04-17 15:24:04');
INSERT INTO `sys_role` VALUES (3, '图书管理员', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (4, '食堂管理员', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (5, '校医管理员', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (6, '教师', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (7, '公寓管理员', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 305 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (158, 1, 79);
INSERT INTO `sys_role_menu` VALUES (159, 1, 91);
INSERT INTO `sys_role_menu` VALUES (160, 1, 80);
INSERT INTO `sys_role_menu` VALUES (161, 1, 93);
INSERT INTO `sys_role_menu` VALUES (162, 1, 81);
INSERT INTO `sys_role_menu` VALUES (163, 1, 95);
INSERT INTO `sys_role_menu` VALUES (164, 1, 83);
INSERT INTO `sys_role_menu` VALUES (165, 1, 97);
INSERT INTO `sys_role_menu` VALUES (166, 1, 84);
INSERT INTO `sys_role_menu` VALUES (167, 1, 99);
INSERT INTO `sys_role_menu` VALUES (245, 3, 101);
INSERT INTO `sys_role_menu` VALUES (246, 3, 79);
INSERT INTO `sys_role_menu` VALUES (247, 3, 90);
INSERT INTO `sys_role_menu` VALUES (248, 3, 91);
INSERT INTO `sys_role_menu` VALUES (249, 3, 85);
INSERT INTO `sys_role_menu` VALUES (250, 4, 102);
INSERT INTO `sys_role_menu` VALUES (251, 4, 80);
INSERT INTO `sys_role_menu` VALUES (252, 4, 92);
INSERT INTO `sys_role_menu` VALUES (253, 4, 93);
INSERT INTO `sys_role_menu` VALUES (254, 4, 86);
INSERT INTO `sys_role_menu` VALUES (255, 5, 103);
INSERT INTO `sys_role_menu` VALUES (256, 5, 81);
INSERT INTO `sys_role_menu` VALUES (257, 5, 94);
INSERT INTO `sys_role_menu` VALUES (258, 5, 95);
INSERT INTO `sys_role_menu` VALUES (259, 5, 87);
INSERT INTO `sys_role_menu` VALUES (260, 6, 104);
INSERT INTO `sys_role_menu` VALUES (261, 6, 83);
INSERT INTO `sys_role_menu` VALUES (262, 6, 96);
INSERT INTO `sys_role_menu` VALUES (263, 6, 97);
INSERT INTO `sys_role_menu` VALUES (264, 6, 88);
INSERT INTO `sys_role_menu` VALUES (265, 7, 105);
INSERT INTO `sys_role_menu` VALUES (266, 7, 84);
INSERT INTO `sys_role_menu` VALUES (267, 7, 98);
INSERT INTO `sys_role_menu` VALUES (268, 7, 99);
INSERT INTO `sys_role_menu` VALUES (269, 7, 89);
INSERT INTO `sys_role_menu` VALUES (270, 2, 100);
INSERT INTO `sys_role_menu` VALUES (271, 2, 76);
INSERT INTO `sys_role_menu` VALUES (272, 2, 77);
INSERT INTO `sys_role_menu` VALUES (273, 2, 78);
INSERT INTO `sys_role_menu` VALUES (274, 2, 106);
INSERT INTO `sys_role_menu` VALUES (275, 2, 2);
INSERT INTO `sys_role_menu` VALUES (276, 2, 15);
INSERT INTO `sys_role_menu` VALUES (277, 2, 16);
INSERT INTO `sys_role_menu` VALUES (278, 2, 17);
INSERT INTO `sys_role_menu` VALUES (279, 2, 18);
INSERT INTO `sys_role_menu` VALUES (280, 2, 101);
INSERT INTO `sys_role_menu` VALUES (281, 2, 79);
INSERT INTO `sys_role_menu` VALUES (282, 2, 90);
INSERT INTO `sys_role_menu` VALUES (283, 2, 91);
INSERT INTO `sys_role_menu` VALUES (284, 2, 85);
INSERT INTO `sys_role_menu` VALUES (285, 2, 102);
INSERT INTO `sys_role_menu` VALUES (286, 2, 80);
INSERT INTO `sys_role_menu` VALUES (287, 2, 92);
INSERT INTO `sys_role_menu` VALUES (288, 2, 93);
INSERT INTO `sys_role_menu` VALUES (289, 2, 86);
INSERT INTO `sys_role_menu` VALUES (290, 2, 103);
INSERT INTO `sys_role_menu` VALUES (291, 2, 81);
INSERT INTO `sys_role_menu` VALUES (292, 2, 94);
INSERT INTO `sys_role_menu` VALUES (293, 2, 95);
INSERT INTO `sys_role_menu` VALUES (294, 2, 87);
INSERT INTO `sys_role_menu` VALUES (295, 2, 104);
INSERT INTO `sys_role_menu` VALUES (296, 2, 83);
INSERT INTO `sys_role_menu` VALUES (297, 2, 96);
INSERT INTO `sys_role_menu` VALUES (298, 2, 97);
INSERT INTO `sys_role_menu` VALUES (299, 2, 88);
INSERT INTO `sys_role_menu` VALUES (300, 2, 105);
INSERT INTO `sys_role_menu` VALUES (301, 2, 84);
INSERT INTO `sys_role_menu` VALUES (302, 2, 98);
INSERT INTO `sys_role_menu` VALUES (303, 2, 99);
INSERT INTO `sys_role_menu` VALUES (304, 2, 89);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `xh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `xb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `zy` bigint(255) NULL DEFAULT NULL COMMENT '专业',
  `bj` bigint(255) NULL DEFAULT NULL COMMENT '班级',
  `rfid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'RFID卡号',
  `ye` double(255, 0) NULL DEFAULT NULL COMMENT '卡内余额',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
)  AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (-1, 'admin', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 'root@renren.io', '321321', 1, NULL, '2018-11-11 11:11:11', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1, 'admin1', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 'admin@admin.com', '13612345678', 1, NULL, '2018-11-11 11:11:11', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, '学生1', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123123', '123123', 1, -1, '2020-05-06 19:52:31', '1', '123', '男', 2, 2, '3123', 123);
INSERT INTO `sys_user` VALUES (4, '图书管理员', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123123', '123123', 1, -1, '2020-05-06 19:56:05', '3', NULL, '男', 2, 2, NULL, NULL);
INSERT INTO `sys_user` VALUES (5, '食堂管理员', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123123', '123123', 1, -1, '2020-05-06 19:56:50', '4', NULL, '男', 2, 2, NULL, NULL);
INSERT INTO `sys_user` VALUES (6, '校医管理员', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123123', '123123', 1, -1, '2020-05-06 19:58:22', '5', NULL, '男', 2, 2, NULL, NULL);
INSERT INTO `sys_user` VALUES (7, '教师', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123123', '123123', 1, -1, '2020-05-06 19:58:34', '6', NULL, '男', 2, 2, NULL, NULL);
INSERT INTO `sys_user` VALUES (8, '公寓管理员', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123123', '123123', 1, -1, '2020-05-06 19:58:53', '7', NULL, '男', 2, 2, NULL, NULL);
INSERT INTO `sys_user` VALUES (9, '学生2', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123123', '123123', 1, -1, '2020-05-06 20:32:56', '1', '123', '男', 2, 2, '123', 123);
INSERT INTO `sys_user` VALUES (10, '王五', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', '123', '123', 1, -1, '2020-05-08 19:18:18', '1', '0001', '男', 3, 3, '001', 123);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (7, -1, 2);
INSERT INTO `sys_user_role` VALUES (8, 2, 1);
INSERT INTO `sys_user_role` VALUES (18, 8, 7);
INSERT INTO `sys_user_role` VALUES (19, 9, 1);
INSERT INTO `sys_user_role` VALUES (20, 7, 6);
INSERT INTO `sys_user_role` VALUES (21, 6, 5);
INSERT INTO `sys_user_role` VALUES (22, 5, 4);
INSERT INTO `sys_user_role` VALUES (23, 4, 3);
INSERT INTO `sys_user_role` VALUES (24, 3, 1);
INSERT INTO `sys_user_role` VALUES (25, 10, 1);

-- ----------------------------
-- Table structure for tsg
-- ----------------------------
DROP TABLE IF EXISTS `tsg`;
CREATE TABLE `tsg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user` bigint(255) NULL DEFAULT NULL COMMENT '学生',
  `name` varchar(255)  NULL DEFAULT NULL COMMENT '借阅书籍名称',
  `time` datetime NULL DEFAULT NULL COMMENT '借出时间',
  `ghtime` datetime NULL DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 6 COMMENT = '图书馆刷卡记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tsg
-- ----------------------------
INSERT INTO `tsg` VALUES (1, 9, '阿萨德', '2020-05-06 20:33:03', '2020-05-06 20:33:05');
INSERT INTO `tsg` VALUES (2, 3, '123我去', '2020-05-06 20:33:39', '2020-05-21 20:33:41');
INSERT INTO `tsg` VALUES (3, 9, 'sad', '2020-05-13 20:34:22', '2020-05-22 20:34:24');
INSERT INTO `tsg` VALUES (4, 10, 'xxx', '2020-05-08 19:18:59', '2020-05-29 19:19:00');
INSERT INTO `tsg` VALUES (5, 10, 'aaa', '2020-05-08 19:19:13', '2020-05-29 19:19:15');

-- ----------------------------
-- Table structure for yy
-- ----------------------------
DROP TABLE IF EXISTS `yy`;
CREATE TABLE `yy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user` bigint(255) NULL DEFAULT NULL COMMENT '学生',
  `content` varchar(255)  NULL DEFAULT NULL COMMENT '就医原因',
  `fy` double(255, 0) NULL DEFAULT NULL COMMENT '治疗费用',
  `time` datetime NULL DEFAULT NULL COMMENT '治疗时间',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 5 COMMENT = '医院就医数据记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yy
-- ----------------------------
INSERT INTO `yy` VALUES (1, 9, '312312而我却二群无', 213, '2020-05-14 20:34:58');
INSERT INTO `yy` VALUES (2, 3, '12轻微的群翁多', 12, '2020-05-22 20:35:05');
INSERT INTO `yy` VALUES (3, 10, '撒大声地', 12, '2020-05-21 19:20:39');
INSERT INTO `yy` VALUES (4, 10, '实打实', 12, '2020-05-20 19:20:53');

-- ----------------------------
-- Table structure for zy
-- ----------------------------
DROP TABLE IF EXISTS `zy`;
CREATE TABLE `zy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255)  NULL DEFAULT NULL COMMENT '专业',
  PRIMARY KEY (`id`) USING BTREE
)  AUTO_INCREMENT = 4 ROW_FORMAT = Compact;

-- ----------------------------
-- Records of zy
-- ----------------------------
INSERT INTO `zy` VALUES (1, '体育');
INSERT INTO `zy` VALUES (2, '美术');
INSERT INTO `zy` VALUES (3, '音乐');

SET FOREIGN_KEY_CHECKS = 1;
