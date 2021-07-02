create table coupon(
	cId int not null auto_increment,
	mId varchar(50) null,
	cmCate int null,
	cpCate varchar(50) null,
	cName varchar(100) null,
	cDiscount int null default 0,
	cPrice int null default 0,
	cRdate datetime default now(),
	cEnddate datetime null,
	primary key(cId),
	foreign key(mId) references member(mId)
);

select *from coupon;

select count(*) from coupon;

drop table coupon;

desc coupon;

select * from coupon group by cName;