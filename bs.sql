/*
 Navicat Premium Data Transfer

 Source Server         : as200
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : bs

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 25/10/2023 18:27:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for x_customer
-- ----------------------------
DROP TABLE IF EXISTS `x_customer`;
CREATE TABLE `x_customer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `id_number` char(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_customer
-- ----------------------------
INSERT INTO `x_customer` VALUES (1, '123456789123456789', '张三', '男', '15779851162');
INSERT INTO `x_customer` VALUES (2, '15779851162', '显洪', '男', '15779851162');
INSERT INTO `x_customer` VALUES (3, '123456789123456789', 'test1', '男', '15779851162');
INSERT INTO `x_customer` VALUES (4, '360423200011254212', 'test2', '女', '15779851162');
INSERT INTO `x_customer` VALUES (5, '1234', '123', '男', '12345678911');
INSERT INTO `x_customer` VALUES (6, '1234', '123', '男', '12345678911');
INSERT INTO `x_customer` VALUES (7, '360423200011254212', '陈显洪', '男', '15779851162');
INSERT INTO `x_customer` VALUES (8, '360423200011254212', '陈显洪', '男', '15779851162');
INSERT INTO `x_customer` VALUES (9, '360423200011254212', '123', '男', '15779851162');
INSERT INTO `x_customer` VALUES (10, '123', '123', '男', '12345678911');
INSERT INTO `x_customer` VALUES (11, '123', '123', '男', '12345678911');
INSERT INTO `x_customer` VALUES (12, '1234', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (13, '1234', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (14, '1234', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (15, '1234', '1243', '男', '12345678901');
INSERT INTO `x_customer` VALUES (16, '1234', '1234', '男', '12345678900');
INSERT INTO `x_customer` VALUES (17, '124', '2345', '男', '12345678900');
INSERT INTO `x_customer` VALUES (18, '1234', '234', '女', '12345678901');
INSERT INTO `x_customer` VALUES (19, '1234', '123', '男', '12345678900');
INSERT INTO `x_customer` VALUES (20, '1234', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (21, '1234', '1234', '男', '12345678900');
INSERT INTO `x_customer` VALUES (22, '1234', '1234', '男', '12345678900');
INSERT INTO `x_customer` VALUES (23, '1234', '1234', '男', '12435678900');
INSERT INTO `x_customer` VALUES (24, '1234', '1234', '男', '12435678900');
INSERT INTO `x_customer` VALUES (25, '1234', '1234', '男', '12435678900');
INSERT INTO `x_customer` VALUES (26, '1234', '1234', '男', '12435678900');
INSERT INTO `x_customer` VALUES (27, '1234', '1324', '男', '12345678900');
INSERT INTO `x_customer` VALUES (28, '1234', '1234', '男', '12345678900');
INSERT INTO `x_customer` VALUES (29, '1234', '124', '男', '12345678900');
INSERT INTO `x_customer` VALUES (30, '123456', '1234', '女', '12345678900');
INSERT INTO `x_customer` VALUES (31, '1243', '1234', '男', '12345678900');
INSERT INTO `x_customer` VALUES (32, '1234', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (33, '360423200011254212', 'test', '男', '15779851162');
INSERT INTO `x_customer` VALUES (34, '123456789123456789', 'asdf', '男', '12345678901');
INSERT INTO `x_customer` VALUES (35, '123456789012355555', '5353', '男', '12345678901');
INSERT INTO `x_customer` VALUES (36, '360423200011254212', 'test', '男', '15779851162');
INSERT INTO `x_customer` VALUES (37, '360423200011254212', '1234', '男', '12345678902');

-- ----------------------------
-- Table structure for x_customer_room
-- ----------------------------
DROP TABLE IF EXISTS `x_customer_room`;
CREATE TABLE `x_customer_room`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `start_date` date NULL DEFAULT NULL,
  `end_date` date NULL DEFAULT NULL,
  `room_id` int(0) NOT NULL,
  `customer_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `room_id`(`room_id`) USING BTREE,
  INDEX `customer_id`(`customer_id`) USING BTREE,
  CONSTRAINT `x_customer_room_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `x_room` (`room_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `x_customer_room_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `x_customer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_customer_room
-- ----------------------------
INSERT INTO `x_customer_room` VALUES (1, '2023-08-23', '2023-08-24', 1, 1);
INSERT INTO `x_customer_room` VALUES (6, '2023-08-22', '2023-08-23', 1, 1);
INSERT INTO `x_customer_room` VALUES (7, '2023-08-11', '2023-08-24', 1, 1);
INSERT INTO `x_customer_room` VALUES (8, '2023-08-23', '2023-08-24', 3, 2);
INSERT INTO `x_customer_room` VALUES (10, '2023-08-27', '2023-08-28', 4, 2);
INSERT INTO `x_customer_room` VALUES (11, '2023-08-23', '2023-08-28', 5, 1);
INSERT INTO `x_customer_room` VALUES (12, '2023-08-21', '2023-08-30', 1, 4);
INSERT INTO `x_customer_room` VALUES (13, '2023-08-21', '2023-08-29', 3, 5);
INSERT INTO `x_customer_room` VALUES (14, '2023-08-21', '2023-08-29', 3, 6);
INSERT INTO `x_customer_room` VALUES (15, '2023-08-29', '2023-08-31', 1, 7);
INSERT INTO `x_customer_room` VALUES (16, '2023-08-29', '2023-08-31', 1, 8);
INSERT INTO `x_customer_room` VALUES (17, '2023-08-07', '2023-08-29', 1, 9);
INSERT INTO `x_customer_room` VALUES (18, '2023-08-07', '2023-08-28', 1, 10);
INSERT INTO `x_customer_room` VALUES (19, '2023-08-07', '2023-08-30', 1, 11);
INSERT INTO `x_customer_room` VALUES (20, '2023-08-14', '2023-08-30', 1, 12);
INSERT INTO `x_customer_room` VALUES (21, '2023-08-14', '2023-08-30', 1, 13);
INSERT INTO `x_customer_room` VALUES (22, '2023-08-29', '2023-08-30', 1, 14);
INSERT INTO `x_customer_room` VALUES (23, '2023-08-21', '2023-08-29', 1, 15);
INSERT INTO `x_customer_room` VALUES (24, '2023-08-28', '2023-08-29', 1, 16);
INSERT INTO `x_customer_room` VALUES (25, '2023-08-13', '2023-08-29', 1, 17);
INSERT INTO `x_customer_room` VALUES (26, '2023-08-30', '2023-08-30', 1, 18);
INSERT INTO `x_customer_room` VALUES (27, '2023-08-22', '2023-08-30', 1, 19);
INSERT INTO `x_customer_room` VALUES (28, '2023-08-21', '2023-08-30', 1, 20);
INSERT INTO `x_customer_room` VALUES (29, '2023-08-29', '2023-08-30', 1, 21);
INSERT INTO `x_customer_room` VALUES (30, '2023-08-29', '2023-08-30', 1, 22);
INSERT INTO `x_customer_room` VALUES (31, '2023-08-21', '2023-08-30', 1, 27);
INSERT INTO `x_customer_room` VALUES (32, '2023-08-28', '2023-08-30', 1, 28);
INSERT INTO `x_customer_room` VALUES (33, '2023-09-04', '2023-09-07', 1, 29);
INSERT INTO `x_customer_room` VALUES (34, '2023-08-29', '2023-08-30', 1, 30);
INSERT INTO `x_customer_room` VALUES (35, '2023-08-30', '2023-08-31', 3, 31);
INSERT INTO `x_customer_room` VALUES (36, '2023-08-28', '2023-08-31', 2, 32);
INSERT INTO `x_customer_room` VALUES (37, '2023-10-15', '2023-10-22', 16, 33);
INSERT INTO `x_customer_room` VALUES (38, '2023-10-12', '2023-10-13', 10, 34);
INSERT INTO `x_customer_room` VALUES (39, '2023-10-12', '2023-10-14', 16, 35);
INSERT INTO `x_customer_room` VALUES (40, '2023-10-12', '2023-10-14', 1, 36);
INSERT INTO `x_customer_room` VALUES (41, '2023-10-11', '2023-10-14', 1, 37);

-- ----------------------------
-- Table structure for x_role
-- ----------------------------
DROP TABLE IF EXISTS `x_role`;
CREATE TABLE `x_role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '_utf8mb4\\\'user\\\'',
  `role_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_role
-- ----------------------------
INSERT INTO `x_role` VALUES (1, 'admin', '超级管理员');
INSERT INTO `x_role` VALUES (2, 'hr', '人事管理');
INSERT INTO `x_role` VALUES (3, 'user', '访客');
INSERT INTO `x_role` VALUES (4, 'normal', '普通员工');

-- ----------------------------
-- Table structure for x_room
-- ----------------------------
DROP TABLE IF EXISTS `x_room`;
CREATE TABLE `x_room`  (
  `room_id` int(0) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `room_price` int(0) NOT NULL,
  `room_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE,
  UNIQUE INDEX `room_name`(`room_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_room
-- ----------------------------
INSERT INTO `x_room` VALUES (1, '001', 100, '单人房');
INSERT INTO `x_room` VALUES (2, '002', 120, '双人间');
INSERT INTO `x_room` VALUES (3, '003', 50, '麻将房');
INSERT INTO `x_room` VALUES (4, '004', 100, '电脑房');
INSERT INTO `x_room` VALUES (5, '100', 100, '大床房');
INSERT INTO `x_room` VALUES (7, '101', 100, '单人房');
INSERT INTO `x_room` VALUES (9, '103', 100, '双人间');
INSERT INTO `x_room` VALUES (10, '104', 100, '单人房');
INSERT INTO `x_room` VALUES (16, '111', 100, 'test12');
INSERT INTO `x_room` VALUES (17, '121', 100, 'test1');

-- ----------------------------
-- Table structure for x_user
-- ----------------------------
DROP TABLE IF EXISTS `x_user`;
CREATE TABLE `x_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `deleted` int(0) NULL DEFAULT 0,
  `state` int(0) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_user
-- ----------------------------
INSERT INTO `x_user` VALUES (1, 'admin', '123465', '534304548@qq.com', '15779851161', '1', 0, 1);
INSERT INTO `x_user` VALUES (2, 'wangwu', '123465', '123@email.com', '12345678901', '1', 0, 1);
INSERT INTO `x_user` VALUES (3, 'zhangsan', '123465', 'test@qwer.com', '12345678901', '1', 0, 1);
INSERT INTO `x_user` VALUES (4, 'lisi', '123465', '123@test.com', '12345678911', '1', 0, 1);
INSERT INTO `x_user` VALUES (5, 'test1', '123456', 'test1@test.com', '12345678912', '1', 1, 1);
INSERT INTO `x_user` VALUES (6, 'test24124', '123456', '1233456@qq.com', '12345678902', '1', 1, 1);

-- ----------------------------
-- Table structure for x_user_role
-- ----------------------------
DROP TABLE IF EXISTS `x_user_role`;
CREATE TABLE `x_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `deleted` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `x_user_role_ibfk_1`(`user_id`) USING BTREE,
  CONSTRAINT `x_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `x_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `x_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `x_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_user_role
-- ----------------------------
INSERT INTO `x_user_role` VALUES (1, 1, 1, 0);
INSERT INTO `x_user_role` VALUES (16, 5, 4, 1);
INSERT INTO `x_user_role` VALUES (17, 6, 3, 1);
INSERT INTO `x_user_role` VALUES (18, 3, 4, 0);
INSERT INTO `x_user_role` VALUES (19, 4, 4, 0);
INSERT INTO `x_user_role` VALUES (20, 2, 3, 0);

-- ----------------------------
-- Triggers structure for table x_customer_room
-- ----------------------------
DROP TRIGGER IF EXISTS `check_start_end`;
delimiter ;;
CREATE TRIGGER `check_start_end` BEFORE INSERT ON `x_customer_room` FOR EACH ROW BEGIN
		IF NEW.start_date > NEW.end_date THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'End time cannot be greater than start time';
		END IF;
	END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table x_customer_room
-- ----------------------------
DROP TRIGGER IF EXISTS `check_start_end_1`;
delimiter ;;
CREATE TRIGGER `check_start_end_1` BEFORE UPDATE ON `x_customer_room` FOR EACH ROW BEGIN
	IF NEW.start_date > NEW.end_date THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'End time cannot be greater than start time';
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
