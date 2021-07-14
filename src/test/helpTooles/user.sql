/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2021-07-15 00:47:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userCode` varchar(32) NOT NULL COMMENT '用户账号',
  `password` varchar(128) NOT NULL COMMENT '用户密码',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `behavior` varchar(128) DEFAULT NULL COMMENT '行为喜好',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '001', 'mima01', '小王', '男', '20', '打篮球');
INSERT INTO `user` VALUES ('2', '002', 'mima02', '小红', '女', '18', '打乒乓球');
