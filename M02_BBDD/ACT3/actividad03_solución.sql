CREATE DATABASE ElectroDB;
USE ElectroDB;

CREATE TABLE IF NOT EXISTS Tienda (
	idtienda TINYINT PRIMARY KEY, 
	nombre VARCHAR(20) NOT NULL,
	ciudad VARCHAR(20),
    num_trabajadores int,
    superficie INT
);

CREATE TABLE IF NOT EXISTS Frigorificos (
codigo INT PRIMARY KEY, 
	marca VARCHAR(15) NOT NULL,
    	modelo VARCHAR(30),
    	color VARCHAR(15),
    	capacidad INT,
    	sistema ENUM('Estático','No frost'),
    	altura INT,
    	precio DECIMAL(7,2),
    	tiendaIdTienda TINYINT,
    	CONSTRAINT fk_frigorificos_tienda FOREIGN KEY (tiendaIdTienda) references      
   		Tienda(idtienda) ON UPDATE CASCADE ON DELETE RESTRICT
    );

INSERT INTO Tienda VALUES (1, 'ElectroSol','Madrid',5,1250);
INSERT INTO Tienda VALUES (2, 'TotalFrigo','Madrid',8,1750);
INSERT INTO Tienda VALUES (3, 'BarnaElectric','Barcelona',10,2000);
INSERT INTO Tienda VALUES (4, 'FrigoDíaz','Barcelona',5,1000); 
INSERT INTO Tienda VALUES (5, 'FrigoElectric','Barcelona',15,3000);

INSERT INTO Frigorificos VALUES (1,'Haier','HTR3619ENMN','Inox',348,'No frost',190,619,1);
INSERT INTO Frigorificos VALUES (2,'Balay','LRB3DE18S','Blanco',311,'Estático',178,1010,2);
INSERT INTO Frigorificos VALUES (3,'Haier','RS650N4AC1','Inox',500,'No frost',110,179,2);
INSERT INTO Frigorificos VALUES (4,'Balay','JF-90','Inox',90,'Estático',75,139,3);
INSERT INTO Frigorificos VALUES (5,'AEG','RB34A7','Blanco',344,'No frost',185,949,4);
INSERT INTO Frigorificos VALUES (6,'Haier','OFX211','Negro',80,'Estático',80,129,1);
INSERT INTO Frigorificos VALUES (7,'AEG','RCB632E5MX','Blanco',290,'No frost',186,799,2);
INSERT INTO Frigorificos VALUES (8,'Balay','3FAF494XE','Inox',533,'No frost',179,1499,NULL);



-- 1.	Consulta que muestre la marca y el modelo de los frigoríficos que sean blancos y su capacidad sea superior a 300.
SELECT marca, modelo, color, capacidad FROM frigorificos
WHERE color='blanco' and capacidad>300;

-- 2.	Consulta que muestre el nombre de la tienda, la marca, el modelo y el precio del frigorífico.
SELECT T.nombre,  F.marca, F.modelo, precio FROM Tienda T
INNER JOIN Frigorificos F ON T.idtienda=F.tiendaIdTienda;

-- 3.	¿Cuántos frigoríficos tenemos de cada marca?
SELECT marca, count(modelo) AS Total FROM Frigorificos 
GROUP BY marca;

-- 4.	¿Cuál es el importe total de los frigoríficos de cada tienda? Se debe mostrar el nombre de la tienda y el importe total.

SELECT T.nombre, sum(F.precio) AS SumaTotal FROM Tienda T
INNER JOIN Frigorificos F ON T.idtienda=F.tiendaIdTienda
GROUP BY T.nombre;

--  5.	Mostrar la marca, modelo, precio, capacidad y la tienda de cada frigorífico. Si un frigorífico no pertenece a ninguna tienda también debe salir.

-- USANDO LEFT JOIN:
SELECT T.nombre, F.marca, F.modelo, F.capacidad FROM Frigorificos F 
LEFT JOIN Tienda T ON T.idtienda=F.tiendaIdTienda;

-- USANDO RIGHT JOIN:
SELECT T.nombre, F.marca, F.modelo, F.capacidad FROM Tienda T
RIGHT JOIN Frigorificos F ON T.idtienda=F.tiendaIdTienda;


--  6.	Mostrar la tienda y la suma del precio de sus frigoríficos, solo de aquellas tiendas que la suma del precio de sus frigoríficos es superior a 1500€

SELECT T.nombre, sum(F.precio)  AS Precio FROM Tienda T
INNER JOIN Frigorificos F ON T.idtienda=F.tiendaIdTienda
GROUP BY T.nombre
HAVING Precio>1500;

--  7.	Mostrar la marca y el modelo de los frigoríficos que no están en ninguna tienda.

SELECT marca, modelo  FROM Frigorificos
WHERE tiendaIdTienda IS NULL;

-- 8.	Mostrar la marca, el modelo, el precio y una nueva columna con un 10% sobre el precio a la que llamaremos descuento de los frigoríficos de altura superior a 170 y un precio menor de 800€.

SELECT marca, modelo, precio, precio*0.10 AS Descuento  FROM Frigorificos
WHERE altura>170 and precio<800;

-- 9.	Marca y modelo del frigorífico de mayor capacidad.

SELECT marca, modelo  FROM Frigorificos
WHERE capacidad = (SELECT MAX(capacidad) FROM Frigorificos); 

--  10.	Marca y modelos de los frigoríficos que tienen una capacidad superior al frigorífico de mayor capacidad de la marca AEG.
SELECT marca, modelo, capacidad  FROM Frigorificos
WHERE capacidad>(SELECT MAX(capacidad) FROM Frigorificos WHERE marca='AEG');
