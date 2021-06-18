create table member(
	mId varchar(50) not null,
	mPwd varchar(100) not null,
	mName varchar(100) not null,
	mPost varchar(10) null,
	mAddress varchar(500) null,
	mPhone varchar(300) not null,
	mEmail varchar(100) null,
	mGender varchar(10) default 'M',
	mRdate datetime default now(),
	mBirth varchar(20) null,
	mSolar varchar(10) default 'T',
	mLevel int default 0,
	mBuy int default 0,
	mDrop varchar(10) default 'NO',
	mCmid varchar(50) null,
	mPoint int default 2000,
	primary key(mId)
);

drop table member;

desc member;

select *from member;

show tables;

show databases;