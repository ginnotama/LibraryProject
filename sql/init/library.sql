
-- 创建图书管理系统数据库
DROP DATABASE IF EXISTS `library`;
CREATE DATABASE IF NOT EXISTS `library` ;
USE `library`;


-- 创建用户表 已用户自增 id 作为主键索引
DROP DATABASE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`( 
`user_id` BIGINT NOT NULL  auto_increment COMMENT '主键自增Id',
`user_login_name` VARCHAR(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名',
`user_password`  VARCHAR(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
`user_name` VARCHAR(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
`user_status` INT(1) NOT NULL COMMENT '账号状态',
`user_type` INT(1) NOT NULL COMMENT '账号类型(0普通,1管理员)',
`user_create_time` datetime NOT NULL COMMENT '创建时间',
 PRIMARY KEY (`user_id`) USING BTREE
)ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- 创建书籍表, 书籍id作为主键索引 建立书籍编号 书籍名称 书籍作者 书籍类型 的 B+树索引, 便于后续大量数据查询, 提高查询效率
DROP DATABASE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book`(
`book_id` BIGINT NOT NULL  auto_increment COMMENT '书籍id',
`book_num` VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍编号',
`book_name`  VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍名称',
`book_author` VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍作者',
`book_desc`  VARCHAR(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍描述',
`book_location`  VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍位置',
`book_type` BIGINT NOT NULL COMMENT '书籍类型',
`book_status` INT(1) NOT NULL COMMENT '书籍状态 0可借阅 1已借阅 2丢失等等',
`book_create_time` datetime NOT NULL COMMENT '书籍创建时间',
`book_update_time` datetime NOT NULL COMMENT '书籍更新时间',
 PRIMARY KEY (`book_id`) USING BTREE,
 KEY `book_num` (`book_num`) USING BTREE,
 KEY `book_name` (`book_name`) USING BTREE,
 KEY `book_author` (`book_author`) USING BTREE,
 KEY `book_type` (`book_type`) USING BTREE
)ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- 创建借阅表, 作为书籍表和用户表中间表, 自增 id 作为主键索引, 创建书籍编号和用户id作为查询索引
DROP DATABASE IF EXISTS `borrow`;
CREATE TABLE IF NOT EXISTS `borrow`(
`borrow_id` BIGINT NOT NULL  auto_increment COMMENT '借阅id',
`book_id` BIGINT NOT NULL  COMMENT '书籍id',
`user_id` BIGINT NOT NULL COMMENT '用户id',
`borrow_date` datetime NOT NULL COMMENT '借阅时间',
`end_date` datetime NOT NULL COMMENT '截止时间',
`return_date` datetime NOT NULL COMMENT '归还时间',
`create_time` datetime NOT NULL COMMENT '创建时间',
 PRIMARY KEY (`borrow_id`) USING BTREE,
 KEY `book_id` (`book_id`) USING BTREE,
 KEY `user_id` (`user_id`) USING BTREE
)ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;


-- 创建书籍类型表, 与书籍 一对多关系表
DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type`(
`type_id` BIGINT NOT NULL  auto_increment COMMENT '类型id',
`type_name` VARCHAR(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
`type_desc`  VARCHAR(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型描述',
`book_id`  BIGINT NOT NULL COMMENT '书籍id',
 PRIMARY KEY (`type_id`) USING BTREE
 )ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

 -- 创建评论表, 作为书籍表和用户表中间表, 自增 id 作为主键索引, 创建书籍编号和用户id作为查询索引
 DROP TABLE IF EXISTS `comment`;
 CREATE TABLE IF NOT EXISTS `comment`(
    `comment_id` BIGINT NOT NULL  auto_increment COMMENT '评论id自增主键',
    `comment_desc`  VARCHAR(20000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论详情',
    `book_create_time` datetime NOT NULL COMMENT '评论创建时间',
    `book_id` BIGINT NOT NULL  COMMENT '书籍id',
    `user_id` BIGINT NOT NULL COMMENT '用户id',
    PRIMARY KEY (`comment_id`) USING BTREE,
    KEY `book_id` (`book_id`) USING BTREE,
    KEY `user_id` (`user_id`) USING BTREE
 )ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

