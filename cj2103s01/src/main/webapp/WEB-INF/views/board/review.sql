create table review(
	rId int not null auto_increment,
	pId int not null,
	rRating int not null,
	rWriter varchar(40) null,
	rTitle varchar(100) null,
	rName varchar(100) null default '미님',
	rPwd varchar(100) null,
	rContent mediumtext null,
	rRdate datetime default now(),
	rHit int default 0,
	primary key(rId),
	foreign key(pId) references product(pId)
);

insert into review values (default, 'yuiop2007', '제목입니다.', default, 1234, '내용입니다.', default, default, default);

show tables;

drop table review;

select *from review;

select * from review order by nId;

select count(*) from review;

select * from review order by nId desc limit 0, 3;

ALTER TABLE orders ADD FOREIGN KEY (customer_sid) REFERENCES customer (sid);

select bb.*, cast(timestampdiff(minute, bb.rRdate, now())/60 as signed integer) as diffTime from review bb where pId=10 order by rId desc limit 1,5;

select bb.*, cast(timestampdiff(minute, bb.rRdate, now())/60 as signed integer) as diffTime from review bb where mId = #{mId} order by rId desc limit #{startIndexNo},#{rpageSize};

select count(*) from review where pId = 10;


select count(*) from review where pId;

select count(*) from review where mId = 'yuiop2007';