# 创建数据库
create database phone_store;

show databases;

use phone_store;

show tables;

# 创建表: 手机类型
DROP TABLE IF EXISTS `phone_category`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# SET character_set_client = utf8mb4 ;
CREATE TABLE `phone_category` (
    `category_id` int(11) NOT NULL AUTO_INCREMENT,
    `category_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '类目名称',
    `category_type` int(11) NOT NULL COMMENT '类目编号',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`category_id`),
    UNIQUE KEY `uqe_category_type` (`category_type`)
) COMMENT='类目表';

insert into phone_category values (null, '铂光金', 1, now(), now());

# 创建表: 手机信息
DROP TABLE IF EXISTS `phone_info`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# SET character_set_client = utf8mb4 ;
CREATE TABLE `phone_info` (
    `phone_id` int(11) NOT NULL AUTO_INCREMENT,
    `phone_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
    `phone_price` decimal(8,2) NOT NULL COMMENT '商品单价',
    `phone_description` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `phone_stock` int(11) NOT NULL COMMENT '库存',
    `phone_icon` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小图',
    `category_type` int(11) NOT NULL COMMENT '类目编号',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `phone_tag` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
    PRIMARY KEY (`phone_id`)
) COMMENT='商品表';

# 创建表: 手机规格
DROP TABLE IF EXISTS `phone_specs`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# SET character_set_client = utf8mb4 ;
CREATE TABLE `phone_specs` (
    `specs_id` int(11) NOT NULL AUTO_INCREMENT,
    `phone_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL,
    `specs_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '规格名称',
    `specs_stock` int(11) NOT NULL COMMENT '库存',
    `specs_price` decimal(8,2) NOT NULL COMMENT '单价',
    `specs_icon` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '小图',
    `specs_preview` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '预览图',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`specs_id`)
) COMMENT='商品规格表';
#) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='商品规格表';