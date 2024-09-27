##由于web框架未搭建, 所有查询所需值都使用 双引号替代. 等待 web搭建后, 需使用 mybatis 修改相关sql

## 切换数据库
use `library`

show CREATE TABLE comment
desc comment

-- 分页查询某本书的所有评论, 按照评论时间排序
SELECT * FROM `comment` WHERE book_id = '' ORDER BY comment_create_time desc LIMIT 0.10;

-- 分页查询查询某一用户所有评论
SELECT * FROM `comment` WHERE user_id = '' ORDER BY comment_create_time desc LIMIT 0.10;

-- 插入评论
INSERT INTO `comment` VALUES(NULL, 'comment_desc',
'comment_create_time',
'book_id',
'user_id')

-- 更新评论
UPDATE `comment` SET comment_desc = '' WHERE comment_id ='';

-- 更新评论状态(已删除, 不可见, 正常)
UPDATE `comment` SET comment_state = '' WHERE comment_id ='';
