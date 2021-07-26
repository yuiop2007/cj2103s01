create table member(
	mId varchar(50) not null,
	mPwd varchar(100) not null,
	mName varchar(100) not null,
	mPost varchar(10) null,
	mAddress varchar(500) null,
	mPhone varchar(300) not null,
	mEmail varchar(100) null,
	mGender varchar(10) default '남자',
	mRdate datetime default now(),
	mBirth varchar(20) null,
	mSolar varchar(10) default '양력',
	mLevel int default 4,
	mBuy int default 0,
	mDrop varchar(10) default 'NO',
	mCmid varchar(50) null,
	mPoint int default 2000,
	mUsepoint int default 0,
	primary key(mId),
	unique(mid)
);

drop table member;

desc member;

select *from member where mLevel > 0;

show tables;

show databases;

select count(*) from member where mLevel > 0;
select count(*) from member where mDrop = 'YES';
select sum(mBuy) from member where mLevel > 0;

update member set mBuy=mBuy-1 mPoint=mPoint-550 where mId='admin';

select count(*) from member where mId like concat('%', 'y' ,'%') and NOT mLevel IN ('0');

select * from member where mId = 'yuiop2012' and mLevel > 0 limit 0, 5;