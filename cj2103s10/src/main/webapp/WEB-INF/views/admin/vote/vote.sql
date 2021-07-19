/* 투표자 기본 인적사항 */
create table votePersonInfor(
  idx    int  not null primary key,
  gender char(2) not null,				/* 성별 */
  age    varchar(5) not null,			/* 나이대 */
  job		 varchar(10) not null,		/* 직업 */
  area   varchar(10) not null		  /* 지역  */
);
drop table votePersonInfor;
select max(idx) from votePersonInfor;
select * from votePersonInfor;

/* 투표지 내용 테이블 */
create table votePopular (
  idx      int          not null  auto_increment primary key,
  part     varchar(10)  not null,	/* 분류 */
  title    varchar(100) not null,	/* 그림 주제 설명 */
  subTitle varchar(200) not null, /* 개별 그림 설명 */
  realImg  varchar(300) not null, /* 실제 서버에 저장되는 파일명 */
  thumbImg varchar(300) not null, /* 썸네일 */
  progress char(3) default 'ON'		/* 진행상태(ON/OFF) */
);
/* 추가로 들어갈 내용 : 날짜(언제부터 언제까지) */
drop table votePopular;
delete from votePopular where idx=2;
select * from votePopular;

/* 투표지 */
create table vote (
  idx  int  not null auto_increment primary key,
  personInforIdx int not null,				/* 사람정보테이블의 고유번호 */
  popularIdx int not null,						/* 설문지 고유번호 */
  voteNum    int not null,						/* 설문지에 투표한 항목번호 */
  foreign key (personInforIdx) references votePersonInfor(idx), /* 외래키로 personInforIdx 설정 */
  foreign key (popularIdx) references votePopular(idx) on update cascade on delete restrict /* 외래키로 popularIdx를 설정했고, popular테이블의 idx가 변경되면 함께 영향을 받고, 현재 레코드가 있다면 원본테이블의 idx는 삭제될 수 없다. */
);
drop table vote;
insert into vote value (default,1,1,3);
select * from vote;
select * from vote where popularIdx=1;
select voteNum, count(voteNum) from vote where popularIdx=1 group by voteNum;
select a.voteNum, count(a.voteNum) as voteSum, b.subTitle from vote a, votePopular b where a.popularIdx=2 group by a.voteNum;

/* 테이블 구조와 데이터 복사 */
create table if not exists works.votePersonInfor select * from javaspring.votePersonInfor;
select * from votePersonInfor;

create table if not exists works.votePopular select * from javaspring.votePopular;
select * from votePopular;

create table if not exists works.vote select * from javaspring.vote;
select * from vote;



insert into works.votePersonInfor select * from javaspring.votePersonInfor;