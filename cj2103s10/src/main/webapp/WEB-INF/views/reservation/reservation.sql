show tables;

/* 예약할 수 있는 '객실'에 대한 정보를 담은 테이블(객실 사용요금은 생략하기로 한다.) */
create table reservationRoom2 (		
  room varchar(20) not null,			/* 준비된 객실명 */
  content text not null, 			/* 객실 정보 */
  available varchar(10) default 'OK',			/* 객실 사용여부(사용(예약)가능(OK), 사용(예약)불가(공사중)(NO) */
  primary key(room)					/* 기본키는 '고유번호'와 '객실이름' */
);
desc reservationRoom2;
drop table reservationRoom2;
select * from reservationRoom2;

/* 객실 예약및 사용 유무 테이블 - 예약이 일어나면 이곳에 예약날짜와 객실을 예약처리하고, 상세내역은 reservation2테이블에 기록한다. */
create table reservationStatus2 (	
  idx  int not null auto_increment,
  reservationDate datetime default now(),	/* 예약일자 */
  reservationRoom varchar(20) not null,		/* 예약테이블의 방이름(외래키 설정) */
  available varchar(10) default 'OK',			/* 객실 사용여부(사용(예약)가능(OK), 사용(예약)불가(NO), 공사중(REPAIR) :설계실수(사용하지 않았다)*/
  primary key(idx),
	foreign key(reservationRoom) references reservationRoom2(room)	/* 외래키 설정 */
  	on update cascade
  	on delete cascade
);
drop table reservationStatus2;
select * from reservationStatus2;

/* 예약 정보 테이블(실제 예약이 일어난후 예약된 모든 정보를 담는 테이블) */
create table reservation2 (
  idx  int not null auto_increment,
  reservationStatusIdx int not null,	/* 객실 예약테이블의 고유번호를 저장하는 필드 */
  mid  varchar(20) not null,					/* 예약 고객의 mid */
  person  int default 2,							/* 인원수 */
  content text,												/* 예약시에 기재될 내역 */
  primary key(idx),
	foreign key(reservationStatusIdx) references reservationStatus2(idx)	/* 외래키 설정 */
  	on update cascade
  	on delete cascade,
	foreign key(mid) references member2(mid)	/* 외래키 설정 */
  	on update cascade
  	on delete cascade
);
drop table reservation2;
select * from reservation2;

select aa.*,(bb.reservationRoom) as room,(bb.reservationDate) as reservationDate  from reservation2 aa, reservationStatus2 bb where mid='hkd1234' and aa.reservationStatusIdx=bb.idx order by reservationDate desc;
select aa.*,(bb.reservationDate) as reservationDate from reservation2 aa, reservationStatus2 bb where aa.idx=2 and aa.reservationStatusIdx=bb.idx;
select aa.*,(bb.reservationRoom) as room,(bb.reservationDate) as reservationDate from reservation2 aa, reservationStatus2 bb where bb.reservationRoom='101호' and aa.reservationStatusIdx=bb.idx order by bb.reservationDate desc;
select aa.*,(bb.reservationRoom) as room,(bb.reservationDate) as reservationDate from reservation2 aa, reservationStatus2 bb where bb.reservationRoom='101호' and aa.reservationStatusIdx=bb.idx order by bb.reservationDate desc limit 10,10;

/* 테이블 구조와 데이터 복사 */
create table if not exists reservationRoomImsi select * from reservationRoom2;
select * from reservationRoomImsi;

insert into reservationRoom2 select * from reservationRoomImsi;

/* DB의 테이블 복사 */

create table works.reservationRoom2 like javaspring.reservationRoom2;
insert into works.reservationRoom2 select * from javaspring.reservationRoom2;

create table works.reservationStatus2 like javaspring.reservationStatus2;
insert into works.reservationStatus2 select * from javaspring.reservationStatus2;

create table works.reservation2 like javaspring.reservation2;
insert into works.reservation2 select * from javaspring.reservation2;
