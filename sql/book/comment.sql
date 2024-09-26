##由于web框架未搭建, 所有查询所需值都使用 双引号替代. 等待 web搭建后, 需使用 mybatis 修改相关sql

## 切换数据库
use `library`

show CREATE TABLE comment
desc comment

-- 分页查询某本书的所有评论, 按照评论时间排序
SELECT * FROM `comment` WHERE book_id = '' ORDER BY comment_create_time desc LIMIT (0.10)


-- 分页查询查询某一用户所有评论, 按照评论时间排序
SELECT * FROM `comment` WHERE user_id = '' ORDER BY comment_create_time desc LIMIT (0.10)