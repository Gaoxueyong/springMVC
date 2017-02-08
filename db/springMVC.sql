/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : springmvc

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-02-08 18:46:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('1', '0', '0,', '中国', '10', '100000', '1', '1', '2013-05-27 08:00:00', '9', '2016-11-30 10:41:34', '中国', '0');
INSERT INTO `sys_area` VALUES ('2', '1', '0,1,', '山东省', '20', '110000', '2', '1', '2013-05-27 08:00:00', '9', '2016-11-30 10:41:43', '山东省', '0');
INSERT INTO `sys_area` VALUES ('20161130094200046', '1', '0,1,', '河南省', '20', '120000', null, '1', '2016-11-30 09:42:46', '9', '2016-11-30 16:07:53', '河南', '0');
INSERT INTO `sys_area` VALUES ('20161130094300050', '20161130094200046', '0,1,20161130094200046,', '郑州市', '30', '120001', null, '1', '2016-11-30 09:43:51', '1', '2016-11-30 09:43:51', '郑州市', '0');
INSERT INTO `sys_area` VALUES ('20161130094500036', '1', '0,1,', '河北省', '20', '130000', null, '1', '2016-11-30 09:45:36', '1', '2016-11-30 09:45:36', '河北省', '1');
INSERT INTO `sys_area` VALUES ('20161130094600005', '20161130094500036', '0,1,20161130094500036,', '石家庄市', '30', '130001', null, '1', '2016-11-30 09:46:05', '1', '2016-11-30 09:46:05', '石家庄市', '1');
INSERT INTO `sys_area` VALUES ('20161130094700007', '20161130094300050', '0,1,20161130094200046,20161130094300050,', '高新区', '40', '120001001', null, '1', '2016-11-30 09:47:07', '1', '2016-11-30 09:47:07', '高新区', '0');
INSERT INTO `sys_area` VALUES ('20161130095400056', '20161130094300050', '0,1,20161130094200046,20161130094300050,', '中原区', '60', '120001002', null, '1', '2016-11-30 09:54:56', '1', '2016-11-30 09:54:56', '中原区', '0');
INSERT INTO `sys_area` VALUES ('20161130170700020', '1', '0,1,', '陕西省', '20', '130000', null, '9', '2016-11-30 17:07:21', '1', '2016-11-30 17:07:21', '陕西省', '0');
INSERT INTO `sys_area` VALUES ('20161130170700047', '20161130170700020', '0,1,20161130170700020,', '西安市', '30', '130001', null, '9', '2016-11-30 17:07:47', '1', '2016-11-30 17:07:47', '西安市', '0');
INSERT INTO `sys_area` VALUES ('20161130170900027', '20161130170700047', '0,1,20161130170700020,20161130170700047,', '新城区', '40', '130001001', null, '9', '2016-11-30 17:09:28', '1', '2016-11-30 17:09:28', '新城区', '0');
INSERT INTO `sys_area` VALUES ('3', '2', '0,1,2,', '济南市', '30', '110101', '3', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_area` VALUES ('4', '3', '0,1,2,3,', '历城区', '40', '110102', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_area` VALUES ('5', '3', '0,1,2,3,', '历下区', '50', '110104', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_area` VALUES ('6', '3', '0,1,2,3,', '高新区', '60', '110105', '4', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '0,', '功能菜单', '0', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '2', '0,1,2,', '机构用户', '970', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '0,1,', '系统设置', '900', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('20161206112900056', '28', '0,1,27,28,', '用户管理', '20', '/sys/user/list', null, 'icon-user', '1', 'sys:user:list', '9', '2016-12-06 11:29:56', '20161230160000015', '2017-01-03 10:15:23', '用户管理', '0');
INSERT INTO `sys_menu` VALUES ('20161206113300032', '28', '0,1,27,28,', '机构管理', '30', '/sys/office/sysOfficeIndex', null, 'icon-th-list', '1', '', '9', '2016-12-06 11:33:33', '20161230160000015', '2017-01-04 16:52:01', '机构管理', '0');
INSERT INTO `sys_menu` VALUES ('20161206113400017', '28', '0,1,27,28,', '区域管理', '40', '/sys/area/sysAreaIndex', null, 'icon-th', '1', '', '9', '2016-12-06 11:34:18', '20161230160000015', '2017-01-04 16:52:29', '', '0');
INSERT INTO `sys_menu` VALUES ('20161206113500027', '28', '0,1,27,28,', '角色管理', '50', '/sys/role/list', null, 'icon-lock', '1', '', '9', '2016-12-06 11:35:27', null, null, '', '0');
INSERT INTO `sys_menu` VALUES ('20161206113600006', '28', '0,1,27,28,', '菜单管理', '60', '/sys/menu/sysMenuIndex', null, 'icon-list-alt', '1', '', '9', '2016-12-06 11:36:06', '20161230160000015', '2017-01-04 16:53:02', '', '0');
INSERT INTO `sys_menu` VALUES ('20161206143500041', '1', '0,1,', '巨魔战将', null, '', null, 'icon-adjust', '1', '', '9', '2016-12-06 14:35:42', null, null, '', '1');
INSERT INTO `sys_menu` VALUES ('20161207110200030', '71', '0,1,27,71,', '个人文件', '30', '', null, 'icon-folder-open-alt', '1', '', '9', '2016-12-07 11:02:30', null, null, '', '0');
INSERT INTO `sys_menu` VALUES ('20170103101500007', '20161206112900056', '0,1,27,28,20161206112900056,', '增加', '21', '', null, '', '1', 'sys:user:add', '20161230160000015', '2017-01-03 10:15:07', null, null, '用户管理列表下的增加按钮', '0');
INSERT INTO `sys_menu` VALUES ('20170103101500051', '20161206112900056', '0,1,27,28,20161206112900056,', '修改', '22', '', null, '', '1', 'sys:user:edit', '20161230160000015', '2017-01-03 10:15:51', null, null, '用户管理列表下的修改按钮', '0');
INSERT INTO `sys_menu` VALUES ('20170103101600022', '20161206112900056', '0,1,27,28,20161206112900056,', '删除', '23', '', null, '', '1', 'sys:user:del', '20161230160000015', '2017-01-03 10:16:23', null, null, '用户管理列表下的删除按钮', '0');
INSERT INTO `sys_menu` VALUES ('20170103160900015', '20161206113300032', '0,1,27,28,20161206113300032,', '增加', '31', '', null, '', '1', 'sys:office:add', '20161230160000015', '2017-01-03 16:09:15', null, null, '机构管理列表下的增加功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103160900056', '20161206113300032', '0,1,27,28,20161206113300032,', '修改', '32', '', null, '', '1', 'sys:office:edit', '20161230160000015', '2017-01-03 16:09:56', null, null, '机构管理列表下的修改功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161000042', '20161206113300032', '0,1,27,28,20161206113300032,', '删除', '33', '', null, '', '1', 'sys:office:del', '20161230160000015', '2017-01-03 16:10:43', null, null, '机构管理列表下的删除功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161300005', '20161206113400017', '0,1,27,28,20161206113400017,', '增加', '41', '', null, '', '1', 'sys:area:add', '20161230160000015', '2017-01-03 16:13:06', null, null, '区域管理列表下的增加功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161300048', '20161206113400017', '0,1,27,28,20161206113400017,', '修改', '42', '', null, '', '1', 'sys:area:edit', '20161230160000015', '2017-01-03 16:13:49', null, null, '区域管理列表下的修改按钮', '0');
INSERT INTO `sys_menu` VALUES ('20170103161400027', '20161206113400017', '0,1,27,28,20161206113400017,', '删除', '43', '', null, '', '1', 'sys:area:del', '20161230160000015', '2017-01-03 16:14:27', null, null, '区域管理列表下的删除按钮', '0');
INSERT INTO `sys_menu` VALUES ('20170103161500034', '20161206113500027', '0,1,27,28,20161206113500027,', '增加', '51', '', null, '', '1', 'sys:role:add', '20161230160000015', '2017-01-03 16:15:35', null, null, '角色管理列表下的增加功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161600007', '20161206113500027', '0,1,27,28,20161206113500027,', '修改', '52', '', null, '', '1', 'sys:role:edit', '20161230160000015', '2017-01-03 16:16:08', null, null, '角色管理列表下的修改功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161600048', '20161206113500027', '0,1,27,28,20161206113500027,', '删除', '53', '', null, '', '1', 'sys:role:del', '20161230160000015', '2017-01-03 16:16:49', null, null, '角色管理列表下的删除功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161700041', '20161206113600006', '0,1,27,28,20161206113600006,', '增加', '61', '', null, '', '1', 'sys:menu:add', '20161230160000015', '2017-01-03 16:17:42', null, null, '菜单管理列表下的增加功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161800009', '20161206113600006', '0,1,27,28,20161206113600006,', '修改', '62', '', null, '', '1', 'sys:menu:edit', '20161230160000015', '2017-01-03 16:18:10', null, null, '菜单管理列表下的修改功能', '0');
INSERT INTO `sys_menu` VALUES ('20170103161800037', '20161206113600006', '0,1,27,28,20161206113600006,', '删除', '63', '', null, '', '1', 'sys:menu:del', '20161230160000015', '2017-01-03 16:18:37', null, null, '菜单管理列表下的删除功能', '0');
INSERT INTO `sys_menu` VALUES ('20170104170700054', '20161206112900056', '0,1,27,28,20161206112900056,', '测试', '24', '', null, '', '1', '', '20161230160000015', '2017-01-04 17:07:55', null, null, '', '1');
INSERT INTO `sys_menu` VALUES ('23', '2', '0,1,2,', '关于帮助', '990', null, null, null, '0', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '0,1,', '我的面板', '100', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('28', '27', '0,1,27,', '个人信息', '30', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('3', '2', '0,1,2,', '系统设置', '980', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('31', '1', '0,1,', '内容管理', '500', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('32', '31', '0,1,31,', '栏目设置', '990', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('3c92c17886944d0687e73e286cada573', '79', '0,1,79,', '生成示例', '120', '', '', '', '1', '', '1', '2013-08-12 13:10:05', '1', '2013-08-12 13:10:05', '', '0');
INSERT INTO `sys_menu` VALUES ('40', '31', '0,1,31,', '内容管理', '500', null, null, null, '1', 'cms:view', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('60', '31', '0,1,31,', '统计分析', '600', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('62', '1', '0,1,', '在线办公', '200', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('63', '62', '0,1,62,', '个人办公', '30', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('67', '2', '0,1,2,', '日志查询', '985', null, null, null, '1', null, '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('69', '62', '0,1,62,', '流程管理', '300', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('71', '27', '0,1,27,', '文件管理', '90', null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('75', '1', '0,1,', '在线演示', '3000', null, null, null, '1', null, '1', '2013-10-08 08:00:00', '1', '2013-10-08 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('79', '1', '0,1,', '代码生成', '5000', null, null, null, '1', null, '1', '2013-10-16 08:00:00', '1', '2013-10-16 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('80', '79', '0,1,79,', '代码生成', '50', null, null, null, '1', null, '1', '2013-10-16 08:00:00', '1', '2013-10-16 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('86', '75', '0,1,75,', '组件演示', '50', null, null, null, '1', null, '1', '2013-10-22 08:00:00', '1', '2013-10-22 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('88', '62', '0,1,62,', '通知通告', '20', '', '', '', '1', '', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `parent_id` varchar(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `area_id` varchar(64) DEFAULT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `type` char(1) DEFAULT NULL COMMENT '机构类型',
  `grade` char(1) DEFAULT '' COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `USEABLE` varchar(64) DEFAULT NULL COMMENT '是否启用',
  `PRIMARY_PERSON` varchar(64) DEFAULT NULL COMMENT '主负责人',
  `DEPUTY_PERSON` varchar(64) DEFAULT NULL COMMENT '副负责人',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_del_flag` (`del_flag`),
  KEY `sys_office_type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '0', '0,', '机构管理', '1', '', null, '1', '1', null, null, null, null, null, null, null, null, null, '1', '2016-11-21 14:50:55', '1', '2016-11-21 14:50:59', null, '0');
INSERT INTO `sys_office` VALUES ('20161206163800000', '1', '0,1,', '河南省总公司', '20', '20161130094200046', '120000', null, null, '河南省郑州市高新区', '450000', null, null, null, null, null, null, null, '9', '2016-12-06 16:38:00', null, null, '河南省郑州市高新区', '0');
INSERT INTO `sys_office` VALUES ('20161206163800045', '1', '0,1,', '山东省总公司', '20', '2', '110000', null, null, '山东省济南市', '', null, null, null, null, null, null, null, '9', '2016-12-06 16:38:45', null, null, '', '0');
INSERT INTO `sys_office` VALUES ('20161206163900022', '1', '0,1,', '陕西省总公司', '20', '20161130170700020', '130000', null, null, '陕西省西安市', '', null, null, null, null, null, null, null, '9', '2016-12-06 16:39:22', null, null, '', '0');
INSERT INTO `sys_office` VALUES ('20161206164200037', '20161206163800045', '0,1,20161206163800045,', '济南市分公司', '30', '3', '110101', null, null, '济南市', '', null, null, null, null, null, null, null, '9', '2016-12-06 16:42:37', null, null, '', '0');
INSERT INTO `sys_office` VALUES ('20161206164300003', '20161206163900022', '0,1,20161206163900022,', '西安市分公司', '30', '20161130170700047', '130001', null, null, '西安市分公司', '', null, null, null, null, null, null, null, '9', '2016-12-06 16:43:03', null, null, '', '0');
INSERT INTO `sys_office` VALUES ('20161206164300025', '20161206163800000', '0,1,20161206163800000,', '郑州市分公司', '30', '20161130094300050', '120001', null, null, '郑州市分公司', '', null, null, null, null, null, null, null, '9', '2016-12-06 16:43:25', null, null, '', '0');
INSERT INTO `sys_office` VALUES ('20170104171000042', '20161206163900022', '0,1,20161206163900022,', '西安市高新区', '60', '20161130170700047', '130001', null, null, '', '', null, null, null, null, null, null, null, '20161230160000015', '2017-01-04 17:10:42', '20161230160000015', '2017-01-04 17:11:00', '', '0');

-- ----------------------------
-- Table structure for sys_project
-- ----------------------------
DROP TABLE IF EXISTS `sys_project`;
CREATE TABLE `sys_project` (
  `id` varchar(64) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(500) NOT NULL COMMENT '名称',
  `type` char(1) DEFAULT NULL COMMENT '类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目信息表';

-- ----------------------------
-- Records of sys_project
-- ----------------------------
INSERT INTO `sys_project` VALUES ('1', 'SpringMVC', '1', '20161230160000015', '2017-02-07 16:57:39', '', '2017-02-07 17:00:10', null, '0');
INSERT INTO `sys_project` VALUES ('2', '来源:<a href=\"http://www.mycodes.net/\" target=\"_blank\">源码之家</a>', '2', '20161230160000015', '2017-02-07 17:02:09', null, null, null, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `office_id` varchar(64) DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(255) NOT NULL COMMENT '英文名称',
  `role_type` varchar(255) DEFAULT NULL COMMENT '角色类型',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `is_sys` varchar(64) DEFAULT NULL COMMENT '是否系统数据',
  `useable` varchar(64) NOT NULL COMMENT '是否可用',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`),
  KEY `sys_role_enname` (`enname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('20161202144800006', null, '系统管理员', 'xtgly', null, null, null, '1', '9', '2016-12-02 14:48:07', '9', '2016-12-02 15:24:57', '系统管理员', '1');
INSERT INTO `sys_role` VALUES ('20161205113200010', null, '超级管理员', 'admin', null, null, null, '1', '9', '2016-12-05 11:32:10', '20161230160000015', '2017-01-03 16:19:20', '超级管理员', '0');
INSERT INTO `sys_role` VALUES ('20161205113200024', null, '管理员', 'gly', null, null, null, '1', '9', '2016-12-05 11:32:25', '20161230160000015', '2017-01-03 16:30:12', '管理员 非系统管理员', '0');
INSERT INTO `sys_role` VALUES ('20161208100700019', null, '测试角色', 'csjs', null, null, null, '1', '9', '2016-12-08 10:07:19', null, null, '', '1');
INSERT INTO `sys_role` VALUES ('20161208102600054', null, '测试角色', 'csjs', null, null, null, '1', '9', '2016-12-08 10:26:55', null, null, '', '1');
INSERT INTO `sys_role` VALUES ('20161208103000002', null, '测试角色2', 'csjs2', null, null, null, '1', '9', '2016-12-08 10:30:03', '9', '2016-12-08 10:30:18', '', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '1');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '13');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '2');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20161206112900056');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20161206113300032');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20161206113400017');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20161206113500027');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20161206113600006');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20161207110200030');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103101500007');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103101500051');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103101600022');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103160900015');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103160900056');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161000042');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161300005');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161300048');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161400027');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161500034');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161600007');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161600048');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161700041');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161800009');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '20170103161800037');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '23');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '27');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '28');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '3');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '31');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '32');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '3c92c17886944d0687e73e286cada573');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '40');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '60');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '62');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '63');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '67');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '69');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '71');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '79');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '80');
INSERT INTO `sys_role_menu` VALUES ('20161205113200010', '88');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20161206112900056');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20161206113300032');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20161206113400017');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20161206113500027');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20161206113600006');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20161207110200030');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20170103101500007');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20170103101500051');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20170103161300005');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20170103161300048');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '20170103161400027');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '27');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '28');
INSERT INTO `sys_role_menu` VALUES ('20161205113200024', '71');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `company_id` varchar(64) DEFAULT NULL COMMENT '归属公司',
  `office_id` varchar(64) DEFAULT NULL COMMENT '归属部门',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `photo` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` varchar(64) DEFAULT NULL COMMENT '是否可登录',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `company` varchar(100) DEFAULT NULL COMMENT '归属公司',
  `office` varchar(100) DEFAULT NULL COMMENT '归属部门',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_company_id` (`company_id`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10', null, null, 'xkjm', '123', '000002', '虚空假面', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:42:19', '1', '2016-11-18 09:42:19', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('11', null, null, 'jmzj', '123', '000003', '巨魔战将', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:42:57', '1', '2016-11-18 09:42:57', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('12', null, null, 'klw', '123', '000004', '李奥瑞克王', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:44:45', '1', '2016-11-18 09:44:45', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('13', null, null, 'lhr', '123', '000005', '裂魂人', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:45:54', '1', '2016-11-18 09:45:54', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('14', null, null, 'js', '123', '000006', '剑圣', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:46:30', '1', '2016-11-18 09:46:30', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('15', null, null, 'tb', '123', '000007', '恶魔猎手', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:47:44', '1', '2016-11-18 09:47:44', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('16', null, null, 'gbz', '123', '000008', '刚背兽', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:48:36', '1', '2016-11-18 09:48:36', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('17', null, null, 'hz', '123', '000009', '幻影长矛手', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:49:06', '1', '2016-11-18 09:49:06', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('18', null, null, 'az', '123', '000010', '矮人狙击手', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:49:42', '1', '2016-11-18 09:49:42', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('19', null, null, 'fw', '123', '000011', '斧王', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:50:17', '1', '2016-11-18 09:50:17', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('20', null, null, 'dom', '123', '000012', '末日使者', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:51:23', '1', '2016-11-18 09:51:23', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('20161208160100011', null, '', 'csry', '123456', '000028', '测试人员', '', '', '', null, null, '192.168.92.24', '2017-01-09 09:35:47', '1', '9', '2016-12-08 16:01:11', '20161230160000015', '2017-01-03 10:57:46', '', '0', null, '山东省总公司');
INSERT INTO `sys_user` VALUES ('20161230160000015', null, '', 'admin', '123456', '000000', '超级管理员', 'xueyong89@126.com', '15838258295', '15838258295', null, null, '192.168.92.24', '2017-02-06 11:22:22', '1', '9', '2016-12-30 16:00:15', '9', '2016-12-30 16:19:57', '超级管理员', '0', null, '机构管理');
INSERT INTO `sys_user` VALUES ('21', null, null, 'xg', '123', '000013', '食尸鬼', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:51:59', '1', '2016-11-18 09:51:59', '末日使者', '0', null, null);
INSERT INTO `sys_user` VALUES ('22', null, null, 'sl', '123', '000014', '神灵武士', '', '', '', null, null, null, null, '1', '1', '2016-11-18 09:52:31', '1', '2016-11-18 09:52:31', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('23', null, null, 'sv', '123', '000015', 'SV', '', '', '', null, null, null, null, '1', '1', '2016-11-18 11:48:05', '1', '2016-11-18 11:48:05', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('24', null, null, 'lp', '123', '000016', '蓝胖', '', '', '', null, null, null, null, '1', '1', '2016-11-18 11:50:30', '1', '2016-11-18 11:50:30', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('25', null, null, 'mvp', '123', '000017', '毒龙', '', '', '', null, null, null, null, '1', '1', '2016-11-18 11:52:20', '1', '2016-11-18 11:52:20', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('26', null, null, 'xd', '123', '000018', '德鲁伊', '', '', '', null, null, null, null, '1', '1', '2016-11-18 11:53:09', '1', '2016-11-18 11:53:09', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('27', null, null, 'jt', '123', '000019', '军团指挥官', '', '', '', null, null, null, null, '1', '1', '2016-11-18 12:05:31', '1', '2016-11-18 12:05:31', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('28', null, null, 'fj', '123', '000020', '矮人直升机', '', '', '', null, null, null, null, '1', '1', '2016-11-18 12:18:54', '1', '2016-11-18 12:18:54', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('29', null, null, 'xz', '123', '000021', '先知', '', '', '', null, null, null, null, '1', '1', '2016-11-18 12:21:09', '1', '2016-11-18 12:21:09', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('30', null, null, 'hdqs', '123', '000022', '混沌骑士', '', '', '', null, null, null, null, '1', '1', '2016-11-18 12:43:32', '1', '2016-11-18 12:43:32', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('31', null, null, 'yc', '123', '000023', '隐形刺客', '', '', '', null, null, null, null, '1', '1', '2016-11-18 14:02:23', '1', '2016-11-18 14:02:23', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('32', null, null, 'lq', '123', '000024', '龙骑士', '', '', '', null, null, null, null, '1', '1', '2016-11-18 14:04:48', '1', '2016-11-18 14:04:48', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('33', null, null, 'cmss', '123', '000025', '沉默术士', '', '', '', null, null, null, null, '1', '1', '2016-11-18 14:06:20', '1', '2016-11-18 14:06:20', 'Dota英雄', '0', null, null);
INSERT INTO `sys_user` VALUES ('9', null, null, 'gxy', '123', '000001', '高学勇', 'xueyong89@126.com', '15838258295', '15838258295', null, null, '0:0:0:0:0:0:0:1', '2016-12-30 15:41:38', '1', '1', '2016-11-16 16:31:07', '1', '2016-11-16 16:31:07', '15838258295', '0', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('20161208160100011', '20161205113200024');
INSERT INTO `sys_user_role` VALUES ('20161230160000015', '20161205113200010');
