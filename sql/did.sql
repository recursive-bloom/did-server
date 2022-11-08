/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : did

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2022-11-08 18:38:01
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
  `log_index` varchar(16) DEFAULT NULL COMMENT 'logIndex',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_tx_hash_log_index` (`tx_hash`,`log_index`) USING BTREE,
  KEY `index_owner` (`owner`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='did_node 记录表';

-- ----------------------------
-- Records of did_node
-- ----------------------------

-- ----------------------------
-- Table structure for did_node_kvdb
-- ----------------------------
DROP TABLE IF EXISTS `did_node_kvdb`;
CREATE TABLE `did_node_kvdb` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `node` char(66) NOT NULL COMMENT 'did编号',
  `owner` char(42) DEFAULT NULL COMMENT '地址',
  `item_key` char(64) DEFAULT NULL COMMENT '配置key',
  `value` blob COMMENT '配置value',
  `block_number` bigint(20) DEFAULT NULL COMMENT '区块高度',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '交易发生时间',
  `tx_hash` char(66) DEFAULT NULL COMMENT '交易hash',
  `log_index` varchar(16) DEFAULT NULL COMMENT 'logIndex',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_tx_hash_log_index` (`tx_hash`,`log_index`) USING BTREE,
  KEY `index_node_item_key` (`node`,`item_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记录了平台注册的did-node对应的属性信息';

-- ----------------------------
-- Records of did_node_kvdb
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
  `log_index` varchar(16) DEFAULT NULL COMMENT 'logIndex',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_tx_hash_log_index` (`tx_hash`,`log_index`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='反向解析记录';



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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'monitor_contract_address', '[\"0x979F0E7F6C5f1AEFcce39C63B9Ad454E9193f8F3\"]', '监控合约地址集合', '2022-09-17 16:37:58', '2022-11-03 14:50:22');
INSERT INTO `sys_config` VALUES ('2', 'monitor_block_height', '7884372', '监控的区块高度', '2022-09-17 17:11:20', '2022-11-03 21:12:55');
INSERT INTO `sys_config` VALUES ('3', 'monitor_block_threshold', '25', '区块链的最大块高与监控块高之差小于这个值，则停止同步', '2022-09-17 17:15:18', '2022-10-21 17:33:07');
INSERT INTO `sys_config` VALUES ('4', 'monitor_event_parse_key', '[{\"0xc157eb3324da08e8457887d9341053619bf3ffa31a17ef836d3e3c1fe725da4a\":\"functionEventParseReverseService\"},{\"0x61010a411bace39b28736dfe49eab03289be1dc889203bb44efcb298cd3558db\":\"functionEventParseNodeService\"},{\"0xc52012b86c69cfce891fe54be77078df6d4ccacc89f5c8d77317afbacd637816\":\"functionEventParsepPlatformIncomeService\"},{\"0x684686418e8029d6f30a561c9660198bd9c383773fb8fb45b26ab250b94d7be5\":\"functionEventParseUserDividentService\"},{\"0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef\":\"functionEventParseTransferService\"},{\"0xab03e3ad5c20af7ce35f6fd4d5408c4d94c8c1669699f56faa3845c9742ba515\":\"functionEventParseRecommenderService\"},{\"0xe255c1bf60a445e6dda1c9a0ba61c5df53ad62b94e7d12fbcc191a19a14a65b5\":\"functionEventParseDidNodeKvDbService\"}]', '方法签名和对应的解析处理类', '2022-09-22 11:01:11', '2022-11-03 14:29:03');
INSERT INTO `sys_config` VALUES ('5', 'block_req_host', 'https://eth-goerli.g.alchemy.com/v2/-s1zkDpkEmnjF4wIk8pLsiJBuxWelYV0', 'block请求host', '2022-10-12 21:26:21', '2022-10-12 21:26:21');

-- ----------------------------

