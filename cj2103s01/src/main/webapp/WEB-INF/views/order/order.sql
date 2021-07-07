create table orders_detail(
	odId int not null auto_increment,
	oId int null,
	mId varchar(50) null,
	pId int null,
	odOption varchar(20) null,
	odCnt int null,
	odPrice int null,
	primary key(odId),
	foreign key(oId) references orders(oId)
);

create table orders(
	oId int not null auto_increment,
	mId varchar(50) null,
	oRdate datetime default now(),
	oName varchar(100) not null,
	oPhone varchar(300) not null,
	oPost varchar(10) null,
	oAddress varchar(500) null,
	oEmail varchar(100) null,
	oMessage varchar(500) null,
	oPrice int null,
	oPayment int null, /* 1:현금입금(무통장) 2:카드 */
	oDelivery varchar(50) null,
	oStatus varchar(50) null default '입금전',
	primary key(oId),
	foreign key(mId) references member(mId)
);

drop table orders_detail;
drop table orders;