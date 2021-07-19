create table notify (
	idx       int not null auto_increment primary key,
	name      varchar(20)  not null,		/* 공지사항 올린이 */
	title     varchar(100) not null,		/* 공지사항 제목 */
	content   text not null,						/* 공지사항 상세내역 */
	startDate datetime default now(),   /* 공지사항 알림 시작일 */
	endDate	  datetime default now(),   /* 공지사항 알림 종료일 */
	popupSw   char(1)  default 'N'			/* 공지사항 초기화면에 표시여부(Y:표시, N:표시않함) */ 
);

desc notify;

insert into notify values (default,'관리맨','여름상품 대방출 세일','2021년 여름맞이 대 바겐세일행사로 여러분들을 모십니다.',default,default,default);

select * from notify order by idx desc;
