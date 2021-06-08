/*
 Navicat Premium Data Transfer

 Source Server         : localhost【MySQL】
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : springboot_mybatis_vuejs

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 29/03/2021 01:22:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wangzhe', 'sdfjksjdfk2134', 'xiaoming', 20, '127389129083', 'sjkdfjks@qq.com', '1');
INSERT INTO `user` VALUES (2, 'xiaozhang123', 'sjsdfiwheho3223', 'xiaozhang', 19, '23732892389', 'sdiwn2@qq.com', '1');
INSERT INTO `user` VALUES (3, 'xxxx', 'xxxxxxx', 'xxxxxxx', 20, 'xxxxxxx', 'xxxxxxx', '1');
INSERT INTO `user` VALUES (10, 'sdf', 'sdf', 'sdf', 222, 'sdf', 'sdfsdfs', '1');

SET FOREIGN_KEY_CHECKS = 1;
