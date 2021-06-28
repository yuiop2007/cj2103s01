create table area(
  address1  varchar(50) not null,
  address2  varchar(20) not null,
  latitude  double not null,
  longitude double not null
);

desc area;

select * from area;

select distinct address1 from area;
