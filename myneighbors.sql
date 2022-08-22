show databases;
create database myneighbors;

select  * from user;
use myneighbors;

   
CREATE TABLE IF NOT EXISTS `user`(
`id` INT UNSIGNED AUTO_INCREMENT KEY COMMENT '编号',
`name` VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
`alais` VARCHAR(20) not null default '小名' COMMENT '别名',
`password` CHAR(32) NOT NULL COMMENT '密码',
`email` VARCHAR(50) NOT NULL UNIQUE COMMENT '邮箱',
`role` VARCHAR(10) NOT NULL default '业主' COMMENT '角色',
`estate` VARCHAR(30) default '富力半岛' COMMENT '小区'
)ENGINE=INNODB DEFAULT CHARSET=UTF8mb4;

insert into user (name,  password, email) values ("harry", "1111111", "harry@gamil.com");

insert into user (name,  password, email) values ("clipus", "1111111", "clipus@hotmail.com");
insert into user (name,  password, email) values ("hugo", "1111111", "hugo@gamil.com");
insert into user (name,  password, email) values ("jerry", "1111111", "jerryy@gamil.com");
insert into user (name,  password, email) values ("tom", "1111111", "tom@gamil.com");
insert into user (name,  password, email) values ("hiroko", "1111111", "hiroko@gamil.com");
insert into user (name,  password, email) values ("mathen", "1111111", "mathen@gamil.com");
