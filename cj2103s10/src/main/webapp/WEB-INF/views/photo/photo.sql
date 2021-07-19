/* 포토 갤러리 */
create table photo2 (
  idx   int  not null  auto_increment primary key,
  name  varchar(20) not null,			/* 올린 사람(닉네임) */
  part  varchar(20) not null,			/* 이미지 분류 */
  title varchar(100) not null,		/* 갤러리 제목 */
  content text not null,					/* 갤러리 내용(사진이 주 목적) */
  thumbnail varchar(100) not null,/* 썸네일 이미지 */
  wDate   datetime default now(),	/* 올린 날짜 */
  hostIp  varchar(50) not null,		/* 접속 사이트 */
  readNum	int	default 0 					/* 조회수 */
);

drop table photo2;

desc photo2;

update photo2 set content = replace(content,'cjs2008','cj2103s10');

select * from photo2 order by idx desc;

/* 데이터베이스의 테이블 복사하기 */
show databases;
create table works.photo2 like javaspring.photo2;
insert into works.photo2 select * from javaspring.photo2;