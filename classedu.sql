/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : classedu

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-07-03 07:39:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(20) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '课程',
  `courseNo` varchar(255) DEFAULT NULL COMMENT '课序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('11', '普通物理', 'ptwl-01');
INSERT INTO `course` VALUES ('12', '普通物理', 'ptwl-02');
INSERT INTO `course` VALUES ('21', '高等数学', 'gdsx-01');
INSERT INTO `course` VALUES ('22', '高等数学', 'gdsx-02');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(45) DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='留言表';

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('3', '133037', '老师好', '2017-04-25 20:24:56');
INSERT INTO `message` VALUES ('4', '133037', '老师好', '2017-04-25 20:25:04');
INSERT INTO `message` VALUES ('5', '133037', '考试你好', '2017-04-25 20:32:26');
INSERT INTO `message` VALUES ('6', '133037', '回来了', '2017-04-25 20:49:31');
INSERT INTO `message` VALUES ('7', '133037', '考虑考虑', '2017-05-02 20:39:02');
INSERT INTO `message` VALUES ('8', '133037', '考虑考虑', '2017-05-02 20:39:12');

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(45) DEFAULT NULL,
  `answera` varchar(45) DEFAULT NULL,
  `answerb` varchar(45) DEFAULT NULL,
  `answerc` varchar(45) DEFAULT NULL,
  `answerd` varchar(45) DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='问题列表';

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('1', '姚明的身高是？', '2.11', '2.12', '2.19', '2.16', '2.16');
INSERT INTO `question` VALUES ('2', '中国的面积是？', '960', '800', '700', '600', '960');
INSERT INTO `question` VALUES ('3', '111', '111', '222', '333', '444', '111');
INSERT INTO `question` VALUES ('4', '444', '444', '333', '222', '111', '111');

-- ----------------------------
-- Table structure for `signin`
-- ----------------------------
DROP TABLE IF EXISTS `signin`;
CREATE TABLE `signin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signin
-- ----------------------------
INSERT INTO `signin` VALUES ('1', '133037', '2017-05-31 11:43:13');
INSERT INTO `signin` VALUES ('7', '111', '2017-04-25 21:28:32');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `userid` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('111', '111');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT '',
  `name` varchar(100) DEFAULT NULL,
  `userclass` varchar(20) DEFAULT NULL,
  `courseid` varchar(20) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('000', '000', '000', '1', '11', '4', '计算机');
INSERT INTO `user` VALUES ('111', '111', '000', '1', '12', '4', '计算机');
INSERT INTO `user` VALUES ('123', '123', '000', '1', '21', '4', '计算机');
INSERT INTO `user` VALUES ('133037', '133037', '000', '1', '22', '4', '计算机');
INSERT INTO `user` VALUES ('222', '222', '000', '1', '11', '4', '计算机');
INSERT INTO `user` VALUES ('333', '333', '000', '1', '12', '4', '通信');
INSERT INTO `user` VALUES ('444', '444', '444', '1', '21', '4', '通信');
