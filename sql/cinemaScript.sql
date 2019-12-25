

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
delete from Movie where id = 2
update Movie set name = 'film 2' where id = 9

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

delete FROM table Acting where id = (2, 2)
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

INSERT INTO Directing (idMovie, idDirector) VALUES (1, 1);
INSERT INTO Directing (idMovie, idDirector) VALUES (1, 3);
delete from Directing
where idMovie = 1 AND idDirector in (1,3)

INSERT INTO Directing (idMovie, idDirector) VALUES (2, 1);


INSERT INTO Directing (idMovie, idDirector) VALUES (2, 1);
INSERT INTO Directing (idMovie, idDirector) VALUES (3, 2);
INSERT INTO Directing (idMovie, idDirector) VALUES (4, 3);

INSERT INTO Directing (idMovie, idDirector) VALUES (6, 1);


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
    registrationDate DATETIME NOT NULL, --XX-XX-XXXX XX:XX
    role VARCHAR(11) NOT NULL DEFAULT 'USER' -- SQLite ne poznaje enum-e --unspecified role 11 char
   
);

drop table User
select * 
from User
where active = 1


--id pre active
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'a', 'a', datetime('2007-01-01 10:00'), 'ADMIN');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'b', 'b', datetime('2008-01-01 10:00'), 'USER');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'c', 'c', datetime('2009-01-01 10:00'), 'UNSPECIFIED');

delete from User
select * from User where registrationDate >  datetime('2007-01-01 10:00');




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



CREATE TABLE Hall
(
     id INTEGER PRIMARY KEY,
     active INTEGER  NOT NULL DEFAULT 0,
     name VARCHAR(20) NOT NULL
     
);



INSERT INTO Hall ( active, name) VALUES (1, 'White hall');
INSERT INTO Hall ( active, name) VALUES (1, 'Black hall');
INSERT INTO Hall ( active, name) VALUES (1, 'Orange hall');
INSERT INTO Hall ( active, name) VALUES (1, 'Blue hall');

select * from Hall


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

select * from Supports


CREATE TABLE Seat
(

    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    number INTEGER UNIQUE NOT NULL,
    idHall INTEGER NOT NULL,
    taken INTEGER NOT NULL
    
);

INSERT INTO Seat (active, number, idHall, taken) VALUES (1, 1, 1, 0);
INSERT INTO Seat (active, number, idHall, taken) VALUES (1, 2, 1, 0);
INSERT INTO Seat (active, number, idHall, taken) VALUES (1, 3, 1, 0);

select * from Seat


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


CREATE TABLE Period
(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    startDate TIMESTEMP NOT NULL,
    endDate TIMESTEMP NOT NULL
    
);









CREATE TABLE Ticket
(
    id INTEGER PRIMARY KEY,
    active INTEGER  NOT NULL DEFAULT 0,
    idProjection INTEGER NOT NULL,
    idSeat INTEEGER NOT NULL,
    timeOfSale TIMESTAMP NOT NULL,
    idUser INTEGER NOT NULL,
    FOREIGN KEY(idProjection) REFERENCES Projection(id)  ON DELETE RESTRICT,
    FOREIGN KEY(idUser) REFERENCES User(id)  ON DELETE RESTRICT
    
    
);

