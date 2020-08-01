/*
 Navicat Premium Data Transfer

 Source Server         : T
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : mall_template

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 01/08/2020 16:13:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_address
-- ----------------------------
DROP TABLE IF EXISTS `sys_address`;
CREATE TABLE `sys_address`  (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收货地址编号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户账号',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户编号',
  `street_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `is_default` int(1) NULL DEFAULT NULL COMMENT '是否为默认',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_address
-- ----------------------------
INSERT INTO `sys_address` VALUES (1, 'admin', '1', '江南大道3588', '1385555', 0);
INSERT INTO `sys_address` VALUES (2, 'admin', '1', '3588恒生电子', '13812222', 0);
INSERT INTO `sys_address` VALUES (3, 'test', '2', '南湖春城', '123111111111', 1);
INSERT INTO `sys_address` VALUES (5, 'test', '2', '江南大8888道3588123123123', '13222222222', 1);
INSERT INTO `sys_address` VALUES (6, 'test', '2', '济南大学', '13222222222', 1);
INSERT INTO `sys_address` VALUES (7, 'test', '2', '济南大学', '13222222222', 1);
INSERT INTO `sys_address` VALUES (11, '1', '4', '1', '1', 0);
INSERT INTO `sys_address` VALUES (12, '2', '4', '2', '2', 1);

-- ----------------------------
-- Table structure for sys_cart
-- ----------------------------
DROP TABLE IF EXISTS `sys_cart`;
CREATE TABLE `sys_cart`  (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT ' 产品id',
  `product_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品图片',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `checked` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `product_num` int(11) NULL DEFAULT NULL COMMENT '产品数量',
  `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '产品价格',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_cart
-- ----------------------------
INSERT INTO `sys_cart` VALUES (11, 1, 1, 'http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg', '《深泽直人》', '0', 1, 199.00);
INSERT INTO `sys_cart` VALUES (12, 2, 1, 'http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg', '《深泽直人》', '0', 1, 199.00);

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `goods_list` varchar(8000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单商品',
  `order_total` decimal(10, 0) NULL DEFAULT NULL COMMENT '订单总价',
  `address_id` int(11) NULL DEFAULT NULL COMMENT '收货地址',
  `order_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单状态',
  `create_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES (9, 1, '[{\"cartId\":9,\"checked\":\"1\",\"productId\":1,\"productImg\":\"http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg\",\"productName\":\"《深泽直人》\",\"productNum\":5,\"productPrice\":199.00,\"userId\":1}]', 995, 1, '1', '20200801154604');
INSERT INTO `sys_order` VALUES (10, 1, '[{\"cartId\":10,\"checked\":\"1\",\"productId\":2,\"productImg\":\"http://image.smartisanos.cn/resource/902befd5f32a8caf4ca79b55d39ee25a.jpg\",\"productName\":\"坚果 Pro 软胶保护套\",\"productNum\":1,\"productPrice\":49.00,\"userId\":1}]', 49, 1, '1', '20200801160115');

-- ----------------------------
-- Table structure for sys_product
-- ----------------------------
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product`  (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `sale_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '零售价',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品名称',
  `product_image_small` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品小图',
  `product_image_big` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品大图\r\n',
  `product_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '产品详细信息',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `limit_num` int(11) NULL DEFAULT NULL COMMENT '限制购买数量',
  PRIMARY KEY (`product_id`) USING BTREE,
  UNIQUE INDEX `uk_product`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_product
-- ----------------------------
INSERT INTO `sys_product` VALUES (1, 199.00, '《深泽直人》', 'http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg', 'http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg', 'http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg,https://resource.smartisan.com/resource/63ea40e5c64db1c6b1d480c48fe01272.jpg,https://resource.smartisan.com/resource/4b8d00ab6ba508a977a475988e0fdb53.jpg,https://resource.smartisan.com/resource/87ea3888491d172b7d7a0e78e4294b4b.jpg,https://resource.smartisan.com/resource/8d30265188ddd1ba05e34f669c5dcf1c.jpg,http://image.smartisanos.cn/resource/902befd5f32a8caf4ca79b55d39ee25a.jpg', 999, '《深泽直人》', 99);
INSERT INTO `sys_product` VALUES (2, 49.00, '坚果 Pro 软胶保护套', 'http://image.smartisanos.cn/resource/902befd5f32a8caf4ca79b55d39ee25a.jpg', 'http://image.smartisanos.cn/resource/902befd5f32a8caf4ca79b55d39ee25a.jpg', 'http://image.smartisanos.cn/resource/902befd5f32a8caf4ca79b55d39ee25a.jpg,http://image.smartisanos.cn/resource/5e4e40120d09fb6791f9430f914c6f68.jpg', 100, '坚果 Pro 软胶保护套', 99);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `avatar` varchar(16000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登陆账号',
  `user_pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登陆密码',
  `cart_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '购物车',
  `address_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收获地址',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_user`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '张三', 'https://gitee.com/ching7777/gitee_graph_bed/raw/master/img/20200730105134.png', 'admin', 'admin', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '李四', 'https://gitee.com/ching7777/gitee_graph_bed/raw/master/img/20200730105134.png', 'test', 'test', NULL, NULL);
INSERT INTO `sys_user` VALUES (3, '游客20200729190028', 'https://gitee.com/ching7777/gitee_graph_bed/raw/master/img/20200729185413.png', 'test123', 'test', NULL, NULL);
INSERT INTO `sys_user` VALUES (4, '游客20200801161202', 'https://gitee.com/ching7777/gitee_graph_bed/raw/master/img/20200729185413.png', 't', 't', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
