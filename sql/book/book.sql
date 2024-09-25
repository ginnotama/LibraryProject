##由于web框架未搭建, 所有查询所需值都使用 双引号替代. 等待 web搭建后, 需使用 mybatis 修改相关sql

## 切换数据库
use `library`

show CREATE TABLE book
desc book

-- 插入图书数据
INSERT INTO book VALUES(null,'','','','','','','','','')

-- 分页查询图书列表, 默认按照书籍名称排序, 左连接查询书籍详情
SELECT * FROM book LEFT JOIN type ON book.type_id = type.type_id ORDER BY book_name desc LIMIT 0,10 


-- 条件查询书籍(使用索引条件查询), 默认按照书籍名称排序, 左连接查询书籍详情
SELECT * FROM book  LEFT JOIN type ON book.type_id = type.type_id AND book_name = '' ORDER BY book_name desc LIMIT 0,10;
SELECT * FROM book  LEFT JOIN type ON book.type_id = type.type_id AND book_author = '' ORDER BY book_name desc LIMIT 0,10;

-- 查询具体书籍详情
SELECT * FROM book WHERE book_id = ''

-- 更新书籍状态('书籍状态 0可借阅 1已借阅 2丢失等等')
UPDATE book SET book_status = '' , book_update_time = '' WHERE book_id =''

-- 修改更新书籍信息
UPDATE book SET book_num ='' , book_author = '' , book_name = '' , book_desc = '', book_location = '', book_update_time ='' WHERE book_id ='';



