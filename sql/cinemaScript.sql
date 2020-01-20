

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
DELETE FROM Movie WHERE ID = 6

INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Joker', 122 ,2019, 'Description goes here', 'Cinematicks', 'USA' );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'John Shaft', 99 ,2000, 'Description goes here', 'DreamWorks', 'USA' );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Avengers: Endgame', 182, 2019, 'Description goes here', '20th Century Fox', 'USA' );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Harry Potter', 120, 2017, 'Description goes here', 'New Line Cinema', 'USA'  );
INSERT INTO Movie (active, name, duration, productionYear, description, distributor, countryOfOrigin) VALUES ( 1, 'Churchil', 105 ,2017, 'Description goes here', 'Silver Reel', 'USA' );

--------------------------------------------------------
create table Acting
(
    idMovie INTEGER NOT NULL,
    idActor INTEGER NOT NULL,
    PRIMARY KEY (idMovie, idActor),
    FOREIGN KEY(idMovie) REFERENCES Movie(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idActor) REFERENCES Actor(id) ON DELETE RESTRICT
)
select * from Acting

INSERT INTO Acting (idMovie, idActor) VALUES (1, 1);

INSERT INTO Acting (idMovie, idActor) VALUES (2, 2);

INSERT INTO Acting (idMovie, idActor) VALUES (3, 3);
INSERT INTO Acting (idMovie, idActor) VALUES (3, 4);
INSERT INTO Acting (idMovie, idActor) VALUES (3, 5);

INSERT INTO Acting (idMovie, idActor) VALUES (4, 6);
INSERT INTO Acting (idMovie, idActor) VALUES (4, 7);

INSERT INTO Acting (idMovie, idActor) VALUES (5, 8);


create table Actor
(

id INTEGER PRIMARY KEY,
active INTEGER NOT NULL,
name TEXT NOT NULL

)


select * from Actor

INSERT INTO Actor (active, name) VALUES (1, 'Joaquin Phoenix');
INSERT INTO Actor (active, name) VALUES (1, 'Samuel L Jackson');
INSERT INTO Actor (active, name) VALUES (1, 'Robert Downey Jr.');
INSERT INTO Actor (active, name) VALUES (1, 'Scarlet Johansson');
INSERT INTO Actor (active, name) VALUES (1, 'Chris Hemsworth');
INSERT INTO Actor (active, name) VALUES (1, 'Daniel Redclif');
INSERT INTO Actor (active, name) VALUES (1, 'Rupert Greeen');
INSERT INTO Actor (active, name) VALUES (1, 'Brian Cox');



--------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------
create table Genre
(
    id INTEGER PRIMARY KEY,
    active INTEGER NOT NULL,
    name TEXT NOT NULL

)
select * from Genre

INSERT INTO Genre (active, name) VALUES (1, 'Crime');
INSERT INTO Genre (active, name) VALUES (1, 'Drama');
INSERT INTO Genre (active, name) VALUES (1, 'Triler');
INSERT INTO Genre (active, name) VALUES (1, 'Action');
INSERT INTO Genre (active, name) VALUES (1, 'Comedy');
INSERT INTO Genre (active, name) VALUES (1, 'Adventure');
INSERT INTO Genre (active, name) VALUES (1, 'Family');
INSERT INTO Genre (active, name) VALUES (1, 'Biography');
INSERT INTO Genre (active, name) VALUES (1, 'History');







create table MovieGenre
(
    idMovie INTEGER NOT NULL,
    idGenre INTEGER NOT NULL,
    PRIMARY KEY (idMovie, idGenre),
    FOREIGN KEY(idMovie) REFERENCES Movie(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idGenre) REFERENCES Genre(id) ON DELETE RESTRICT

)
select * from MovieGenre
--Joker
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (1, 1);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (1, 2);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (1, 3);
--Shaft
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (2, 4);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (2, 5);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (2, 1);
--Avengers
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (3, 4);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (3, 6);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (3, 2);
--potter
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (4, 4);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (4, 6);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (4, 7);
--churchill
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (5, 8);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (5, 2);
INSERT INTO MovieGenre (idMovie, idGenre) VALUES (5, 9);





create table Director
(
    id INTEGER PRIMARY KEY,
    active INTEGER NOT NULL,
    name TEXT NOT NULL
)
select * from Director
INSERT INTO Director (active, name) VALUES (1, 'Todd Phillips');
INSERT INTO Director (active, name) VALUES (2, 'John Singleton');
INSERT INTO Director (active, name) VALUES (3, 'Anthony Russo');
INSERT INTO Director (active, name) VALUES (3, 'Cris Columbus');
INSERT INTO Director (active, name) VALUES (3, 'Jonathan Teplitzky');



create table Directing
(
    idMovie INTEGER NOT NULL,
    idDirector INTEGER NOT NULL,
    PRIMARY KEY (idMovie, idDirector),
    FOREIGN KEY(idMovie) REFERENCES Movie(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idDirector) REFERENCES Director(id) ON DELETE RESTRICT

)
select * from Directing
INSERT INTO Directing (idMovie, idDirector) VALUES (1, 1);
INSERT INTO Directing (idMovie, idDirector) VALUES (2, 2);
INSERT INTO Directing (idMovie, idDirector) VALUES (3, 3);
INSERT INTO Directing (idMovie, idDirector) VALUES (4, 4);
INSERT INTO Directing (idMovie, idDirector) VALUES (5, 5);




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


select * from User
--id pre active

INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'a', 'a', '15-12-2009 12:00', 'ADMIN');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'b', 'b', '16-02-2008 12:00', 'USER');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'c', 'c', '15-03-2012 10:00', 'USER');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'd', 'd', '15-03-2012 10:00', 'UNSPECIFIED');




CREATE TABLE ProjectionType
(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    dimension VARCHAR(4) NOT NULL
    
);

select * from ProjectionType

INSERT INTO ProjectionType ( active, dimension) VALUES (1, 'IID');
INSERT INTO ProjectionType ( active, dimension) VALUES (1, 'IIID');
INSERT INTO ProjectionType ( active, dimension) VALUES (1, 'IVD');






CREATE TABLE Hall
(
     id INTEGER PRIMARY KEY,
     active INTEGER  NOT NULL DEFAULT 0,
     capacity INTEGER NOT NULL,
     name VARCHAR(20) NOT NULL
     
);
select * from hall



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
--order by Hall.id
where Hall.id = 4


create table Supports
(
    idHall INTEGER, 
    idProjectionType INTEGER,
    PRIMARY KEY(idHall, idProjectionType),
    FOREIGN KEY(idHall) REFERENCES Hall(id) ON DELETE RESTRICT, 
    FOREIGN KEY(idProjectionType) REFERENCES ProjectionType(id) ON DELETE RESTRICT

);
select * from Supports
--White hall supports 2D, 3D
INSERT INTO Supports ( idHall, idProjectionType) VALUES (1, 1);
INSERT INTO Supports ( idHall, idProjectionType) VALUES (1, 2);

--Black hall supports 3D, 4D
INSERT INTO Supports ( idHall, idProjectionType) VALUES (2, 2);
INSERT INTO Supports ( idHall, idProjectionType) VALUES (2, 3);

--Orange hall supports 2D, 4D
INSERT INTO Supports ( idHall, idProjectionType) VALUES (3, 2);
INSERT INTO Supports ( idHall, idProjectionType) VALUES (3, 3);

delete from Supports





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
delete from Projection
--id filmova

--'Joker', 1
--'John Shaft', 2
-- 'Avengers: Endgame', 3
-- 'Harry Potter', 4
--'Churchil', 5
select * from Projection
--projekcije za dan 14.01.2020 white hall (8 projekcija, 8 Perioda)
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 1, 1, 1, 100, 1); --id 1
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 2, 1, 2, 150, 1); --id 2
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 2, 1, 3, 200, 1); --id 3
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 1, 1, 4, 200, 1); --id 4
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 2, 1, 5, 200, 1); --id 5
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 2, 1, 6, 200, 1); --id 6
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 1, 1, 7, 200, 1); --id 7
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 1, 1, 8, 200, 1); --id 8


--Projekcije za dan 15.01.2020 black hall (8 projekcija 8 perioda)
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 3, 2, 9, 100, 1); --id 9
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 2, 2, 10, 150, 1); --id 10
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 3, 2, 11, 200, 1); --id 11
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 3, 2, 12, 200, 1); --id 12
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 2, 2, 13, 200, 1); --id 13
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 2, 2, 14, 200, 1); --id 14
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 3, 2, 15, 200, 1); --id 15
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 3, 2, 16, 200, 1); --id 16

--Projekcije za dan 16.01.2020 Orange hall (8 projekcija 8 perioda)
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 1, 3, 17, 100, 1); --id 17
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 3, 3, 18, 150, 1); --id 18
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 3, 3, 19, 200, 1); --id 19
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 1, 3, 20, 200, 1); --id 20
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 3, 3, 21, 200, 1); --id 21
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 3, 3, 22, 200, 1); --id 22
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 1, 3, 23, 200, 1); --id 23
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 1, 3, 24, 200, 1); --id 24

--Projekcije za dan 17.01.2020 White hall (8 projekcija 8 perioda)
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 1, 1, 25, 100, 1); --id 25
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 2, 1, 26, 150, 1); --id 26
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 2, 1, 27, 200, 1); --id 27
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 1, 1, 28, 200, 1); --id 28
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 2, 1, 29, 200, 1); --id 29
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 2, 1, 30, 200, 1); --id 30
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 1, 1, 31, 200, 1); --id 31
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 1, 1, 32, 200, 1); --id 32

--Projekcije za dan 18.01.2020 Black hall (8 projekcija 8 perioda)
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 3, 2, 33, 100, 1); --id 33
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 2, 2, 34, 150, 1); --id 34
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 1, 2, 2, 35, 200, 1); --id 35
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 2, 3, 2, 35, 200, 1); --id 35
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 2, 2, 37, 200, 1); --id 37
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 2, 2, 38, 200, 1); --id 38
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 3, 3, 2, 39, 200, 1); --id 39
INSERT INTO Projection (active, idMovie, idProjectionType, idHall, idPeriod, price, idAdmin) VALUES (1, 4, 3, 2, 40, 200, 1); --id 40


select Projection.id, Projection.active, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(Ticket.id)
from Projection
LEFT JOIN Ticket on  Projection.id = Ticket.idProjection 
where Projection.active = 1 --and Ticket.active = 1 
--where Projection.id = 1 and Projection.active = 1 and Ticket.active = 1 
group by Projection.id, Ticket.idProjection

--projections for hall

select Projection.id, Projection.active, Projection.idMovie, Projection.idProjectionType, Projection.idHall, Projection.idPeriod, Projection.price, Projection.idAdmin, COUNT(*)
from Projection
LEFT JOIN Ticket on  Projection.id = Ticket.idProjection 
where Projection.active = 1 and Ticket.active = 1 and Projection.idHall = 1 
group by Projection.id, Ticket.idProjection




CREATE TABLE Period
(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    startDate VARCHAR(16) NOT NULL,
    endDate  VARCHAR(16) NOT NULL
    
);
delete from Period
--durations
--'Joker', 122 
 --'John Shaft', 99 
--'Avengers: Endgame', 182
--'Harry Potter', 120
--'Churchil', 105
select * from Period
--Period objekti za dan 21.01.2020 (8 projekcija za white hall)
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 09:00', '21-01-2020 11:02'); --id 1
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 11:30', '21-01-2020 12:59'); --id 2
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 13:30', '21-01-2020 15:32'); --id 3
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 16:00', '21-01-2020 17:39'); --id 4
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 17:45', '21-01-2020 20:47'); --id 5
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 21:00', '21-01-2020 23:00'); --id 6
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 04:00', '21-01-2020 07:02'); --id 7
INSERT INTO Period (active, startDate, endDate) VALUES (1, '21-01-2020 07:10', '21-01-2020 08:59'); --id 8

--Period objekti za dan 22.01.2020 (8 projekcija za Black Hall)
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 09:00', '22-01-2020 11:02'); --id 9
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 11:30', '22-01-2020 12:59'); --id 10
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 13:30', '22-01-2020 15:32'); --id 11
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 16:00', '22-01-2020 17:39'); --id 12
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 17:45', '22-01-2020 20:47'); --id 13
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 21:00', '22-01-2020 23:00'); --id 14
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 04:00', '22-01-2020 07:02'); --id 15
INSERT INTO Period (active, startDate, endDate) VALUES (1, '22-01-2020 07:10', '22-01-2020 08:59'); --id 16

--Period objekti za dan 23.01.2020 (8 projekcija za Orange Hall)
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 09:00', '23-01-2020 11:02'); --id 17
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 11:30', '23-01-2020 12:59'); --id 18
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 13:30', '23-01-2020 15:32'); --id 19
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 16:00', '23-01-2020 17:39'); --id 20
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 17:45', '23-01-2020 20:47'); --id 12
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 21:00', '23-01-2020 23:00'); --id 22
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 04:00', '23-01-2020 07:02'); --id 23
INSERT INTO Period (active, startDate, endDate) VALUES (1, '23-01-2020 07:10', '23-01-2020 08:59'); --id 24

--Period objekti za dan 24.01.2020 (8 projekcija za White Hall)
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 09:00', '24-01-2020 11:02'); --id 25
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 11:30', '24-01-2020 12:59'); --id 26
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 13:30', '24-01-2020 15:32'); --id 27
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 16:00', '24-01-2020 17:39'); --id 28
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 17:45', '24-01-2020 20:47'); --id 29
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 21:00', '24-01-2020 23:00'); --id 30
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 04:00', '24-01-2020 07:02'); --id 31
INSERT INTO Period (active, startDate, endDate) VALUES (1, '24-01-2020 07:10', '24-01-2020 08:59'); --id 32

--Period objekti za dan 25.01.2020 (8 projekcija za Black Hall)
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 09:00', '25-01-2020 11:02'); --id 33
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 11:30', '25-01-2020 12:59'); --id 34
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 13:30', '25-01-2020 15:32'); --id 35
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 16:00', '25-01-2020 17:39'); --id 36
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 17:45', '25-01-2020 20:47'); --id 37
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 21:00', '25-01-2020 23:00'); --id 38
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 04:00', '25-01-2020 07:02'); --id 39
INSERT INTO Period (active, startDate, endDate) VALUES (1, '25-01-2020 07:10', '25-01-2020 08:59'); --id 40

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
delete from Ticket
select * from Ticket
--Tickets for Projections on 14.01.2020 White hall; ID projekcija (1-8); id sedišta (1-3)
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 1, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 1, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 2, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 2, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 3, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 3, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 4, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 4, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 5, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 5, 2, '10-01-2020 12:00', 2);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 6, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 6, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 7, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 7, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 8, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 8, 2, '10-01-2020 12:00', 3);


--Tickets for Projections on 15.01.2020 Black hall; ID projekcija (9-16); id sedišta (4-7)
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 9, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 9, 5, '10-01-2020 12:00', 3);


INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 10, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 10, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 11, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 11, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 12, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 12, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 13, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 13, 5, '10-01-2020 12:00', 2);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 14, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 14, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 15, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 15, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 16, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 16, 5, '10-01-2020 12:00', 3);

--Tickets for Projections on 16.01.2020 Orange hall; ID projekcija (17-24); id sedišta (8-12)
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 17, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 17, 9, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 18, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 18, 9, '10-01-2020 12:00', 3);


INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 19, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 19, 9, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 20, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 20, 9, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 21, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 21, 9, '10-01-2020 12:00', 2);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 22, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 22, 9, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 23, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 23, 9, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 24, 8, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 24, 9, '10-01-2020 12:00', 3);

--Tickets for Projections on 17.01.2020  white hall; ID projekcija (25-32); id sedišta (1-3)
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 25, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 25, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 26, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 26, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 27, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 27, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 28, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 28, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 29, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 29, 2, '10-01-2020 12:00', 2);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 30, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 30, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 31, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 31, 2, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 32, 1, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 32, 2, '10-01-2020 12:00', 3);


--Tickets for Projections on 18.01.2020  Black hall; ID projekcija (33-40); id sedišta (4-7)
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 33, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 33, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 34, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 34, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 35, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 35, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 36, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 36, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 37, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 37, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 38, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 38, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 39, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 39, 5, '10-01-2020 12:00', 3);

INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 40, 4, '10-01-2020 12:00', 2);
INSERT INTO  Ticket (active, idProjection, idSeat, timeOfSale, idUser) VALUES (1, 40, 5, '10-01-2020 12:00', 3);



CREATE TABLE Seat
(

    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    number INTEGER  NOT NULL,
    idHall INTEGER NOT NULL
   
    
);
select * from Seat
--3 seats for White Hall
INSERT INTO Seat (active, number, idHall ) VALUES (1, 1, 1); --id 1
INSERT INTO Seat (active, number, idHall ) VALUES (1, 2, 1);--id 2
INSERT INTO Seat (active, number, idHall) VALUES (1, 3, 1); --id 3

--4 seats for Black Hall
INSERT INTO Seat (active, number, idHall ) VALUES (1, 1, 2); --id 4
INSERT INTO Seat (active, number, idHall ) VALUES (1, 2, 2);--id 5
INSERT INTO Seat (active, number, idHall) VALUES (1, 3, 2);--id 6
INSERT INTO Seat (active, number, idHall) VALUES (1, 4, 2);--id 7

--5 sests for Orange Hall
INSERT INTO Seat (active, number, idHall ) VALUES (1, 1, 3); --id 8
INSERT INTO Seat (active, number, idHall ) VALUES (1, 2, 3);--id 9
INSERT INTO Seat (active, number, idHall) VALUES (1, 3, 3);--id 10
INSERT INTO Seat (active, number, idHall) VALUES (1, 4, 3);--id 11
INSERT INTO Seat (active, number, idHall) VALUES (1, 5, 3);--id 12



select * 
from Hall
where Hall.id = 1 and Seat.idHall = 1 AND Seat.id not in (select Ticket.idSeat
                                          from Ticket
                                          where Ticket.idProjection = 1)
                                          


SELECT Seat.id
from seat
where Seat.idHall = 1 and Seat.id not in (select Ticket.idSeat
                                    from Ticket
                                    where Ticket.idProjection = 1);



--report
SELECT Movie.id, Movie.name, Projection.id, Projection.active, Period.startDate, Period.endDate, Projection.price, count(Ticket.id)
FROM Movie 
LEFT JOIN Projection ON Movie.id = Projection.idMovie
LEFT JOIN Ticket ON Projection.id = Ticket.idProjection
LEFT JOIN Period ON Projection.idPeriod = Period.id
WHERE Movie.active = 1
group by Projection.id
ORDER BY Movie.id
