##由于web框架未搭建, 所有查询所需值都使用 双引号替代. 等待 web搭建后, 需使用 mybatis 修改相关sql

## 切换数据库
use `library`;
show CREATE TABLE borrow;

desc borrow;

-- 查询某书籍的借阅信息
SELECT * FROM book LEFT JOIN borrow ON book.book_id = borrow.book_id ORDER BY borrow.borrow_date LIMIT 0,10

-- 查询某用户的借阅信息
SELECT * FROM `user` LEFT JOIN borrow ON `user`.user_id = borrow.user_id ORDER BY borrow.borrow_date desc LIMIT 0,10

-- 更新借阅信息
UPDATE borrow SET return_date = '' , borrow_state ='' WHERE borrow_id = '';

-- 更新借阅延期
UPDATE borrow SET end_date = '' , borrow_state ='' WHERE borrow_id = '';


--插入借阅信息
INSERT INTO borrow VALUES(null, 'book_id','user_id','borrow_state','borrow_date','end_date','return_date','create_time');


