/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : eright

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-08-28 16:37:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_privilege_account
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_account`;
CREATE TABLE `t_privilege_account` (
  `ACCOUNT_ID` int(11) NOT NULL COMMENT '账户Id',
  `USER_ID` int(11) NOT NULL COMMENT '用户Id',
  `ACCOUNT_NAME` varchar(60) DEFAULT NULL COMMENT '账户名',
  `PASSWORD` varchar(200) DEFAULT NULL COMMENT '密码',
  `ACCOUNT_TAG` varchar(100) DEFAULT '-1' COMMENT '三方标识',
  `STATE` varchar(3) DEFAULT '01' COMMENT '01允许，02不允许',
  `SALT` varchar(10) DEFAULT '' COMMENT '盐',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFIED_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CREATE_USER` varchar(20) DEFAULT '' COMMENT '创建人',
  `MODIFIED_USER` varchar(20) DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`ACCOUNT_ID`),
  KEY `FK_USER` (`USER_ID`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`USER_ID`) REFERENCES `t_privilege_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_account
-- ----------------------------
INSERT INTO `t_privilege_account` VALUES ('1110', '1110', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '-1', '01', null, '2017-07-31 21:55:21', '2017-08-21 14:02:04', 'admin', 'admin');
INSERT INTO `t_privilege_account` VALUES ('1112', '1112', 'test@xm.com', 'e35cf7b66449df565f93c607d5a81d09', '-1', '01', '', '2017-08-15 17:29:40', null, 'admin', '');
INSERT INTO `t_privilege_account` VALUES ('1113', '1127', 'yejf@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '-1', '01', '', '2017-08-28 15:51:36', null, 'admin', '');

-- ----------------------------
-- Table structure for t_privilege_group
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_group`;
CREATE TABLE `t_privilege_group` (
  `GROUP_ID` int(11) NOT NULL COMMENT '组Id',
  `GROUP_NAME` varchar(40) DEFAULT NULL COMMENT '组名称',
  `PARENT_GROUP_ID` int(11) DEFAULT NULL COMMENT '父Id',
  `STATE` varchar(3) DEFAULT '01' COMMENT '01允许，02不允许',
  `GROUP_DESC` varchar(200) DEFAULT NULL COMMENT '组描述',
  `CREATE_USER` varchar(20) DEFAULT '' COMMENT '创建人',
  `MODIFIED_USER` varchar(20) DEFAULT '' COMMENT '修改人',
  `MOIDIFIED_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`GROUP_ID`),
  KEY `GROUP_ID` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_group
-- ----------------------------
INSERT INTO `t_privilege_group` VALUES ('1110', '超级管理组', '-1', '01', '超级管理组', 'admin', 'admin', '2017-07-31 21:46:48', '2017-07-31 21:46:51');
INSERT INTO `t_privilege_group` VALUES ('1116', '124563', '-1', '01', '', 'admin', '', '2017-08-24 11:02:37', '2017-08-18 12:31:23');
INSERT INTO `t_privilege_group` VALUES ('1118', '111111', '-1', '01', '', 'admin', '', '2017-08-24 10:48:25', '2017-08-21 15:25:16');
INSERT INTO `t_privilege_group` VALUES ('1119', 'test', '-1', '01', '', 'admin', '', '2017-08-28 15:39:25', '2017-08-23 15:51:24');

-- ----------------------------
-- Table structure for t_privilege_group_role
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_group_role`;
CREATE TABLE `t_privilege_group_role` (
  `GROUP_ID` int(11) DEFAULT NULL COMMENT '组ID',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色ID',
  `STATE` varchar(3) DEFAULT NULL COMMENT '状态，01可用，02不可用',
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(20) DEFAULT NULL,
  `MODIFIED_USER` varchar(20) DEFAULT NULL,
  KEY `FK_GROUP_ROLE_RELATION` (`GROUP_ID`),
  KEY `FK_ROLE_GROUP_RELATION` (`ROLE_ID`),
  CONSTRAINT `FK_GROUP_ROLE_RELATION` FOREIGN KEY (`GROUP_ID`) REFERENCES `t_privilege_group` (`GROUP_ID`),
  CONSTRAINT `FK_ROLE_GROUP_RELATION` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_privilege_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_group_role
-- ----------------------------
INSERT INTO `t_privilege_group_role` VALUES ('1110', '1110', '01', '2017-08-01 08:55:26', '2017-08-01 08:55:29', 'admin', 'admin');
INSERT INTO `t_privilege_group_role` VALUES ('1110', '1111', '01', '2017-08-01 08:55:51', '2017-08-01 08:55:54', 'admin', 'admin');
INSERT INTO `t_privilege_group_role` VALUES ('1119', '1120', '01', '2017-08-28 15:39:25', null, 'admin', null);

-- ----------------------------
-- Table structure for t_privilege_log
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_log`;
CREATE TABLE `t_privilege_log` (
  `LOG_ID` int(11) NOT NULL,
  `LOG_TYPE` varchar(10) DEFAULT NULL,
  `USER_NAME` varchar(20) DEFAULT NULL,
  `SYSTEM_PLATFORM_NAME` varchar(36) DEFAULT NULL,
  `RESOURCE_NAME` varchar(36) DEFAULT NULL,
  `OPERATION_METHOD` varchar(36) DEFAULT NULL,
  `MESSEGE` varchar(200) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_log
-- ----------------------------
INSERT INTO `t_privilege_log` VALUES ('1001', '用户', 'admin', null, 'test@xm.com', 'insert', '新增[test@xm.com]用户', '2017-08-15 17:29:40');
INSERT INTO `t_privilege_log` VALUES ('1002', '用户', 'admin', null, 'textMIma@mima.com', 'insert', '新增[textMIma@mima.com]用户', '2017-08-16 10:49:08');
INSERT INTO `t_privilege_log` VALUES ('1003', '用户组', 'admin', null, 'err', 'insert', '新增[err]用户组', '2017-08-18 11:52:49');
INSERT INTO `t_privilege_log` VALUES ('1004', '用户组', 'admin', null, 'trr', 'insert', '新增[trr]用户组', '2017-08-18 11:54:41');
INSERT INTO `t_privilege_log` VALUES ('1005', '用户组', 'admin', null, 'test45', 'insert', '新增[test45]用户组', '2017-08-18 11:57:53');
INSERT INTO `t_privilege_log` VALUES ('1006', '用户组', 'admin', null, 'tref', 'update', '修改[tref]用户组', '2017-08-18 12:25:04');
INSERT INTO `t_privilege_log` VALUES ('1007', '用户组', 'admin', null, 'testy', 'insert', '新增[testy]用户组', '2017-08-18 12:29:05');
INSERT INTO `t_privilege_log` VALUES ('1008', '用户组', 'admin', null, '124563', 'insert', '新增[124563]用户组', '2017-08-18 12:31:32');
INSERT INTO `t_privilege_log` VALUES ('1009', '用户组', 'admin', null, '1qwaas', 'insert', '新增[1qwaas]用户组', '2017-08-18 12:33:52');
INSERT INTO `t_privilege_log` VALUES ('1010', '系统', null, '权限系统', '权限系统', 'update', '修改[权限系统]系统', '2017-08-18 14:25:21');
INSERT INTO `t_privilege_log` VALUES ('1011', '系统', 'admin', 'text', 'text', 'insert', '增加[text]系统', '2017-08-18 14:37:49');
INSERT INTO `t_privilege_log` VALUES ('1012', '系统', null, 'text', 'text', 'update', '修改[text]系统', '2017-08-18 14:39:16');
INSERT INTO `t_privilege_log` VALUES ('1013', '角色', 'admin', '权限系统', 'test111', 'insert', '新增[test111]角色', '2017-08-18 14:40:26');
INSERT INTO `t_privilege_log` VALUES ('1014', '角色', 'admin', '权限系统', 'test111', 'update', '修改[test111]角色', '2017-08-18 14:46:19');
INSERT INTO `t_privilege_log` VALUES ('1015', '角色', 'admin', '权限系统', '5656556', 'insert', '新增[5656556]角色', '2017-08-18 14:46:19');
INSERT INTO `t_privilege_log` VALUES ('1016', '菜单', 'admin', 'text', 'test11', 'insert', '新增[test11]菜单', '2017-08-18 19:42:56');
INSERT INTO `t_privilege_log` VALUES ('1017', '菜单', 'admin', 'text', 'test2', 'insert', '新增[test2]菜单', '2017-08-18 19:58:14');
INSERT INTO `t_privilege_log` VALUES ('1018', '系统', 'admin', 'test', 'test', 'insert', '增加[test]系统', '2017-08-21 13:57:50');
INSERT INTO `t_privilege_log` VALUES ('1019', '系统', 'admin', 'test', 'test', 'delete', '删除[test]系统', '2017-08-21 13:57:57');
INSERT INTO `t_privilege_log` VALUES ('1020', '用户', 'admin', null, 'admin', 'update', '修改[admin]用户', '2017-08-21 14:02:04');
INSERT INTO `t_privilege_log` VALUES ('1021', '用户组', 'admin', null, 'err', 'delete', '删除[err]用户组', '2017-08-21 14:02:11');
INSERT INTO `t_privilege_log` VALUES ('1022', '用户组', 'admin', null, 'trr', 'delete', '删除[trr]用户组', '2017-08-21 14:02:15');
INSERT INTO `t_privilege_log` VALUES ('1023', '用户组', 'admin', null, 'tref', 'delete', '删除[tref]用户组', '2017-08-21 14:02:19');
INSERT INTO `t_privilege_log` VALUES ('1024', '用户组', 'admin', null, 'testy', 'delete', '删除[testy]用户组', '2017-08-21 14:02:24');
INSERT INTO `t_privilege_log` VALUES ('1025', '用户组', 'admin', null, '1qwaas', 'delete', '删除[1qwaas]用户组', '2017-08-21 14:02:28');
INSERT INTO `t_privilege_log` VALUES ('1026', '系统', 'admin', '8888', '8888', 'insert', '增加[8888]系统', '2017-08-21 15:10:05');
INSERT INTO `t_privilege_log` VALUES ('1027', '系统', null, '888811', '888811', 'update', '修改[888811]系统', '2017-08-21 15:10:17');
INSERT INTO `t_privilege_log` VALUES ('1028', '页面资源', 'admin', '权限系统', 'rr', 'insert', '新增[rr]页面资源', '2017-08-21 15:23:32');
INSERT INTO `t_privilege_log` VALUES ('1029', '页面资源', null, '权限系统', 'rrqq', 'update', '修改[rrqq]页面资源', '2017-08-21 15:23:47');
INSERT INTO `t_privilege_log` VALUES ('1030', '菜单', 'admin', '888811', 'aaa', 'insert', '新增[aaa]菜单', '2017-08-21 15:24:16');
INSERT INTO `t_privilege_log` VALUES ('1031', '页面资源', null, '888811', 'rrqq', 'update', '修改[rrqq]页面资源', '2017-08-21 15:24:38');
INSERT INTO `t_privilege_log` VALUES ('1032', '角色', 'admin', '888811', 'aa', 'insert', '新增[aa]角色', '2017-08-21 15:25:00');
INSERT INTO `t_privilege_log` VALUES ('1033', '用户组', 'admin', null, 'qqq', 'insert', '新增[qqq]用户组', '2017-08-21 15:25:16');
INSERT INTO `t_privilege_log` VALUES ('1034', '系统', null, '测试系统的测试数据', '测试系统的测试数据', 'update', '修改[测试系统的测试数据]系统', '2017-08-22 15:12:53');
INSERT INTO `t_privilege_log` VALUES ('1035', '系统', null, '测试系统', '测试系统', 'insert', '增加[测试系统]系统', '2017-08-22 15:15:44');
INSERT INTO `t_privilege_log` VALUES ('1036', '用户', 'admin', null, 'test', 'insert', '新增[test]用户', '2017-08-22 15:41:00');
INSERT INTO `t_privilege_log` VALUES ('1037', '用户', 'admin', null, 'test11', 'insert', '新增[test11]用户', '2017-08-22 15:45:34');
INSERT INTO `t_privilege_log` VALUES ('1038', '用户', 'admin', null, 'test1123', 'insert', '新增[test1123]用户', '2017-08-22 15:52:08');
INSERT INTO `t_privilege_log` VALUES ('1039', '用户', 'admin', null, 'test1123', 'insert', '新增[test1123]用户', '2017-08-22 15:54:01');
INSERT INTO `t_privilege_log` VALUES ('1040', '用户', 'admin', null, 'test1123', 'insert', '新增[test1123]用户', '2017-08-22 15:54:49');
INSERT INTO `t_privilege_log` VALUES ('1041', '用户', 'admin', null, 'test1123', 'insert', '新增[test1123]用户', '2017-08-22 16:02:09');
INSERT INTO `t_privilege_log` VALUES ('1042', '用户', 'admin', null, 'admin', 'update', '修改[admin]用户', '2017-08-22 16:12:56');
INSERT INTO `t_privilege_log` VALUES ('1043', '用户', 'admin', null, 'test475', 'delete', '删除[test475]用户', '2017-08-22 16:16:51');
INSERT INTO `t_privilege_log` VALUES ('1044', '角色', 'admin', 'text', 'test', 'insert', '新增[test]角色', '2017-08-22 16:24:27');
INSERT INTO `t_privilege_log` VALUES ('1045', '角色', 'admin', 'text', 'testRole', 'insert', '新增[testRole]角色', '2017-08-22 16:45:28');
INSERT INTO `t_privilege_log` VALUES ('1046', '菜单', 'admin', 'text', 'aaa', 'update', '修改[aaa]菜单', '2017-08-22 16:59:33');
INSERT INTO `t_privilege_log` VALUES ('1047', '角色', 'admin', 'text', 'testRole11', 'insert', '新增[testRole11]角色', '2017-08-22 17:01:49');
INSERT INTO `t_privilege_log` VALUES ('1048', '角色', null, 'text', 'testRole', 'update', '修改[testRole]角色', '2017-08-22 17:14:34');
INSERT INTO `t_privilege_log` VALUES ('1049', '菜单', 'admin', 'text', 'qudao', 'insert', '新增[qudao]菜单', '2017-08-22 17:36:25');
INSERT INTO `t_privilege_log` VALUES ('1050', '菜单', 'admin', 'text', 'qudao', 'update', '修改[qudao]菜单', '2017-08-22 17:37:45');
INSERT INTO `t_privilege_log` VALUES ('1051', '菜单', 'admin', '权限系统', '111', 'insert', '新增[111]菜单', '2017-08-22 17:38:21');
INSERT INTO `t_privilege_log` VALUES ('1052', '菜单', 'admin', 'text', 'qudao', 'update', '修改[qudao]菜单', '2017-08-22 17:38:54');
INSERT INTO `t_privilege_log` VALUES ('1053', '角色', 'admin', '权限系统', '可访问系统', 'insert', '新增[可访问系统]角色', '2017-08-22 17:46:55');
INSERT INTO `t_privilege_log` VALUES ('1054', '角色', 'admin', '权限系统', '可访问系统', 'update', '修改[可访问系统]角色', '2017-08-22 17:52:06');
INSERT INTO `t_privilege_log` VALUES ('1055', '角色', null, 'text', 'testRole', 'update', '修改[testRole]角色', '2017-08-22 17:52:43');
INSERT INTO `t_privilege_log` VALUES ('1056', '角色', 'admin', '权限系统', '可访问系统', 'update', '修改[可访问系统]角色', '2017-08-22 20:37:34');
INSERT INTO `t_privilege_log` VALUES ('1057', '角色', 'admin', '权限系统', '可访问系统', 'update', '修改[可访问系统]角色', '2017-08-22 20:47:56');
INSERT INTO `t_privilege_log` VALUES ('1058', '角色', 'admin', '权限系统', '可访问系统', 'update', '修改[可访问系统]角色', '2017-08-22 20:56:16');
INSERT INTO `t_privilege_log` VALUES ('1059', '用户', 'admin', null, 'admin', 'update', '修改[admin]用户', '2017-08-22 20:57:47');
INSERT INTO `t_privilege_log` VALUES ('1060', '用户', 'admin', null, '111@WW.com', 'insert', '新增[111@WW.com]用户', '2017-08-22 21:01:15');
INSERT INTO `t_privilege_log` VALUES ('1061', '角色', 'admin', '权限系统', '5656556', 'update', '修改[5656556]角色', '2017-08-23 10:10:15');
INSERT INTO `t_privilege_log` VALUES ('1062', '角色', 'admin', '权限系统', '5656556', 'update', '修改[5656556]角色', '2017-08-23 10:10:21');
INSERT INTO `t_privilege_log` VALUES ('1063', '角色', 'admin', '权限系统', '5656556', 'update', '修改[5656556]角色', '2017-08-23 10:10:33');
INSERT INTO `t_privilege_log` VALUES ('1064', '角色', 'admin', '权限系统', '5656556', 'delete', '删除了[5656556]角色', '2017-08-23 10:10:38');
INSERT INTO `t_privilege_log` VALUES ('1065', '角色', 'admin', 'text', 'test', 'update', '修改[test]角色', '2017-08-23 10:10:45');
INSERT INTO `t_privilege_log` VALUES ('1066', '角色', 'admin', 'text', 'test', 'delete', '删除了[test]角色', '2017-08-23 10:10:49');
INSERT INTO `t_privilege_log` VALUES ('1067', '角色', 'admin', 'text', 'testRole11', 'update', '修改[testRole11]角色', '2017-08-23 10:11:01');
INSERT INTO `t_privilege_log` VALUES ('1068', '角色', 'admin', 'text', 'testRoleData', 'update', '修改[testRoleData]角色', '2017-08-23 10:11:11');
INSERT INTO `t_privilege_log` VALUES ('1069', '角色', 'admin', 'text', 'testRoleData', 'delete', '删除了[testRoleData]角色', '2017-08-23 10:11:23');
INSERT INTO `t_privilege_log` VALUES ('1070', '角色', 'admin', 'text', 'testRole11', 'delete', '删除了[testRole11]角色', '2017-08-23 10:11:27');
INSERT INTO `t_privilege_log` VALUES ('1071', '角色', 'admin', '测试系统的测试数据', 'aa', 'update', '修改[aa]角色', '2017-08-23 10:11:38');
INSERT INTO `t_privilege_log` VALUES ('1072', '角色', 'admin', '测试系统的测试数据', 'aa', 'delete', '删除了[aa]角色', '2017-08-23 10:11:42');
INSERT INTO `t_privilege_log` VALUES ('1073', '角色', 'admin', '权限系统', 'test111', 'update', '修改[test111]角色', '2017-08-23 10:11:50');
INSERT INTO `t_privilege_log` VALUES ('1074', '角色', 'admin', '权限系统', 'test111', 'delete', '删除了[test111]角色', '2017-08-23 10:11:55');
INSERT INTO `t_privilege_log` VALUES ('1075', '角色', 'admin', '权限系统', '超级管理员', 'update', '修改[超级管理员]角色', '2017-08-23 10:12:31');
INSERT INTO `t_privilege_log` VALUES ('1076', '菜单', 'admin', '权限系统', '111', 'delete', '删除[111]菜单', '2017-08-23 10:12:51');
INSERT INTO `t_privilege_log` VALUES ('1077', '菜单', 'admin', 'text', 'qudao', 'delete', '删除[qudao]菜单', '2017-08-23 10:12:56');
INSERT INTO `t_privilege_log` VALUES ('1078', '菜单', 'admin', 'text', 'test2', 'delete', '删除[test2]菜单', '2017-08-23 10:13:00');
INSERT INTO `t_privilege_log` VALUES ('1079', '菜单', 'admin', 'text', 'test11', 'delete', '删除[test11]菜单', '2017-08-23 10:13:04');
INSERT INTO `t_privilege_log` VALUES ('1080', '页面资源', 'admin', '测试系统的测试数据', 'rrqq', 'delete', '删除了[rrqq]页面资源', '2017-08-23 10:14:07');
INSERT INTO `t_privilege_log` VALUES ('1081', '菜单', 'admin', 'text', 'aaa', 'delete', '删除[aaa]菜单', '2017-08-23 10:14:14');
INSERT INTO `t_privilege_log` VALUES ('1082', '菜单', 'admin', '测试系统的测试数据', 'test', 'insert', '新增[test]菜单', '2017-08-23 10:14:35');
INSERT INTO `t_privilege_log` VALUES ('1083', '页面资源', 'admin', '测试系统的测试数据', 'test页面资源', 'insert', '新增[test页面资源]页面资源', '2017-08-23 10:15:06');
INSERT INTO `t_privilege_log` VALUES ('1084', '用户', 'admin', null, '111@WW.com', 'delete', '删除[111@WW.com]用户', '2017-08-23 10:15:45');
INSERT INTO `t_privilege_log` VALUES ('1085', '用户', 'admin', null, 'test', 'delete', '删除[test]用户', '2017-08-23 10:15:49');
INSERT INTO `t_privilege_log` VALUES ('1086', '用户', 'admin', null, 'test1123', 'delete', '删除[test1123]用户', '2017-08-23 10:15:53');
INSERT INTO `t_privilege_log` VALUES ('1087', '用户', 'admin', null, 'textMIma@mima.com', 'delete', '删除[textMIma@mima.com]用户', '2017-08-23 10:15:57');
INSERT INTO `t_privilege_log` VALUES ('1088', '用户组', 'admin', null, 'test', 'insert', '新增[test]用户组', '2017-08-23 15:51:25');
INSERT INTO `t_privilege_log` VALUES ('1089', '用户组', 'admin', null, '111111', 'update', '修改[111111]用户组', '2017-08-24 10:48:26');
INSERT INTO `t_privilege_log` VALUES ('1090', '用户组', 'admin', null, '124563', 'update', '修改[124563]用户组', '2017-08-24 11:02:37');
INSERT INTO `t_privilege_log` VALUES ('1091', '系统', 'admin', '测试菜单', '测试菜单', 'insert', '增加[测试菜单]系统', '2017-08-28 15:36:18');
INSERT INTO `t_privilege_log` VALUES ('1092', '菜单', 'admin', '测试菜单', 'test1', 'insert', '新增[test1]菜单', '2017-08-28 15:36:39');
INSERT INTO `t_privilege_log` VALUES ('1093', '菜单', 'admin', '测试菜单', 'test2', 'insert', '新增[test2]菜单', '2017-08-28 15:37:07');
INSERT INTO `t_privilege_log` VALUES ('1094', '菜单', 'admin', '测试菜单', 'test3', 'insert', '新增[test3]菜单', '2017-08-28 15:37:27');
INSERT INTO `t_privilege_log` VALUES ('1095', '角色', 'admin', '权限系统', '可访问系统', 'update', '修改[可访问系统]角色', '2017-08-28 15:37:50');
INSERT INTO `t_privilege_log` VALUES ('1096', '角色', 'admin', '权限系统', '可访问系统', 'update', '修改[可访问系统]角色', '2017-08-28 15:38:27');
INSERT INTO `t_privilege_log` VALUES ('1097', '角色', 'admin', '权限系统', '可访问系统', 'delete', '删除了[可访问系统]角色', '2017-08-28 15:38:35');
INSERT INTO `t_privilege_log` VALUES ('1098', '角色', 'admin', '测试菜单', 'test', 'insert', '新增[test]角色', '2017-08-28 15:38:54');
INSERT INTO `t_privilege_log` VALUES ('1099', '用户组', 'admin', null, 'test', 'update', '修改[test]用户组', '2017-08-28 15:39:25');

-- ----------------------------
-- Table structure for t_privilege_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_menu`;
CREATE TABLE `t_privilege_menu` (
  `MENU_ID` int(11) NOT NULL COMMENT '菜单ID',
  `SYSTEM_PLATFORM_ID` int(11) NOT NULL COMMENT '系统ID',
  `MENU_NAME` varchar(40) DEFAULT NULL COMMENT '菜单名',
  `MENU_DESC` varchar(200) DEFAULT NULL COMMENT '菜单描述',
  `REQUST_METHOD` varchar(6) DEFAULT NULL COMMENT '请求方式',
  `URL` varchar(100) DEFAULT NULL COMMENT 'url',
  `PARENT_ID` int(11) DEFAULT NULL COMMENT '父ID',
  `MENU_LEVEL` varchar(3) DEFAULT NULL COMMENT '菜单级别',
  `CHANNEL` varchar(50) DEFAULT '' COMMENT '渠道',
  `SHOW_ORDER` int(11) DEFAULT NULL COMMENT '顺序',
  `STATE` varchar(3) DEFAULT NULL COMMENT '状态',
  `CREATE_USER` varchar(20) DEFAULT NULL,
  `MODIFIED_USER` varchar(20) DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`),
  KEY `FK_SYSTEM_MENU` (`SYSTEM_PLATFORM_ID`),
  KEY `MENU_ID` (`MENU_ID`),
  CONSTRAINT `FK_SYSTEM_MENU` FOREIGN KEY (`SYSTEM_PLATFORM_ID`) REFERENCES `t_privilege_system_platform` (`SYSTEM_PLATFORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_menu
-- ----------------------------
INSERT INTO `t_privilege_menu` VALUES ('1110', '1111', '菜单管理', '菜单管理', 'GET', '/content/menuCont', '-1', '1', 'PC', '3', '01', 'admin', 'admin', '2017-08-01 09:03:57', '2017-08-01 09:03:59');
INSERT INTO `t_privilege_menu` VALUES ('1111', '1111', '页面资源管理', '页面资源管理', 'GET', '/content/pageCont', '-1', '1', 'PC', '2', '01', 'admin', 'admin', '2017-08-01 09:05:20', '2017-08-01 09:05:22');
INSERT INTO `t_privilege_menu` VALUES ('1112', '1111', '角色管理', '角色管理', 'GET', '/content/roleCont', '-1', '1', 'PC', '4', '01', 'admin', 'admin', '2017-08-01 09:06:28', '2017-08-01 09:06:31');
INSERT INTO `t_privilege_menu` VALUES ('1113', '1111', '用户管理', '用户管理', 'GET', '/content/userCont', '-1', '1', 'PC', '6', '01', 'admin', 'admin', '2017-08-01 09:07:17', '2017-08-01 09:07:20');
INSERT INTO `t_privilege_menu` VALUES ('1114', '1111', '系统资源管理', '系统资源管理', 'GET', '/content/systemCont', '-1', '1', 'PC', '1', '01', 'admin', 'admin', '2017-08-01 09:08:00', '2017-08-01 09:08:05');
INSERT INTO `t_privilege_menu` VALUES ('1115', '1111', '日志管理', '日志管理', 'GET', '/content/logCont', '-1', '1', 'PC', '7', '01', 'admin', 'admin', '2017-08-01 09:09:35', '2017-08-01 09:09:32');
INSERT INTO `t_privilege_menu` VALUES ('1116', '1111', '文件上传', '文件上传', 'GET', '/content/upload', '-1', '1', 'PC', '8', '02', 'admin', 'admin', '2017-08-01 09:10:51', '2017-08-01 09:10:54');
INSERT INTO `t_privilege_menu` VALUES ('1117', '1110', '门户首页', '门户首页', 'GET', '/trust/privilege/showPortal', '-1', '1', 'PC', '1', '01', 'admin', 'admin', '2017-08-01 09:12:18', '2017-08-01 09:12:21');
INSERT INTO `t_privilege_menu` VALUES ('1118', '1110', '个人信息', '个人信息', 'GET', '/etheta/management/privilege/user/getUserInfo', '-1', '1', 'PC', '2', '01', 'admin', 'admin', '2017-08-15 17:26:45', '2017-08-08 19:28:28');
INSERT INTO `t_privilege_menu` VALUES ('1119', '1111', '用户组管理', '用户组管理', 'GET', '/content/userGroupCont', '-1', '1', 'PC', '5', '01', 'admin', 'admin', '2017-08-15 17:26:50', '2017-08-08 19:28:31');
INSERT INTO `t_privilege_menu` VALUES ('1126', '1118', 'test', '', null, '/test', '-1', null, '', '1', '01', 'admin', null, null, '2017-08-23 10:14:35');
INSERT INTO `t_privilege_menu` VALUES ('1127', '1120', 'test1', '', null, '/test1', '-1', null, 'PC', '1', '01', 'admin', null, null, '2017-08-28 15:36:39');
INSERT INTO `t_privilege_menu` VALUES ('1128', '1120', 'test2', '', null, '/test2', '1127', null, 'PC', '102', '01', 'admin', null, null, '2017-08-28 15:37:07');
INSERT INTO `t_privilege_menu` VALUES ('1129', '1120', 'test3', '', null, '/test3', '1128', null, 'PC', '10201', '01', 'admin', null, null, '2017-08-28 15:37:27');

-- ----------------------------
-- Table structure for t_privilege_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_menu_role`;
CREATE TABLE `t_privilege_menu_role` (
  `MENU_ID` int(11) DEFAULT NULL COMMENT '菜单ID',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色ID',
  `STATE_TYPE_ID` int(11) DEFAULT NULL COMMENT '状态类型ID',
  `STATE` varchar(3) DEFAULT '01' COMMENT '状态',
  `CREATE_USER` varchar(20) DEFAULT NULL,
  `MODIFIED_USER` varchar(20) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  KEY `FK_MENU_ROLE_RELATION` (`MENU_ID`),
  KEY `FK_ROLE_MENU_RELATION` (`ROLE_ID`),
  KEY `FK_STATE_TYPE` (`STATE_TYPE_ID`),
  CONSTRAINT `FK_MENU_ROLE_RELATION` FOREIGN KEY (`MENU_ID`) REFERENCES `t_privilege_menu` (`MENU_ID`),
  CONSTRAINT `FK_ROLE_MENU_RELATION` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_privilege_role` (`ROLE_ID`),
  CONSTRAINT `FK_STATE_TYPE` FOREIGN KEY (`STATE_TYPE_ID`) REFERENCES `t_privilege_state_type` (`STATE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_menu_role
-- ----------------------------
INSERT INTO `t_privilege_menu_role` VALUES ('1117', '1110', '1', '1', null, null, '2017-08-15 17:19:27', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1118', '1110', '1', '1', null, null, '2017-08-15 17:19:27', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1110', '1111', '1', '1', null, null, '2017-08-23 10:12:31', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1111', '1111', '1', '1', null, null, '2017-08-23 10:12:31', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1112', '1111', '1', '1', null, null, '2017-08-23 10:12:31', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1113', '1111', '1', '1', null, null, '2017-08-23 10:12:31', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1114', '1111', '1', '1', null, null, '2017-08-23 10:12:31', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1115', '1111', '1', '1', null, null, '2017-08-23 10:12:31', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1119', '1111', '1', '1', null, null, '2017-08-23 10:12:31', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1127', '1120', '1', '1', null, null, '2017-08-28 15:38:53', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1128', '1120', '1', '1', null, null, '2017-08-28 15:38:53', null);
INSERT INTO `t_privilege_menu_role` VALUES ('1129', '1120', '1', '1', null, null, '2017-08-28 15:38:53', null);

-- ----------------------------
-- Table structure for t_privilege_res
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_res`;
CREATE TABLE `t_privilege_res` (
  `PRIVILEGE_RES_ID` int(11) NOT NULL COMMENT '资源ID',
  `RES_TYPE_ID` int(11) NOT NULL COMMENT '资源类型ID',
  `SYSTEM_PLATFORM_ID` int(11) NOT NULL COMMENT '系统ID',
  `MENU_ID` int(11) NOT NULL COMMENT '菜单ID',
  `RES_NAME` varchar(40) DEFAULT '' COMMENT '资源名称',
  `RES_DESC` varchar(200) DEFAULT '' COMMENT '资源描述',
  `REQUST_METHOD` varchar(10) DEFAULT '' COMMENT '请求方式',
  `URL` varchar(100) DEFAULT '' COMMENT 'url',
  `STATE` varchar(3) DEFAULT '01' COMMENT '状态',
  `VERSION` varchar(10) DEFAULT '' COMMENT '版本',
  `TYPE` varchar(10) DEFAULT '' COMMENT '操作类型',
  `RES_CODE` varchar(30) DEFAULT '' COMMENT '资源编号',
  `RES_TYPE` varchar(30) DEFAULT '' COMMENT '系统作为资源时所属类型',
  `MODIFIED_USER` varchar(20) DEFAULT '',
  `CREATE_USER` varchar(20) DEFAULT '',
  `MODIFIED_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`PRIVILEGE_RES_ID`),
  KEY `FK_SYSTEMPLATE_RES` (`SYSTEM_PLATFORM_ID`),
  KEY `FK_RES_TYPE` (`RES_TYPE_ID`),
  KEY `FK_MENU_RES_RELATION` (`MENU_ID`),
  KEY `PRIVILEGE_RES_ID` (`PRIVILEGE_RES_ID`),
  CONSTRAINT `FK_MENU_RES_RELATION` FOREIGN KEY (`MENU_ID`) REFERENCES `t_privilege_menu` (`MENU_ID`),
  CONSTRAINT `FK_RES_TYPE` FOREIGN KEY (`RES_TYPE_ID`) REFERENCES `t_privilege_res_type` (`RES_TYPE_ID`),
  CONSTRAINT `FK_SYSTEMPLATE_RES` FOREIGN KEY (`SYSTEM_PLATFORM_ID`) REFERENCES `t_privilege_system_platform` (`SYSTEM_PLATFORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_res
-- ----------------------------
INSERT INTO `t_privilege_res` VALUES ('16', '1110', '1110', '1117', '权限系统', '', 'GET', 'http://cloud2-portal.trustlife.com:8080/static/index.html', '01', '', 'add', '123411qw', '运营', '', 'admin', null, '2017-08-14 21:10:19');
INSERT INTO `t_privilege_res` VALUES ('1110', '1111', '1111', '1113', '添加用户', '添加用户', null, '/etheta/management/privilege/user/add', '01', null, 'add', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1111', '1111', '1111', '1113', '	删除用户', '	删除用户', null, '/etheta/management/privilege/user/delete', '01', null, 'delete', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1112', '1111', '1111', '1113', '修改用户', '修改用户', null, '/etheta/management/privilege/user/update', '01', null, 'update', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1113', '1111', '1111', '1119', '添加用户组', '添加用户组', null, '/etheta/management/privilege/group/add', '01', null, 'add', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1114', '1111', '1111', '1119', '删除用户组', '删除用户组', null, '/etheta/management/privilege/group/delete', '01', null, 'delete', null, null, null, '', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1115', '1111', '1111', '1119', '修改用户组', '修改用户组', null, '/etheta/management/privilege/group/update', '01', null, 'update', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1116', '1111', '1111', '1112', '添加角色', '添加角色', null, '/etheta/management/xinmei/insertRole', '01', null, 'add', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1117', '1111', '1111', '1112', '删除角色', '删除角色', null, '/etheta/management/xinmei/deleteRoleByRoleId', '01', null, 'delete', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1118', '1111', '1111', '1112', '修改角色', '修改角色', null, '/etheta/management/xinmei/updateRole', '01', null, 'update', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1119', '1111', '1111', '1111', '添加资源', '添加资源', null, '/etheta/management/xinmei/button/privilege/add', '01', null, 'add', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1120', '1111', '1111', '1111', '删除资源	', '删除资源	', null, '/etheta/management/xinmei/button/privilege/delete', '01', null, 'delete', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1121', '1111', '1111', '1111', '	修改资源', '	修改资源', null, '/etheta/management/xinmei/button/privilege/update', '01', null, 'update', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1122', '1111', '1111', '1110', '添加菜单', '添加菜单', null, '/etheta/management/xinmei/addMenu', '01', null, 'add', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1123', '1111', '1111', '1110', '删除菜单', '删除菜单', null, '/etheta/management/xinmei/deleteMenu', '01', null, 'delete', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1124', '1111', '1111', '1110', '	修改菜单', '	修改菜单', null, '/etheta/management/xinmei/updateMenu', '01', null, 'update', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1125', '1111', '1111', '1114', '添加系统', '添加系统', null, '/etheta/management/xinmei/button/systemPlatform/addSystem', '01', null, 'add', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1126', '1111', '1111', '1114', '	删除系统', '	删除系统', null, '/etheta/management/xinmei/button/systemPlatform/deleteSystem', '01', null, 'delete', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1127', '1111', '1111', '1114', '修改系统', '修改系统', null, '/etheta/management/xinmei/button/systemPlatform/UpadateSystemPlatform', '01', null, 'update', null, null, null, 'admin', null, '2017-08-15 17:27:13');
INSERT INTO `t_privilege_res` VALUES ('1130', '1111', '1118', '1126', 'test页面资源', '', 'GET', '/123456', '01', '', 'add', '45785', '', '', 'admin', null, '2017-08-23 10:15:06');

-- ----------------------------
-- Table structure for t_privilege_res_role
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_res_role`;
CREATE TABLE `t_privilege_res_role` (
  `ROLE_ID` int(36) NOT NULL,
  `PRIVILEGE_RES_ID` int(36) NOT NULL,
  `STATE` varchar(3) NOT NULL DEFAULT '01',
  `STATE_TYPE_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(20) DEFAULT NULL,
  `MODIFIED_USER` varchar(20) DEFAULT NULL,
  KEY `FK_RES_ROLE_RELATION` (`ROLE_ID`),
  KEY `FK_RES_ROLE_REL` (`PRIVILEGE_RES_ID`),
  KEY `FK_RES_STATE` (`STATE_TYPE_ID`),
  CONSTRAINT `FK_RES_ROLE_REL` FOREIGN KEY (`PRIVILEGE_RES_ID`) REFERENCES `t_privilege_res` (`PRIVILEGE_RES_ID`),
  CONSTRAINT `FK_RES_ROLE_RELATION` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_privilege_role` (`ROLE_ID`),
  CONSTRAINT `FK_RES_STATE` FOREIGN KEY (`STATE_TYPE_ID`) REFERENCES `t_privilege_state_type` (`STATE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_res_role
-- ----------------------------
INSERT INTO `t_privilege_res_role` VALUES ('1110', '16', '01', '1', '2017-08-15 17:19:27', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1126', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1122', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1115', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1127', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1113', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1111', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1112', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1118', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1125', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1114', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1117', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1124', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1110', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1116', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1121', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1120', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1119', '01', '1', '2017-08-23 10:12:31', null, null, null);
INSERT INTO `t_privilege_res_role` VALUES ('1111', '1123', '01', '1', '2017-08-23 10:12:31', null, null, null);

-- ----------------------------
-- Table structure for t_privilege_res_type
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_res_type`;
CREATE TABLE `t_privilege_res_type` (
  `RES_TYPE_ID` int(11) NOT NULL,
  `RES_TYPE_MARK` int(11) NOT NULL,
  `RES_TYPE_NAME` varchar(40) DEFAULT NULL,
  `RES_TYPE_DESC` varchar(200) DEFAULT NULL,
  KEY `RES_TYPE_ID` (`RES_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_res_type
-- ----------------------------
INSERT INTO `t_privilege_res_type` VALUES ('1110', '1', '系统首页', '系统首页');
INSERT INTO `t_privilege_res_type` VALUES ('1111', '2', '按钮', '按钮');

-- ----------------------------
-- Table structure for t_privilege_role
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_role`;
CREATE TABLE `t_privilege_role` (
  `ROLE_ID` int(11) NOT NULL,
  `SYSTEM_PLATFORM_ID` int(11) NOT NULL,
  `ROLE_NAME` varchar(40) NOT NULL DEFAULT '',
  `ROLE_DESC` varchar(200) DEFAULT '',
  `STATE` varchar(3) NOT NULL,
  `ROLE_CODE` varchar(30) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(20) DEFAULT '',
  `MODIFIED_USER` varchar(20) DEFAULT '',
  PRIMARY KEY (`ROLE_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  KEY `FK_SYSTEMPLAT_ID` (`SYSTEM_PLATFORM_ID`),
  CONSTRAINT `FK_SYSTEMPLAT_ID` FOREIGN KEY (`SYSTEM_PLATFORM_ID`) REFERENCES `t_privilege_system_platform` (`SYSTEM_PLATFORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_role
-- ----------------------------
INSERT INTO `t_privilege_role` VALUES ('1110', '1110', '统一门户权限角色', '统一门户权限角色', '01', '10001', '2017-08-01 08:50:44', '2017-08-15 17:19:27', 'admin', 'admin');
INSERT INTO `t_privilege_role` VALUES ('1111', '1111', '超级管理员', '超级管理员', '01', '10000', '2017-08-01 08:51:24', '2017-08-23 10:12:31', 'admin', 'admin');
INSERT INTO `t_privilege_role` VALUES ('1120', '1120', 'test', '', '01', '123456', '2017-08-28 15:38:53', null, 'admin', '');

-- ----------------------------
-- Table structure for t_privilege_sequence
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_sequence`;
CREATE TABLE `t_privilege_sequence` (
  `NAME` varchar(80) NOT NULL COMMENT '序列名',
  `Current_Value` int(30) DEFAULT NULL COMMENT '初始值',
  `Increment` varchar(11) DEFAULT NULL COMMENT '步长',
  `Date` varchar(20) DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='序列表';

-- ----------------------------
-- Records of t_privilege_sequence
-- ----------------------------
INSERT INTO `t_privilege_sequence` VALUES ('account', '1132', '1', null);
INSERT INTO `t_privilege_sequence` VALUES ('group', '1119', '1', null);
INSERT INTO `t_privilege_sequence` VALUES ('log', '1099', '1', null);
INSERT INTO `t_privilege_sequence` VALUES ('menu', '1129', '1', null);
INSERT INTO `t_privilege_sequence` VALUES ('privilege', '1130', '1', null);
INSERT INTO `t_privilege_sequence` VALUES ('role', '1120', '1', null);
INSERT INTO `t_privilege_sequence` VALUES ('system', '1120', '1', null);
INSERT INTO `t_privilege_sequence` VALUES ('systemUser', '1127', '1', null);

-- ----------------------------
-- Table structure for t_privilege_state_type
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_state_type`;
CREATE TABLE `t_privilege_state_type` (
  `STATE_TYPE_ID` int(11) NOT NULL,
  `STATE_TYPE_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`STATE_TYPE_ID`),
  KEY `STATE_TYPE_ID` (`STATE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_state_type
-- ----------------------------
INSERT INTO `t_privilege_state_type` VALUES ('1', '可见');
INSERT INTO `t_privilege_state_type` VALUES ('2', '不可见');

-- ----------------------------
-- Table structure for t_privilege_system_platform
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_system_platform`;
CREATE TABLE `t_privilege_system_platform` (
  `SYSTEM_PLATFORM_ID` int(11) NOT NULL,
  `SYSTEM_PLATFORM_NAME` varchar(50) DEFAULT NULL,
  `SYSTEM_PLATFORM_DESC` varchar(200) DEFAULT '',
  `SYSTEM_CODE` varchar(50) DEFAULT '',
  `URL` varchar(100) DEFAULT '',
  `SYSTEM_TYPE` varchar(20) DEFAULT '',
  `TOKEN_SYS` varchar(50) DEFAULT NULL COMMENT '认证token',
  `SYSTEM_PRIVILEGE_LEVEL` varchar(20) DEFAULT NULL COMMENT '获取系统权限的级别：10000代表用户信息，10001代表系统级别，10002代表角色级别，10003代表菜单级别，10004代表资源级别',
  `STATE` varchar(3) DEFAULT '01' COMMENT '状态，01可用，02不可用',
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  `CREATE_USER` varchar(20) DEFAULT NULL,
  `MODIFIED_USER` varchar(20) DEFAULT '',
  PRIMARY KEY (`SYSTEM_PLATFORM_ID`),
  KEY `SYSTEM_PLATFORM_ID` (`SYSTEM_PLATFORM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_system_platform
-- ----------------------------
INSERT INTO `t_privilege_system_platform` VALUES ('1110', '统一门户', '统一门户', 'portal', 'http://cloud2-portal.trustlife.com/privilege/mPortal/static/index.html', 'IT', '123456', '10002', '01', '2017-07-31 22:07:24', '2017-07-31 22:07:26', 'admin', 'admin');
INSERT INTO `t_privilege_system_platform` VALUES ('1111', '权限系统', '权限系统', 'privilege', 'http://cloud2-portal.trustlife.com/privilege/static/index.html', 'IT', '123456', '10002', '01', '2017-07-31 22:04:55', '2017-07-31 22:04:58', 'admin', 'admin');
INSERT INTO `t_privilege_system_platform` VALUES ('1116', 'text', '', '999', 'http://yijiebu', 'IT管理', '999', '10003', '01', '2017-08-18 14:37:46', null, 'admin', 'admin');
INSERT INTO `t_privilege_system_platform` VALUES ('1118', '测试系统的测试数据', '', '12qw', 'http://111', 'IT管理', '111', '10001', '01', '2017-08-21 15:10:05', null, 'admin', 'admin');
INSERT INTO `t_privilege_system_platform` VALUES ('1119', '测试系统', '', 'test', 'http://www.haole.com', 'IT', '123123', null, '01', '2017-08-22 15:15:44', null, null, '');
INSERT INTO `t_privilege_system_platform` VALUES ('1120', '测试菜单', '', '123456', 'http://', 'IT管理', '123456', '10003', '01', '2017-08-28 15:36:18', null, 'admin', '');

-- ----------------------------
-- Table structure for t_privilege_user
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_user`;
CREATE TABLE `t_privilege_user` (
  `USER_ID` int(11) NOT NULL,
  `ECIF_ID` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT '',
  `TEL_NUMBER` varchar(11) DEFAULT '',
  `GENDER` varchar(3) DEFAULT NULL,
  `USER_DESC` varchar(500) DEFAULT '',
  `MEMBER_TYPE` varchar(10) DEFAULT '',
  `CERTI_CODE` varchar(20) DEFAULT '',
  `CREATE_USER` varchar(20) DEFAULT '',
  `MODIFIED_USER` varchar(20) DEFAULT '',
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_user
-- ----------------------------
INSERT INTO `t_privilege_user` VALUES ('1110', null, 'admin', '18212114163', 'M', '111', '1', '230403199503170015', 'admin', 'admin', null, '2017-08-21 14:02:04');
INSERT INTO `t_privilege_user` VALUES ('1112', null, null, null, 'M', null, '身份证', '123456789', null, 'test1@xm.com', null, '2017-08-22 09:26:42');
INSERT INTO `t_privilege_user` VALUES ('1116', null, 'test@QQ.COM', '13456214212', 'F', 'test', '身份证', '123456', 'admin', '', '2017-08-22 15:52:08', null);
INSERT INTO `t_privilege_user` VALUES ('1117', null, 'test@QQ.COM', '13456214212', 'F', 'test', '身份证', '123456', 'admin', '', '2017-08-22 15:54:01', null);
INSERT INTO `t_privilege_user` VALUES ('1118', null, 'test@QQ.COM', '13456214212', 'F', 'test', '身份证', '123456', 'admin', '', '2017-08-22 15:54:49', null);
INSERT INTO `t_privilege_user` VALUES ('1119', null, 'test@QQ.COM', '13456214212', 'F', 'test', '身份证', '123456', 'admin', '', '2017-08-22 15:58:30', null);
INSERT INTO `t_privilege_user` VALUES ('1120', null, 'test@QQ.COM', '13456214212', 'F', 'test', '身份证', '123456', 'admin', '', '2017-08-22 16:01:39', null);
INSERT INTO `t_privilege_user` VALUES ('1127', null, 'yejf@qq.com', '1111', 'M', '', '身份证', '111111121011011', 'admin', '', '2017-08-28 15:49:53', null);

-- ----------------------------
-- Table structure for t_privilege_user_group
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_user_group`;
CREATE TABLE `t_privilege_user_group` (
  `USER_ID` int(11) DEFAULT NULL,
  `GROUP_ID` int(11) DEFAULT NULL,
  `CREATE_USER` varchar(20) DEFAULT NULL,
  `MODIFIED_USER` varchar(20) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFIED_TIME` datetime DEFAULT NULL,
  KEY `FK_USER_GROUP_RELATION` (`GROUP_ID`),
  KEY `FK_GROUP_USER_RELATION` (`USER_ID`),
  CONSTRAINT `FK_GROUP_USER_RELATION` FOREIGN KEY (`USER_ID`) REFERENCES `t_privilege_user` (`USER_ID`),
  CONSTRAINT `FK_USER_GROUP_RELATION` FOREIGN KEY (`GROUP_ID`) REFERENCES `t_privilege_group` (`GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_user_group
-- ----------------------------
INSERT INTO `t_privilege_user_group` VALUES ('1112', '1110', 'admin', null, '2017-08-15 17:29:40', null);
INSERT INTO `t_privilege_user_group` VALUES ('1110', '1110', 'admin', null, '2017-08-21 14:02:04', null);
INSERT INTO `t_privilege_user_group` VALUES ('1110', '1118', 'admin', null, '2017-08-21 15:25:16', null);
INSERT INTO `t_privilege_user_group` VALUES ('1110', '1119', 'admin', null, '2017-08-23 15:51:24', null);
INSERT INTO `t_privilege_user_group` VALUES ('1127', '1119', 'admin', null, null, null);

-- ----------------------------
-- Table structure for t_privilege_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege_user_role`;
CREATE TABLE `t_privilege_user_role` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `MODIFIED_USER` varchar(50) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MOIDIFIED_TIME` datetime DEFAULT NULL,
  KEY `FK_USER_ROLE_RELATION` (`USER_ID`),
  KEY `FK_ROLE_USER_RELATION` (`ROLE_ID`),
  CONSTRAINT `FK_ROLE_USER_RELATION` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_privilege_role` (`ROLE_ID`),
  CONSTRAINT `FK_USER_ROLE_RELATION` FOREIGN KEY (`USER_ID`) REFERENCES `t_privilege_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege_user_role
-- ----------------------------
INSERT INTO `t_privilege_user_role` VALUES ('1112', '1111', 'admin', null, '2017-08-15 17:29:40', null);
INSERT INTO `t_privilege_user_role` VALUES ('1110', '1110', 'admin', null, '2017-08-21 14:02:04', null);
INSERT INTO `t_privilege_user_role` VALUES ('1110', '1111', 'admin', null, '2017-08-21 14:02:04', null);
INSERT INTO `t_privilege_user_role` VALUES ('1127', '1120', 'admin', null, '2017-08-28 15:52:56', null);

-- ----------------------------
-- Procedure structure for nextvalbatch
-- ----------------------------
DROP PROCEDURE IF EXISTS `nextvalbatch`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nextvalbatch`(IN `seq_name` varchar(50),IN `number` int,OUT `value` int)
BEGIN
    start TRANSACTION;
     UPDATE t_privilege_sequence SET current_value = current_value + increment*number WHERE name = seq_name;
    SELECT current_value INTO value FROM t_privilege_sequence WHERE name = seq_name;
    COMMIT;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for currval
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(`seq_name` varchar(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN 
    DECLARE VALUE INTEGER;  
         SET VALUE = 0;  
         SELECT current_value INTO VALUE  
                    FROM t_privilege_sequence  
                   WHERE NAME = seq_name ;  
         RETURN VALUE;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for nextval
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN  
         UPDATE t_privilege_sequence  
                   SET current_value = current_value + increment  
                    WHERE name = seq_name;  
         RETURN currval(seq_name);  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for setval
-- ----------------------------
DROP FUNCTION IF EXISTS `setval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `setval`(seq_name VARCHAR(50), seq_value INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN  
         UPDATE t_privilege_sequence  
                   SET current_value = seq_value
                    WHERE name = seq_name;  
         RETURN currval(seq_name);  
END
;;
DELIMITER ;
