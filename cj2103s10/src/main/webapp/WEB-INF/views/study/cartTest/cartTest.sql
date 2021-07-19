create table cartTest2 (
  idx  int not null auto_increment,
  product varchar(30) not null,
  thumbImg varchar(50) not null,
  price int not null default 0,
  su int not null default 0,
  unique key (idx)
);

desc cartTest2;

insert into cartTest2 values (default,'냉장고','ready.jpg',50000,2);
insert into cartTest2 values (default,'에어컨','ready.jpg',100000,1);
insert into cartTest2 values (default,'TV','ready.jpg',40000,3);
