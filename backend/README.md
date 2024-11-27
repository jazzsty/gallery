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
    (0, 'alpha', '/img/landscape.jpg', 10000000, 5),
    (0, 'beta', '/img/hill.jpg', 20000000, 10),
    (0, 'gamma', '/img/vincent-van-gogh.jpg', 30000000, 30);
    
commit;