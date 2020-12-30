/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50547
 Source Host           : localhost:3306
 Source Schema         : gyt

 Target Server Type    : MySQL
 Target Server Version : 50547
 File Encoding         : 65001

 Date: 30/12/2020 17:15:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_doctor
-- ----------------------------
DROP TABLE IF EXISTS `t_doctor`;
CREATE TABLE `t_doctor`  (
  `doc_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '医生编号',
  `doc_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '医生名字',
  `doc_pass` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `doc_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '医生手机',
  `doc_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '医生名号',
  `doc_role` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'admin 或者 user',
  PRIMARY KEY (`doc_no`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_patient
-- ----------------------------
DROP TABLE IF EXISTS `t_patient`;
CREATE TABLE `t_patient`  (
  `patient_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `patient_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '病人姓名',
  `patient_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '病人手机',
  `patient_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `patient_age` int(5) NULL DEFAULT NULL COMMENT '年龄',
  `patient_memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`patient_no`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `project_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '项目编号',
  `project_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '项目名称',
  `project_price` decimal(10, 2) NOT NULL COMMENT '项目价格',
  PRIMARY KEY (`project_no`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_queue
-- ----------------------------
DROP TABLE IF EXISTS `t_queue`;
CREATE TABLE `t_queue`  (
  `patient_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '病人编号',
  `tb_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '治疗单编号',
  `project_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '项目编号'
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_treat_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_treat_bill`;
CREATE TABLE `t_treat_bill`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tb_no` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '治疗单号',
  `kaidan_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '开单人医生编号',
  `patient_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '病人编号',
  `project_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '项目编号',
  `treat_total_count` int(10) NOT NULL COMMENT '总治疗次数（疗程）',
  `treat_use_count` int(10) NOT NULL COMMENT '已经完成的次数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '开单时间',
  `is_over` varchar(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '是否结束(冗余)',
  `over_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_treat_log
-- ----------------------------
DROP TABLE IF EXISTS `t_treat_log`;
CREATE TABLE `t_treat_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_id` int(11) NULL DEFAULT NULL COMMENT '治疗单id,而不是编号，一个治疗单号对应多个tb_id',
  `zhiliao_no` int(11) NULL DEFAULT NULL COMMENT '治疗师医生编号',
  `treat_count` int(11) NULL DEFAULT NULL COMMENT '第几次治疗',
  `treat_time` datetime NULL DEFAULT NULL COMMENT '治疗时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Fixed;

SET FOREIGN_KEY_CHECKS = 1;
