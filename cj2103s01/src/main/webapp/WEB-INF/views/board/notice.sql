create table notice(
	nId int not null auto_increment,
	nWriter varchar(40) null,
	nTitle varchar(100) null,
	nName varchar(100) null default '미님',
	nPwd varchar(100) null,
	nContent mediumtext null,
	nFile varchar(4000) null,
	nRdate datetime default now(),
	nHit int default 0,
	primary key(nId)	
);

insert into notice values (default, 'yuiop2007', '제목입니다.', default, 1234, '내용입니다.', default, default, default);

show tables;

drop table notice;

select *from notice;

select * from notice order by nId;

select count(*) from notice;

select * from notice order by nId desc limit 0, 3;