create table wishlist(
	wId int not null auto_increment,
	mId varchar(50) null,
	pId int null,
	primary key(wId),
	foreign key(mId) references member(mId),
	foreign key(pId) references product(pId)
);


select *from wishlist where mId;