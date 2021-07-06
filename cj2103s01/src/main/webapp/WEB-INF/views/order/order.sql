create table orders_detail(
	odId int not null auto_increment,
	oId int null,
	pId int null,
	odName varchar(20) null,
	odNum int null,
	odPrice int null,
	primary key(odId),
	foreign key(oId) references orders(oId),
	foreign key(pId) references product(pId)
);

create table orders(
	oId int not null auto_increment,
	mId varchar(50) null,
	cId int null,
	oRdate datetime default now(),
	oName varchar(100) not null,
	oPhone varchar(300) not null,
	oPost varchar(10) null,
	oAddress varchar(500) null,
	oMassage varchar(500) null,
	oPrice int null,
	oPayment int null, /* 1:현금입금(무통장) 2:카드 */
	oDelivery varchar(50) null,
	primary key(oId),
	foreign key(mId) references member(mId),
	foreign key(cId) references coupon(cId)
);

drop table orders_detail;
drop table orders;