show tables;

create table schedule2 (
  idx    int         not null  auto_increment primary key,
  mid    varchar(20) not null,
  scDate datetime    not null,      /* 일정등록날짜 */
  memory varchar(10) not null,			/* 1.모임, 2.업무, 3.학습, 4.여행, 0:기타 */
  title  varchar(100) not null,			/* 일정 제목 */
  content text not null							/* 일정 상세 내역 */
);

desc schedule2;

insert into schedule2 value (default, 'hkd1234', '2021-06-29', '모임', '가족모임', '가족회의, 장소:집, 시간:19시');
insert into schedule2 value (default, 'hkd1234', '2021-06-30', '기타', '건강검진', '아침굽기, 장소:청주의료원, 시간:10시');
insert into schedule2 value (default, 'hkd1234', '2021-06-29', '모임', '친구모임', '친구생일, 장소:치킨호프, 시간:21시');
insert into schedule2 value (default, 'hkd1234', '2021-06-25', '학습', '영어', '영어회와, 장소:영어학원, 시간:17시');
insert into schedule2 value (default, 'hkd1234', '2021-06-29', '학습', 'JAVA', '객체학습, 장소:도서관, 시간:12시');
insert into schedule2 value (default, 'hkd1234', '2021-06-25', '모임', '통일회', '6.25모임, 장소:통일관, 시간:14시');
insert into schedule2 value (default, 'hkd1234', '2021-07-04', '업무', '임원회의', '주제발표, 장소:회사, 시간:10시');
insert into schedule2 value (default, 'hkd1234', '2021-06-20', '모임', '가족모임', '가족행사, 장소:집, 시간:19시');
insert into schedule2 value (default, 'hkd1234', '2021-07-04', '모임', '외국인친구들모임', '여행친구, 장소:월드컵호프, 시간:20시');
insert into schedule2 value (default, 'hkd1234', '2021-07-15', '여행', '홀로여행', '정동진 해돋이, 장소:강릉, 시간:05시');
insert into schedule2 value (default, 'kms1234', '2021-06-15', '모임', '친구생일', '소영이생일파티, 장소:홈파티홀, 시간:15시');
insert into schedule2 value (default, 'kms1234', '2021-06-29', '여행', '목포', '유달산산행, 장소:유달산, 시간:09시');

select * from schedule2 order by scDate;
select * from schedule2 where mid='hkd1234' order by scDate;
select * from schedule2 where mid='hkd1234' and scDate='2021-06' order by scDate;  /* 검색 결과 없음 */
select * from schedule2 where mid='hkd1234' and substring(scDate,1,7)='2021-06' order by scDate;
select * from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m')='2021-06' order by scDate;
select * from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m')='2021-06' group by scDate  order by scDate;
/* select * from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m')='2021-06' group by substring(scDate,1,7)  order by substring(scDate,1,7); */
select * from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m')='2021-06' group by scDate  order by scDate, memory;
select scDate,count(*) from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m')='2021-06' group by scDate  order by scDate, memory;
select scDate,substring(title,1,4) from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m-%d')='2021-06-29' order by scDate, memory;
select scDate,memory from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m-%d')='2021-06-29' order by scDate;
select scDate,memory,count(*) as memoryCount from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m-%d')='2021-06-29' group by memory order by scDate;
select scDate,memory,count(*) as memoryCount from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m')='2021-06' group by memory order by scDate;

select * from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m')='2021-06' order by scDate;

select * from schedule2 where mid='hkd1234' and date_format(scDate, '%Y-%m-%d')='2021-6-30';

