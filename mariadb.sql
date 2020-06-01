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


insert into brd (title, userid, contents)
VALUES ('테스트입니다','xyz987','아잉~냉무');

insert into brd (title, userid, contents)
VALUES ('시험용데이터입니다','abc456','제곧내~');

insert into brd (title, userid, contents)
VALUES ('연습용입니다','stu642','');



-- 페이징 SQL 문 : limit by
-- 첫 번째 게시문을 기준으로
-- 10-1 만큼 게시물을 추출해서 출력

SELECT bno,title,userid,regdate,views from brd
order by bno desc limit 0, 10; -- 1page

SELECT bno,title,userid,regdate,views from brd
order by bno desc limit 10, 10; -- 2page

SELECT bno,title,userid,regdate,views from brd
order by bno desc limit 20, 10; -- 3page


-- SELECT bno,title,userid,regdate,views from brd
-- order by bno desc limit startNum, 10
-- cp : 1, perPage : 10
-- startNum? : startNum = (cp - 1) * perPage

SELECT count(bno) bdcnt from brd;


-- zipcode : 지번주소 (총 52144건)
CREATE TABLE zipcode (
 zipcode VARCHAR (7),
 sido VARCHAR (10),
 gugun VARCHAR (25),
 dong VARCHAR (35),
 ri VARCHAR (50),
 bunji VARCHAR (25),
 seq VARCHAR (5)
);

SELECT * from zipcode
where dong like '충신동';











