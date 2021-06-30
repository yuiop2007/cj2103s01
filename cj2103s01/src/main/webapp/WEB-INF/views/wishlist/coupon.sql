create table coupon(
	cId int not null auto_increment,
	mId varchar(50) null,
	pId int null,
	cName varchar(100) null,
	cDiscount int null default 0,
	cPrice int null default 0,
	cRdate datetime default now(),
	cEnddate datetime null,
	primary key(cId),
	foreign key(mId) references member(mId),
	foreign key(pId) references product(pId)
);

select *from coupon;

desc coupon;