DROP DATABASE IF EXISTS `db_volunteer`;
CREATE DATABASE IF NOT EXISTS `db_volunteer`;
USE `db_volunteer`;
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`(
    `id` BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '账户主键ID',
    `username` VARCHAR (32) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '密码',
    `email` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '邮箱',
    `phone` VARCHAR(12) NOT NULL DEFAULT '' COMMENT '电话号码',
    `id_card` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '身份证',
    `roles` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '权限',
    `is_valid` TINYINT (1) DEFAULT 1 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '学生信息表';

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`(
    `id` BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '学生主键ID',
    `account_id` BIGINT (20) UNSIGNED COMMENT '账户主键ID',
    `real_name` VARCHAR (32) NOT NULL DEFAULT '' COMMENT '真实名称',
    `nickname` VARCHAR (32) NOT NULL DEFAULT '' COMMENT '简称',
    `school_name` VARCHAR (32) NOT NULL DEFAULT '' COMMENT '学校',
    `student_number` VARCHAR (16) NOT NULL DEFAULT '' COMMENT '学号',
    `avatar_url` VARCHAR (255) DEFAULT NULL COMMENT '头像',
    `is_valid` TINYINT (1) DEFAULT 1 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '学生信息表';


DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`(
    `id` BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '学校主键ID',
    `account_id` BIGINT (20) UNSIGNED COMMENT '账户主键ID',
    `school_name` VARCHAR (32) NOT NULL DEFAULT '' COMMENT '学校名称',
    `nickname` VARCHAR (32) NOT NULL DEFAULT '' COMMENT '简称',
    `avatar_url` VARCHAR (255) DEFAULT NULL COMMENT '头像',
    `is_valid` TINYINT (1) DEFAULT 1 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '学生信息表';

DROP TABLE IF EXISTS `student_record`;
CREATE TABLE `student_record`(
    `id` BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `student_id` BIGINT (20) UNSIGNED COMMENT '学生ID',
    `activity_id` BIGINT (20) UNSIGNED COMMENT '活动ID',
    `title` VARCHAR(64) NOT NULL COMMENT '志愿名称',
    `score` BIGINT (20) UNSIGNED default 0 COMMENT '积分',
    `status` TINYINT (2) NOT NULL COMMENT '活动状态',
    `is_valid` TINYINT (1) DEFAULT 0 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '学生记录信息表';

DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`(
    `id` BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `school_id` BIGINT (20) UNSIGNED COMMENT '学校主键',
    `title` VARCHAR(64) NOT NULL COMMENT '志愿名称',
    `subtitle` VARCHAR(64) NOT NULL COMMENT '副标题',
    `image_url` VARCHAR(64) COMMENT '展示图片URL',
    `content` VARCHAR(1024) NOT NULL COMMENT '活动内容',
    `score` BIGINT (20) NOT NULL COMMENT '得分',
    `status` TINYINT (2) NOT NULL COMMENT '活动状态',
    `person_number` INT(10) NOT NULL DEFAULT 0 COMMENT '当前人数',
    `total_number` INT(10) NOT NULL COMMENT '总人数',
    `star` INT(10) NOT NULL DEFAULT 0 COMMENT '点赞人数',
    `start_time` VARCHAR(32) COMMENT '活动开始时间',
    `end_time` VARCHAR(32) COMMENT '活动结束时间',
    `address` VARCHAR(64) COMMENT '活动地点',
    `is_valid` TINYINT (1) DEFAULT 1 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '志愿活动表';


DROP TABLE IF EXISTS `activity_star`;
CREATE TABLE `activity_star`(
    `id` BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `student_id` BIGINT(20) UNSIGNED COMMENT '学生ID',
    `activity_id` BIGINT(20) UNSIGNED COMMENT '活动ID',
    `is_valid` TINYINT (1) DEFAULT 1 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '活动点赞表';


DROP TABLE IF EXISTS `event_comment`;
CREATE TABLE `event_comment`(
    `id`  BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `activity_id` BIGINT UNSIGNED COMMENT '活动ID',
    `student_id` BIGINT UNSIGNED COMMENT '学生ID',
    `from` BIGINT UNSIGNED DEFAULT 0 COMMENT '来自的评论',
    `content` varchar(128) NOT NULL COMMENT '活动评论内容',
    `is_valid` TINYINT (1) DEFAULT 1 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '活动评论表';

DROP TABLE IF EXISTS `comment_star`;
CREATE TABLE `comment_star`(
    `id` BIGINT (20) UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `student_id` BIGINT(20) UNSIGNED COMMENT '学生ID',
    `comment_id` BIGINT(20) UNSIGNED COMMENT '评论ID',
    `is_valid` TINYINT (1) DEFAULT 1 COMMENT '是否有效',
    `create_date` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT '评论点赞表';



