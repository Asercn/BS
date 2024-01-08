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

 Date: 08/01/2024 14:39:14
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
  `sex` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 164 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `x_customer` VALUES (38, '123456789012323423', '234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (39, '360121200110220510', 'WW', '其它', '14785236998');
INSERT INTO `x_customer` VALUES (40, '123456789012345678', '王晓峰', '男', '12345678901');
INSERT INTO `x_customer` VALUES (41, '123456789012345678', '晓', '男', '12345678901');
INSERT INTO `x_customer` VALUES (42, '123456789012345678', '王', '男', '12345678901');
INSERT INTO `x_customer` VALUES (43, '123456789012345678', '长大', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (44, '123456789012345678', '滔搏', '男', '12345678901');
INSERT INTO `x_customer` VALUES (45, '123456789012345678', '测试', '女', '12345678901');
INSERT INTO `x_customer` VALUES (46, '123456789012345678', '艺人', '男', '12345678901');
INSERT INTO `x_customer` VALUES (47, '123456789012345678', '周末', '男', '12345678901');
INSERT INTO `x_customer` VALUES (48, '123456789012345678', 'wwe', '男', '12345678901');
INSERT INTO `x_customer` VALUES (49, '123456789012345678', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (50, '123456789012345678', '0123', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (51, '123456789012345678', '123', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (52, '123456789012345678', '124', '男', '15779851162');
INSERT INTO `x_customer` VALUES (53, '360423200011254212', '陈显洪', '男', '15779851162');
INSERT INTO `x_customer` VALUES (54, '123456789012345678', '123', '男', '15779851162');
INSERT INTO `x_customer` VALUES (55, '123456789012345678', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (56, '123456789012345678', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (57, '123456789012345678', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (58, '123456789012345678', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (59, '123456789012345678', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (60, '123456789012345678', '1234', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (61, '123456789012345678', '1234', '女', '12345678901');
INSERT INTO `x_customer` VALUES (62, '123456789012345678', '1234', '女', '12345678901');
INSERT INTO `x_customer` VALUES (63, '123456789012345678', '1234', '女', '12345678901');
INSERT INTO `x_customer` VALUES (64, '123456789012345678', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (65, '123456789012345678', '1234', '女', '12345678901');
INSERT INTO `x_customer` VALUES (66, '123456789012345678', 'asdf', '男', '12345679801');
INSERT INTO `x_customer` VALUES (67, '123456789012345678', '1234', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (68, '123456789012345678', '1234', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (69, '123456789012345678', '1234', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (70, '123456789012345678', '123', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (71, '123456781234567899', '123', '其它', '12345678901');
INSERT INTO `x_customer` VALUES (72, '123456789012345678', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (73, '123456789012345678', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (74, '123456789012345678', '1234', '男', '12345678909');
INSERT INTO `x_customer` VALUES (75, '123456789012345678', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (76, '123456789012345678', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (77, '123456789012345678', '1243', '男', '12345678909');
INSERT INTO `x_customer` VALUES (78, '123456789012345678', '1234', '男', '12435678909');
INSERT INTO `x_customer` VALUES (79, '123456789123456789', '1234', '男', '12345678900');
INSERT INTO `x_customer` VALUES (80, '123123132231231321', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (81, '123123132231231321', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (82, '123123132231231321', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (83, '123123132231231321', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (84, '123123132231231321', '1234', '男', '12345678901');
INSERT INTO `x_customer` VALUES (85, '123321123312123132', '测试1', '男', '12345678901');
INSERT INTO `x_customer` VALUES (86, '123456789123123123', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (87, '123456789123123123', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (88, '123456789123123123', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (89, '123456789123123123', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (90, '123456789123123123', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (91, '123456789123123123', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (92, '123456789123123123', '123', '男', '12345678901');
INSERT INTO `x_customer` VALUES (93, '132123132321132321', '123', '男', '13223132101');
INSERT INTO `x_customer` VALUES (94, '132123132321132321', '123', '男', '13223132101');
INSERT INTO `x_customer` VALUES (95, '132123132321132321', '123', '男', '13223132101');
INSERT INTO `x_customer` VALUES (96, '231132132321132312', '1243', '男', '13212332101');
INSERT INTO `x_customer` VALUES (97, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (98, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (99, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (100, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (101, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (102, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (103, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (104, '132321321213132132', '1234', '男', '12332112301');
INSERT INTO `x_customer` VALUES (105, '123132321132312312', '1234', '男', '12313213201');
INSERT INTO `x_customer` VALUES (106, '132123321321132123', '1423', '男', '12321332101');
INSERT INTO `x_customer` VALUES (107, '132321321321132132', '1234', '男', '12312332101');
INSERT INTO `x_customer` VALUES (108, '132213213132213312', '243', '男', '12312313201');
INSERT INTO `x_customer` VALUES (109, '132123321213321123', 'qwer', '男', '12312332101');
INSERT INTO `x_customer` VALUES (110, '132123321132312132', '14231243', '男', '12332131201');
INSERT INTO `x_customer` VALUES (111, '132123321132312132', '14231243', '男', '12332131201');
INSERT INTO `x_customer` VALUES (112, '132123321132312132', '14231243', '男', '12332131201');
INSERT INTO `x_customer` VALUES (113, '132123321132312132', '14231243', '男', '12332131201');
INSERT INTO `x_customer` VALUES (114, '213123321123213213', '1243', '男', '12312312301');
INSERT INTO `x_customer` VALUES (115, '321132312312321321', '1234', '男', '21332132101');
INSERT INTO `x_customer` VALUES (116, '132321321321321132', '1243', '男', '32112332101');
INSERT INTO `x_customer` VALUES (117, '132321321321321132', '1243', '男', '32112332101');
INSERT INTO `x_customer` VALUES (118, '312213213132321123', '1423', '男', '12313232101');
INSERT INTO `x_customer` VALUES (119, '312132132132132132', '213', '男', '13212312301');
INSERT INTO `x_customer` VALUES (120, '123213231132312231', '1423', '男', '12312312301');
INSERT INTO `x_customer` VALUES (121, '321231321312321132', '1234', '男', '32112321301');
INSERT INTO `x_customer` VALUES (122, '312132213321132132', '1243', '男', '12312312312');
INSERT INTO `x_customer` VALUES (123, '312132213321132132', '1243', '男', '12312312312');
INSERT INTO `x_customer` VALUES (124, '312132213321132132', '1243', '男', '12312312312');
INSERT INTO `x_customer` VALUES (125, '123132321123123132', '1234', '男', '13232113202');
INSERT INTO `x_customer` VALUES (126, '132132312312312132', '213', '男', '13213213201');
INSERT INTO `x_customer` VALUES (127, '132321321132132132', '213', '男', '32112313201');
INSERT INTO `x_customer` VALUES (128, '123123312321132132', '1234', '男', '12332112331');
INSERT INTO `x_customer` VALUES (129, '123123312321132132', '1234', '男', '12332112331');
INSERT INTO `x_customer` VALUES (130, '321123132123123321', '213', '男', '12313232101');
INSERT INTO `x_customer` VALUES (131, '132132312132132132', '231', '女', '12313231202');
INSERT INTO `x_customer` VALUES (132, '132123123123321132', '213', '男', '12313232102');
INSERT INTO `x_customer` VALUES (133, '123123132213321132', '1234', '男', '13213231202');
INSERT INTO `x_customer` VALUES (134, '123321132132321132', 'afds', '男', '12313232101');
INSERT INTO `x_customer` VALUES (135, '132123321321231321', '123', '其它', '21312332102');
INSERT INTO `x_customer` VALUES (136, '132123321123321321', '23', '男', '12323113202');
INSERT INTO `x_customer` VALUES (137, '123321132132321132', '123', '男', '23113232102');
INSERT INTO `x_customer` VALUES (138, '123123321321132321', '1234', '男', '12313212302');
INSERT INTO `x_customer` VALUES (139, '231123123213123132', '123', '男', '13213213202');
INSERT INTO `x_customer` VALUES (140, '321123132231321321', '213', '女', '13213212331');
INSERT INTO `x_customer` VALUES (141, '123321312312231312', '123', '男', '12345678909');
INSERT INTO `x_customer` VALUES (142, '123132132132132132', '123', '男', '12312312301');
INSERT INTO `x_customer` VALUES (143, '231123132132321123', '123', '男', '12321332102');
INSERT INTO `x_customer` VALUES (144, '132321213132123123', '123', '男', '12313213202');
INSERT INTO `x_customer` VALUES (145, '132213213213123321', '123', '男', '12321332102');
INSERT INTO `x_customer` VALUES (146, '231213321132132213', '123', '男', '12332123102');
INSERT INTO `x_customer` VALUES (147, '123231213132132213', '123', '男', '12313213202');
INSERT INTO `x_customer` VALUES (148, '123123231132231321', '123', '男', '12312313202');
INSERT INTO `x_customer` VALUES (149, '123132213123321123', '1234', '男', '12313213202');
INSERT INTO `x_customer` VALUES (150, '321132132132132132', '123', '男', '12312312302');
INSERT INTO `x_customer` VALUES (151, '123123123132123213', '213', '男', '12312313202');
INSERT INTO `x_customer` VALUES (152, '123312132132321123', '123', '男', '13213212302');
INSERT INTO `x_customer` VALUES (153, '123123132132123123', '123', '男', '13213221302');
INSERT INTO `x_customer` VALUES (154, '123321123132321312', '123', '男', '12331213202');
INSERT INTO `x_customer` VALUES (155, '123312312132312312', '123', '男', '12323132102');
INSERT INTO `x_customer` VALUES (156, '123123213312321321', '123', '男', '12312332121');
INSERT INTO `x_customer` VALUES (157, '123321132213123123', '123', '男', '12332113202');
INSERT INTO `x_customer` VALUES (158, '123132321132132321', '123', '男', '12332113202');
INSERT INTO `x_customer` VALUES (159, '123132321132321132', '231', '男', '13213213202');
INSERT INTO `x_customer` VALUES (160, '123123123321132321', '123', '男', '12313232102');
INSERT INTO `x_customer` VALUES (161, '123132132312132132', '测试', '男', '12331213202');
INSERT INTO `x_customer` VALUES (162, '213321213231321231', '陈先生', '男', '13221312331');
INSERT INTO `x_customer` VALUES (163, '123132132312123213', '陈显洪', '男', '12345678901');
INSERT INTO `x_customer` VALUES (164, '231231321132231321', '1', '其它', '11111111111');

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
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_customer_room
-- ----------------------------
INSERT INTO `x_customer_room` VALUES (1, '2023-10-26', '2023-08-24', 1, 1);
INSERT INTO `x_customer_room` VALUES (6, '2023-10-26', '2023-08-23', 1, 1);
INSERT INTO `x_customer_room` VALUES (7, '2023-10-26', '2023-08-24', 1, 1);
INSERT INTO `x_customer_room` VALUES (8, '2023-10-26', '2023-08-24', 3, 2);
INSERT INTO `x_customer_room` VALUES (10, '2023-10-26', '2023-08-28', 4, 2);
INSERT INTO `x_customer_room` VALUES (11, '2023-10-26', '2023-08-28', 5, 1);
INSERT INTO `x_customer_room` VALUES (12, '2023-10-26', '2023-08-30', 1, 4);
INSERT INTO `x_customer_room` VALUES (13, '2023-10-26', '2023-08-29', 3, 5);
INSERT INTO `x_customer_room` VALUES (14, '2023-10-26', '2023-08-29', 3, 6);
INSERT INTO `x_customer_room` VALUES (15, '2023-10-26', '2023-08-31', 1, 7);
INSERT INTO `x_customer_room` VALUES (16, '2023-10-26', '2023-08-31', 1, 8);
INSERT INTO `x_customer_room` VALUES (17, '2023-10-26', '2023-08-29', 1, 9);
INSERT INTO `x_customer_room` VALUES (18, '2023-10-26', '2023-08-28', 1, 10);
INSERT INTO `x_customer_room` VALUES (19, '2023-10-26', '2023-08-30', 1, 11);
INSERT INTO `x_customer_room` VALUES (20, '2023-10-26', '2023-08-30', 1, 12);
INSERT INTO `x_customer_room` VALUES (21, '2023-10-26', '2023-08-30', 1, 13);
INSERT INTO `x_customer_room` VALUES (22, '2023-10-26', '2023-08-30', 1, 14);
INSERT INTO `x_customer_room` VALUES (23, '2023-10-26', '2023-08-29', 1, 15);
INSERT INTO `x_customer_room` VALUES (24, '2023-10-26', '2023-08-29', 1, 16);
INSERT INTO `x_customer_room` VALUES (25, '2023-10-26', '2023-08-29', 1, 17);
INSERT INTO `x_customer_room` VALUES (26, '2023-10-26', '2023-08-30', 1, 18);
INSERT INTO `x_customer_room` VALUES (27, '2023-10-26', '2023-08-30', 1, 19);
INSERT INTO `x_customer_room` VALUES (28, '2023-10-26', '2023-08-30', 1, 20);
INSERT INTO `x_customer_room` VALUES (29, '2023-10-26', '2023-08-30', 1, 21);
INSERT INTO `x_customer_room` VALUES (30, '2023-10-26', '2023-08-30', 1, 22);
INSERT INTO `x_customer_room` VALUES (31, '2023-10-26', '2023-08-30', 1, 27);
INSERT INTO `x_customer_room` VALUES (32, '2023-10-26', '2023-08-30', 1, 28);
INSERT INTO `x_customer_room` VALUES (33, '2023-10-26', '2023-09-07', 1, 29);
INSERT INTO `x_customer_room` VALUES (34, '2023-10-26', '2023-08-30', 1, 30);
INSERT INTO `x_customer_room` VALUES (35, '2023-10-26', '2023-08-31', 3, 31);
INSERT INTO `x_customer_room` VALUES (36, '2023-10-26', '2023-08-31', 2, 32);
INSERT INTO `x_customer_room` VALUES (37, '2023-10-26', '2023-10-22', 16, 33);
INSERT INTO `x_customer_room` VALUES (38, '2023-10-26', '2023-10-13', 10, 34);
INSERT INTO `x_customer_room` VALUES (39, '2023-10-26', '2023-10-14', 16, 35);
INSERT INTO `x_customer_room` VALUES (40, '2023-10-26', '2023-10-14', 1, 36);
INSERT INTO `x_customer_room` VALUES (41, '2023-10-26', '2023-10-14', 1, 37);
INSERT INTO `x_customer_room` VALUES (42, '2023-10-26', '2023-10-26', 1, 38);
INSERT INTO `x_customer_room` VALUES (43, '2023-11-07', '2023-11-07', 1, 39);
INSERT INTO `x_customer_room` VALUES (44, '2023-11-11', '2023-11-14', 5, 40);
INSERT INTO `x_customer_room` VALUES (45, '2023-11-12', '2023-11-18', 16, 41);
INSERT INTO `x_customer_room` VALUES (46, '2023-11-12', '2023-11-21', 17, 42);
INSERT INTO `x_customer_room` VALUES (47, '2023-11-17', '2023-11-20', 16, 43);
INSERT INTO `x_customer_room` VALUES (48, '2023-11-18', '2023-11-26', 18, 44);
INSERT INTO `x_customer_room` VALUES (49, '2023-11-18', '2023-11-28', 3, 45);
INSERT INTO `x_customer_room` VALUES (50, '2023-11-18', '2023-11-27', 7, 46);
INSERT INTO `x_customer_room` VALUES (51, '2023-11-16', '2023-11-29', 9, 47);
INSERT INTO `x_customer_room` VALUES (52, '2023-12-04', '2023-12-13', 3, 48);
INSERT INTO `x_customer_room` VALUES (53, '2023-12-12', '2023-12-12', 4, 49);
INSERT INTO `x_customer_room` VALUES (54, '2023-12-12', '2023-12-12', 4, 50);
INSERT INTO `x_customer_room` VALUES (55, '2023-12-12', '2023-12-12', 4, 51);
INSERT INTO `x_customer_room` VALUES (56, '2023-12-12', '2023-12-12', 4, 52);
INSERT INTO `x_customer_room` VALUES (57, '2023-12-12', '2023-12-12', 4, 53);
INSERT INTO `x_customer_room` VALUES (58, '2023-12-12', '2023-12-12', 4, 54);
INSERT INTO `x_customer_room` VALUES (59, '2023-12-12', '2023-12-12', 4, 60);
INSERT INTO `x_customer_room` VALUES (60, '2023-12-11', '2023-12-12', 4, 64);
INSERT INTO `x_customer_room` VALUES (61, '2023-12-12', '2023-12-12', 4, 66);
INSERT INTO `x_customer_room` VALUES (62, '2023-12-11', '2023-12-12', 4, 70);
INSERT INTO `x_customer_room` VALUES (63, '2023-12-11', '2023-12-12', 4, 72);
INSERT INTO `x_customer_room` VALUES (64, '2023-12-11', '2023-12-12', 4, 73);
INSERT INTO `x_customer_room` VALUES (65, '2023-12-12', '2023-12-12', 4, 75);
INSERT INTO `x_customer_room` VALUES (66, '2023-12-12', '2023-12-12', 4, 77);
INSERT INTO `x_customer_room` VALUES (67, '2023-12-11', '2023-12-12', 4, 79);
INSERT INTO `x_customer_room` VALUES (68, '2023-12-12', '2023-12-12', 4, 85);
INSERT INTO `x_customer_room` VALUES (69, '2023-12-11', '2023-12-12', 18, 96);
INSERT INTO `x_customer_room` VALUES (70, '2023-12-11', '2023-12-12', 18, 105);
INSERT INTO `x_customer_room` VALUES (71, '2023-12-12', '2023-12-12', 4, 109);
INSERT INTO `x_customer_room` VALUES (72, '2023-12-11', '2023-12-12', 5, 108);
INSERT INTO `x_customer_room` VALUES (73, '2023-12-12', '2023-12-12', 4, 109);
INSERT INTO `x_customer_room` VALUES (74, '2023-12-12', '2023-12-12', 18, 115);
INSERT INTO `x_customer_room` VALUES (75, '2023-12-12', '2023-12-12', 18, 124);
INSERT INTO `x_customer_room` VALUES (76, '2023-12-12', '2023-12-12', 5, 120);
INSERT INTO `x_customer_room` VALUES (77, '2023-12-12', '2023-12-12', 18, 124);
INSERT INTO `x_customer_room` VALUES (78, '2023-12-12', '2023-12-12', 4, 125);
INSERT INTO `x_customer_room` VALUES (79, '2023-12-12', '2023-12-12', 4, 127);
INSERT INTO `x_customer_room` VALUES (80, '2023-12-12', '2023-12-12', 4, 129);
INSERT INTO `x_customer_room` VALUES (81, '2023-12-12', '2023-12-12', 4, 130);
INSERT INTO `x_customer_room` VALUES (82, '2023-12-12', '2023-12-12', 4, 131);
INSERT INTO `x_customer_room` VALUES (83, '2023-12-12', '2023-12-12', 18, 132);
INSERT INTO `x_customer_room` VALUES (84, '2023-12-12', '2023-12-12', 4, 133);
INSERT INTO `x_customer_room` VALUES (85, '2023-12-13', '2023-12-13', 3, 134);
INSERT INTO `x_customer_room` VALUES (86, '2023-12-13', '2023-12-13', 4, 135);
INSERT INTO `x_customer_room` VALUES (87, '2023-12-13', '2023-12-13', 18, 136);
INSERT INTO `x_customer_room` VALUES (88, '2023-12-13', '2023-12-13', 2, 137);
INSERT INTO `x_customer_room` VALUES (89, '2023-12-13', '2023-12-13', 3, 138);
INSERT INTO `x_customer_room` VALUES (90, '2023-12-13', '2023-12-15', 4, 139);
INSERT INTO `x_customer_room` VALUES (91, '2023-12-13', '2023-12-13', 3, 140);
INSERT INTO `x_customer_room` VALUES (92, '2023-12-13', '2023-12-13', 3, 141);
INSERT INTO `x_customer_room` VALUES (93, '2023-12-13', '2023-12-14', 3, 142);
INSERT INTO `x_customer_room` VALUES (94, '2023-12-13', '2023-12-13', 18, 143);
INSERT INTO `x_customer_room` VALUES (95, '2023-12-13', '2023-12-13', 5, 144);
INSERT INTO `x_customer_room` VALUES (96, '2023-12-13', '2023-12-13', 7, 145);
INSERT INTO `x_customer_room` VALUES (97, '2023-12-13', '2023-12-15', 7, 146);
INSERT INTO `x_customer_room` VALUES (98, '2023-12-13', '2023-12-13', 9, 147);
INSERT INTO `x_customer_room` VALUES (99, '2023-12-13', '2023-12-13', 2, 148);
INSERT INTO `x_customer_room` VALUES (100, '2023-12-12', '2023-12-15', 2, 149);
INSERT INTO `x_customer_room` VALUES (101, '2023-12-13', '2023-12-13', 5, 150);
INSERT INTO `x_customer_room` VALUES (102, '2023-12-13', '2023-12-13', 5, 151);
INSERT INTO `x_customer_room` VALUES (103, '2023-12-13', '2023-12-13', 5, 152);
INSERT INTO `x_customer_room` VALUES (104, '2023-12-13', '2023-12-13', 5, 153);
INSERT INTO `x_customer_room` VALUES (105, '2023-12-13', '2023-12-13', 5, 154);
INSERT INTO `x_customer_room` VALUES (106, '2023-12-13', '2023-12-13', 10, 155);
INSERT INTO `x_customer_room` VALUES (107, '2023-12-13', '2023-12-13', 20, 156);
INSERT INTO `x_customer_room` VALUES (108, '2023-12-13', '2023-12-13', 5, 157);
INSERT INTO `x_customer_room` VALUES (109, '2023-12-13', '2023-12-15', 5, 158);
INSERT INTO `x_customer_room` VALUES (110, '2023-12-13', '2023-12-13', 20, 159);
INSERT INTO `x_customer_room` VALUES (111, '2023-12-13', '2023-12-15', 20, 160);
INSERT INTO `x_customer_room` VALUES (112, '2023-12-14', '2023-12-17', 18, 161);
INSERT INTO `x_customer_room` VALUES (113, '2023-12-14', '2023-12-19', 9, 162);
INSERT INTO `x_customer_room` VALUES (114, '2024-01-06', '2024-01-07', 1, 163);
INSERT INTO `x_customer_room` VALUES (115, '2024-01-06', '2024-01-08', 2, 164);

-- ----------------------------
-- Table structure for x_menu
-- ----------------------------
DROP TABLE IF EXISTS `x_menu`;
CREATE TABLE `x_menu`  (
  `menu_id` int(0) NOT NULL AUTO_INCREMENT,
  `component` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `redirect` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `parent_id` int(0) NULL DEFAULT 0,
  `is_leaf` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'N',
  `hidden` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_menu
-- ----------------------------
INSERT INTO `x_menu` VALUES (2, 'Layout', '/room', '/room/boroom', 'room', '房间', 'tree', 0, 'N', 0);
INSERT INTO `x_menu` VALUES (3, 'room/boroom', 'boroom', '', 'boroom', '开房记录', 'el-icon-s-custom', 2, 'Y', 0);
INSERT INTO `x_menu` VALUES (4, 'room/oroom', 'oroom', '', 'oroom', '订房', 'el-icon-house', 2, 'Y', 0);
INSERT INTO `x_menu` VALUES (5, 'room/roommanagement', 'roommanagement', '', 'roommanagement', '房间设置', 'el-icon-s-home', 2, 'Y', 0);
INSERT INTO `x_menu` VALUES (6, 'Layout', '/system', '/system/user', 'system', '系统管理', 'el-icon-share', 0, 'N', 0);
INSERT INTO `x_menu` VALUES (7, 'system/user', 'user', '', 'user', '用户管理', 'el-icon-user', 6, 'Y', 0);
INSERT INTO `x_menu` VALUES (8, 'system/role', 'role', '', 'role', '角色管理', 'el-icon-s-custom', 6, 'Y', 0);
INSERT INTO `x_menu` VALUES (9, 'Layout', '/systemmonitor', '/systemmonitor/systeminterface', 'systemmonitor', '系统监控', 'el-icon-video-camera-solid', 0, 'N', 0);
INSERT INTO `x_menu` VALUES (10, 'systemmonitor/systeminterface', 'systeminterface', NULL, 'systeminterface', '系统接口', 'el-icon-pie-chart', 9, 'Y', 0);
INSERT INTO `x_menu` VALUES (11, 'systemmonitor/systeminfo', 'systeminfo', NULL, 'systeminfo', '系统信息', 'el-icon-tickets', 9, 'Y', 0);
INSERT INTO `x_menu` VALUES (13, 'system/controlmenu', 'controlmenu', NULL, 'controlmenu', '菜单控制', 'el-icon-s-operation', 6, 'Y', 0);
INSERT INTO `x_menu` VALUES (14, 'room/orderfrom', 'orderfrom', NULL, 'orderfrom', '订单记录', 'el-icon-s-order', 2, 'Y', 0);

-- ----------------------------
-- Table structure for x_orderfrom
-- ----------------------------
DROP TABLE IF EXISTS `x_orderfrom`;
CREATE TABLE `x_orderfrom`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `date` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `income` decimal(10, 2) NULL DEFAULT NULL,
  `order_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `order_source` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_orderfrom
-- ----------------------------
INSERT INTO `x_orderfrom` VALUES (1, '2023-12-12 20:00:18', 100.00, '1', NULL);
INSERT INTO `x_orderfrom` VALUES (2, '2023-12-13 20:10:48', 200.00, '3', NULL);
INSERT INTO `x_orderfrom` VALUES (3, '2023-12-13 21:01:16', 20172.00, '111', NULL);
INSERT INTO `x_orderfrom` VALUES (4, '2023-12-14 18:59:39', 360.00, '112', NULL);
INSERT INTO `x_orderfrom` VALUES (5, '2023-12-14 20:40:45', 500.00, '113', NULL);
INSERT INTO `x_orderfrom` VALUES (6, '2024-01-06 11:53:58', 100.00, '114', NULL);
INSERT INTO `x_orderfrom` VALUES (7, '2024-01-06 12:39:50', 240.00, '115', NULL);

-- ----------------------------
-- Table structure for x_role
-- ----------------------------
DROP TABLE IF EXISTS `x_role`;
CREATE TABLE `x_role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '_utf8mb4\\\'user\\\'',
  `role_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_role
-- ----------------------------
INSERT INTO `x_role` VALUES (1, 'admin', '超级管理员');
INSERT INTO `x_role` VALUES (2, 'hr', '人事管理');
INSERT INTO `x_role` VALUES (3, 'user', '普通用户');
INSERT INTO `x_role` VALUES (4, 'normal', '普通员工');
INSERT INTO `x_role` VALUES (7, 'test', '测试角色');

-- ----------------------------
-- Table structure for x_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `x_role_menu`;
CREATE TABLE `x_role_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NOT NULL,
  `menu_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_role_menu
-- ----------------------------
INSERT INTO `x_role_menu` VALUES (114, 1, 2);
INSERT INTO `x_role_menu` VALUES (115, 1, 3);
INSERT INTO `x_role_menu` VALUES (116, 1, 4);
INSERT INTO `x_role_menu` VALUES (117, 1, 5);
INSERT INTO `x_role_menu` VALUES (118, 1, 14);
INSERT INTO `x_role_menu` VALUES (119, 1, 6);
INSERT INTO `x_role_menu` VALUES (120, 1, 7);
INSERT INTO `x_role_menu` VALUES (121, 1, 8);
INSERT INTO `x_role_menu` VALUES (122, 1, 13);
INSERT INTO `x_role_menu` VALUES (123, 1, 9);
INSERT INTO `x_role_menu` VALUES (124, 1, 10);
INSERT INTO `x_role_menu` VALUES (125, 1, 11);

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `x_room` VALUES (18, '009', 120, '总统房');
INSERT INTO `x_room` VALUES (20, '测试房间', 10086, 'test');

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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_user
-- ----------------------------
INSERT INTO `x_user` VALUES (1, 'admin', '$2a$10$k.ZzSn2kyqV6vq1wjOBxve0fvml1e5sQZ9YuTzyI8fYkutPMPPbye', '534304548@qq.com', '15779851165', '1', 0, 1);
INSERT INTO `x_user` VALUES (2, 'wangwu', '$2a$10$wT2ZKhprqPza2nVgJwudxusK7TW/7Tt9.mON7IQP1o35dNOb.rKTa', '123@email.com', '12345678901', '1', 0, 1);
INSERT INTO `x_user` VALUES (3, 'zhangsan', '$2a$10$wT2ZKhprqPza2nVgJwudxusK7TW/7Tt9.mON7IQP1o35dNOb.rKTa', 'test@qwer.com', '12345678901', '1', 0, 1);
INSERT INTO `x_user` VALUES (4, 'lisi', '$2a$10$gehIMaKCP2EHj2nbTsKMWu9dKFe8O8b5GmbY.SelQkW.2mbkzg8vG', '123@test.com', '12345678911', '1', 0, 1);
INSERT INTO `x_user` VALUES (5, 'test1', '$2a$10$wT2ZKhprqPza2nVgJwudxusK7TW/7Tt9.mON7IQP1o35dNOb.rKTa', 'test1@test.com', '12345678912', '1', 0, 1);
INSERT INTO `x_user` VALUES (6, 'test241241', '$2a$10$wT2ZKhprqPza2nVgJwudxusK7TW/7Tt9.mON7IQP1o35dNOb.rKTa', '1233456@qq.com', '12345678902', '1', 1, 1);
INSERT INTO `x_user` VALUES (7, 'user1', '$2a$10$wT2ZKhprqPza2nVgJwudxusK7TW/7Tt9.mON7IQP1o35dNOb.rKTa', '123@q.com', '12345678901', NULL, 0, 1);
INSERT INTO `x_user` VALUES (8, 'HHH', '$2a$10$wT2ZKhprqPza2nVgJwudxusK7TW/7Tt9.mON7IQP1o35dNOb.rKTa', '1274321301@qq.com', '13345662626', NULL, 0, 1);
INSERT INTO `x_user` VALUES (9, 'test2', '$2a$10$FXSv2o0SyADSOUn0FJr2I.u.poaQMqO09yynGZdnZz27rNGXef76m', '123@test.com', '12345678903', NULL, 0, 1);
INSERT INTO `x_user` VALUES (12, 'test3', '$2a$10$2qskBQPjEfDrLPOW7Tzgp.yBEv1EY96BvrUYz/ToZ5yn3ZOqOVh0O', '534304548@qq.com', '12345678901', NULL, 0, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of x_user_role
-- ----------------------------
INSERT INTO `x_user_role` VALUES (1, 1, 1, 0);
INSERT INTO `x_user_role` VALUES (16, 5, 4, 0);
INSERT INTO `x_user_role` VALUES (17, 6, 4, 1);
INSERT INTO `x_user_role` VALUES (18, 3, 4, 0);
INSERT INTO `x_user_role` VALUES (19, 4, 1, 0);
INSERT INTO `x_user_role` VALUES (20, 2, 3, 0);
INSERT INTO `x_user_role` VALUES (21, 7, 3, 0);
INSERT INTO `x_user_role` VALUES (22, 8, 1, 0);
INSERT INTO `x_user_role` VALUES (23, 9, 4, 0);
INSERT INTO `x_user_role` VALUES (25, 12, 3, 0);

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
