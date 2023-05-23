-- Crear base de datos electro

create database electro;

-- seleccionarla para operar con ella

use electro;

create table tienda(
IDtienda tinyint primary key,
nombre varchar(40),
ciudad varchar(20),
num_trabajadores tinyint,
superficie int
);

create table frigorificos(
codigo tinyint primary key,
marca varchar(20),
modelo varchar(40),
color varchar(20),
capacidad int,
sistema varchar(20),
altura int,
precio int,
tiendaIDtienda tinyint,
constraint fk_frigorificos_tienda foreign key(tiendaIDtienda) references tienda(IDtienda)
);

insert into tienda values (1, 'ElectroSol', 'Madrid', 5, 1250);
insert into tienda values (2, 'TotalFrigo', 'Madrid', 8, 1750);
insert into tienda values (3, 'BarnaElectric', 'Barcelona', 10, 2000);
insert into tienda values (4, 'FrigoDiaz', 'Barcelona', 5, 1000);
insert into tienda values (5, 'FrigoElectric', 'Barcelona', 15, 3000);

insert into frigorificos values (1,'Haier', 'HTR3619ENMN','Inox', 348, 'No frost', 190, 619, 1);
insert into frigorificos values (2, 'Balay', 'LRB3DE18S', 'Blanco', 311, 'Estatico', 178, 1010, 2);
insert into frigorificos values (3, 'Haier', 'RS650N4AC1', 'Inox', 500, 'No frost', 110, 179, 2);
insert into frigorificos values (4, 'Balay', 'JF-90', 'Inox', 90, 'Estatico', 75, 139, 3);
insert into frigorificos values (5, 'AEG', 'RB34A7', 'Blanco', 344, 'No frost', 185, 949, 4);
insert into frigorificos values (6, 'Haier', 'OFX211', 'Negro', 80, 'Estatico', 80, 129, 1);
insert into frigorificos values (7, 'AEG', 'RCB632E5MX', 'Blanco', 290, 'No frost', 186, 799, 2);
insert into frigorificos values (8, 'Balay', '3FAF494XE', 'Inox', 533, 'No frost', 179, 1499, null);

/* Consultas realizadas */

-- Consulta 1: mostrar marca y modelo frigorificos blancos y capacidad>300

select marca, modelo from frigorificos where color = 'blanco' and capacidad > 300;

-- Consulta 2: mostrar nombre tienda, marca, modelo y precio del frigorifico

select T.nombre, F.marca, F.modelo, F.precio from Tienda T
	inner join Frigorificos F on T.IDtienda = F.tiendaIDtienda;
    
-- Consulta 3: Mostrar numero total de frigorificos por su marca

select count(*) as total, marca from frigorificos
	group by marca;
    
-- Consulta 4: Importe total de los frigorificos de cada tienda, mostrar el nombre de la tienda y el importe total

select sum(F.precio) as ImporteTotal, T.nombre as nombreTienda from frigorificos F
	inner join tienda T on F.tiendaIDtienda = T.IDtienda
    group by T.nombre;

-- Si se desea que aparezcan todas las tiendas (hay una tienda que no tiene ningun frigorifico usariamos left join)
select T.nombre as nombreTienda, sum(F.precio) as ImporteTotal from tienda T
	left join frigorificos F on T.IDtienda = F.tiendaIDtienda
    group by T.nombre;

-- Consulta 5: Mostrar marca, modelo, precio, capacidad y la tienda de cada frigorifico. Si un frigorifico no pertenece a ninguna tienda tambien debe salir
select F.codigo, F.marca, F.modelo, F.precio, F.capacidad, T.IDtienda, T.nombre from frigorificos F
	left join tienda T on F.tiendaIDtienda = T.IDtienda;
    
-- Consulta 6: Mostrar tienda y suma de precio de frigorificos, de las tiendas que el importe total > 1500€
    
select T.nombre, sum(F.precio) as ImporteTotal from tienda T
	inner join frigorificos F on T.IDtienda = F.tiendaIDtienda
    group by T.nombre
    having sum(F.precio) > 1500;
    
-- Consulta 7: Mostrar marca y modelos de los frigorificos que no estan en ninguna tienda (2 maneras)

-- manera corta en la propia tabla

select marca, modelo from frigorificos where tiendaIDtienda is null;

-- manera larga usando las 2 tablas

select F.marca, F.modelo, T.IDtienda, T.nombre from tienda T
	 right join frigorificos F on T.IDtienda = F.tiendaIDtienda
    where F.tiendaIDtienda is null;
    
-- Consulta 8: Mostrar marca, modelo, precio y columna de descuento del 10% para frigorificos con altura > 170 y precio < 800

select marca, modelo, precio, precio*0.9 as Descuento from frigorificos
	where altura > 170 and precio < 800;

-- Consulta 9: Marca y modelo del frigorifico de mayor capacidad

select max(capacidad) from frigorificos; -- parte 1 de la consulta verificar comprobación de la capacidad maxima

select marca, modelo from frigorificos 
	where capacidad = (select max(capacidad) from frigorificos);

-- Consulta 10: Marca y modelos de frigorificos con capacidad superior al frigorifico de mayor capacidad de la marca AEG

select max(capacidad) from frigorificos where marca = 'AEG'; -- parte 1: de los frigorificos AEG mostrar el que tenga la capacidad maxima 

select marca, modelo, capacidad from frigorificos 
	where capacidad > (select max(capacidad) from frigorificos where marca = 'AEG')
    order by capacidad desc;