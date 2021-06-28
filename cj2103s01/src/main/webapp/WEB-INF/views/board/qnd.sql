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
	primary key(qId),
	foreign key(pId) references product(pId)
);


show tables;

drop table qna;

select *from qna;

select * from qna order by nId;

select count(*) from qna;

select * from qna order by nId desc limit 0, 3;

ALTER TABLE orders ADD FOREIGN KEY (customer_sid) REFERENCES customer (sid);

select bb.*, cast(timestampdiff(minute, bb.rRdate, now())/60 as signed integer) as diffTime from qna bb where pId=10 order by rId desc limit 1,5;

select count(*) from qna where pId = 10;


select count(*) from qna where pId;