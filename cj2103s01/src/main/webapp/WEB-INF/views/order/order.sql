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
	oUsePoint int null default 0,
	oSetPoint int null default 0,
	oPayment varchar(100) null, /* 무통장, 카드 */
	oDelivery varchar(50) null,
	oStatus varchar(50) null default '입금전', /* 배송준비중 배송중 배송완료*/
	oChange varchar(50) null default '주문', /* 취소 교환 반품 */
	primary key(oId),
	foreign key(mId) references member(mId)
);

drop table orders_detail;
drop table orders;

select *from orders;
select now()-;
select to_days(now()) - to_days('2021-07-01 09:57:19');
select date_add(now(), interval -7 day);
select datediff(now(), '2021-4-1');

select *from orders where mid = 'yuiop2007' and oRdate > date_add(now(), interval -30 day);
select * from orders where mId = 'yuiop2007';

select count(*) from orders where oStatus = '입금전' and mId = 'yuiop2007';

select * from orders_detail group by oId;

select count(*) from orders where oChange = '취소' or oChange = '취소완료' and mId ='admin';

select count(*) from orders where oStatus = 'notPay';

select sum(oPrice) from orders where oStatus = '배송완료' and oChange = '구매확정';

select * from orders_detail where mId = 'yuiop2007' group by oId;