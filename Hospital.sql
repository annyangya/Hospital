/*
 Navicat Premium Data Transfer

 Source Server         : ann
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : Hospital

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/05/2022 11:47:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Bed
-- ----------------------------
DROP TABLE IF EXISTS `Bed`;
CREATE TABLE `Bed` (
  `Wno` varchar(3) NOT NULL,
  `Bno` varchar(2) NOT NULL,
  `Binform` varchar(1) NOT NULL,
  `Boffice` varchar(50) NOT NULL,
  PRIMARY KEY (`Wno`,`Bno`),
  KEY `bed_frk-1_idx` (`Boffice`),
  CONSTRAINT `bed_frk-1` FOREIGN KEY (`Boffice`) REFERENCES `Office` (`Oname`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of Bed
-- ----------------------------
BEGIN;
INSERT INTO `Bed` VALUES ('101', '02', '0', 'child');
INSERT INTO `Bed` VALUES ('101', '03', '1', 'child');
INSERT INTO `Bed` VALUES ('201', '01', '1', 'eye');
INSERT INTO `Bed` VALUES ('201', '02', '0', 'eye');
INSERT INTO `Bed` VALUES ('201', '03', '0', 'eye');
INSERT INTO `Bed` VALUES ('502', '01', '0', 'throat');
COMMIT;

-- ----------------------------
-- Table structure for Doctor
-- ----------------------------
DROP TABLE IF EXISTS `Doctor`;
CREATE TABLE `Doctor` (
  `Dno` varchar(3) NOT NULL,
  `Dname` varchar(8) NOT NULL,
  `Dage` varchar(6) DEFAULT NULL,
  `Dsex` varchar(2) NOT NULL,
  `Dtel` varchar(11) DEFAULT NULL,
  `Doffice` varchar(20) NOT NULL,
  PRIMARY KEY (`Dno`),
  KEY `doctor_frk_1_idx` (`Doffice`),
  CONSTRAINT `doctor_frk_1` FOREIGN KEY (`Doffice`) REFERENCES `Office` (`Oname`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of Doctor
-- ----------------------------
BEGIN;
INSERT INTO `Doctor` VALUES ('001', 'ed', '34', 'm', '133133', 'child');
INSERT INTO `Doctor` VALUES ('002', 'mary', '45', 'w', '121233', 'eye');
INSERT INTO `Doctor` VALUES ('003', 'a', '23', 'm', '234234', 'child');
COMMIT;

-- ----------------------------
-- Table structure for Office
-- ----------------------------
DROP TABLE IF EXISTS `Office`;
CREATE TABLE `Office` (
  `Oname` varchar(20) NOT NULL,
  `Otel` varchar(11) NOT NULL,
  `Oadd` varchar(50) NOT NULL,
  PRIMARY KEY (`Oname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of Office
-- ----------------------------
BEGIN;
INSERT INTO `Office` VALUES ('child', '10081', 'floor1');
INSERT INTO `Office` VALUES ('eye', '10082', 'floor2');
INSERT INTO `Office` VALUES ('heart', '10084', 'floor4');
INSERT INTO `Office` VALUES ('mouse', '10083', 'floor3');
INSERT INTO `Office` VALUES ('throat', '10085', 'floor5');
COMMIT;

-- ----------------------------
-- Table structure for Patient
-- ----------------------------
DROP TABLE IF EXISTS `Patient`;
CREATE TABLE `Patient` (
  `Pno` varchar(10) NOT NULL,
  `Pname` varchar(8) NOT NULL,
  `Page` varchar(6) NOT NULL,
  `Psex` varchar(2) NOT NULL,
  `Pid` varchar(14) NOT NULL,
  `Ptel` varchar(11) NOT NULL,
  `Pdoc` varchar(3) NOT NULL,
  `Pward` varchar(3) DEFAULT NULL,
  `Pbed` varchar(2) DEFAULT NULL,
  `Penter` varchar(45) DEFAULT NULL,
  `Pout` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Pno`),
  KEY `Pdoc` (`Pdoc`),
  KEY `patient_2_idx` (`Pward`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`Pdoc`) REFERENCES `Doctor` (`Dno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of Patient
-- ----------------------------
BEGIN;
INSERT INTO `Patient` VALUES ('1', 'ann', '12', 'm', '123', '123', '001', '', '', '2018-3-2', NULL);
INSERT INTO `Patient` VALUES ('3', 'a', '1', 'm', '1', '1', '001', '101', '03', '2014', NULL);
INSERT INTO `Patient` VALUES ('4', 'as', '12', 'm', '12', '12', '002', '201', '01', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(10) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'ann', '234');
INSERT INTO `user` VALUES ('2', 'an', '123');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
