create table slide(
	sId int not null auto_increment,
	sImage varchar(1000) null,
	sNum int null,
	primary key(sId)
);

select *from slide;

select count(*) from slide;


select *from slide order by sNum asc;