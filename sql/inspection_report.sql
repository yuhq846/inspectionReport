/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 26/01/2025 22:56:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for inspection_report
-- ----------------------------
DROP TABLE IF EXISTS `inspection_report`;
CREATE TABLE `inspection_report` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `entrust_no` varchar(32) NOT NULL COMMENT '委托编号',
  `report_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '报告编号',
  `entrust_pay` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '委托方',
  `apply_product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申报品名',
  `sample_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '样品状态',
  `transport` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运输工具',
  `apply_weight` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申报重量',
  `loading_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '装货地点',
  `flow_direction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流向',
  `inspection_date` datetime NOT NULL COMMENT '检测日期',
  `sign_issue_date` datetime NOT NULL COMMENT '签发日期',
  `inspection_describe` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '检测说明',
  `mt_ar` varchar(255) DEFAULT NULL COMMENT '全水分收到基',
  `mad_ad` varchar(255) DEFAULT NULL COMMENT '水分空气干燥基',
  `a_ar` varchar(255) DEFAULT NULL COMMENT '灰分收到基',
  `a_ad` varchar(255) DEFAULT NULL COMMENT '灰分空气干燥基',
  `a_dr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '灰分干燥基',
  `v_ar` varchar(255) DEFAULT NULL COMMENT '挥发分收到基',
  `v_ad` varchar(255) DEFAULT NULL COMMENT '挥发分空气干燥基',
  `v_dr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '挥发分干燥基',
  `v_daf` varchar(255) DEFAULT NULL COMMENT '挥发分干灰基',
  `fc_ar` varchar(255) DEFAULT NULL COMMENT '固定碳收到基',
  `fc_ad` varchar(255) DEFAULT NULL COMMENT '固定碳空气干燥基',
  `fc_dr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固定碳干燥基',
  `st_ar` varchar(255) DEFAULT NULL COMMENT '全硫收到基',
  `st_ad` varchar(255) DEFAULT NULL COMMENT '全硫空气干燥基',
  `st_dr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '全硫干燥基',
  `h_ar` varchar(255) DEFAULT NULL COMMENT '氢收到基',
  `h_ad` varchar(255) DEFAULT NULL COMMENT '氢空气干燥基',
  `h_dr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '氢干燥基',
  `qgrv_ar` varchar(255) DEFAULT NULL COMMENT '高位发热量收到基',
  `qgrv_ad` varchar(255) DEFAULT NULL COMMENT '高位发热量空气干燥基',
  `qgrv_dr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '高位发热量干燥基',
  `qnetar_ar` varchar(255) DEFAULT NULL COMMENT '低位发热量收到基',
  `cb` varchar(255) DEFAULT NULL COMMENT '焦渣特征',
  `notes` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='检测报告';

SET FOREIGN_KEY_CHECKS = 1;
