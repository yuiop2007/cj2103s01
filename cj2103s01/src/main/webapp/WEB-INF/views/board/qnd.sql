create table qna(
	qId int not null auto_increment, 
	pId int not null,
	qCate varchar(100) not null,
	qWriter varchar(40) null,
	qTitle varchar(100) null,
	qName varchar(100) null default '미님',
	qPwd varchar(100) null,
	qContent mediumtext null,
	qSecret int null default 0, /* 공개0, 비공개1 */ 
	qRdate datetime default now(),
	qHit int default 0,
	originNo int not null default 0,  
	groupNo int not null default 0,		
	primary key(qId),
	foreign key(pId) references product(pId)
);

insert into qna values (default, 6, '기타', '작성자' ,'제목', '이름', 1234, '내용', 1, default, default, qId, default, default);

insert into qna values (default, 5, '상품관련', 'yuiop2007' ,'(상품관련) 문의합니다.', '미님', '$2a$10$06S0J7ZDI3.cBWncOb/Hre9z2tpux7cXt8x1wRYPYzKxnFUKCVPKG', '<p>qweqwe</p>
', 1, default, default, 7, 0+1, default);

show tables;

drop table qna;

select *from qna;

select * from qna order by qId desc limit 1;

select count(*) from qna;

select * from qna order by nId desc limit 0, 3;

ALTER TABLE orders ADD FOREIGN KEY (customer_sid) REFERENCES customer (sid);

select bb.*, cast(timestampdiff(minute, bb.rRdate, now())/60 as signed integer) as diffTime from qna bb where pId=10 order by rId desc limit 1,5;

select count(*) from qna where pId = 10;


select count(*) from qna where pId;

select qId from qna  where pId=12 order by qId desc limit 1;