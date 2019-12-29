

CREATE TABLE Movie
(

    id INTEGER PRIMARY KEY, --auto increment
    active INTEGER NOT NULL DEFAULT 0,
    name VARCHAR(50) NOT NULL,
    duration REAL CHECK(duration > 0),
    productionYear INTEGER CHECK(productionYear > 0),
    description VARCHAR(100) DEFAULT '',
    distributor VARCHAR(100) NOT NULL,
    countryOfOrigin VARCHAR(100) NOT NULL

);





select * from Movie
delete from Movie where id  in (2, 3,4,5)
update Movie set active = 0 where id  in (2, 3,4,5)

INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Jumanji: Jungle', 120, 2017, 'Description goes here', '20th Century Fox', 'USA' );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Jumanji: The next level', 140,2019, 'Description goes here', '20th Century Fox', 'USA'  );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'John Shaft', 100 ,2001, 'Description goes here', 'DreamWorks', 'USA' );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Lord of the Rings - Part I', 125 ,2003, 'Description goes here', 'Lions Gate', 'USA' );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Joker', 125 ,2019, '', 'Cinematicks', 'USA' );

--------------------------------------------------------
create table Acting
(
    idMovie INTEGER NOT NULL,
    idActor INTEGER NOT NULL,
    PRIMARY KEY (idMovie, idActor),
    FOREIGN KEY(idMovie) REFERENCES Movie(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idActor) REFERENCES Actor(id) ON DELETE RESTRICT
)
delete FROM Acting
delete FROM table Acting where id = 1
select * from Acting
INSERT INTO Acting (idMovie, idActor) VALUES (1, 1);
INSERT INTO Acting (idMovie, idActor) VALUES (1, 2);
INSERT INTO Acting (idMovie, idActor) VALUES (2, 2);
INSERT INTO Acting (idMovie, idActor) VALUES (3, 3);
create table Actor
(

id INTEGER PRIMARY KEY,
active INTEGER NOT NULL,
name TEXT NOT NULL

)

drop table Actor
select * from Actor
INSERT INTO Actor (active, name) VALUES (1, 'Dwayne Johnson - Rock');
INSERT INTO Actor (active, name) VALUES (1, 'Karen Gillan');
INSERT INTO Actor (active, name) VALUES (1, 'Samuel L Jackson');
--------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------
create table Genre
(
    id INTEGER PRIMARY KEY,
    active INTEGER NOT NULL,
    name TEXT NOT NULL

)
select * from Genre
INSERT INTO Genre (active, name) VALUES (1, 'Comedy');
INSERT INTO Genre (active, name) VALUES (1, 'Action');
INSERT INTO Genre (active, name) VALUES (1, 'Adventure');

create table MovieGenre
(
    idMovie INTEGER NOT NULL,
    idGenre INTEGER NOT NULL,
    PRIMARY KEY (idMovie, idGenre),
    FOREIGN KEY(idMovie) REFERENCES Movie(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idGenre) REFERENCES Genre(id) ON DELETE RESTRICT

)
delete from MovieGenre
select * from MovieGenre
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (1, 1);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (2, 1);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (3, 2);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (4, 3);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (4, 2);





create table Director
(
    id INTEGER PRIMARY KEY,
    active INTEGER NOT NULL,
    name TEXT NOT NULL
)
select * from Director
INSERT INTO Director (active, name) VALUES (1, 'Jake Kasdan');
INSERT INTO Director (active, name) VALUES (1, 'John Singleton');
INSERT INTO Director (active, name) VALUES (1, 'Peter Jackson');


create table Directing
(
    idMovie INTEGER NOT NULL,
    idDirector INTEGER NOT NULL,
    PRIMARY KEY (idMovie, idDirector),
    FOREIGN KEY(idMovie) REFERENCES Movie(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idDirector) REFERENCES Director(id) ON DELETE RESTRICT

)
select * from Directing
select * from Director
drop table Directing
delete from Directing

INSERT INTO Directing (idMovie, idDirector) VALUES (1, 1);
INSERT INTO Directing (idMovie, idDirector) VALUES (1, 3);
delete from Directing
where idMovie = 1 AND idDirector in (1,3)

INSERT INTO Directing (idMovie, idDirector) VALUES (2, 1);


INSERT INTO Directing (idMovie, idDirector) VALUES (3, 2);
INSERT INTO Directing (idMovie, idDirector) VALUES (4, 3);



-------------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------------------------------

SELECT Movie.id, Movie.active, Movie.name, Movie.duration, Movie.productionYear, Movie.description, Movie.distributor, Movie.countryOfOrigin, Acting.idMovie, Actor.id, Actor.active, Actor.name, MovieGenre.idMovie, Genre.id, Genre.active, Genre.name, Directing.idMovie, Director.id, Director.active, Director.name                  
FROM Movie
left join Acting on Movie.id = Acting.idMovie
left join Actor on Acting.idActor = Actor.id
left join MovieGenre on Movie.id = MovieGenre.idMovie
left join Genre on MovieGenre.idGenre = Genre.id
left join Directing on Movie.id = Directing.idMovie
left join Director on Directing.idDirector = Director.id
where Movie.active = 1
order by Movie.id;


SELECT Movie.id, Movie.active, Movie.name, Movie.duration, Movie.productionYear, Movie.description, Movie.distributor, Movie.countryOfOrigin, Acting.idMovie, Actor.id, Actor.active, Actor.name,  MovieGenre.idMovie, Genre.id, Genre.active, Genre.name, Directing.idMovie, Director.id, Director.active, Director.name
FROM Movie
LEFT JOIN Acting ON Movie.id = Acting.idMovie
LEFT JOIN Actor ON Acting.idActor = Actor.id
LEFT JOIN MovieGenre ON Movie.id = MovieGenre.idMovie
LEFT JOIN Genre ON MovieGenre.idGenre = Genre.id
LEFT JOIN Directing on Movie.id = Directing.idMovie
LEFT JOIN Director on Directing.idDirector = Director.id
WHERE Movie.active = 1 and Movie.id = 4
ORDER BY Movie.id


--where Movie.id = 1 --ovo je za getMovieById
--group by Movie.id, Acting.idMovie
--where m.id = acting.idMovie and acting.idActor = actor.id









CREATE TABLE User 
(
    id INTEGER PRIMARY KEY, --auto increment
    active INTEGER NOT NULL DEFAULT 0,
    username VARCHAR(10) UNIQUE NOT NULL, 
    password VARCHAR(10) NOT NULL, 
    registrationDate VARCHAR(16) NOT NULL, --XX-XX-XXXX XX:XX
    role VARCHAR(11) NOT NULL DEFAULT 'USER' -- SQLite ne poznaje enum-e --unspecified role 11 char
   
);

drop table User
select * 
from User
where active = 1
delete from User where id = 4


--id pre active
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'a', 'a', '15-12-2009 12:00', 'ADMIN');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'b', 'b', '16-02-2008 12:00', 'USER');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'c', 'c', '15-03-2012 10:00', 'UNSPECIFIED');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'd', 'd', '16-02-2008 12:00', 'USER');




delete from User
select * from User where datetime(registrationDate) >  datetime('2007-01-01 10:00');
update User set password = 'b' where id = 2



CREATE TABLE ProjectionType
(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    dimension VARCHAR(4) NOT NULL
    
);

INSERT INTO ProjectionType ( active, dimension) VALUES (1, 'IID');
INSERT INTO ProjectionType ( active, dimension) VALUES (1, 'IIID');
INSERT INTO ProjectionType ( active, dimension) VALUES (1, 'IVD');


select * from ProjectionType


delete from Hall
drop table Hall

CREATE TABLE Hall
(
     id INTEGER PRIMARY KEY,
     active INTEGER  NOT NULL DEFAULT 0,
     capacity INTEGER NOT NULL,
     name VARCHAR(20) NOT NULL
     
);



INSERT INTO Hall ( active, capacity, name) VALUES (1,  3,'White hall');
INSERT INTO Hall ( active, capacity,name) VALUES (1,  4, 'Black hall');
INSERT INTO Hall ( active, capacity,name) VALUES (1,  5,'Orange hall');
INSERT INTO Hall ( active, capacity, name) VALUES (1,  6, 'Blue hall');

--FROM Movie
--left join Acting on Movie.id = Acting.idMovie
select Hall.id, Hall.active, Hall.capacity, Hall.name, ProjectionType.id, ProjectionType.active, ProjectionType.dimension
from Hall
left join Supports on Hall.id = Supports.idHall
left join ProjectionType on Supports.idProjectionType = ProjectionType.id
where Hall.id = 1


create table Supports
(
    idHall INTEGER, 
    idProjectionType INTEGER,
    PRIMARY KEY(idHall, idProjectionType),
    FOREIGN KEY(idHall) REFERENCES Hall(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idProjectionType) REFERENCES ProjectionType(id) ON DELETE RESTRICT

);


INSERT INTO Supports ( idHall, idProjectionType) VALUES (1, 1);
INSERT INTO Supports ( idHall, idProjectionType) VALUES (1, 2);
INSERT INTO Supports ( idHall, idProjectionType) VALUES (1, 3);

delete from Supports
select * from Supports




drop table Projection
CREATE TABLE Projection

(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    idMovie INTEGER NOT NULL,
    idProjectionType INTEGER NOT NULL,
    idHall INTEGER NOT NULL,
    idPeriod INTEGER NOT NULL,
    price REAL CHECK(price > 0),
    idAdmin INTEGER NOT NULL,
    FOREIGN KEY(idMovie) REFERENCES Movie(id) ON DELETE RESTRICT,
    FOREIGN KEY(idProjectionType) REFERENCES ProjectionType(id) ON DELETE RESTRICT,
    FOREIGN KEY(idHall) REFERENCES Hall(id) ON DELETE RESTRICT,
    FOREIGN KEY(idPeriod) REFERENCES Period(id)  ON DELETE RESTRICT,
    FOREIGN KEY(idAdmin) REFERENCES User(id)  ON DELETE RESTRICT

);
select * from Projection
delete from Projection where id = 1
--test projekcija za dugme Kupi na stranici Filmova
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) 
                VALUES   (1,    1,       1,                1,       1,        100,   1 );

INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) 
                VALUES   (1,    1,       1,                2,       2,        100,   1 );
-----

                             
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) 
                VALUES   (1,    8,       1,                1,        1,        100,   1 );
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) 
                VALUES   (1,    8,       1,                3,        2,        100,   1 );

INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) 
                VALUES   (1,    1,       1,                4,        4,        100,   1 );

--LEFT JOIN Acting ON Movie.id = Acting.idMovie
--LEFT JOIN Actor ON Acting.idActor = Actor.id

SELECT Projection.id FROM Projection WHERE idMovie = 1 AND active = 1




select Projection.id, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(*) as Tickets_Sold 
from Projection
LEFT JOIN Ticket on  Projection.id = Ticket.idProjection
where Projection.id = 1 and Projection.active = 1 and Ticket.active = 1 
group by Projection.id, Ticket.idProjection


delete from Projection where id in (1,2, 3, 4)

delete from  Period
CREATE TABLE Period
(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    startDate VARCHAR(16) NOT NULL,
    endDate  VARCHAR(16) NOT NULL
    
);
select * from Period where id = 1
update Period set startDate = '20-02-2020 12:00', endDate = '20-02-2020 14:00' WHERE ID = 2
delete from Period WHERE Id = 1
INSERT INTO Period (active, startDate, endDate) VALUES (1, '20-02-2019 12:00', '20-02-2019 14:00');
INSERT INTO Period (active, startDate, endDate) VALUES (1, '15-02-2020 12:00', '15-02-2020 14:00');


INSERT INTO Period (active, startDate, endDate) VALUES (1, '15-01-2019 12:00', '15-01-2019 14:00');

INSERT INTO Period (active, startDate, endDate) VALUES (1, '19-02-2019 12:00', '19-02-2019 14:00');



--INSERT INTO Period (active, startDate, endDate) VALUES (1, '20-02-2019 12:00', '20-02-2019 14:00');





DELETE FROM Ticket WHERE ID  =2
CREATE TABLE Ticket
(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    idProjection INTEGER NOT NULL,
    idSeat INTEEGER NOT NULL,
    timeOfSale VARCHAR(16) NOT NULL,
    idUser INTEGER NOT NULL,
    FOREIGN KEY(idSeat) REFERENCES Seat(id)  ON DELETE RESTRICT,
    FOREIGN KEY(idProjection) REFERENCES Projection(id)  ON DELETE RESTRICT,
    FOREIGN KEY(idUser) REFERENCES User(id)  ON DELETE RESTRICT
);
select * from Ticket

--3 TICKETS FOR White Hall
INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale,     idUser) 
              VALUES (1,        1,            1, '14-02-2019 14:00', 4);
INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale,     idUser) 
              VALUES (1,        1,            2, '14-02-2019 14:00', 4);
INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale,     idUser) 
              VALUES (1,        1,            3, '14-02-2019 14:00', 4);


--tickets for Black hall
INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale,     idUser) 
              VALUES (1,        2,            4, '14-02-2019 14:00', 4);
INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale,     idUser) 
              VALUES (1,        2,            5, '14-02-2019 14:00', 4);
INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale,     idUser) 
              VALUES (1,        2,            6, '14-02-2019 14:00', 4);
INSERT INTO Ticket (active, idProjection, idSeat, timeOfSale,     idUser) 
              VALUES (1,        2,            7, '14-02-2019 14:00', 4);



DROP TABLE Seat
CREATE TABLE Seat
(

    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    number INTEGER  NOT NULL,
    idHall INTEGER NOT NULL
   
    
);

--3 seats for White Hall
INSERT INTO Seat (active, number, idHall ) VALUES (1, 1, 1);
INSERT INTO Seat (active, number, idHall ) VALUES (1, 2, 1);
INSERT INTO Seat (active, number, idHall) VALUES (1, 3, 1);

--4 seats for Black Hall
INSERT INTO Seat (active, number, idHall ) VALUES (1, 1, 2); --id 4
INSERT INTO Seat (active, number, idHall ) VALUES (1, 2, 2);--id 5
INSERT INTO Seat (active, number, idHall) VALUES (1, 3, 2);--id 6
INSERT INTO Seat (active, number, idHall) VALUES (1, 4, 2);--id 7


select * from Seat

