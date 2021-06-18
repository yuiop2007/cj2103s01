create table notice(
	nId int not null auto_increment,
	nWriter varchar(100) null,
	nPwd varchar(100) null,
	nTitle varchar(100) null,
	nContent mediumtext null,
	nFile varchar(4000) null,
	nRdate datetime default now(),
	nHit int default 0,
	primary key(nId)	
);

insert into notice values (default, '관리자', 1234, '제목입니다.', '내용입니다.', default, default, default);

show tables;

select *from notice;

select * from notice order by nId;

select count(*) from notice;

select * from notice order by nId desc limit 0, 3;