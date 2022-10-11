/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : did

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2022-10-11 19:28:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for did_node
-- ----------------------------
DROP TABLE IF EXISTS `did_node`;
CREATE TABLE `did_node` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `node` char(66) NOT NULL COMMENT 'did编号',
  `parent_node` char(66) DEFAULT NULL COMMENT '父did编号',
  `owner` char(42) DEFAULT NULL COMMENT '地址',
  `expire` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `ttl` bigint(20) DEFAULT NULL COMMENT 'Time to live',
  `transfer` bigint(20) DEFAULT NULL COMMENT '变更时间',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '交易发生时间',
  `tx_hash` char(66) DEFAULT NULL COMMENT '交易hash',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_node` (`node`) USING BTREE,
  KEY `index_owner` (`owner`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='did_node 记录表';

-- ----------------------------
-- Records of did_node
-- ----------------------------

-- ----------------------------
-- Table structure for did_reverse
-- ----------------------------
DROP TABLE IF EXISTS `did_reverse`;
CREATE TABLE `did_reverse` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `owner` char(42) NOT NULL COMMENT '地址',
  `node` char(66) DEFAULT NULL COMMENT 'did编号',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '交易发生时间',
  `tx_hash` char(66) DEFAULT NULL COMMENT '交易hash',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_owner` (`owner`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='反向解析记录';

-- ----------------------------
-- Records of did_reverse
-- ----------------------------

-- ----------------------------
-- Table structure for divident
-- ----------------------------
DROP TABLE IF EXISTS `divident`;
CREATE TABLE `divident` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `node` char(66) NOT NULL COMMENT 'did编号',
  `owner` char(42) DEFAULT NULL COMMENT '地址',
  `user_signature` char(67) DEFAULT NULL COMMENT '用户签名',
  `time` bigint(20) DEFAULT NULL COMMENT '打卡申请时间(每个node每天只记录一次)',
  `gas` varchar(42) DEFAULT NULL COMMENT 'gas数量',
  `value` varchar(255) DEFAULT NULL COMMENT '分红额度',
  `platform_signature` char(67) DEFAULT NULL COMMENT '平台签名',
  `platform_address` char(42) DEFAULT NULL COMMENT '平台签名地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_node` (`node`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='divident分红表';

-- ----------------------------
-- Records of divident
-- ----------------------------

-- ----------------------------
-- Table structure for platform_income
-- ----------------------------
DROP TABLE IF EXISTS `platform_income`;
CREATE TABLE `platform_income` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `date` bigint(20) DEFAULT NULL COMMENT '记录那一天(从1970年1月1号作为第一天开始)',
  `income` varchar(32) DEFAULT NULL COMMENT '今日收益(单位ether)',
  `item` char(42) DEFAULT NULL COMMENT '地址',
  `accumulated` varchar(32) DEFAULT NULL COMMENT '收益累计',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '交易发生时间',
  `tx_hash` char(66) DEFAULT NULL COMMENT '交易hash',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记录了平台收入情况';

-- ----------------------------
-- Records of platform_income
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `config_key` varchar(255) NOT NULL COMMENT '配置key',
  `config_value` varchar(10240) DEFAULT NULL COMMENT '配置value',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_config_key` (`config_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'monitor_contract_address', '[\"0x6cC0cAd18cd71bD63D505B864643F8E81b893ecD\",\"0x7F5DeDa7bCeE0Aa44e64Ae0d980c45362C3Bb599\"]', '监控合约地址集合', '2022-09-17 16:37:58', '2022-10-11 07:50:40');
INSERT INTO `sys_config` VALUES ('2', 'monitor_block_height', '	7744226', '监控的区块高度', '2022-09-17 17:11:20', '2022-10-11 08:23:34');
INSERT INTO `sys_config` VALUES ('3', 'monitor_block_threshold', '128', '区块链的最大块高与监控块高之差小于这个值，则停止同步', '2022-09-17 17:15:18', '2022-09-17 17:15:18');
INSERT INTO `sys_config` VALUES ('4', 'monitor_event_parse_key', '[{\"0xc157eb3324da08e8457887d9341053619bf3ffa31a17ef836d3e3c1fe725da4a\":\"functionEventParseReverseService\"},{\"0x61010a411bace39b28736dfe49eab03289be1dc889203bb44efcb298cd3558db\":\"functionEventParseNodeService\"},{\"0xb31b9803c41115119bce23fa65fe38195a01d595c17187ec8f03a63293564f8a\":\"functionEventParsepPlatformIncomeService\"},{\"0x3eee8565afad5d48c7b6d6c96918f8d6b851054251f794aff90bc0804a6b3778\":\"functionEventParseUserDividentService\"}]', '方法签名和对应的解析处理类', '2022-09-22 11:01:11', '2022-10-11 08:08:24');

-- ----------------------------
-- Table structure for user_divident
-- ----------------------------
DROP TABLE IF EXISTS `user_divident`;
CREATE TABLE `user_divident` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `date` bigint(20) DEFAULT NULL COMMENT '记录那一天(从1970年1月1号作为第一天开始)',
  `amount` varchar(32) DEFAULT NULL COMMENT '金额(单位ether)',
  `owner` char(42) DEFAULT NULL COMMENT '地址',
  `node` varchar(66) DEFAULT NULL COMMENT 'node',
  `accumulated` varchar(32) DEFAULT NULL COMMENT '累计分红d',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '交易发生时间',
  `tx_hash` char(66) DEFAULT NULL COMMENT '交易hash',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记录用户领取分红相关信息';

-- ----------------------------
-- Records of user_divident
-- ----------------------------
