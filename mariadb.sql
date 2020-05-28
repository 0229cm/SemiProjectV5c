-- member
create table member(
  mno number(5) primary key auto_increment,
	name varchar(15) not null,
	jumin char(15) not null,

	userid varchar(18) not null,
	passwd varchar(18) not null,

	zipcode char(7) not null,
	addr1 varchar(50) not null,
	addr2 varchar(50) not null,
	email varchar(50) not null,
	mobile char(13) not null,
	regdate datetime default current_timestamp
);


-- board
create table board(
  bno NUMBER (5) PRIMARY key auto_increment,
  title VARCHAR (50) not null,
  userid VARCHAR (18) not null,
  regdate datetime DEFAULT CURRENT_TIMESTAMP,
  thumbup NUMBER (5) DEFAULT 0,
  views NUMBER (5) DEFAULT 0,
  contents mediumtext not null
);




-- pds
CREATE TABLE pds (
  pno int PRIMARY key auto_increment,
  title VARCHAR (50) not null,
  userid VARCHAR (18) not null,
  regdate datetime DEFAULT CURRENT_TIMESTAMP,
  thumbup int DEFAULT 0,
  views int DEFAULT 0,
  contents mediumtext not null,
  fname VARCHAR (50),
  fsize int DEFAULT 0,
  fdown int DEFAULT 0,
  ftype varchar(10)
);





-- gallery
CREATE TABLE gallery (
  gno int PRIMARY key auto_increment,
  title VARCHAR (50) not null,
  userid VARCHAR (18) not null,
  regdate datetime DEFAULT CURRENT_TIMESTAMP,
  thumbup int DEFAULT 0,
  views int DEFAULT 0,
  contents mediumtext not null,
  fname1 VARCHAR (50),
  fname2 VARCHAR (50),
  fname3 VARCHAR (50)
  );



