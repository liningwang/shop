/*
MySQL Data Transfer
Source Host: localhost
Source Database: ykd
Target Host: localhost
Target Database: ykd
Date: 2017/9/13 ������ ���� 3:05:02
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `userAddreessId` int(200) NOT NULL AUTO_INCREMENT,
  `userName` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `userTelephone` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `userProvince` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `userCity` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `userDistrict` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `userDetailedAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `userIsType` int(3) DEFAULT NULL,
  `userId` int(200) DEFAULT NULL,
  PRIMARY KEY (`userAddreessId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for classify
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `classifyPictureUrl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `classifyId` int(200) DEFAULT NULL,
  `classifyTitle` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `evaluateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `evaluateGrade` int(10) DEFAULT NULL,
  `evaluateContent` varchar(5000) CHARACTER SET utf8 DEFAULT '',
  `evaluatePictureUrl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `shopId` int(200) DEFAULT NULL,
  `userId` int(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for moneydetail
-- ----------------------------
DROP TABLE IF EXISTS `moneydetail`;
CREATE TABLE `moneydetail` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `detail` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `balance` double(200,2) DEFAULT NULL,
  `money` double(200,2) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `userId` int(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for order_product
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `orderId` int(200) DEFAULT NULL,
  `shopId` int(200) DEFAULT NULL,
  `ProductNum` int(200) DEFAULT NULL,
  `orderNum` varchar(5000) DEFAULT NULL,
  `type` int(20) DEFAULT '1',
  `cancelState` int(20) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `shopId` int(200) NOT NULL AUTO_INCREMENT,
  `shopTitle` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `shopPrice` double(100,2) unsigned DEFAULT NULL,
  `shopFreight` double(200,2) DEFAULT NULL,
  `shopSalesVolume` int(200) DEFAULT NULL,
  `shopType` int(10) DEFAULT '0',
  `shopPictureUrl` varchar(5000) CHARACTER SET utf8 DEFAULT NULL,
  `shopGraphicDetails` varchar(400) CHARACTER SET utf8 DEFAULT NULL,
  `classifyId` int(200) DEFAULT NULL,
  `twoClassifyId` int(200) DEFAULT NULL,
  `recommend` int(10) DEFAULT '0',
  `shopCount` int(200) DEFAULT NULL,
  PRIMARY KEY (`shopId`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for returnorder
-- ----------------------------
DROP TABLE IF EXISTS `returnorder`;
CREATE TABLE `returnorder` (
  `returnId` int(200) NOT NULL AUTO_INCREMENT,
  `orderNum` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `shopId` int(200) DEFAULT NULL,
  `salesReturnId` int(200) DEFAULT NULL,
  `refundPrice` double(200,2) DEFAULT NULL,
  `salesReturnExplain` varchar(5000) CHARACTER SET utf8 DEFAULT NULL,
  `salesReturnPictures` varchar(5000) CHARACTER SET utf8 DEFAULT NULL,
  `startTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endTime` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `returnNum` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `returnCount` int(200) DEFAULT NULL,
  `orderType` int(15) DEFAULT '1' COMMENT '1',
  `cancelState` int(15) unsigned DEFAULT '1',
  PRIMARY KEY (`returnId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for returnreason
-- ----------------------------
DROP TABLE IF EXISTS `returnreason`;
CREATE TABLE `returnreason` (
  `salesReturnId` int(200) NOT NULL AUTO_INCREMENT,
  `returnReason` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`salesReturnId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for second_level
-- ----------------------------
DROP TABLE IF EXISTS `second_level`;
CREATE TABLE `second_level` (
  `secondLevelId` int(200) NOT NULL AUTO_INCREMENT,
  `secondLevelTitle` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `secondLevelPictureUrl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `classifyId` int(200) DEFAULT NULL,
  PRIMARY KEY (`secondLevelId`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for shoporder
-- ----------------------------
DROP TABLE IF EXISTS `shoporder`;
CREATE TABLE `shoporder` (
  `orderId` int(200) NOT NULL AUTO_INCREMENT,
  `userId` int(200) DEFAULT NULL,
  `userAddreessId` int(200) DEFAULT NULL,
  `rentalPrice` double(100,2) DEFAULT NULL,
  `buyerMessage` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `orderNum` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `orderType` int(100) DEFAULT '1',
  `orderTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `shopTotalPrices` double(100,2) DEFAULT NULL,
  `expressagePrices` double(100,2) DEFAULT NULL,
  `sumofSalesPrice` double(100,2) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for slide_pic
-- ----------------------------
DROP TABLE IF EXISTS `slide_pic`;
CREATE TABLE `slide_pic` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `pictureUrl` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `shopId` int(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(200) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `passWord` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `myCode` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `phoneNumber` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `identity` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `myUserCount` int(200) DEFAULT NULL,
  `money` double(200,2) DEFAULT NULL,
  `sexType` int(3) DEFAULT NULL,
  `regiTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `invitationCode` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `headPortrait` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `openid` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `userGrade` int(100) DEFAULT NULL,
  `shareType` int(10) DEFAULT NULL,
  `shareLink` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `deduction` double(100,2) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user_product
-- ----------------------------
DROP TABLE IF EXISTS `user_product`;
CREATE TABLE `user_product` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `userId` int(200) DEFAULT NULL,
  `shopId` int(200) DEFAULT NULL,
  `shopNumber` int(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for usermsg
-- ----------------------------
DROP TABLE IF EXISTS `usermsg`;
CREATE TABLE `usermsg` (
  `userId` int(200) DEFAULT NULL,
  `msg` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `msgtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `messagePictureType` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Procedure structure for countDevicesName
-- ----------------------------
DROP PROCEDURE IF EXISTS `countDevicesName`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `countDevicesName`(IN num VARCHAR(12))
begin 
	update shoporder set rentalPrice=60
	where orderId = num;
	update shoporder set buyerMessage='wanglining'
	where orderId=4;
	end;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteOrder
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteOrder`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOrder`(IN num VARCHAR(50))
begin 
	delete from shoporder
	where orderNum = num;
	delete from order_product
	where orderNum = num;
end;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for mypro
-- ----------------------------
DROP PROCEDURE IF EXISTS `mypro`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mypro`()
BEGIN
update user set nickname='10';
end;;
DELIMITER ;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `address` VALUES ('1', '张坤', '1861123400', '河北省', '石家庄市', '桥西区', '王家庄寸昆明大街112号', '1', '1');
INSERT INTO `address` VALUES ('2', '鳌拜', '1861123400', '河北省', '石家庄市', '桥东区', '王家庄寸昆明大街112号', '0', '2');
INSERT INTO `address` VALUES ('3', 'aaa', null, null, null, null, null, null, null);
INSERT INTO `classify` VALUES ('1', 'http://114.215.46.63/Test/images/1176886.png', '0', '医药保健');
INSERT INTO `classify` VALUES ('2', 'http://114.215.46.63/Test/images/1194540.png', '1', '食品生鲜');
INSERT INTO `classify` VALUES ('3', 'http://114.215.46.63/Test/images/1204387.png', '2', '电器');
INSERT INTO `classify` VALUES ('4', 'http://114.215.46.63/Test/images/1185182.png', '3', '装饰装潢');
INSERT INTO `classify` VALUES ('5', 'http://114.215.46.63/Test/images/1188517.png', '4', '酒水饮料');
INSERT INTO `classify` VALUES ('6', 'http://114.215.46.63/Test/images/1176886.png', '5', '母婴童装');
INSERT INTO `classify` VALUES ('7', 'http://114.215.46.63/Test/images/1114469.png', '6', '运动户外');
INSERT INTO `classify` VALUES ('8', 'http://114.215.46.63/Test/images/1204387.png', '7', '电器');
INSERT INTO `classify` VALUES ('9', 'http://114.215.46.63/Test/images/1185182.png', '8', '装饰装潢');
INSERT INTO `classify` VALUES ('10', 'http://114.215.46.63/Test/images/1188517.png', '9', '酒水饮料');
INSERT INTO `classify` VALUES ('11', 'http://114.215.46.63/Test/images/1176886.png', '10', '母婴童装');
INSERT INTO `classify` VALUES ('12', 'http://114.215.46.63/Test/images/1114469.png', '11', '运动户外');
INSERT INTO `classify` VALUES ('13', 'http://114.215.46.63/Test/images/1114469.png', '12', '家具用品');
INSERT INTO `comment` VALUES ('1', '2017-08-26 18:11:08', '2', 'fdjsfdsafdsa', null, '2', '2');
INSERT INTO `comment` VALUES ('2', '2017-08-27 11:47:20', '3', 'fdsfds', null, '2', '2');
INSERT INTO `comment` VALUES ('3', '2017-09-03 09:58:04', '2', 'jfdsfdsl', 'dfsjfdsafd,dksfdsflds,jfdsfdskl', '11', '1');
INSERT INTO `comment` VALUES ('4', '2017-09-03 09:59:26', '2', 'jfdsfdsl', 'dfsjfdsafd,dksfdsflds,jfdsfdskl', '11', '1');
INSERT INTO `comment` VALUES ('5', '2017-09-03 09:59:26', '2', 'jfdsfdsl', 'dfsjfdsafd,dksfdsflds,jfdsfdskl', '11', '1');
INSERT INTO `order_product` VALUES ('4', '12', '123', null, null, null);
INSERT INTO `order_product` VALUES ('6', '12', '123', '1000001794580598', null, null);
INSERT INTO `order_product` VALUES ('11', '10', '123', '1000001061738973', '1', '1');
INSERT INTO `order_product` VALUES ('25', '9', '1', '1000000812879950', '1', '1');
INSERT INTO `order_product` VALUES ('27', '1', '1', '1000001160683675', '2', '1');
INSERT INTO `order_product` VALUES ('27', '3', '1', '1000001160683675', '2', '1');
INSERT INTO `order_product` VALUES ('28', '1', '1', '1000001696320681', '2', '2');
INSERT INTO `order_product` VALUES ('28', '2', '1', '1000001696320681', '2', '2');
INSERT INTO `order_product` VALUES ('30', '1', '1', '1000001229754718', '2', '2');
INSERT INTO `order_product` VALUES ('30', '2', '1', '1000001229754718', '2', '3');
INSERT INTO `order_product` VALUES ('31', '1', '1', '1000001763422512', '1', '1');
INSERT INTO `order_product` VALUES ('31', '2', '1', '1000001763422512', '1', '1');
INSERT INTO `order_product` VALUES ('32', '1', '1', '1000001266238470', '1', '1');
INSERT INTO `order_product` VALUES ('32', '2', '1', '1000001266238470', '1', '1');
INSERT INTO `order_product` VALUES ('33', '1', '1', '1000001441442465', '1', '1');
INSERT INTO `order_product` VALUES ('33', '2', '1', '1000001441442465', '1', '1');
INSERT INTO `order_product` VALUES ('37', '3', '1', '1000000791115020', '2', '2');
INSERT INTO `order_product` VALUES ('37', '2', '1', '1000000791115020', '2', '2');
INSERT INTO `order_product` VALUES ('38', '3', '1', '1000001064435193', '2', '2');
INSERT INTO `order_product` VALUES ('38', '2', '1', '1000001064435193', '2', '3');
INSERT INTO `product` VALUES ('1', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '0', '1', '1', '10');
INSERT INTO `product` VALUES ('2', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '1', '1', '1', '10');
INSERT INTO `product` VALUES ('3', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '2', '1', '1', '10');
INSERT INTO `product` VALUES ('4', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '3', '2', '1', '10');
INSERT INTO `product` VALUES ('5', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '4', '2', '1', '10');
INSERT INTO `product` VALUES ('6', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '5', '2', '1', '10');
INSERT INTO `product` VALUES ('7', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '3', '1', '10');
INSERT INTO `product` VALUES ('8', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '3', '1', '10');
INSERT INTO `product` VALUES ('9', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '2', '1', '10');
INSERT INTO `product` VALUES ('10', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '4', '1', '10');
INSERT INTO `product` VALUES ('11', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '4', '1', '10');
INSERT INTO `product` VALUES ('12', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '0', '1', '1', '10');
INSERT INTO `product` VALUES ('13', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '1', '1', '1', '10');
INSERT INTO `product` VALUES ('14', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '2', '1', '1', '10');
INSERT INTO `product` VALUES ('15', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '3', '2', '1', '10');
INSERT INTO `product` VALUES ('16', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '4', '2', '1', '10');
INSERT INTO `product` VALUES ('17', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '5', '2', '1', '10');
INSERT INTO `product` VALUES ('18', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '3', '1', '10');
INSERT INTO `product` VALUES ('19', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '3', '1', '10');
INSERT INTO `product` VALUES ('20', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '2', '1', '10');
INSERT INTO `product` VALUES ('21', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '4', '1', '10');
INSERT INTO `product` VALUES ('22', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '4', '1', '10');
INSERT INTO `product` VALUES ('23', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '0', '1', '1', '10');
INSERT INTO `product` VALUES ('24', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '1', '1', '1', '10');
INSERT INTO `product` VALUES ('25', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '2', '1', '1', '10');
INSERT INTO `product` VALUES ('26', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '3', '2', '1', '10');
INSERT INTO `product` VALUES ('27', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '4', '2', '1', '10');
INSERT INTO `product` VALUES ('28', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '5', '2', '1', '10');
INSERT INTO `product` VALUES ('29', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '3', '1', '10');
INSERT INTO `product` VALUES ('30', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '3', '1', '10');
INSERT INTO `product` VALUES ('31', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '2', '1', '10');
INSERT INTO `product` VALUES ('32', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '4', '1', '10');
INSERT INTO `product` VALUES ('33', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '4', '1', '10');
INSERT INTO `product` VALUES ('34', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '0', '1', '1', '10');
INSERT INTO `product` VALUES ('35', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '1', '1', '1', '10');
INSERT INTO `product` VALUES ('36', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '2', '1', '1', '10');
INSERT INTO `product` VALUES ('37', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '3', '2', '1', '10');
INSERT INTO `product` VALUES ('38', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '4', '2', '1', '10');
INSERT INTO `product` VALUES ('39', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '5', '2', '1', '10');
INSERT INTO `product` VALUES ('40', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '11', '10', '1', '10');
INSERT INTO `product` VALUES ('41', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '11', '11', '1', '10');
INSERT INTO `product` VALUES ('42', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '11', '11', '1', '10');
INSERT INTO `product` VALUES ('43', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '12', '11', '1', '10');
INSERT INTO `product` VALUES ('44', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '12', '11', '1', '10');
INSERT INTO `product` VALUES ('45', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('46', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('47', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('48', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('49', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '0', '13', '1', '10');
INSERT INTO `product` VALUES ('50', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '0', '13', '1', '10');
INSERT INTO `product` VALUES ('51', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '13', '1', '10');
INSERT INTO `product` VALUES ('52', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '14', '1', '10');
INSERT INTO `product` VALUES ('53', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '14', '1', '10');
INSERT INTO `product` VALUES ('54', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '14', '1', '10');
INSERT INTO `product` VALUES ('55', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '14', '1', '10');
INSERT INTO `product` VALUES ('72', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '11', '18', '1', '10');
INSERT INTO `product` VALUES ('73', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '11', '18', '1', '10');
INSERT INTO `product` VALUES ('74', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '11', '19', '1', '10');
INSERT INTO `product` VALUES ('75', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '12', '19', '1', '10');
INSERT INTO `product` VALUES ('76', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '12', '19', '1', '10');
INSERT INTO `product` VALUES ('77', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '12', '19', '1', '10');
INSERT INTO `product` VALUES ('78', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '12', '19', '1', '10');
INSERT INTO `product` VALUES ('79', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '12', '1', '1', '10');
INSERT INTO `product` VALUES ('80', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '12', '1', '1', '10');
INSERT INTO `product` VALUES ('81', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '0', '1', '1', '10');
INSERT INTO `product` VALUES ('82', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '0', '1', '1', '10');
INSERT INTO `product` VALUES ('83', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '1', '1', '10');
INSERT INTO `product` VALUES ('84', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '1', '1', '10');
INSERT INTO `product` VALUES ('85', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '1', '1', '10');
INSERT INTO `product` VALUES ('86', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '1', '1', '10');
INSERT INTO `product` VALUES ('87', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '1', '1', '10');
INSERT INTO `product` VALUES ('88', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '3', '1', '10');
INSERT INTO `product` VALUES ('89', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '2', '1', '10');
INSERT INTO `product` VALUES ('90', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '4', '1', '10');
INSERT INTO `product` VALUES ('91', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '4', '1', '10');
INSERT INTO `product` VALUES ('92', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '0', '1', '1', '10');
INSERT INTO `product` VALUES ('93', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '1', '1', '1', '10');
INSERT INTO `product` VALUES ('94', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '2', '1', '1', '10');
INSERT INTO `product` VALUES ('95', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '3', '2', '1', '10');
INSERT INTO `product` VALUES ('96', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '4', '2', '1', '10');
INSERT INTO `product` VALUES ('97', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '5', '2', '1', '10');
INSERT INTO `product` VALUES ('98', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '3', '1', '10');
INSERT INTO `product` VALUES ('99', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '3', '1', '10');
INSERT INTO `product` VALUES ('100', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '2', '1', '10');
INSERT INTO `product` VALUES ('101', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '4', '1', '10');
INSERT INTO `product` VALUES ('102', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '4', '1', '10');
INSERT INTO `product` VALUES ('103', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '11', '10', '1', '10');
INSERT INTO `product` VALUES ('104', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '11', '11', '1', '10');
INSERT INTO `product` VALUES ('105', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '11', '11', '1', '10');
INSERT INTO `product` VALUES ('106', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '12', '11', '1', '10');
INSERT INTO `product` VALUES ('107', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '12', '11', '1', '10');
INSERT INTO `product` VALUES ('108', '优卡丹复方小儿退热栓', '16.00', '5.00', '100', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402692.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('109', '智利奇异果猕猴桃', '39.05', '5.00', '100', '1', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402693.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('110', '智利奇异果猕猴桃', '39.05', '2.00', '99', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402694.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('111', '优卡丹复方小儿退热栓', '16.00', '4.00', '100', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402695.html', '12', '12', '1', '10');
INSERT INTO `product` VALUES ('112', '优卡丹复方小儿退热栓', '16.00', '3.00', '20', '0', 'http://114.215.46.63/Test/images/ddd.jpg', 'https://in.m.jd.com/product/jieshao/2402696.html', '0', '13', '1', '10');
INSERT INTO `product` VALUES ('113', '智利奇异果猕猴桃', '10.00', '5.00', '30', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402697.html', '0', '13', '1', '10');
INSERT INTO `product` VALUES ('114', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '13', '1', '10');
INSERT INTO `product` VALUES ('115', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/bbb.jpg', 'https://in.m.jd.com/product/jieshao/2402699.html', '3', '14', '1', '10');
INSERT INTO `product` VALUES ('116', '智利奇异果猕猴桃', '10.00', '4.00', '40', '0', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402609.html', '2', '14', '1', '10');
INSERT INTO `product` VALUES ('117', '智利奇异果猕猴桃', '10.00', '3.00', '60', '1', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402619.html', '1', '14', '1', '10');
INSERT INTO `product` VALUES ('118', '智利奇异果猕猴桃', '20.00', '4.00', '80', '1', 'http://114.215.46.63/Test/images/aaa.jpg', 'https://in.m.jd.com/product/jieshao/2402629.html', '2', '14', '1', '10');
INSERT INTO `product` VALUES ('119', '优卡丹复方小儿退热栓', '20.00', '4.00', '20', '0', 'http://114.215.46.63/Test/images/ccc.jpg', 'https://in.m.jd.com/product/jieshao/2402698.html', '6', '3', '1', '10');
INSERT INTO `returnorder` VALUES ('1', '1000001332572439', '1', '1', '10.10', 'fdsflkdslkfds', 'fdslkfjdslfsd', '2017-08-29 18:24:55', null, null, null, null, null);
INSERT INTO `returnorder` VALUES ('2', '1000001332572431', null, null, null, null, null, '2017-08-29 19:37:07', null, null, null, null, null);
INSERT INTO `returnorder` VALUES ('3', '1000000225147240', '10', '2', '10.10', 'dsdd', 'fdsfsa', '2017-08-31 13:54:29', null, null, null, null, null);
INSERT INTO `returnorder` VALUES ('4', '1000000225147240', '9', '1', '100.00', null, null, '2017-09-01 16:36:59', null, null, null, null, null);
INSERT INTO `returnorder` VALUES ('5', '1000001160683675', '1', '1', '16.00', 'fdsfsasf', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:46', null, '0908140316-3264', null, null, null);
INSERT INTO `returnorder` VALUES ('6', '1000001160683675', '3', '1', '39.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:48', null, '0908142533-1605', null, null, null);
INSERT INTO `returnorder` VALUES ('7', '1000001696320681', '1', '1', '16.00', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:50', null, '090817154855633', null, '2', '2');
INSERT INTO `returnorder` VALUES ('10', '1000001696320681', '2', '1', '16.00', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:52', null, '090819013916296', null, '2', '2');
INSERT INTO `returnorder` VALUES ('11', '1000001229754718', '1', '1', '16.00', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:54', null, '090821032420292', null, '2', '2');
INSERT INTO `returnorder` VALUES ('12', '1000001229754718', '2', '1', '16.00', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:55', null, '090821101021292', null, '2', '3');
INSERT INTO `returnorder` VALUES ('13', '1000001229754718', '2', '1', '23.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:57', null, '0908211203-2131', null, '2', '2');
INSERT INTO `returnorder` VALUES ('14', '1000001229754718', '2', '1', '23.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:13:58', null, '0908211334-1684', null, '1', '1');
INSERT INTO `returnorder` VALUES ('15', '1000001229754718', '2', '1', '23.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:14:00', null, '0908211420-6747', null, '1', '1');
INSERT INTO `returnorder` VALUES ('16', '1000001229754718', '2', '1', '23.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:14:02', null, '090821142210930', null, '1', '1');
INSERT INTO `returnorder` VALUES ('17', '1000000791115020', '2', '1', '39.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:14:04', null, '0908215057-1170', null, '2', '2');
INSERT INTO `returnorder` VALUES ('18', '1000000791115020', '3', '1', '39.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:14:05', null, '090821532935979', null, '2', '2');
INSERT INTO `returnorder` VALUES ('19', '1000001064435193', '3', '1', '39.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:14:07', null, '090821594311105', null, '2', '2');
INSERT INTO `returnorder` VALUES ('20', '1000001064435193', '2', '1', '39.06', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:14:08', null, '0908220210-1804', null, '1', '1');
INSERT INTO `returnorder` VALUES ('21', '1000001064435193', '2', '1', '39.05', 'fdsfds', 'http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg,http://114.215.46.63/Test/images/aaa.jpg', '2017-09-12 20:14:10', null, '0908220220-1369', null, '2', '3');
INSERT INTO `returnreason` VALUES ('1', '7天无理由退货');
INSERT INTO `returnreason` VALUES ('2', '对商品不满意');
INSERT INTO `second_level` VALUES ('1', '唇彩', 'http://114.215.46.63/Test/images/511442.png', '0');
INSERT INTO `second_level` VALUES ('2', '电视', 'http://114.215.46.63/Test/images/520448.png', '1');
INSERT INTO `second_level` VALUES ('3', '冰箱', 'http://114.215.46.63/Test/images/1096551.png', '1');
INSERT INTO `second_level` VALUES ('4', '电脑', 'http://114.215.46.63/Test/images/2801.png', '1');
INSERT INTO `second_level` VALUES ('5', '药物', 'http://114.215.46.63/Test/images/548852.png', '2');
INSERT INTO `second_level` VALUES ('6', '手机', 'http://114.215.46.63/Test/images/1132205.png', '2');
INSERT INTO `second_level` VALUES ('7', '手机', 'http://114.215.46.63/Test/images/1132205.png', '2');
INSERT INTO `second_level` VALUES ('8', '手机', 'http://114.215.46.63/Test/images/1132205.png', '2');
INSERT INTO `second_level` VALUES ('9', '手机', 'http://114.215.46.63/Test/images/1132205.png', '2');
INSERT INTO `second_level` VALUES ('10', '手机', 'http://114.215.46.63/Test/images/1132205.png', '3');
INSERT INTO `second_level` VALUES ('11', '手机', 'http://114.215.46.63/Test/images/1132205.png', '3');
INSERT INTO `second_level` VALUES ('12', '手机', 'http://114.215.46.63/Test/images/1132205.png', '3');
INSERT INTO `second_level` VALUES ('13', '手机', 'http://114.215.46.63/Test/images/1132205.png', '3');
INSERT INTO `second_level` VALUES ('14', '手机', 'http://114.215.46.63/Test/images/1132205.png', '3');
INSERT INTO `second_level` VALUES ('15', '手机', 'http://114.215.46.63/Test/images/1132205.png', '3');
INSERT INTO `second_level` VALUES ('16', '药物', 'http://114.215.46.63/Test/images/1132205.png', '4');
INSERT INTO `second_level` VALUES ('17', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '4');
INSERT INTO `second_level` VALUES ('18', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '4');
INSERT INTO `second_level` VALUES ('19', '电视', 'http://114.215.46.63/Test/images/2801.png', '4');
INSERT INTO `second_level` VALUES ('20', '电视', 'http://114.215.46.63/Test/images/2801.png', '5');
INSERT INTO `second_level` VALUES ('21', '手机', 'http://114.215.46.63/Test/images/1132205.png', '5');
INSERT INTO `second_level` VALUES ('22', '药物', 'http://114.215.46.63/Test/images/1132205.png', '5');
INSERT INTO `second_level` VALUES ('23', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '5');
INSERT INTO `second_level` VALUES ('24', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '5');
INSERT INTO `second_level` VALUES ('25', '电视', 'http://114.215.46.63/Test/images/2801.png', '6');
INSERT INTO `second_level` VALUES ('26', '电视', 'http://114.215.46.63/Test/images/2801.png', '6');
INSERT INTO `second_level` VALUES ('27', '手机', 'http://114.215.46.63/Test/images/1132205.png', '6');
INSERT INTO `second_level` VALUES ('28', '药物', 'http://114.215.46.63/Test/images/1132205.png', '6');
INSERT INTO `second_level` VALUES ('29', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '7');
INSERT INTO `second_level` VALUES ('30', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '7');
INSERT INTO `second_level` VALUES ('31', '电视', 'http://114.215.46.63/Test/images/2801.png', '7');
INSERT INTO `second_level` VALUES ('32', '电视', 'http://114.215.46.63/Test/images/2801.png', '7');
INSERT INTO `second_level` VALUES ('33', '手机', 'http://114.215.46.63/Test/images/1132205.png', '7');
INSERT INTO `second_level` VALUES ('34', '药物', 'http://114.215.46.63/Test/images/1132205.png', '8');
INSERT INTO `second_level` VALUES ('35', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '8');
INSERT INTO `second_level` VALUES ('36', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '8');
INSERT INTO `second_level` VALUES ('37', '电视', 'http://114.215.46.63/Test/images/2801.png', '8');
INSERT INTO `second_level` VALUES ('38', '电视', 'http://114.215.46.63/Test/images/2801.png', '8');
INSERT INTO `second_level` VALUES ('39', '手机', 'http://114.215.46.63/Test/images/1132205.png', '9');
INSERT INTO `second_level` VALUES ('40', '药物', 'http://114.215.46.63/Test/images/1132205.png', '9');
INSERT INTO `second_level` VALUES ('41', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '9');
INSERT INTO `second_level` VALUES ('42', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '9');
INSERT INTO `second_level` VALUES ('43', '电视', 'http://114.215.46.63/Test/images/2801.png', '9');
INSERT INTO `second_level` VALUES ('44', '电视', 'http://114.215.46.63/Test/images/2801.png', '9');
INSERT INTO `second_level` VALUES ('45', '手机', 'http://114.215.46.63/Test/images/1132205.png', '10');
INSERT INTO `second_level` VALUES ('46', '药物', 'http://114.215.46.63/Test/images/1132205.png', '10');
INSERT INTO `second_level` VALUES ('47', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '10');
INSERT INTO `second_level` VALUES ('48', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '10');
INSERT INTO `second_level` VALUES ('49', '电视', 'http://114.215.46.63/Test/images/2801.png', '10');
INSERT INTO `second_level` VALUES ('50', '电视', 'http://114.215.46.63/Test/images/2801.png', '11');
INSERT INTO `second_level` VALUES ('51', '手机', 'http://114.215.46.63/Test/images/1132205.png', '11');
INSERT INTO `second_level` VALUES ('52', '药物', 'http://114.215.46.63/Test/images/1132205.png', '11');
INSERT INTO `second_level` VALUES ('53', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '11');
INSERT INTO `second_level` VALUES ('54', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '12');
INSERT INTO `second_level` VALUES ('55', '电视', 'http://114.215.46.63/Test/images/2801.png', '12');
INSERT INTO `second_level` VALUES ('56', '手机', 'http://114.215.46.63/Test/images/1132205.png', '12');
INSERT INTO `second_level` VALUES ('57', '药物', 'http://114.215.46.63/Test/images/1132205.png', '0');
INSERT INTO `second_level` VALUES ('58', '冰箱', 'http://114.215.46.63/Test/images/2801.png', '0');
INSERT INTO `second_level` VALUES ('59', '洗发水', 'http://114.215.46.63/Test/images/540888.png', '0');
INSERT INTO `second_level` VALUES ('60', '电视', 'http://114.215.46.63/Test/images/2801.png', '0');
INSERT INTO `shoporder` VALUES ('4', '2', '1', '60.00', 'wanglining', '1000001332572439', '3', '2017-09-12 16:52:46', null, null, '100.00');
INSERT INTO `shoporder` VALUES ('11', '1', '1', '10.00', 'fdsfsdafdsaf', '1000001061738973', '3', '2017-09-12 16:52:46', null, null, '12.00');
INSERT INTO `shoporder` VALUES ('27', '1', '1', '57.05', 'fdsfd', '1000001160683675', '3', '2017-09-12 16:52:47', '55.05', '2.00', '57.05');
INSERT INTO `shoporder` VALUES ('28', '1', '1', '-99.95', '233', '1000001696320681', '3', '2017-09-12 22:21:08', '7.05', '5.00', '12.05');
INSERT INTO `shoporder` VALUES ('30', '1', '1', '5.00', '233', '1000001229754718', '3', '2017-09-12 16:52:47', '0.00', '5.00', '5.00');
INSERT INTO `shoporder` VALUES ('32', '1', '1', '60.05', '233', '1000001266238470', '3', '2017-09-12 16:52:47', '55.05', '5.00', '60.05');
INSERT INTO `shoporder` VALUES ('33', '1', '1', '60.05', '233', '1000001441442465', '1', '2017-09-12 16:52:30', '55.05', '5.00', '60.05');
INSERT INTO `shoporder` VALUES ('37', '1', '1', '5.00', '233', '1000000791115020', '1', '2017-09-08 21:54:17', '0.00', '5.00', '5.00');
INSERT INTO `shoporder` VALUES ('38', '1', '1', '5.00', '233', '1000001064435193', '1', '2017-09-08 22:03:38', '0.00', '5.00', '5.00');
INSERT INTO `slide_pic` VALUES ('1', 'http://192.168.0.2:8080/Test/pic/xiaoer.jpg', '2');
INSERT INTO `slide_pic` VALUES ('2', 'http://localhost:8080/Test/pic/qiyiguo.jpg', '6');
INSERT INTO `user` VALUES ('1', 'aaaaaa', 'bbb', 'string', '111', 'string', '0', '5.00', '0', '2017-09-07 12:40:47', '333', 'fdsfdsafdsfdsfdsfdsf', null, null, null, null, null);
INSERT INTO `user` VALUES ('2', '12', null, null, null, null, null, null, null, '2017-09-04 11:12:58', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('3', '12', 'fdsfdl', null, '1111', null, null, null, null, '2017-09-04 11:12:58', 'fdsfd', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('4', '12', 'fdsfdl', null, '1111', null, null, null, null, '2017-09-04 11:12:58', 'fdsfd', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('5', '12', 'fdsfdl', null, '1111', null, null, null, null, '2017-09-04 11:12:58', 'fdsfd', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('6', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('7', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('8', '12', '6666666', 'string', '3333', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', '111', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('9', '12', 'bbb', 'string', '6666666', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', '333', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('10', '12', '6666', 'string', '222222', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', '1111', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('11', '12', 'string', 'string', '2222222', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', '66666666666', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('12', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('13', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('14', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('15', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('16', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('17', '12', 'string', 'string', 'string', 'string', '0', '0.00', '0', '2017-09-04 11:12:58', 'string', 'string', null, null, null, null, null);
INSERT INTO `user` VALUES ('60', '12', '111', null, '1111111', null, null, null, null, '2017-09-04 11:12:58', '1111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('61', '12', '1111', null, '11111112222', null, null, null, null, '2017-09-04 11:12:58', '1111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('62', '12', '111', null, '23331', null, null, null, null, '2017-09-04 11:12:58', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('63', '12', '111', null, '233311', null, null, null, null, '2017-09-04 11:12:58', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('64', '12', '888', null, '888888888', null, null, null, null, '2017-09-04 11:12:58', '88', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('65', '12', '999', null, '9999999', null, null, null, null, '2017-09-04 11:12:58', '999', null, '1234567', null, null, null, null);
INSERT INTO `user` VALUES ('66', '12', '888', null, '88888888888', null, null, null, null, '2017-09-04 11:12:58', '88', null, '2345', null, null, null, null);
INSERT INTO `user` VALUES ('67', '12', '111', null, '1111111', null, null, null, null, '2017-09-04 11:12:58', '111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('68', '12', '111', null, '1111', null, null, null, null, '2017-09-04 11:12:58', '111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('69', '12', '1111', null, '111', null, null, null, null, '2017-09-04 11:12:58', '111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('71', '12', '11111', null, '13381045307', null, null, null, null, '2017-09-04 11:12:58', '11111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('72', '12', '22222', null, '13381045307', null, null, null, null, '2017-09-04 11:12:58', '1111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('73', '12', '22222', null, '13381045307', null, null, null, null, '2017-09-04 11:12:58', '1111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('74', '12', '111111', null, '11111111', null, null, null, null, '2017-09-04 11:12:58', '11111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('75', '12', '111111', null, '11111111', null, null, null, null, '2017-09-04 11:12:58', '88', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('76', '12', '111', null, '123456', null, null, null, null, '2017-09-04 11:12:58', '1111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('77', '12', '111111', null, '123456', null, null, null, null, '2017-09-04 11:12:58', '1111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('78', null, '1233', null, '1324516276', null, null, null, null, '2017-09-07 14:31:42', '111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('79', null, '111111', null, '11111111111', null, null, null, null, '2017-09-07 16:00:30', '111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('80', null, '111111', null, '111111', null, null, null, null, '2017-09-07 18:33:44', '111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('81', null, '111111', null, '111111', null, null, null, null, '2017-09-07 18:42:48', '111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('82', null, '111111', null, '12233', null, null, null, null, '2017-09-07 18:43:34', '111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('83', null, 'aaaaaaaa', null, '18132458729', null, null, null, null, '2017-09-08 11:43:06', 'sss', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('84', null, '111111', null, '155155', null, null, null, null, '2017-09-08 10:44:12', '111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('85', null, '123', null, '13223494146', null, null, null, null, '2017-09-08 11:39:53', '123', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('86', null, '111111', null, '17734578640', null, null, null, null, '2017-09-08 15:55:42', '111111', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('87', null, '111', null, '11133', null, null, null, null, '2017-09-08 18:16:27', null, null, '111', null, null, null, null);
INSERT INTO `user_product` VALUES ('1', '2', '3', '5');
INSERT INTO `user_product` VALUES ('2', '2', '6', '6');
INSERT INTO `usermsg` VALUES ('222', 'dsfdsfdsfds', '2017-09-06 18:23:04', null);
INSERT INTO `usermsg` VALUES ('1', '你购买的商品已经发货请注意查收', '2017-09-13 12:54:32', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 13:00:24', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 13:02:56', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 13:39:26', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 13:59:13', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 14:21:17', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 14:21:26', null);
INSERT INTO `usermsg` VALUES ('1', '你购买的商品已经发货请注意查收', '2017-09-13 14:34:14', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 14:54:35', null);
INSERT INTO `usermsg` VALUES ('1', '拒绝退款请求', '2017-09-13 14:54:54', null);
