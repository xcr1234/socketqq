/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : swingqq

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-01-07 18:56:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userinfo1` int(11) DEFAULT NULL,
  `userinfo2` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_friend_user1` (`userinfo1`),
  KEY `fk_friend_user2` (`userinfo2`),
  CONSTRAINT `fk_friend_user1` FOREIGN KEY (`userinfo1`) REFERENCES `userinfo` (`id`),
  CONSTRAINT `fk_friend_user2` FOREIGN KEY (`userinfo2`) REFERENCES `userinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('1', '2', '3');
INSERT INTO `friends` VALUES ('2', '1', '3');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '虽然qq号也是唯一的，但用自增的int作主键更方便。',
  `qq` int(20) DEFAULT NULL COMMENT 'qq号',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `password` varchar(32) DEFAULT NULL COMMENT '密码，用md5存储',
  `sex` bit(1) DEFAULT NULL COMMENT 'true=男,false=女',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  `star` varchar(20) DEFAULT NULL COMMENT '星座',
  `study` varchar(20) DEFAULT NULL COMMENT '学历',
  `blood` varchar(10) DEFAULT NULL COMMENT '血型',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(50) DEFAULT NULL COMMENT '住址',
  `sign` varchar(50) DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo  这里的密码都是md5('123')
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '100', '飞翔的企鹅', '202cb962ac59075b964b07152d234b70', '', '1995-1-5', '白羊座', '本科', 'ABC型', '110', '100@qq.com', '腾讯公司', '个性签名');
INSERT INTO `userinfo` VALUES ('2', '101', '无极剑圣', '202cb962ac59075b964b07152d234b70', '', '2000-5-12', '狮子座', '小学生', 'O型', '120', '101@qq.com', '召唤师峡谷', '我的剑，就是你的剑！');
INSERT INTO `userinfo` VALUES ('3', '102', '诺克萨斯之手', '202cb962ac59075b964b07152d234b70', '', '2000-12-5', '双子座', '中学生', 'N型', '119', '102@qq.com', '诺克萨斯', '德玛西亚！');
