create table cart(
	cartId int not null auto_increment,
	mId varchar(50) null,
	pId int null,
	pOption varchar(20) null,
	pCnt int null,
	pPrice int null,
	primary key(cartId),
	foreign key(mId) references member(mId),
	foreign key(pId) references product(pId)
);

select *from cart where cartId = 60;

select cartId from cart where mId = 'admin' and pOption = 'Black_L' and pId = 19;

drop table cart;
