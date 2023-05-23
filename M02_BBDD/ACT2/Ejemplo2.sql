CREATE DATABASE clase6ejericio;
USE clase6ejercicio;

-- alumno: dni pk email cc fecha nac
-- uf_ cod pk nomb horas
-- cursa nota, alumdni pk fk, uf cod pk fk

CREATE TABLE Alumno(
DNI CHAR(9) PRIMARY KEY,
nombre VARCHAR(20) NOT NULL,
apellidos VARCHAR(30) NOT NULL, 
email VARCHAR(20) UNIQUE,
fechaNacimiento date
);

ALTER TABLE Alumno CHANGE COLUMN apellidos apellido VARCHAR(30);
ALTER TABLE Alumno ADD COLUMN poblacion VARCHAR(20);

CREATE TABLE UF(
codigo CHAR(5) PRIMARY KEY,
nombre VARCHAR(20) NOT NULL, 
horas SMALLINT
);

CREATE TABLE Cursa(
nota DECIMAL(4,2), 
alumnoDNI CHAR(9), -- FK al
UFcodigo CHAR(5), -- FK uf
PRIMARY KEY(AlumnoDNI, ufcodigo),
CONSTRAINT fk_cursa_alumno FOREIGN KEY(AlumnoDNI) REFERENCES Alumno(DNI)
ON UPDATE CASCADE
ON DELETE RESTRICT,
CONSTRAINT fk_cursa_uf FOREIGN KEY(ufcodigo) REFERENCES UF(codigo) 
ON UPDATE CASCADE
ON DELETE RESTRICT
);

show index from Cursa;