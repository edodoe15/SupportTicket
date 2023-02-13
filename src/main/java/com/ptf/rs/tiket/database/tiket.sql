CREATE DATABASE tiky;

USE tiky;

CREATE TABLE Tiket (
	id int not null auto_increment,
	tipUsluge varchar(255) not null,
	nazivProblema varchar(500) not null,
	korisnik varchar(100) not null,
    datumPrijave date,
    nacinPrijave varchar(255) not null,
    prioritet varchar(255) not null,
    opisProblema varchar(500) not null,
    agent varchar(255) not null,
    email varchar(255) not null,
    telefon varchar(50) not null,
    vrsta varchar(50) not null DEFAULT 'NOVI',
    datumRjesenja date,
    primary key (id)
);

SELECT * FROM Tiket
SELECT * FROM Tiket WHERE Tiket.id=2