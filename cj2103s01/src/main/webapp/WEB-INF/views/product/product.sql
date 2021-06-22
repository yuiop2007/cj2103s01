create table product(
	pId int not null auto_increment,
	pName varchar(100) not null,
	pCate varchar(100) not null,
	pPrice int not null,
	pSale int not null default 0,
	pStock int not null,
	pImage varchar(1000) not null,
	pContent mediumtext not null,
	pDelivery varchar(50) not null default '일반',
	pRdate datetime default now(),
	pHit int not null default 0,
	pSell int not null default 0,
	primary key(pId)
);

insert into product values (default, '상품이름', 'bottom', '50000', '10', '20', '대표이미지', '상품상세내용', default, default, default, default);

select *from product;

drop table product;