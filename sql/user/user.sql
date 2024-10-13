##web框架未搭建, 所有查询所需值都使用 双引号替代. 等待 web搭建后, 需使用 mybatis 修改相关sql

## 切换数据库
use `library`

SHOW CREATE TABLE user

desc user

## 用户注册
INSERT INTO `user` VALUES(NULL, "user_login_name", "user_password", "user_name", "user_status",'user_type'
'user_create_time')


##用户登录名查询用户信息是否存在
SELECT count(*) FROM `user` WHERE user_login_name = "" AND user_type ="";

##用户登录校验查询
SELECT * FROM `user` WHERE user_login_name = "" AND user_password ="";


##用户密码修改
UPDATE `user` SET user_password = "" WHERE user_id = ""

##用户名修改
UPDATE `user` SET user_name = "" WHERE user_id = ""

##用户状态修改(注销, 封号等)
UPDATE `user` SET user_status = "" WHERE user_id = ""


