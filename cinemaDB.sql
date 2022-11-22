-- 2022.11.19 제약 조건/UTF-8 설정 완료

create table tbl_member
(
	mId varchar(45) primary key,
	mPw varchar(45),
	mEmail varchar(45),
	mBirth varchar(45),
	mRole int
);
desc tbl_member;
select * from tbl_member;

create table tbl_cinema
(
	cinemaNum int primary key,
	cinemaName varchar(45)
);
desc tbl_cinema;
-- Insert 테스트 후 실행
delete from tbl_cinema where cinemaName="Test";
select * from tbl_cinema;

create table tbl_movie
(
	movieCode int primary key,
	movieName varchar(45),
	movieStartDate varchar(45),
	movieEndDate varchar(45),
	movieTime varchar(45),
	cinemaNum int,
	constraint tbl_movie_cinemaNum foreign key(cinemaNum) references tbl_cinema(cinemaNum)
);
desc tbl_movie;
-- Insert 테스트 후 실행
delete from tbl_movie where movieName="TestMovie";
select * from tbl_movie;
-- movie 테이블의 전체 행을 제거한다.
delete from tbl_movie;

create table tbl_theater
(
	theaterNum int,
	theaterSeat int,
	theaterName varchar(45),
	cinemaNum int,
	primary key(theaterNum, theaterSeat),
	constraint tbl_theater_cinemaNum foreign key(cinemaNum) references tbl_cinema(cinemaNum)
);
desc tbl_theater;
select * from tbl_theater;
delete from tbl_theater;
drop table tbl_theater;

create table tbl_reserve
(
	ticketNum int primary key,
	mId varchar(45),
	movieCode int,
	ticketDate varchar(45),
	theaterNum int,
	cinemaNum int,
	theaterSeat int
--	constraint tbl_reserve_mId foreign key(mId) references tbl_member(mId),
--	constraint tbl_reserve_movieCode foreign key(movieCode) references tbl_movie(movieCode),
--	constraint tbl_reserve_theaterNum foreign key(theaterNum) references tbl_theater(theaterNum),
--	constraint tbl_reserve_cinemaNum foreign key(cinemaNum) references tbl_cinema(cinemaNum),
--	constraint tbl_reserve_theaterSeat foreign key(theaterSeat) references tbl_theater(theaterSeat)
);
alter table tbl_reserve add constraint mId foreign key (mId) references tbl_member(mId);
alter table tbl_reserve add constraint movieCode foreign key (movieCode) references tbl_movie(movieCode);
alter table tbl_reserve add constraint theaterNum foreign key (theaterNum) references tbl_theater(theaterNum);
alter table tbl_reserve add constraint cinemaNum foreign key (cinemaNum) references tbl_cinema(cinemaNum);
--alter table tbl_reserve add constraint theaterSeat foreign key (theaterSeat) references tbl_theater(theaterSeat);
desc tbl_reserve;

drop table tbl_reserve;

select * from tbl_reserve;

-- 테이블 생성 이후 UTF-8 설정
alter table tbl_member convert to charset UTF8;
alter table tbl_cinema convert to charset UTF8;
alter table tbl_movie convert to charset UTF8;
alter table tbl_theater convert to charset UTF8;
alter table tbl_reserve convert to charset UTF8;

-- 테이블 생성 망했을 때 삭제용
drop table tbl_member;
drop table tbl_cinema;
drop table tbl_movie;
drop table tbl_theater;
drop table tbl_reserve;

-- 2022.11.19 14:13 SavePoint
-- 기점: theater_view 생성 완료
savepoint point1;
-- point1 rollback;
rollback to savepoint point1;

-- 좌석 확인용 View
create view theaterSeat_view
as
	select c.cinemaName,
		   t.theaterName,
		   t.theaterSeat
	from   tbl_theater t,
		   tbl_cinema c
	where  c.cinemaNum=t.cinemaNum;
	
select * from theaterSeat_view;

-- 기점: 좌석 확인용 View 생성
savepoint point2;

-- 잔여 좌석 확인용 Join
select	cinemaName,
		movieName,
		theaterName,
		theaterSeat
from	tbl_cinema,
		tbl_movie,
		tbl_theater
where	tbl_cinema.cinemaNum=tbl_movie.cinemaNum
and		tbl_movie.cinemaNum=tbl_theater.cinemaNum;

-- 잔여 좌석 확인용 View
create view theaterSeatOK_view
as
select	cinemaName,
		movieName,
		theaterName,
		theaterSeat
from	tbl_cinema,
		tbl_movie,
		tbl_theater
where	tbl_cinema.cinemaNum=tbl_movie.cinemaNum
and		tbl_movie.cinemaNum=tbl_theater.cinemaNum;
select * from theaterSeatOK_view;
drop view theaterSeatOK_view;

-- 상영 영화 확인용 Join
-- TheaterDAO View 생성
create view movie_view
as
	select c.cinemaName,
		   m.movieName,
		   t.theaterName
	from   tbl_movie m,
		   tbl_cinema c,
		   tbl_theater t
	where  c.cinemaNum=m.cinemaNum
	and	   m.cinemaNum=t.cinemaNum;
	
select * from movie_view; -- 확인
drop view movie_view;
--drop view movie_view;
delete from tbl_movie;
delete from tbl_theater;

select * from tbl_movie;
