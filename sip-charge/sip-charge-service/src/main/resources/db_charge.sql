/*
Navicat MySQL Data Transfer

Source Server         : 去去去11
Source Server Version : 80016
Source Host           : 127.0.0.1:3306
Source Database       : db_charge

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-06-17 08:02:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_sip_charge_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_sip_charge_details`;
CREATE TABLE `tb_sip_charge_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `charge_type` varchar(255) NOT NULL,
  `traffic` int(2) NOT NULL,
  `boarding_codes` varchar(255) DEFAULT NULL,
  `routes_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `amount` decimal(16,2) NOT NULL DEFAULT '0.00',
  `description` varchar(600) DEFAULT NULL,
  `logic_del` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_sip_charge_details
-- ----------------------------
INSERT INTO `tb_sip_charge_details` VALUES ('16', '111', '2019-06-16 08:10:17', '2019-06-16 14:04:58', '29', null, 'charge_type', '0', 'boarding_all,boarding_go,boarding_half', null, '书本费', '0.00', '书本费1222', null);
INSERT INTO `tb_sip_charge_details` VALUES ('17', '111', '2019-06-16 08:10:41', '2019-06-16 14:03:28', '29', null, 'charge_type', '1', 'boarding_go', null, '交通费用', '0.00', '交通费用', null);
INSERT INTO `tb_sip_charge_details` VALUES ('18', '111', '2019-06-16 08:36:00', '2019-06-16 14:04:47', '29', '17', 'charge_item', '1', 'boarding_go', 'route_1', '路线1', '202.00', '武器', null);
INSERT INTO `tb_sip_charge_details` VALUES ('19', '111', '2019-06-16 08:40:32', '2019-06-16 14:03:52', '29', '17', 'charge_item', '1', 'boarding_go', 'route_2', '路线2', '52.60', '大大', null);
INSERT INTO `tb_sip_charge_details` VALUES ('22', '111', '2019-06-16 08:46:29', '2019-06-16 14:05:05', '29', '16', 'charge_item', '0', 'boarding_all,boarding_go,boarding_half', 'route_1', '学杂费1', '500.50', '111', null);
INSERT INTO `tb_sip_charge_details` VALUES ('23', '111', '2019-06-16 09:38:13', '2019-06-16 14:03:46', '29', '17', 'charge_item', '1', 'boarding_go', 'route_2', '111', '21.30', '11', null);
INSERT INTO `tb_sip_charge_details` VALUES ('24', '111', '2019-06-16 14:05:37', '2019-06-16 14:05:37', '29', '16', 'charge_item', '0', 'boarding_all,boarding_go,boarding_half', null, '加班费', '10.20', '爱爱', null);

-- ----------------------------
-- Table structure for tb_sip_charge_project
-- ----------------------------
DROP TABLE IF EXISTS `tb_sip_charge_project`;
CREATE TABLE `tb_sip_charge_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `logic_del` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_sip_charge_project
-- ----------------------------
INSERT INTO `tb_sip_charge_project` VALUES ('14', '111', '2019-06-15 01:57:36', '2019-06-15 05:42:47', '122fff', '2019-06-13 16:00:00', '2019-06-21 16:00:00', '11', null);
INSERT INTO `tb_sip_charge_project` VALUES ('15', '111', '2019-06-15 03:04:16', '2019-06-16 01:45:09', '11', '2019-06-06 16:00:00', '2019-06-21 16:00:00', '2222爱爱', null);
INSERT INTO `tb_sip_charge_project` VALUES ('22', '111', '2019-06-15 03:38:45', '2019-06-15 05:42:58', '11', '2019-06-05 16:00:00', '2019-06-13 16:00:00', '111', null);
INSERT INTO `tb_sip_charge_project` VALUES ('23', '111', '2019-06-15 03:38:55', '2019-06-15 03:38:55', '11', '2019-06-11 16:00:00', '2019-06-19 16:00:00', '11', null);
INSERT INTO `tb_sip_charge_project` VALUES ('24', '111', '2019-06-15 03:39:02', '2019-06-15 03:39:02', '11', '2019-06-12 16:00:00', '2019-06-27 16:00:00', '111', null);
INSERT INTO `tb_sip_charge_project` VALUES ('25', '111', '2019-06-15 03:39:12', '2019-06-15 03:39:12', '11', '2019-06-19 16:00:00', '2019-06-20 16:00:00', '11', null);
INSERT INTO `tb_sip_charge_project` VALUES ('26', '111', '2019-06-15 03:39:20', '2019-06-15 03:39:20', '11', '2019-06-20 16:00:00', '2019-06-27 16:00:00', '111', null);
INSERT INTO `tb_sip_charge_project` VALUES ('29', '111', '2019-06-15 12:07:37', '2019-06-15 12:07:37', '2019年上学期学费', '2019-06-12 16:00:00', '2019-06-27 16:00:00', '安安安', null);

-- ----------------------------
-- Table structure for tb_sip_sys_boarding_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_sip_sys_boarding_type`;
CREATE TABLE `tb_sip_sys_boarding_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(600) DEFAULT NULL,
  `logic_del` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_sip_sys_boarding_type
-- ----------------------------
INSERT INTO `tb_sip_sys_boarding_type` VALUES ('1', '111', '2019-06-19 13:55:35', '2019-06-11 13:55:39', '全托', 'boarding_all', '全托', null);
INSERT INTO `tb_sip_sys_boarding_type` VALUES ('2', '111', '2019-06-16 13:56:33', '2019-06-16 13:56:36', '半托', 'boarding_half', '半托', null);
INSERT INTO `tb_sip_sys_boarding_type` VALUES ('3', '111', '2019-06-16 13:57:48', '2019-06-16 13:57:57', '走读', 'boarding_go', '走读', null);

-- ----------------------------
-- Table structure for tb_sip_sys_routes
-- ----------------------------
DROP TABLE IF EXISTS `tb_sip_sys_routes`;
CREATE TABLE `tb_sip_sys_routes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `logic_del` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_sip_sys_routes
-- ----------------------------
INSERT INTO `tb_sip_sys_routes` VALUES ('1', '111', '2019-06-16 13:59:07', '2019-06-16 13:59:10', '交通路线1', 'route_1', '北京到上海', null);
INSERT INTO `tb_sip_sys_routes` VALUES ('2', '111', '2019-06-16 14:00:04', '2019-06-16 14:00:08', '交通路线1', 'route_2', '北京到上海2', null);

-- ----------------------------
-- Table structure for tb_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test` (
  `id` int(11) NOT NULL,
  `tenant_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_test
-- ----------------------------
INSERT INTO `tb_test` VALUES ('1', '22', '2019-06-08 17:30:06', '2019-06-19 17:30:09', null);
