/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : cms

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 20/07/2020 19:54:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `categoryId` int(10) NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `viewCount` int(255) NULL DEFAULT NULL,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (47, 'spring', 1, 'rw', '2020-07-18 11:20:57', 11, '11111111');
INSERT INTO `article` VALUES (50, 'elasticsearch', 11, 'ktm', '2020-07-18 15:06:24', 5, '**Elasticsearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口**');
INSERT INTO `article` VALUES (51, 'Lucene', 11, 'ktm', '2020-07-18 15:07:46', 2, '**Lucene是apache软件基金会4 jakarta项目组的一个子项目，是一个开放源代码的全文检索引擎工具包，但它不是一个完整的全文检索引擎，而是一个全文检索引擎的架构，提供了完整的查询引擎和索引引擎，部分文本分析引擎（英文与德文两种西方语言）**');
INSERT INTO `article` VALUES (52, 'Java', 2, 'ktm', '2020-07-18 15:08:55', 2, '**Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征**');
INSERT INTO `article` VALUES (54, 'redis', 13, 'rw', '2020-07-18 21:06:47', 3, '**基于内存的非关系型数据库**');
INSERT INTO `article` VALUES (58, 'solr....', 11, 'ktm', '2020-07-19 20:14:35', 5, '**也是一种搜索引擎...**');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` int(10) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'java', 2, 'balabala');
INSERT INTO `category` VALUES (2, 'language', NULL, '编程语言');
INSERT INTO `category` VALUES (11, '搜索引擎', NULL, '搜索引擎(search engine)是指根据一定的策略、运用特定的计算机程序搜集互联网上的信息，在对信息进行组织和处理后，并将处理后的信息显示给用户，是为用户提供检索服务的系统');
INSERT INTO `category` VALUES (12, '数据库', NULL, '22222');
INSERT INTO `category` VALUES (13, 'NoSql', 12, '非关系型数据库');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleid` int(11) NOT NULL,
  `status` int(11) NULL DEFAULT 1,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (40, 50, 1, '干什么的\n', '2020-07-19 15:19:14');
INSERT INTO `comment` VALUES (43, 51, 1, '1111', '2020-07-19 15:36:30');
INSERT INTO `comment` VALUES (44, 52, 1, '2222', '2020-07-19 15:38:18');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'ktm', '123', 'https://avatars0.githubusercontent.com/u/60636088?s=460&u=a8c8dd7514265b52c5a7f85fdae9c7b80c37fe22&v=4', '康天明', '2413114403@qq.com');
INSERT INTO `user` VALUES (2, 'rw', '123', 'http://localhost:8099/4f0c9253-aa2f-489a-82f2-1463566248ce.jpg', 'rw', '888888888888@163.com');

SET FOREIGN_KEY_CHECKS = 1;
