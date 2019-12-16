

CREATE TABLE Movie
(

    id INTEGER PRIMARY KEY, --auto increment
    active INTEGER NOT NULL DEFAULT 0,
    name VARCHAR(50) NOT NULL,
    duration REAL CHECK(duration > 0),
    productionYear INTEGER CHECK(productionYear > 0),
    description VARCHAR(100) DEFAULT 'No available description'

);
select * from Movie


INSERT INTO Movie (active, name, duration, productionYear, description) VALUES ( 1, 'Jumanji: Welcome to the Jungle', 120, 2017, 'Description goes here' );
INSERT INTO Movie (active, name, duration, productionYear, description) VALUES ( 1, 'Jumanji: The next level', 140,2019, 'Description goes here' );
INSERT INTO Movie (active, name, duration, productionYear, description) VALUES ( 1, 'John Shaft', 100 ,2001, 'Description goes here' );
INSERT INTO Movie (active, name, duration, productionYear, description) VALUES ( 1, 'Lord of the Rings - Fellowship of the Ring', 125 ,2003, 'Description goes here' );


CREATE TABLE User 
(
    id INTEGER PRIMARY KEY, --auto increment
    active INTEGER NOT NULL DEFAULT 0,
    username VARCHAR(10) UNIQUE NOT NULL, 
    password VARCHAR(10) NOT NULL, 
    registrationDate TIMESTEMP NOT NULL, --XX-XX-XXXX XX:XX
    role VARCHAR(11) NOT NULL DEFAULT 'USER' -- SQLite ne poznaje enum-e --unspecified role 11 char
   
);


INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'a', 'a', '2007-01-01 10:00:00', 'ADMIN');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'b', 'b', '2008-01-01 10:00:00', 'USER');
INSERT INTO User ( active, username, password, registrationDate, role) VALUES (1, 'c', 'c', '2009-01-01 10:00:00', 'UNSPECIFIED');

delete from User
select * from User




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

