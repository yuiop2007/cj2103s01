create table product(
	pId int not null auto_increment,
	pName varchar(100) not null,
	pCate varchar(100) not null,
	pColor varchar(50) not null,
	pSize varchar(10) not null,
	pPrice int not null,
	pSale int not null default 0,
	pStock int not null,
	pImage varchar(1000) not null,
	pInfo mediumtext not null,
	pContent mediumtext not null,
	pDelivery varchar(50) not null default '일반', /* or 60000이상 무료*/
	pRdate datetime default now(),
	pHit int not null default 0,
	pSell int not null default 0,
	primary key(pId)
);

insert into product values (default, '상품이름', 'bottom','블랙/화이트/', 'M/L', '50000', '10', '20', '대표이미지', '상품 정보', '상품상세내용', default, default, default, default);

select *from product;

drop table product;

update product set pName='버튼 미니멀 레더 자켓' where pId=3;

update product set pName='1', pCate='O', pColor='q', pSize='M', pPrice=123, pStock=12, pImage='asd', pInfo='123', pContent='asd' where pId=3;

delete from product where pId = 13;