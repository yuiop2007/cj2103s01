create table event(
	eId int not null auto_increment,
	eWriter varchar(50) null,
	eTitle varchar(100) null,
	eName varchar(100) null default '미님',
	ePwd varchar(100) null,
	eContent mediumtext null,
	eFile varchar(4000) null,
	eRdate datetime default now(),
	eHit int default 0,
	primary key(eId)	
);

insert into event values (default, 'yuiop2007', '제목입니다.', default, 1234, '내용입니다.', default, default, default);

show tables;

drop table event;

select *from event;

select * from event order by eId;

select count(*) from event;

select * from event order by eId desc limit 0, 3;

SELECT bb.*, CAST(TIMESTAMPDIFF(MINUTE, bb.eRdate, NOW())/60 AS SIGNED INTEGER) AS diffTime FROM event bb ORDER BY eRdate DESC;

select count(eId) from event where eTitle like concat('%','제목','%');

select bb.*, cast(timestampdiff(minute, bb.eRdate, now())/60 as signed integer) as diffTime from event bb where eTitle like concat('%','제','%')  order by eId desc limit 1,10;

select count(eId) from event where eTitle like concat('%', '제' ,'%');