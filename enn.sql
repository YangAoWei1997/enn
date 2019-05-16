/*
 Navicat Premium Data Transfer

 Source Server         : Test
 Source Server Type    : MySQL
 Source Server Version : 50096
 Source Host           : localhost:3306
 Source Schema         : enn

 Target Server Type    : MySQL
 Target Server Version : 50096
 File Encoding         : 65001

 Date: 15/05/2019 16:53:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ability
-- ----------------------------
DROP TABLE IF EXISTS `ability`;
CREATE TABLE `ability`  (
  `ability_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ability_value` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ability_ipId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ability
-- ----------------------------
INSERT INTO `ability` VALUES ('Arrangement', 'true', 'org1');
INSERT INTO `ability` VALUES ('BillOfLading', 'true', 'org1');
INSERT INTO `ability` VALUES ('PO', 'true', 'org1');
INSERT INTO `ability` VALUES ('PackingList', 'true', 'org1');
INSERT INTO `ability` VALUES ('Arrangement', 'false', '91120222MA05J9Y81T');
INSERT INTO `ability` VALUES ('BillOfLading', 'true', '91120222MA05J9Y81T');
INSERT INTO `ability` VALUES ('PO', 'true', '91120222MA05J9Y81T');
INSERT INTO `ability` VALUES ('PackingList', 'true', '91120222MA05J9Y81T');
INSERT INTO `ability` VALUES ('PackingList', 'false', 'org4');
INSERT INTO `ability` VALUES ('PO', 'false', 'org4');
INSERT INTO `ability` VALUES ('BillOfLading', 'false', 'org4');
INSERT INTO `ability` VALUES ('Arrangement', 'true', 'org4');
INSERT INTO `ability` VALUES ('Arrangement', 'false', 'org5');
INSERT INTO `ability` VALUES ('BillOfLading', 'false', 'org5');
INSERT INTO `ability` VALUES ('PO', 'false', 'org5');
INSERT INTO `ability` VALUES ('PackingList', 'false', 'org5');
INSERT INTO `ability` VALUES ('BillOfLading', 'false', 'org6');
INSERT INTO `ability` VALUES ('Arrangement', 'false', 'org6');
INSERT INTO `ability` VALUES ('PO', 'false', 'org6');
INSERT INTO `ability` VALUES ('PackingList', 'false', 'org6');

-- ----------------------------
-- Table structure for arrangement
-- ----------------------------
DROP TABLE IF EXISTS `arrangement`;
CREATE TABLE `arrangement`  (
  `arrangement_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `arrangement_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_buyerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_buyerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_sellerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_sellerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_issuedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_expireDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_expiredDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_productId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_customerArrangementId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_materialDetailId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_startDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_endDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_dataSourceId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `arrangement_dataSourceName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`arrangement_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of arrangement
-- ----------------------------
INSERT INTO `arrangement` VALUES ('a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'Arrangement', 'a', '55549100x', '新能（达旗）生物能源有限公司', '057808959', '内蒙古新能矿业有限公司', 'a', '2019-01-01', 'a', 'a', '1', '精煤', 'a', '7', '2019-01-01', '2020-01-01', NULL, NULL);
INSERT INTO `arrangement` VALUES ('b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'Arrangement', 'b', '55549100x', '新能（达旗）生物能源有限公司', '057808959', '内蒙古新能矿业有限公司', 'b', '2019-01-01', NULL, NULL, '1', '精煤', NULL, '', '2019-02-01', '2020-02-01', NULL, NULL);
INSERT INTO `arrangement` VALUES ('agreementoftest1', NULL, NULL, NULL, 'organization2', 'organization2', 'user1', 'user1', NULL, 'Arrangement', '煤', 'org4', 'org4', 'organization1', 'organization1', NULL, '2020-01-01', NULL, NULL, 'pro1', '混煤', NULL, '5dbf24eec4444ca3b411f6df4f3cf090', '2019-01-21', '2019-01-21', 'source1', 'source1');

-- ----------------------------
-- Table structure for billoflading
-- ----------------------------
DROP TABLE IF EXISTS `billoflading`;
CREATE TABLE `billoflading`  (
  `billoflading_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `billoflading_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_salesPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_buyerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_buyerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_sellerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_sellerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_barCode` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_issuedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_cardDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_productId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_productType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_quantity` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_quantityUnit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_carryPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_carrierId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_carrierName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_plateNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_driverId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_driverName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_relatedPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_carWeight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_weight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_emptyCarWeight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_purchasePONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_pickUpDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_dataSourceId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `billoflading_dataSourceName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`billoflading_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of billoflading
-- ----------------------------
INSERT INTO `billoflading` VALUES ('1', '1', '1', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'org1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-01-10', NULL, NULL);
INSERT INTO `billoflading` VALUES ('billoftest100', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, NULL, NULL, 'saleoftest100', '55549100x', '新能（达旗）生物能源有限公司', '057808959', '内蒙古新能矿业有限公司', NULL, '2019-01-10', NULL, 'p1', '精煤', NULL, '100', '吨', NULL, NULL, NULL, NULL, '蒙BS8888', NULL, NULL, NULL, NULL, '100', NULL, 'purchaseoftest100', '2019-01-10', NULL, NULL);
INSERT INTO `billoflading` VALUES ('billoftest201', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, NULL, NULL, 'saleoftest100', 'org1', 'organization2', '91120222MA05J9Y81T', '新能(天津)能源有限公司', NULL, '2019-01-10', NULL, '2', 'p2', NULL, '100', NULL, NULL, NULL, NULL, NULL, '蒙BS8889', NULL, NULL, NULL, NULL, NULL, NULL, 'purchaseoftest100', '2019-01-10', NULL, NULL);
INSERT INTO `billoflading` VALUES ('664571', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, '动力煤TA', '1001A11000000000REI1', '9005', '张家港保税区汇维国际贸易有限公司', '370481169909797', '新能矿业有限公司', NULL, NULL, NULL, '250101TA0001', '混煤', NULL, '', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, '', NULL, NULL, '', '', '新能矿业有限公司');

-- ----------------------------
-- Table structure for businessarea
-- ----------------------------
DROP TABLE IF EXISTS `businessarea`;
CREATE TABLE `businessarea`  (
  `ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channelName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chaincodePackageName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chaincodePackageVersion` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chaincodeDeployedName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `chaincodeDeployedVersion` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lastDeployedDateTime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of businessarea
-- ----------------------------
INSERT INTO `businessarea` VALUES ('area1', 'area-rename', 'some other comments', '', 'other name', 'v1.0.2', 'name1', 'v1.0.2', '2019-02-26');
INSERT INTO `businessarea` VALUES ('area2', 'area2', 'some comment', 'channel2', 'name2', 'v1.0.1', 'name2', 'v1.0.1', '2019-02-27');

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function`  (
  `funindex` int(30) NOT NULL AUTO_INCREMENT,
  `funid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `funname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `funurl` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `poweridfy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fundetail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`funindex`)
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES (1, '1', '质检结果比较', NULL, 'QualityListCompare', NULL);
INSERT INTO `function` VALUES (2, '2', '出入厂信息比较', NULL, 'WeightCompare', NULL);
INSERT INTO `function` VALUES (3, '3', '装箱单查询', NULL, 'QueryPackingListForSell', NULL);
INSERT INTO `function` VALUES (4, '4', '提货单查询', NULL, 'QueryBillOfLadingForSell', NULL);
INSERT INTO `function` VALUES (5, '', '排队信息查询', NULL, 'QueryQueuingInfo', NULL);
INSERT INTO `function` VALUES (6, '', '订单状态查询', NULL, 'QueryOrderStatus', NULL);
INSERT INTO `function` VALUES (7, '', '单车状态查询', NULL, 'QuerysingleCarSatus', NULL);
INSERT INTO `function` VALUES (8, '', '车辆位置查询', NULL, 'QueryArriveInfoForSell', NULL);
INSERT INTO `function` VALUES (9, '', '价格查询', NULL, 'QueryMineralResourcesInfo', NULL);
INSERT INTO `function` VALUES (10, '', '采购订单查询', NULL, 'Querypurchasingorder', NULL);
INSERT INTO `function` VALUES (11, '', '销售订单查询', NULL, 'QuerySalesOrder', NULL);
INSERT INTO `function` VALUES (12, '', '订单比较', NULL, 'OrderCompare', NULL);
INSERT INTO `function` VALUES (13, '', '周边矿源价格录入', NULL, 'CreateMineralResourcesInfo', NULL);
INSERT INTO `function` VALUES (14, '', '出厂质检单录入', NULL, 'CreateQualityListOfOut', NULL);
INSERT INTO `function` VALUES (15, '', '销售订单录入', NULL, 'CreateSalesOrder', NULL);
INSERT INTO `function` VALUES (16, '', '提货单录入', NULL, 'SellOrderForBill', NULL);
INSERT INTO `function` VALUES (17, '', '装箱单查询', NULL, 'QueryPackingListForBuy', NULL);
INSERT INTO `function` VALUES (18, '', '提货单查询', NULL, 'QueryBillOfLadingForBuy', NULL);
INSERT INTO `function` VALUES (19, '', '预警信息查询', NULL, 'QueryWarningInfo', NULL);
INSERT INTO `function` VALUES (21, '', '车辆位置查询', NULL, 'QueryArriveInfoForBuy', NULL);
INSERT INTO `function` VALUES (22, '', '提货单录入', NULL, 'PurchaseOrderForBill', NULL);
INSERT INTO `function` VALUES (23, '', '提货单绑定车牌号', NULL, 'BillOfLadingForBuyer', NULL);
INSERT INTO `function` VALUES (24, '', '到货单录入', NULL, 'CreateReceiveList', NULL);
INSERT INTO `function` VALUES (25, '', '装箱单查询', NULL, 'QueryPackingListForDrive', NULL);
INSERT INTO `function` VALUES (26, '', '提货单查询', NULL, 'QueryBillOfLadingForCarrier', NULL);
INSERT INTO `function` VALUES (27, '', '单车状态查询', NULL, 'QuerysingleCarSatusForDrive', NULL);
INSERT INTO `function` VALUES (28, '', '车辆位置查询', NULL, 'QueryArriveInfoForCarrier', NULL);
INSERT INTO `function` VALUES (29, '', '提货单绑定车牌号', NULL, 'BillOfLadingForDriver', NULL);
INSERT INTO `function` VALUES (30, '', '提货单查询', NULL, 'QueryBillOfLadingForDriver', NULL);
INSERT INTO `function` VALUES (31, '', '装箱单信息完善', NULL, 'CreatePacking', NULL);
INSERT INTO `function` VALUES (32, '', '入厂质检单录入', NULL, 'CreateQualityListOfEnter', NULL);
INSERT INTO `function` VALUES (33, '', '采购订单录入', NULL, 'CreatePurchaseOrder', NULL);
INSERT INTO `function` VALUES (34, '', '长协契约查询', NULL, 'QueryAgreement', NULL);
INSERT INTO `function` VALUES (35, '', '长协契约录入', NULL, 'CreateAgreement', NULL);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_buyerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_buyerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_sellerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_sellerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_ownerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_ownerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_managerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_managerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_carrierId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_carrierName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_plateNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_driverId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_driverName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_productId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_quantity` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_quantityUnit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_source` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_destination` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_relatedPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_billOfLadingId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_packingListIds` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_parentsGoodsId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_salesPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goods_purchasePONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`goods_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commnets` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shareDataTemplateTypeID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ownerID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ownerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createdDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `members` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ip
-- ----------------------------
DROP TABLE IF EXISTS `ip`;
CREATE TABLE `ip`  (
  `ip_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ip_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_contactID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_contactName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_contactPhone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_loginUser` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_loginPass` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_legal` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_registeredLocation` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_scopeOfBusiness` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_registeredCapital` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_abbreviation` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_publicKey` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ip_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ip
-- ----------------------------
INSERT INTO `ip` VALUES ('org1', 'org1', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i', 'i');
INSERT INTO `ip` VALUES ('org3', 'org3', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o');
INSERT INTO `ip` VALUES ('orgoftest001', '化plus', '', '', 'org2', '化plus', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO `ip` VALUES ('org2', '化plus', NULL, NULL, 'org2', '化plus', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ip` VALUES ('057808959', '内蒙古新能矿业有限公司', NULL, NULL, '057808959', '内蒙古新能矿业有限公司', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test');
INSERT INTO `ip` VALUES ('55549100x', '新能（达旗）生物能源有限公司', NULL, NULL, '55549100x', '新能（达旗）生物能源有限公司', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test');
INSERT INTO `ip` VALUES ('91120222MA05J9Y81T', '新能(天津)能源有限公司', NULL, NULL, '91120222MA05J9Y81T', '新能(天津)能源有限公司', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test', 'test');
INSERT INTO `ip` VALUES ('org4', 'org4', '', '', 'org4', 'org4', '11', '11', '11', 'IP', NULL, '11', '11', '11', NULL, NULL, '11', '11', '11', '11', '11', NULL);
INSERT INTO `ip` VALUES ('org5', 'org5', '', '', 'org5', 'org5', '11', '11', '11', 'IP', NULL, '11', '11', '11', NULL, NULL, '11', '11', '11', '11', '11', NULL);
INSERT INTO `ip` VALUES ('org6', 'org6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `key` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `keyName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`key`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location`  (
  `location_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location_contactName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location_contactPhone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location_ipId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('l1', 'lo', 'lo', '55549100x');
INSERT INTO `location` VALUES ('l2', 'll', 'll', 'org1');
INSERT INTO `location` VALUES ('l3', 'l3', 'l3', '057808959');
INSERT INTO `location` VALUES ('l4', 'l4', 'l4', 'org2');
INSERT INTO `location` VALUES ('beijing', 'beijing', '11111', 'orgoftest001');
INSERT INTO `location` VALUES ('shenzhen', 'shenzhen', '22222', 'orgoftest001');

-- ----------------------------
-- Table structure for materialdetail
-- ----------------------------
DROP TABLE IF EXISTS `materialdetail`;
CREATE TABLE `materialdetail`  (
  `materialdetail_id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `materialdetail_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_quantity` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_quantityUnit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_priceNoTax` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_priceWithTax` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_totalPriceNoTax` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_totalPriceWithTax` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_priceQualityUnit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_taxRate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_tax` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_masterId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_realTotalPrice` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `materialdetail_realPrice` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`materialdetail_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of materialdetail
-- ----------------------------
INSERT INTO `materialdetail` VALUES ('4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '元', '4', '4', '4', '4', '4');
INSERT INTO `materialdetail` VALUES ('7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '77', '7', '7', '7', '7', '77', '7', '元', '7', '7', '7', '7', '7');
INSERT INTO `materialdetail` VALUES ('1933fcbc57ae4d5e9732863d2e8d75dc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', '22', '22', '22', '22', '', NULL, NULL, NULL, '', '');
INSERT INTO `materialdetail` VALUES ('9e08d7880cd049c49b536bc271066573', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '34', '吨', '11', '11', '112', '112', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('ce073bbcb95a4f95a8fee56f09783b3b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '吨', NULL, NULL, '11', '11', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('f0498acb05de4338a80fa15a7d1aa14a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '35', '吨', '11', '11', '112', '114', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('2201e7054bfd446888e5075371202189', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '36', '吨', '11', '11', '112', '112', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('af13c60d5ef044608f699006c0a5530c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', '吨', '100', '97', '10000', '9700', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('de7ae307358c419da8a2e58854ad3bde', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', '吨', '100', '97', '10000', '9700', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('8116302c612b4f48bf8fd230c1002ece', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '36', '吨', '11', '11', '112', '112', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('1c1b75a4de2941c192a44327fe7ae4d8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0.0013', NULL, '14957.26495726', '17500.0', '19.44', '22.75', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('5dbf24eec4444ca3b411f6df4f3cf090', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '100', '吨', '', '100', '', '10000', '元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('ffdbc9b38158499a9c4f9ba74f676fcf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0.0013', NULL, '14957.26495726', '17500.0', '19', '22', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('e938b83d88ee44bbaff021bb66e76921', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '35', NULL, '11', '11', '112', '114', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('df84eb7d835d488b99ec345073d2b72a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0.0013', NULL, '14957.26495726', '17500.0', '19', '22', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('02824fb76dc349389bb9272feb27ed09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0.0013', NULL, '14957.26495726', '17500.0', '19', '22', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('c2156abe1b01478a94c0b9cf1beeb7ca', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '35', NULL, '11', '11', '112', '114', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('658a4436b96f428f929277c7c4729627', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0.0013', NULL, '14957.26495726', '17500.0', '19', '22', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('1f43107c231d45099e266e69acf23e99', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '35', NULL, '11', '11', '112', '114', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('94ef34f9a0eb43d789370bf664bf87e6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0.0013', NULL, '14957.26495726', '17500.0', '19', '22', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `materialdetail` VALUES ('15173fecc40e4076aabe524409d1039d', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '35', NULL, '11', '11', '112', '114', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for mineralresourcesinfo
-- ----------------------------
DROP TABLE IF EXISTS `mineralresourcesinfo`;
CREATE TABLE `mineralresourcesinfo`  (
  `localityInfoId` int(30) NOT NULL AUTO_INCREMENT,
  `locality` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `unitprice` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `commitdate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phonenumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `showname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `combustionvalue` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isCompany` int(10) NULL DEFAULT NULL,
  `dataSourceId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataSourceName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`localityInfoId`)
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor`  (
  `monitor_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `monitor_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_location` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_plateNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_issueDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `monitor_goodsId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`monitor_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for nodeorg
-- ----------------------------
DROP TABLE IF EXISTS `nodeorg`;
CREATE TABLE `nodeorg`  (
  `ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IP` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `port` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orgauth
-- ----------------------------
DROP TABLE IF EXISTS `orgauth`;
CREATE TABLE `orgauth`  (
  `orgauth_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orgauth_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_grantor` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_grantee` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_authType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_authDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_authStartTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_authEndTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgauth_key` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`orgauth_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orginfo
-- ----------------------------
DROP TABLE IF EXISTS `orginfo`;
CREATE TABLE `orginfo`  (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identify` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orginfo
-- ----------------------------
INSERT INTO `orginfo` VALUES ('1', '1', '1', '1');
INSERT INTO `orginfo` VALUES ('org2', '化plus', '化多多', '1');
INSERT INTO `orginfo` VALUES ('org222', '化工新能矿业', '化少少', '0');

-- ----------------------------
-- Table structure for packinglist
-- ----------------------------
DROP TABLE IF EXISTS `packinglist`;
CREATE TABLE `packinglist`  (
  `packinglist_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `packinglist_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_productId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_quantity` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_quantityUnit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_buyerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_buyerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_sellerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_sellerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_plateNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_billOfLadingId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_source` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_destination` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_relatedPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_packingType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_carrierName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_carrierId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_leavefirsttime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_leavefirstweight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_leavesecondtime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_leavesecondweight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_leavedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_reachedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_driverName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_driverId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_weight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_emptyCarWeight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_carWeight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_qualityId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_salesPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_purchasePONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_dataSourceId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packinglist_dataSourceName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`packinglist_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of packinglist
-- ----------------------------
INSERT INTO `packinglist` VALUES ('packing1', 'packing1', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'org1', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p', '', 'p', 'p', NULL, NULL);
INSERT INTO `packinglist` VALUES ('packoftest100', NULL, NULL, NULL, 'organization3', NULL, 'user3', NULL, NULL, 'PackingList', NULL, '1', '1', NULL, NULL, 'org1', 'org1', '91120222MA05J9Y81T', '新能(天津)能源有限公司', '蒙BS8888', 'billoftest100', NULL, NULL, '2019-01-03', NULL, 'packingList', 'organization3', 'organization3', NULL, '100', NULL, '200', '2019-01-03', NULL, 'driver1', 'user7', '100', NULL, NULL, '', 'saleoftest100', NULL, NULL, NULL);
INSERT INTO `packinglist` VALUES ('arriveoftest100', NULL, NULL, NULL, 'organization2', NULL, 'user2', NULL, NULL, 'PackingList', NULL, 'pro1', 'product1', NULL, NULL, 'organization2', 'organization2', 'organization1', 'organization1', '京BS9999', 'billoftest100', NULL, NULL, '2019-01-10', NULL, 'arrivalList', 'organization3', 'organization3', NULL, '102', NULL, '100', NULL, '2019-01-10', 'driver1', 'user7', '100', NULL, NULL, '', NULL, NULL, NULL, NULL);
INSERT INTO `packinglist` VALUES ('packoftest101', NULL, NULL, NULL, 'organization3', NULL, 'user3', NULL, NULL, 'PackingList', NULL, '1', '1', NULL, NULL, 'org1', 'org1', '91120222MA05J9Y81T', '新能(天津)能源有限公司', '蒙BS8888', 'billoftest100', NULL, NULL, '2019-01-03', NULL, 'packingList', 'organization3', 'organization3', NULL, '100', NULL, '200', '2019-01-03', NULL, 'driver1', 'user7', '100', NULL, NULL, '', 'saleoftest100', NULL, NULL, NULL);
INSERT INTO `packinglist` VALUES ('arriveoftest101', NULL, NULL, NULL, 'organization2', NULL, 'user2', NULL, NULL, 'PackingList', NULL, 'pro1', 'product1', NULL, NULL, 'organization2', 'organization2', 'organization1', 'organization1', '京BS9999', 'billoftest100', NULL, NULL, '2019-01-10', NULL, 'arrivalList', 'organization3', 'organization3', NULL, '102', NULL, '100', NULL, '2019-01-10', 'driver1', 'user7', '200', NULL, NULL, '', NULL, 'purchaseoftest100', NULL, NULL);
INSERT INTO `packinglist` VALUES ('packoftest102', NULL, NULL, NULL, 'organization3', NULL, 'user3', NULL, NULL, 'PackingList', NULL, '1', '1', NULL, NULL, 'org1', 'org1', '91120222MA05J9Y81T', '新能(天津)能源有限公司', '蒙BS8888', 'billoftest100', NULL, NULL, '2019-01-03', NULL, 'packingList', 'organization3', 'organization3', NULL, '100', NULL, '200', '2019-01-03', NULL, 'driver1', 'user7', '100', NULL, NULL, '', 'saleoftest100', NULL, NULL, NULL);
INSERT INTO `packinglist` VALUES ('arriveoftest102', NULL, NULL, NULL, 'organization2', NULL, 'user2', NULL, NULL, 'PackingList', NULL, 'pro1', 'product1', NULL, NULL, 'organization2', 'organization2', 'organization1', 'organization1', '京BS9999', 'billoftest100', NULL, NULL, '2019-01-10', NULL, 'arrivalList', 'organization3', 'organization3', NULL, '102', NULL, '100', NULL, '2019-01-10', 'driver1', 'user7', '200', NULL, NULL, '', NULL, 'purchaseoftest100', NULL, NULL);
INSERT INTO `packinglist` VALUES ('XC2019040100000151', NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, 'PackingList', '动力煤TA', '250101TA0002', '洗精末', NULL, NULL, '9005', '村民自用煤', '370481169909797', '新能矿业有限公司', '', NULL, NULL, NULL, '2019-04-01', NULL, 'packingList', '', '', NULL, '', NULL, '', NULL, NULL, NULL, NULL, '44.0', NULL, NULL, NULL, 'saleoftest100', NULL, '', '新能矿业有限公司');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `payment_id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `payment_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payerBankOfDeposit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payerAccountId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payeeId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payeeName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payeeBankOfDeposit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payeeAccountId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_payType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_reason` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_customerPaymentId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_poId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`payment_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES ('p1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3');
INSERT INTO `payment` VALUES ('p2', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '3');
INSERT INTO `payment` VALUES ('2204e0c5d20945158666e3e64b4c688d', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'saleoftest100');
INSERT INTO `payment` VALUES ('5f773f7f12b44d8092957410308b2935', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'purchaseoftest100');
INSERT INTO `payment` VALUES ('59861a989449487b940c0b2e14b36fee', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'saleoftest101');
INSERT INTO `payment` VALUES ('9b4df21a5b814bd29148410232e00de0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'saleoftest102');
INSERT INTO `payment` VALUES ('b7d7b8c47abf482e8bce9bd8292a58c8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'purchaseoftest101');
INSERT INTO `payment` VALUES ('3da8d9f7ebf742ada71a91fdee4e374e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'purchaseoftest300');
INSERT INTO `payment` VALUES ('93afe388eafd4a129ec4dc421524be19', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'purchaseoftest301');
INSERT INTO `payment` VALUES ('26bb004a94e04d4799245dce544c3ce8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'purchaseoftest104');
INSERT INTO `payment` VALUES ('47d1d67a1a0f4481ada6b904ab56c18b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CGDD201904160002');
INSERT INTO `payment` VALUES ('9f52734feffb48e9b5c54bf34368ee17', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CGDD201905141001');
INSERT INTO `payment` VALUES ('78c8ad0c3d4a4b28b60bfa68b9556f4c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'saleoftest0515001');
INSERT INTO `payment` VALUES ('d63bf58402414292a17249c08a2c7315', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CGDD201905141002');
INSERT INTO `payment` VALUES ('9bdeb79f247a4f59abad406fdba3ac51', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'cgdd001');
INSERT INTO `payment` VALUES ('a78cd716534d44a9b18045cc78567b06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xsdd001');
INSERT INTO `payment` VALUES ('ee1d7a9c4522492c87846d75e7a19a26', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'cgdd002');
INSERT INTO `payment` VALUES ('9d060478cc2049eeacf81c421cb51a5f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xsdd002');
INSERT INTO `payment` VALUES ('71e1e62d99a14a77a557a14b44c83600', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'cgdd003');
INSERT INTO `payment` VALUES ('e04ed20a74c84b5cbe68f6b97443542e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xsdd003');

-- ----------------------------
-- Table structure for paymentdetail
-- ----------------------------
DROP TABLE IF EXISTS `paymentdetail`;
CREATE TABLE `paymentdetail`  (
  `paymentdetail_id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `paymentdetail_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_paymentId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_amount` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_currency` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentdetail_customerPaymentId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`paymentdetail_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paymentdetail
-- ----------------------------
INSERT INTO `paymentdetail` VALUES ('pd1', 'name1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 'p1', '1', '1', '1', '1');
INSERT INTO `paymentdetail` VALUES ('pd2', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', 'p2', '6', '6', '6', '6');
INSERT INTO `paymentdetail` VALUES ('c23d895674fd4f949ea1a1860634b360', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2204e0c5d20945158666e3e64b4c688d', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('7de77fa4838c455aad22228cdefb8196', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5f773f7f12b44d8092957410308b2935', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('49576ac6e3c3422eb320fbf0667d591c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '59861a989449487b940c0b2e14b36fee', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('ae26db81e8bf4208a022dcec2acc8d2e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '9b4df21a5b814bd29148410232e00de0', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('ec1c883e88eb42a7a4078ec7de636e2f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'b7d7b8c47abf482e8bce9bd8292a58c8', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('eea22f6a38df48ba906eb64e8c62839a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3da8d9f7ebf742ada71a91fdee4e374e', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('34058e03d9694d87b2b7a75f9712da8c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '93afe388eafd4a129ec4dc421524be19', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('629284e14196447eacc3028a98043b2e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '26bb004a94e04d4799245dce544c3ce8', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('2403c380e7884d4d80d53e0cfb57ef6f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '47d1d67a1a0f4481ada6b904ab56c18b', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('822fc6fd40664756aeec45160d797e7a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '9f52734feffb48e9b5c54bf34368ee17', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('6c4faec256d444318560deb11ba73f3e', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '78c8ad0c3d4a4b28b60bfa68b9556f4c', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('b13e399f85f0432194151487068c340a', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'd63bf58402414292a17249c08a2c7315', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('cea87776203e4f50ae8bb4f8a0bafbce', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '9bdeb79f247a4f59abad406fdba3ac51', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('e6f8ea362fe84419b86b3c2756fc6b26', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'a78cd716534d44a9b18045cc78567b06', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('297f8995030b49cb9aa24302be3cb740', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ee1d7a9c4522492c87846d75e7a19a26', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('95f8b4c12dd645149055ffaa5f954ff9', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '9d060478cc2049eeacf81c421cb51a5f', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('a7863d9a061f4391b03728ba2f09e212', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '71e1e62d99a14a77a557a14b44c83600', NULL, NULL, NULL, NULL);
INSERT INTO `paymentdetail` VALUES ('dfeebb2e194d464eb59476d604935c19', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'e04ed20a74c84b5cbe68f6b97443542e', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan`  (
  `planindex` int(20) NOT NULL AUTO_INCREMENT,
  `ordertime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enorder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordered` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orderdate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY USING BTREE (`planindex`)
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES (1, '9:00-17:00', '20', '10', '2018-12-10', 'organization2');
INSERT INTO `plan` VALUES (2, '9:00-17:00', '21', '9', '2018-12-21', 'organization1');
INSERT INTO `plan` VALUES (3, '9:00-14:00', '22', '8', '2018-12-21', 'organization1');
INSERT INTO `plan` VALUES (4, '7:00-12:00', '4', '26', '2018-12-22', 'organization2');
INSERT INTO `plan` VALUES (5, '7:00-12:00', '10', '10', '2018-12-24', 'organization1');
INSERT INTO `plan` VALUES (6, '8:00-14:00', '20', '10', '2018-12-24', 'organization1');
INSERT INTO `plan` VALUES (7, '9:00-15:00', '10', '20', '2018-12-25', 'organization1');

-- ----------------------------
-- Table structure for po
-- ----------------------------
DROP TABLE IF EXISTS `po`;
CREATE TABLE `po`  (
  `po_id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `po_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_buyerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_buyerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_sellerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_sellerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_arrangementId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_customerArrangementId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_relatedPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_issuedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_productId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_productType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_carryPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_carrierId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_carrierName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_discontRate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_payType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_poType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_plateno` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_payTypeName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_salesPONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_purchasePONumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_materialDetailId` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_dataSourceId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_dataSourceName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_completeWeight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `po_carNumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY USING BTREE (`po_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of po
-- ----------------------------
INSERT INTO `po` VALUES ('3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', 'org1', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', NULL, NULL, NULL, '');
INSERT INTO `po` VALUES ('saleoftest100', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, 'PO', '煤', '55549100x', '新能（达旗）生物能源有限公司', '057808959', '内蒙古新能矿业有限公司', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', 'p1', '精煤', '煤', NULL, 'MA0N43CA6', '鄂尔多斯市新能物流有限公司', NULL, '2', 'sales', NULL, 'ddff', NULL, 'purchaseoftest100', '1933fcbc57ae4d5e9732863d2e8d75dc', 'source1', 'source1', '344.0', '3');
INSERT INTO `po` VALUES ('purchaseoftest100', NULL, NULL, NULL, 'organization2', NULL, 'user2', NULL, NULL, 'PO', '煤', '55549100x', '新能（达旗）生物能源有限公司', '057808959', '内蒙古新能矿业有限公司', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', 'p2', '混煤', '煤', NULL, 'MA0N43CA6', '鄂尔多斯市新能物流有限公司', NULL, '2', 'purchase', NULL, '现金', NULL, NULL, '9e08d7880cd049c49b536bc271066573', NULL, NULL, '400.0', '1');
INSERT INTO `po` VALUES ('saleoftest101', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, 'PO', '煤', 'org1', 'organization2', 'organization1370481169909797', 'organization1', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', '11', 'ddd', NULL, NULL, 'organization3', 'organization3', NULL, '2', 'sales', NULL, 'ddff', NULL, 'purchaseoftest100', 'ce073bbcb95a4f95a8fee56f09783b3b', 'source1', 'source1', NULL, '');
INSERT INTO `po` VALUES ('saleoftest102', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, 'PO', '煤', 'org1', 'organization2', 'organization1370481169909797', 'organization1', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', '11', 'ddd', NULL, NULL, 'organization3', 'organization3', NULL, '2', 'sales', NULL, 'ddff', NULL, 'purchaseoftest100', 'f0498acb05de4338a80fa15a7d1aa14a', 'source1', 'source1', NULL, '');
INSERT INTO `po` VALUES ('purchaseoftest101', NULL, NULL, NULL, 'organization2', NULL, 'user2', NULL, NULL, 'PO', NULL, 'org2', 'organization2', '370481169909797', 'organization1', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', 'product5', 'product5', NULL, NULL, 'organization3', 'organization3', NULL, '2', 'purchase', NULL, '现金', NULL, NULL, '2201e7054bfd446888e5075371202189', NULL, NULL, NULL, '');
INSERT INTO `po` VALUES ('purchaseoftest300', NULL, NULL, NULL, '91120222MA05J9Y81T', NULL, '1', NULL, NULL, 'PO', NULL, 'org1', 'org1', '91120222MA05J9Y81T', '新能(天津)能源有限公司', NULL, 'a1', NULL, NULL, '2018-11-22', '1', 'p1', '精煤', NULL, NULL, NULL, 'carr', NULL, '2', 'purchase', NULL, 'ddff', NULL, NULL, 'af13c60d5ef044608f699006c0a5530c', NULL, NULL, NULL, '');
INSERT INTO `po` VALUES ('purchaseoftest301', NULL, NULL, NULL, '91120222MA05J9Y81T', NULL, '1', NULL, NULL, 'PO', NULL, 'org1', 'org1', '91120222MA05J9Y81T', '新能(天津)能源有限公司', NULL, 'a1', NULL, NULL, '2018-11-22', '1', 'p1', '精煤', NULL, NULL, NULL, 'carr', NULL, '2', 'purchase', NULL, 'ddff', NULL, NULL, 'de7ae307358c419da8a2e58854ad3bde', NULL, NULL, NULL, '');
INSERT INTO `po` VALUES ('purchaseoftest104', NULL, NULL, NULL, 'organization2', NULL, 'user2', NULL, NULL, 'PO', NULL, 'org2', '111', '370481169909797', '111', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', 'product5', 'product5', NULL, NULL, 'organization3', 'organization3', NULL, '2', 'purchase', NULL, '现金', NULL, NULL, '8116302c612b4f48bf8fd230c1002ece', NULL, NULL, NULL, '');
INSERT INTO `po` VALUES ('CGDD201904160002', NULL, NULL, NULL, '', NULL, '', NULL, NULL, 'PO', '平钢板类', '9005', '新能凤凰(滕州)能源有限公司', '370481169909797', '滕州市五交化集团总公司五交化商场', NULL, '9005-2017AQ-0476', NULL, NULL, '2019-04-16', '审批通过', 'X0102010013', '钢板', NULL, NULL, NULL, '', NULL, '', 'purchase', NULL, '', NULL, NULL, '1c1b75a4de2941c192a44327fe7ae4d8', '', '', NULL, NULL);
INSERT INTO `po` VALUES ('CGDD201905141001', NULL, NULL, NULL, '', NULL, '', NULL, NULL, 'PO', '平钢板类', '9005', '新能凤凰(滕州)能源有限公司', '370481169909797', '滕州市五交化集团总公司五交化商场', NULL, '9005-2017AQ-0476', NULL, NULL, '2019-04-16', '审批通过', 'X0102010013', '钢板', NULL, NULL, NULL, '', NULL, '', 'purchase', NULL, '', NULL, NULL, 'ffdbc9b38158499a9c4f9ba74f676fcf', '9005', '', NULL, NULL);
INSERT INTO `po` VALUES ('saleoftest0515001', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, 'PO', '煤', '9005', '新能凤凰(滕州)能源有限公司', '370481169909797', '滕州市五交化集团总公司五交化商场', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', '11', 'ddd', NULL, NULL, '', '', NULL, '2', 'sales', NULL, 'ddff', NULL, 'CGDD201905141001', 'e938b83d88ee44bbaff021bb66e76921', '370481169909797', 'source1', NULL, NULL);
INSERT INTO `po` VALUES ('CGDD201905141002', NULL, NULL, NULL, '9005', NULL, '', NULL, NULL, 'PO', '平钢板类', '9005', '新能凤凰(滕州)能源有限公司', 'org1', 'org1', NULL, '9005-2017AQ-0476', NULL, NULL, '2019-04-16', '审批通过', 'X0102010013', '钢板', NULL, NULL, NULL, '', NULL, '', 'purchase', NULL, '', NULL, NULL, 'df84eb7d835d488b99ec345073d2b72a', '', '', NULL, NULL);
INSERT INTO `po` VALUES ('cgdd001', NULL, NULL, NULL, 'b', NULL, '', NULL, NULL, 'PO', '平钢板类', 'b', 'b', 'a', 'a', NULL, '9005-2017AQ-0476', NULL, NULL, '2019-04-16', '审批通过', 'X0102010013', '钢板', NULL, NULL, NULL, '', NULL, '', 'purchase', NULL, '', NULL, NULL, '02824fb76dc349389bb9272feb27ed09', 'b', '', NULL, NULL);
INSERT INTO `po` VALUES ('xsdd001', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, 'PO', '煤', 'b', 'b', 'a', 'a', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', '11', 'ddd', NULL, NULL, '', '', NULL, '2', 'sales', NULL, 'ddff', NULL, 'cgdd001', 'c2156abe1b01478a94c0b9cf1beeb7ca', 'a', 'a', NULL, NULL);
INSERT INTO `po` VALUES ('cgdd002', NULL, NULL, NULL, 'b', NULL, '', NULL, NULL, 'PO', '平钢板类', 'c', 'c', 'b', 'b', NULL, '9005-2017AQ-0476', NULL, NULL, '2019-04-16', '审批通过', 'X0102010013', '钢板', NULL, NULL, NULL, '', NULL, '', 'purchase', NULL, '', NULL, NULL, '658a4436b96f428f929277c7c4729627', 'c', '', NULL, NULL);
INSERT INTO `po` VALUES ('xsdd002', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, 'PO', '煤', 'c', 'c', 'b', 'b', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', '11', 'ddd', NULL, NULL, '', '', NULL, '2', 'sales', NULL, 'ddff', NULL, 'cgdd002', '1f43107c231d45099e266e69acf23e99', 'b', 'b', NULL, NULL);
INSERT INTO `po` VALUES ('cgdd003', NULL, NULL, NULL, 'b', NULL, '', NULL, NULL, 'PO', '平钢板类', 'c', 'c', 'a', 'a', NULL, '9005-2017AQ-0476', NULL, NULL, '2019-04-16', '审批通过', 'X0102010013', '钢板', NULL, NULL, NULL, '', NULL, '', 'purchase', NULL, '', NULL, NULL, '94ef34f9a0eb43d789370bf664bf87e6', 'c', '', NULL, NULL);
INSERT INTO `po` VALUES ('xsdd003', NULL, NULL, NULL, 'organization1', NULL, 'user1', NULL, NULL, 'PO', '煤', 'c', 'c', 'a', 'a', NULL, 'arrangementoftest100', NULL, NULL, '2019-01-11', '1', '11', 'ddd', NULL, NULL, '', '', NULL, '2', 'sales', NULL, 'ddff', NULL, 'cgdd003', '15173fecc40e4076aabe524409d1039d', 'a', 'a', NULL, NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_price` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_customerProductId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_ipId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_priceDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`product_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('p1', '精煤', 'p', 'p', '55549100x', '新能（达旗）生物能源有限公司', 'p', 'p', 'p', 'p', '煤', 'p', 'p', '100', 'p', '55549100x', '2019-01-01');
INSERT INTO `product` VALUES ('p2', '混煤', 'pp', 'pp', '55549100x', '新能（达旗）生物能源有限公司', 'pp', 'pp', 'pp', 'pp', '煤', 'pp', 'pp', '200', 'pp', '55549100x', '2019-01-01');
INSERT INTO `product` VALUES ('p3', '精煤2', 'r', 'r', '057808959', '内蒙古新能矿业有限公司', 'r', 'r', 'r', 'r', '煤', 'r', 'r', '300', 'r', '057808959', '2019-01-01');
INSERT INTO `product` VALUES ('p4', '混煤2', 'rr', 'rr', '057808959', '内蒙古新能矿业有限公司', 'rr', 'rr', 'rr', 'rr', '煤', 'rr', 'rr', '400', 'rr', '057808959', '2019-02-01');
INSERT INTO `product` VALUES ('p3z', 'p3z', 'rz', 'z', 'rz', 'rz', 'rz', 'rz', 'rz', 'z', 'z', 'rz', 'z', 'rz', 'z', 'orgoftest001', NULL);
INSERT INTO `product` VALUES ('p4z', 'p4', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'rrz', 'orgoftest001', NULL);
INSERT INTO `product` VALUES ('pro1', '精煤', NULL, NULL, 'org1', 'org1', NULL, NULL, NULL, NULL, '煤', NULL, NULL, '200', NULL, 'org1', '2019-02-01');
INSERT INTO `product` VALUES ('pro2', '混煤', NULL, NULL, 'org1', 'org1', NULL, NULL, NULL, NULL, '煤', NULL, NULL, '150', NULL, 'org1', '2019-01-01');

-- ----------------------------
-- Table structure for qualityinfo
-- ----------------------------
DROP TABLE IF EXISTS `qualityinfo`;
CREATE TABLE `qualityinfo`  (
  `qualityinfo_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `qualityinfo_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_validator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_validatorName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_qualityDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_serialNumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_quality` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_productId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_plateNo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_orgname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_qualityType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_orgId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_reportQualityDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_source` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_dataSourceId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qualityinfo_dataSourceName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`qualityinfo_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qualityinfo
-- ----------------------------
INSERT INTO `qualityinfo` VALUES ('repoftest1001', NULL, NULL, NULL, 'organization2', NULL, 'user2', NULL, NULL, 'Quality', NULL, NULL, NULL, '2018-12-26', NULL, 'mi:20;aad:10;vad:20;fcad:20;si:20;had:10;coalt1:10;coalt2:20;coalt3:20;coalt4:20;calorificqbad:20;calorificvalue:20;', '1', NULL, 'org1', 'product1', 'enterQuality', 'org1', '2018-12-26', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for queuing
-- ----------------------------
DROP TABLE IF EXISTS `queuing`;
CREATE TABLE `queuing`  (
  `infoid` int(20) NOT NULL AUTO_INCREMENT,
  `listcount` int(20) NULL DEFAULT NULL,
  `orgid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataSourceId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataSourceName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`infoid`)
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of queuing
-- ----------------------------
INSERT INTO `queuing` VALUES (2, 10, 'organization1', NULL, NULL, NULL);
INSERT INTO `queuing` VALUES (5, 16, 'o2', NULL, NULL, NULL);
INSERT INTO `queuing` VALUES (1, 17, 'o1', NULL, NULL, NULL);
INSERT INTO `queuing` VALUES (3, 11, 'organization2', NULL, NULL, NULL);
INSERT INTO `queuing` VALUES (4, 12, 'organization3', NULL, NULL, NULL);
INSERT INTO `queuing` VALUES (6, 13, 'organization4', NULL, NULL, NULL);
INSERT INTO `queuing` VALUES (7, 14, 'organization5', NULL, NULL, NULL);
INSERT INTO `queuing` VALUES (8, 15, 'organization6', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for relationinfo
-- ----------------------------
DROP TABLE IF EXISTS `relationinfo`;
CREATE TABLE `relationinfo`  (
  `relationinfo_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `relationinfo_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_ownerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_ownerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_friendId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_friendName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_issuedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_expiredDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `relationinfo_reason` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`relationinfo_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of relationinfo
-- ----------------------------
INSERT INTO `relationinfo` VALUES ('9ea69259eb3e4a1eb5a4d560e7c9fd82', NULL, NULL, NULL, 'org2', '化plus', 'user2', '化plus2', NULL, NULL, NULL, 'user2', '化plus2', 'user1', '化多多1', '已失效', '2019-03-21', '', '手动取消');

-- ----------------------------
-- Table structure for requestinfo
-- ----------------------------
DROP TABLE IF EXISTS `requestinfo`;
CREATE TABLE `requestinfo`  (
  `requestinfo_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requestinfo_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_ownerId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_ownerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_friendId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_friendName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestinfo_issuedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`requestinfo_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of requestinfo
-- ----------------------------
INSERT INTO `requestinfo` VALUES ('00bcff8206084ef5bdaf48795320af15', NULL, NULL, NULL, 'org1', '化多多', 'user1', '化多多1', NULL, NULL, NULL, 'user2', '化plus2', 'user1', '化多多1', '已通过', '2019-03-20');
INSERT INTO `requestinfo` VALUES ('5afbf227342f49ad9ebf57ef433d3fd9', NULL, NULL, NULL, 'org1', '化多多', 'user1', '化多多1', NULL, NULL, NULL, 'org2', '化plus', 'org1', '化多多', '待验证', '2019-03-25');

-- ----------------------------
-- Table structure for requestoforg
-- ----------------------------
DROP TABLE IF EXISTS `requestoforg`;
CREATE TABLE `requestoforg`  (
  `requestoforg_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requestoforg_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_version` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_organizationName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_userName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_privateId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_requestOrgId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_requestOrgName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_issuedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_dataSource` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestoforg_contact` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`requestoforg_id`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of requestoforg
-- ----------------------------
INSERT INTO `requestoforg` VALUES ('6f1c8b13f6b94849bc355eac6e6bfa75', NULL, NULL, NULL, 'org1', 'org1', '', '', NULL, NULL, NULL, 'org1', 'org1', '已通过', '2019-04-08', '化多多', '');
INSERT INTO `requestoforg` VALUES ('b1c957fd1c4241d4823394fe8e072c1c', NULL, NULL, NULL, 'org6', 'org6', '', '', NULL, NULL, NULL, 'org6', 'org6', '已通过', '2019-04-23', '化多多', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleindex` int(30) NOT NULL AUTO_INCREMENT,
  `roleId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rolename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roledetail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `organizationName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY USING BTREE (`roleindex`),
  INDEX `userindex` USING BTREE(`userId`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '1', 'o1', 'role1', '', '1', 'org1');
INSERT INTO `role` VALUES (2, '2', 'o1', 'role2', NULL, '1', 'org1');
INSERT INTO `role` VALUES (3, '3', 'o2', 'role2', NULL, '2', 'org2');
INSERT INTO `role` VALUES (4, '4', 'o4', 'role3', NULL, '4', 'org4');
INSERT INTO `role` VALUES (5, '5', 'o1', 'role3', NULL, '1', 'org1');
INSERT INTO `role` VALUES (6, '6', 'o3', 'role1', NULL, '3', 'org3');
INSERT INTO `role` VALUES (7, '7', 'o3', 'role2', 'detail', 'u3', 'org3');
INSERT INTO `role` VALUES (8, '8', '057808959', 'role1', NULL, 'xnky1', '内蒙古新能矿业有限公司');
INSERT INTO `role` VALUES (9, '9', '55549100x', 'role1', NULL, 'daqi1', '新能（达旗）生物能源有限公司');
INSERT INTO `role` VALUES (10, '10', 'MA0N43CA6', 'role1', NULL, 'xnwl1', '鄂尔多斯市新能物流有限公司');
INSERT INTO `role` VALUES (11, '11', 'MA0N43CA6', 'role1', NULL, 'xnwl2', '鄂尔多斯市新能物流有限公司');
INSERT INTO `role` VALUES (12, '12', 'MA0N43CA6', 'role1', NULL, 'xnwl3', '鄂尔多斯市新能物流有限公司');
INSERT INTO `role` VALUES (13, '13', 'wqy', 'role1', NULL, 'wbuser1', '无企业');
INSERT INTO `role` VALUES (14, '14', 'xsqy1', 'role1', NULL, 'seller1', '销售企业1');

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function`  (
  `relindex` int(30) NOT NULL AUTO_INCREMENT,
  `roleindex` int(30) NOT NULL,
  `funindex` int(30) NOT NULL,
  PRIMARY KEY USING BTREE (`relindex`),
  INDEX `roleindex` USING BTREE(`roleindex`),
  INDEX `funindex` USING BTREE(`funindex`),
  CONSTRAINT `funindex` FOREIGN KEY (`funindex`) REFERENCES `function` (`funindex`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roleindex` FOREIGN KEY (`roleindex`) REFERENCES `role` (`roleindex`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 92 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'InnoDB free: 9216 kB; (`funindex`) REFER `enn/function`(`funindex`) ON UPDATE CA' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES (1, 1, 2);
INSERT INTO `role_function` VALUES (2, 1, 3);
INSERT INTO `role_function` VALUES (3, 1, 4);
INSERT INTO `role_function` VALUES (4, 2, 1);
INSERT INTO `role_function` VALUES (5, 2, 3);
INSERT INTO `role_function` VALUES (6, 3, 2);
INSERT INTO `role_function` VALUES (7, 4, 4);
INSERT INTO `role_function` VALUES (8, 5, 1);
INSERT INTO `role_function` VALUES (9, 5, 2);
INSERT INTO `role_function` VALUES (10, 6, 4);
INSERT INTO `role_function` VALUES (11, 6, 2);
INSERT INTO `role_function` VALUES (12, 8, 1);
INSERT INTO `role_function` VALUES (13, 8, 2);
INSERT INTO `role_function` VALUES (14, 8, 3);
INSERT INTO `role_function` VALUES (15, 8, 4);
INSERT INTO `role_function` VALUES (16, 8, 5);
INSERT INTO `role_function` VALUES (17, 8, 6);
INSERT INTO `role_function` VALUES (18, 8, 7);
INSERT INTO `role_function` VALUES (19, 8, 8);
INSERT INTO `role_function` VALUES (20, 8, 9);
INSERT INTO `role_function` VALUES (21, 8, 10);
INSERT INTO `role_function` VALUES (22, 8, 11);
INSERT INTO `role_function` VALUES (23, 8, 12);
INSERT INTO `role_function` VALUES (24, 8, 13);
INSERT INTO `role_function` VALUES (25, 8, 14);
INSERT INTO `role_function` VALUES (26, 8, 15);
INSERT INTO `role_function` VALUES (27, 8, 16);
INSERT INTO `role_function` VALUES (28, 9, 1);
INSERT INTO `role_function` VALUES (29, 9, 2);
INSERT INTO `role_function` VALUES (30, 9, 17);
INSERT INTO `role_function` VALUES (31, 9, 18);
INSERT INTO `role_function` VALUES (32, 9, 19);
INSERT INTO `role_function` VALUES (33, 9, 5);
INSERT INTO `role_function` VALUES (34, 9, 6);
INSERT INTO `role_function` VALUES (35, 9, 7);
INSERT INTO `role_function` VALUES (36, 9, 21);
INSERT INTO `role_function` VALUES (37, 9, 9);
INSERT INTO `role_function` VALUES (38, 9, 10);
INSERT INTO `role_function` VALUES (39, 9, 11);
INSERT INTO `role_function` VALUES (40, 9, 12);
INSERT INTO `role_function` VALUES (41, 9, 13);
INSERT INTO `role_function` VALUES (42, 9, 33);
INSERT INTO `role_function` VALUES (43, 9, 34);
INSERT INTO `role_function` VALUES (44, 9, 22);
INSERT INTO `role_function` VALUES (45, 9, 23);
INSERT INTO `role_function` VALUES (46, 9, 24);
INSERT INTO `role_function` VALUES (47, 10, 25);
INSERT INTO `role_function` VALUES (48, 10, 26);
INSERT INTO `role_function` VALUES (49, 10, 5);
INSERT INTO `role_function` VALUES (50, 10, 27);
INSERT INTO `role_function` VALUES (51, 10, 28);
INSERT INTO `role_function` VALUES (52, 10, 9);
INSERT INTO `role_function` VALUES (53, 10, 13);
INSERT INTO `role_function` VALUES (54, 10, 29);
INSERT INTO `role_function` VALUES (55, 11, 25);
INSERT INTO `role_function` VALUES (56, 11, 30);
INSERT INTO `role_function` VALUES (57, 11, 5);
INSERT INTO `role_function` VALUES (58, 11, 9);
INSERT INTO `role_function` VALUES (59, 11, 13);
INSERT INTO `role_function` VALUES (60, 11, 31);
INSERT INTO `role_function` VALUES (61, 12, 25);
INSERT INTO `role_function` VALUES (62, 12, 30);
INSERT INTO `role_function` VALUES (63, 12, 5);
INSERT INTO `role_function` VALUES (64, 12, 9);
INSERT INTO `role_function` VALUES (65, 12, 13);
INSERT INTO `role_function` VALUES (66, 12, 31);
INSERT INTO `role_function` VALUES (67, 13, 9);
INSERT INTO `role_function` VALUES (68, 13, 13);
INSERT INTO `role_function` VALUES (69, 14, 1);
INSERT INTO `role_function` VALUES (70, 14, 2);
INSERT INTO `role_function` VALUES (71, 14, 3);
INSERT INTO `role_function` VALUES (72, 14, 4);
INSERT INTO `role_function` VALUES (73, 14, 5);
INSERT INTO `role_function` VALUES (74, 14, 6);
INSERT INTO `role_function` VALUES (75, 14, 7);
INSERT INTO `role_function` VALUES (76, 14, 8);
INSERT INTO `role_function` VALUES (77, 14, 9);
INSERT INTO `role_function` VALUES (78, 14, 10);
INSERT INTO `role_function` VALUES (79, 14, 11);
INSERT INTO `role_function` VALUES (80, 14, 12);
INSERT INTO `role_function` VALUES (81, 14, 13);
INSERT INTO `role_function` VALUES (82, 14, 14);
INSERT INTO `role_function` VALUES (83, 14, 15);
INSERT INTO `role_function` VALUES (84, 14, 16);
INSERT INTO `role_function` VALUES (85, 9, 32);
INSERT INTO `role_function` VALUES (86, 8, 34);
INSERT INTO `role_function` VALUES (87, 8, 35);
INSERT INTO `role_function` VALUES (88, 9, 34);
INSERT INTO `role_function` VALUES (89, 9, 35);
INSERT INTO `role_function` VALUES (90, 14, 34);
INSERT INTO `role_function` VALUES (91, 14, 35);

-- ----------------------------
-- Table structure for sharedata
-- ----------------------------
DROP TABLE IF EXISTS `sharedata`;
CREATE TABLE `sharedata`  (
  `ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resourceOrgID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resourceOrgName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resourceOrgType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resourceSystemID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resourceSystemName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `destinations` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `productID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requestType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(30, 2) NULL DEFAULT NULL,
  `quantity` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantityUnit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `detail` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expired` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `issuedDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `bizID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sharedatetemplate
-- ----------------------------
DROP TABLE IF EXISTS `sharedatetemplate`;
CREATE TABLE `sharedatetemplate`  (
  `ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shareDataTemplateTypeID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ownerID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ownerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createdDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for systemorg
-- ----------------------------
DROP TABLE IF EXISTS `systemorg`;
CREATE TABLE `systemorg`  (
  `ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comments` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IP` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `port` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`ID`)
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userindex` int(30) NOT NULL AUTO_INCREMENT,
  `userId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `organizationId` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `showname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contactinfo` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `organizationName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userstatus` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usersconnect` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userno` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `plateno` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`userindex`),
  INDEX `userId` USING BTREE(`userId`)
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'u1', 'Tom', 'o1', 'Tom', '111', '', '', 'org1', '正常', NULL, 'u1', NULL);
INSERT INTO `user` VALUES (2, 'u2', 'Jerry', 'o2', 'Jerry', '111', NULL, NULL, 'org2', '正常', NULL, 'u2', NULL);
INSERT INTO `user` VALUES (3, 'u3', 'Jack', 'o3', 'Jack', '123456', NULL, NULL, 'org3', '正常', NULL, 'u3', '京BS1111');
INSERT INTO `user` VALUES (4, 'u4', 'Rose', 'o4', 'Rose', 'abc', NULL, NULL, 'org4', '冻结', NULL, 'u4', '');
INSERT INTO `user` VALUES (5, 'user1', 'Tom', 'organization1', 'Tom', '123', NULL, NULL, 'organization1', '正常', NULL, 'user1', NULL);
INSERT INTO `user` VALUES (6, 'user2', 'Jerry', 'organization2', 'Jerry', '111', NULL, NULL, 'organization2', '正常', NULL, 'user2', NULL);
INSERT INTO `user` VALUES (7, 'user3', 'Jack', 'organization3', 'Jack', '123456', NULL, NULL, 'organization3', '正常', NULL, 'user3', NULL);
INSERT INTO `user` VALUES (8, 'user4', 'Rose', 'organization4', 'Rose', 'abc', NULL, NULL, 'organization4', '正常', NULL, 'user4', NULL);
INSERT INTO `user` VALUES (9, 'user5', 'Jackson', 'organization5', 'Jackson', '321', NULL, NULL, 'organization5', '正常', NULL, 'user5', NULL);
INSERT INTO `user` VALUES (10, 'user6', 'Lucy', 'organization6', 'Lucy', '111', NULL, NULL, 'organization6', '正常', NULL, 'user6', NULL);
INSERT INTO `user` VALUES (11, 'user7', 'driver1', 'organization3', 'driver1', '123', NULL, NULL, 'organization3', '正常', NULL, 'user7', '京BS9999');
INSERT INTO `user` VALUES (12, 'user8', 'driver2', 'organization3', 'driver2', '123', NULL, NULL, 'organization3', '正常', NULL, 'user8', '蒙BS8888');
INSERT INTO `user` VALUES (13, 'xnky1', '矿业用户', '057808959', 'John', '123', NULL, NULL, '内蒙古新能矿业有限公司', '正常', NULL, 'user9', NULL);
INSERT INTO `user` VALUES (14, 'daqi1', '达旗用户', '55549100x', 'Lily', '123', NULL, NULL, '新能（达旗）生物能源有限公司', '正常', NULL, 'user10', NULL);
INSERT INTO `user` VALUES (15, 'xnwl1', '新能物流用户', 'MA0N43CA6', 'Ben', '123', NULL, NULL, '鄂尔多斯市新能物流有限公司', '正常', NULL, 'user11', NULL);
INSERT INTO `user` VALUES (16, 'xnwl2', '新能物流司机1', 'MA0N43CA6', 'driver3', '123', NULL, NULL, '鄂尔多斯市新能物流有限公司', '正常', NULL, 'user12', '蒙AA0001');
INSERT INTO `user` VALUES (17, 'xnwl3', '新能物流司机2', 'MA0N43CA6', 'driver4', '123', NULL, NULL, '鄂尔多斯市新能物流有限公司', '正常', NULL, 'user13', '蒙AA0002');
INSERT INTO `user` VALUES (18, 'seller1', '销售企业1', 'xsqy1', 'seller1', '123', NULL, NULL, '销售企业1', '正常', NULL, 'seller1', NULL);
INSERT INTO `user` VALUES (19, 'wbuser1', '外部信息员', 'wqe', 'wbxxy', '123', NULL, NULL, '无企业', '正常', NULL, 'wbxxy1', NULL);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `relindex` int(30) NOT NULL AUTO_INCREMENT,
  `userindex` int(30) NOT NULL,
  `roleindex` int(30) NOT NULL,
  PRIMARY KEY USING BTREE (`relindex`),
  INDEX `userindex` USING BTREE(`userindex`),
  INDEX `roleindex` USING BTREE(`roleindex`)
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 1, 2);
INSERT INTO `user_role` VALUES (3, 1, 3);
INSERT INTO `user_role` VALUES (4, 2, 0);

SET FOREIGN_KEY_CHECKS = 1;
