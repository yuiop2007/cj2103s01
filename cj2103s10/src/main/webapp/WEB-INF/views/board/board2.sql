/* board2.sql */
create table board2 (
  idx		int not null auto_increment,		/* 게시글의 고유번호 */
  name  varchar(20)  not null,					/* 게시글 올린사람의 닉네임 */
  title varchar(100) not null,					/* 게시글의 글 제목 */
  email varchar(50)  not null,					/* 이메일 */
  pwd 	varchar(100)	not null,					/* 비밀번호 */
  wDate datetime	 default now(),				/* 글쓴날짜 */
  readNum int		default 0,							/* 글 조회수 */	
  hostIp	varchar(50) not null,					/* 접속 IP주소 */
  good		int 	default 0,							/* 좋아요 */
  content text  not null,								/* 게시글 내용 */
  primary key(idx)											/* 기본키는 고유번호 */
);

desc board2;

insert into board2 values (default,'관리맨','게시판 서비스를 시작합니다.','cjsk1126@hanmail.net','1234',default,default,'218.236.203.146',default,'게시판 서비스 개시.. 잘 부탁드립니다.');

select * from board2;

select * from board2 where idx = 16;
select * from board2 where idx < 16 order by idx desc limit 1;  /* 이전글 */
select * from board2 where idx > 16 limit 1;										 /* 다음글 */

----------------댓글테이블(replyBoard)------------------------------

create table replyBoard2(
  idx   		int  	not null auto_increment primary key,	/* 댓글의 고유번호 */
  boardIdx 	int 	not null,						/* 원본글의 고유번호 */
  mid			 	varchar(20) not null,				/* 올린이의 아이디 */
  nickName 	varchar(20) not null,				/* 올린이의 닉네임 */
  wDate			datetime		default now(),	/* 댓글을 쓴 날짜 */
  hostIp		varchar(50)	not null,				/* 댓글쓴 PC의 IP */
  content		text				not null,				/* 댓글내용 */
  foreign key(boardIdx) references board(idx)
  	on update cascade
  	on delete restrict
);
desc replyBoard2;

select * from replyBoard2 order by idx desc;

select count(*) from replyBoard2;
select count(*) from replyBoard2 where boardIdx = 17;

select *, (select count(*) from replyBoard2 where boardIdx = board.idx) as replyCount  from board order by idx desc limit 0, 5;
select *, (select count(*) from replyBoard2 where boardIdx = board.idx) as replyCount from board where name = '홍장군' order by idx desc limit 0, 5;


------- 날짜 함수 연습 ---------------------------------------------------------------

/* 오늘날짜 몇일? 몇시? 몇분? 몇초? */
select NOW();
select SYSDATE();
SELECT LOCALTIME();

select NOW() + 1;
select SYSDATE() + 1;
SELECT LOCALTIME() + 1;

/* 오늘날짜? */
SELECT CURDATE();

/* 오늘은 몇년? */
SELECT YEAR(NOW());

/* 오늘은 몇월? */
SELECT MONTH(NOW());

/* 오늘은 몇일? */
SELECT DAY(NOW());

SELECT NAME, DAY(NOW()) FROM BOARD;

SELECT HOUR(NOW());

SELECT MINUTE(NOW());

SELECT SECOND(NOW());

/* 오늘은 무슨요일(인덱스값으로 반환 : 일-1, 월-2, 화-3, 수-4, 목-5)? */
SELECT DAYOFWEEK(NOW());

/* 오늘은 무슨요일(영문으로 반환) */
SELECT DAYNAME(NOW());
SELECT DAYNAME(CURDATE());

SELECT NAME, DAYNAME(wDate) FROM BOARD;
SELECT NAME, DAYNAME(birthday) FROM MEMBER;
SELECT * FROM MEMBER;


/* 오늘은 무슨요일(영문으로 반환) */
SELECT DAYNAME(NOW());
SELECT DAYNAME(CURDATE());

SELECT NAME, DAYNAME(wDate) FROM BOARD;
SELECT NAME, DAYNAME(birthday) FROM MEMBER;
SELECT * FROM MEMBER;

/* 해당월의 마지막 날짜를 구하시오 */
SELECT LAST_DAY("2021-02-05 10:50:20");
SELECT LAST_DAY("2020-02-05 10:50:20");
SELECT LAST_DAY(NOW());

/* 날짜형식 */
SELECT DATE_FORMAT(NOW(), "%Y %m %d");
SELECT DATE_FORMAT(NOW(), "%Y-%m-%d");
SELECT DATE_FORMAT(NOW(), "%Y년 %m월 %d일");

SELECT NAME, DATE_FORMAT(birthday, "%Y년 %m월 %d일") FROM MEMBER;

/* 두 날짜 사이의 값을 비교(DATEDIFF())하여 반환(앞쪽날짜 - 뒤쪽날짜) */
SELECT DATEDIFF("2021-06-01", "2021-01-01");
SELECT DATEDIFF("2021-06-23", "2021-06-01");
SELECT DATEDIFF(NOW(), "2021-01-01");
SELECT DATEDIFF(NOW(), "2021-12-31");
SELECT DATEDIFF(NOW(), "2021-06-01");
SELECT DATEDIFF(NOW(), NOW()-5);  /* 처리 불가 */

SELECT NAME, DATEDIFF(NOW(), birthday) AS '살아온날' FROM MEMBER;

/* 날짜 덧셈(DATE_ADD()) */
/* 오늘날짜 에 10을 더한 날짜? */
SELECT DATE_ADD(NOW(), INTERVAL 10 DAY);

/* 오늘날짜 에 10을 뺀 날짜? */
SELECT DATE_ADD(NOW(), INTERVAL -10 DAY);

/* 회원테이블에서 최근 5일 이내 가입한 회원 */
SELECT name,startDate from member WHERE DATE_ADD(NOW(), INTERVAL -5 DAY) < startDate;

/* 방명록테이블에서 최근 10일 이내 올린글을 보여주시오 */
SELECT name,vdate from guest WHERE DATE_ADD(NOW(), INTERVAL -10 DAY) < vdate;

/* 날짜에 2개월을 더한 날짜를 반환? */
SELECT DATE_ADD(NOW(), INTERVAL 2 MONTH);

/* 날짜에 2개월을 뺀 날짜를 반환? */
SELECT DATE_ADD(NOW(), INTERVAL -2 MONTH);

/* 날짜에 24시간 전의 날짜/시간을 반환? */
SELECT DATE_ADD(NOW(), INTERVAL -24 HOUR);
SELECT DATE_ADD(NOW(), INTERVAL -10 HOUR);

/* 날짜에 30분 전의 날짜/시간을 반환? */
SELECT DATE_ADD(NOW(), INTERVAL -30 MINUTE);

/* 날짜 뺄셈(DATE_SUB()) */
/* 오늘날짜에서 10일 이전의 날짜를 출력하시오 */
SELECT DATE_SUB(NOW(), INTERVAL 10 DAY);
SELECT DATE_SUB(NOW(), INTERVAL 20 DAY);
SELECT * FROM board ORDER BY wdate DESC;
SELECT idx,name,wdate FROM board where DATE_SUB(NOW(), INTERVAL 20 DAY) < wdate ORDER BY wdate DESC;


/* 오늘날짜에서 10일 이후의 날짜를 출력하시오 */
SELECT DATE_SUB(NOW(), INTERVAL -10 DAY);

--------------------------------------------------------------

/* 시간 관련함수 : TIMESTAMPDIFF(a,b,c) : 뒤(c)에서 앞(b)에 해당되는 '년/월/일'(a)을 뺀다. */
SELECT TIMESTAMPDIFF(YEAR, '2020-06-23', '2021-06-23');
SELECT TIMESTAMPDIFF(YEAR, '2020-06-23', NOW());

SELECT TIMESTAMPDIFF(MONTH, '2021-01-23', '2021-06-23');
SELECT TIMESTAMPDIFF(MONTH, '2021-01-23', NOW());

SELECT TIMESTAMPDIFF(DAY, '2021-06-01', '2021-06-23');
SELECT TIMESTAMPDIFF(DAY, '2021-06-01', NOW());

SELECT TIMESTAMPDIFF(HOUR, '2021-06-23 00:00:00', '2021-06-23 10:08:20');
SELECT TIMESTAMPDIFF(HOUR, '2021-06-23', NOW());

SELECT TIMESTAMPDIFF(MINUTE, '2021-06-23 00:00:00', '2021-06-23 10:08:20');
SELECT TIMESTAMPDIFF(MINUTE, '2021-06-23', NOW());
SELECT TIMESTAMPDIFF(MINUTE, '2021-06-23', NOW())/60;
SELECT CAST(TIMESTAMPDIFF(MINUTE, '2021-06-23', NOW())/60 as SIGNED INTEGER);

SELECT bb.*, TIMESTAMPDIFF(MINUTE, bb.wDate, NOW()) AS diffTime FROM board2 bb ORDER BY wDate DESC;
SELECT bb.*, TIMESTAMPDIFF(MINUTE, bb.wDate, NOW())/60 AS diffTime FROM board2 bb ORDER BY wDate DESC;
SELECT bb.*, CAST(TIMESTAMPDIFF(MINUTE, bb.wDate, NOW())/60 AS SIGNED INTEGER) AS diffTime FROM board2 bb ORDER BY wDate DESC;