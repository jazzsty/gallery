create database gallery;
use gallery

show tables;

drop table items;

create table items (
	id int primary key auto_increment,
	name varchar(50) not null,
	img_path varchar(100),
	price int,
	discount_per int
)

show tables;

desc items;

select * from items;

INSERT INTO items (id, name, img_path, price, discount_per) 
VALUES 
    (0, 'landscape', '/img/landscape.jpg', 10000000, 5),
    (0, 'hill', '/img/hill.jpg', 20000000, 10),
    (0, 'vincent-van-gogh', '/img/vincent-van-gogh.jpg', 30000000, 30);
    
INSERT INTO members (id, email, password) VALUES (0, 'jazzsty@naver.com', '1234');
    
commit;