create database empresa;
use empresa;
create table if not exists trabajadores(
dni char(9) primary key,
nombre varchar(50),
ciudad varchar(40),
antiguedad int,
salaraio decimal(8,2),
numdep int
);

insert into trabajadores values ('1A','Fernando','Madrid',4,45000,1);
insert into trabajadores values ('2A','Ana','Valencia',8,30000,7);
insert into trabajadores values ('3A','Maite','Bilbao',12,55000,5);
insert into trabajadores values ('4A','Eugenio','Extremadura',14,35000,8);
insert into trabajadores values ('5A','Begoña','Huesca',8,25000,12);
insert into trabajadores values ('6A','Paco','Huelva',7,15000,10);

select*from trabajadores;
-- muestra los registros con las columnas elegidas
select nombre, salaraio, ciudad from trabajadores;
--  muestra registros entre tal y tal
select*from trabajadores limit 4,6;
-- trabajdores departamento numero 8
select*from trabajadores where numdep = 8;

-- muestra los trabajadores que tienen mas de 5 años de antiguedad
select*from trabajadores where antiguedad > 11;
use empresa;
-- concatenar filtros and y or
select*from trabajadores where antiguedad > 11 and salaraio > 40000;
select*from trabajadores where antiguedad > 11 or ciudad = 'Valencia';

-- concatenar; mostrar trabajadores con 2 condicionantes el parentesis es importante
select*from trabajadores where antiguedad > 11 or antiguedad > 12 and ciudad = 'Valencia' or ciudad = 'bilbao';
select*from trabajadores where (antiguedad > 11 or antiguedad > 12) and (ciudad = 'Valencia' or ciudad = 'bilbao');

-- mostrar trabajdores que tengan un salario entre tal y tal

select*from trabajadores where salaraio>35000 and salaraio<50000;


-- muestra trabjadores que ciudad empiece por la letra h

select*from trabajadores where ciudad like 'H%';
-- muestra trabjadores que ciudad tenga la letra a

select*from trabajadores where ciudad like '%a%';

-- muestra trabjadores que ciudad tenga la letra a como penultima letra

select*from trabajadores where ciudad like '%a_';

-- muestra salario de trabajadores marta o maria

select nombre, salaraio from trabajadores where nombre like '%eugenio%' or nombre like '%ana%';

-- muestra el nombre de los trabajadores que no son de huesca

select nombre, ciudad from trabajadores where ciudad <> 'Huesca';

-- muestra el nombre de los trabajadores que no son de una ciudad que tenga la letra h

select nombre, ciudad from trabajadores where ciudad not like 'H%';


insert into trabajadores values ('7A','Fernando','Santander',null,50000,null);

select nombre from trabajadores where antiguedad is null;

-- mostrar trabajdores de vairas ciudaddes

select*from trabajadores where ciudad in ('valencia','huesca','huelva');

-- mostrar trabajdores que no sean de vairas ciudaddes (la contraria a  la anterior)
select*from trabajadores where ciudad not in ('valencia','huesca','huelva');

-- mostrar trabajadores de ciudades ordenados por ciudad

select*from trabajadores where ciudad in ('valencia','huesca','huelva')
order by ciudad asc;

-- mostrar trabajadores de ciudades ordenados por ciudad si coinciden varias valores de algunos campos

/* select*from trabajadores where ciudad in ('valencia','huesca','huelva')
order by ciudad asc, antiguedad asc; */


-- no mostrar decimales (del salario y nombre de los trabajadores de bilbao y valencia) cambiar el nombre de columna con alias

select nombre, round(salaraio,0) from trabajadores where ciudad in ('bilbao','valencia');

-- cambiar el nombre de columna con alias

select nombre as 'Nombre del trabajador', round(salaraio,0) as salario from trabajadores where ciudad in ('bilbao','valencia');

-- nombre en mayusculas
select upper (nombre), round(salaraio,0) from trabajadores where ciudad in ('bilbao','valencia');

-- longitud de nombres
select length (nombre) as longitud, nombre, round(salaraio,0) from trabajadores where ciudad in ('bilbao','valencia');

-- muestra cuantos trabajadores hay con ciudad = bilbao

select count(*) as 'Trabajadores de Bilbao' from trabajadores where ciudad = 'bilbao';

-- muestra media de salario de trabajadores
select round(avg(salaraio),3) as media from trabajadores;

-- mostrar el maximo valor del campo salario de la ciudad valencia
select max(salaraio) from trabajadores where ciudad = 'valencia';

-- mostrar el maximo valor de salario

select max(salaraio) from trabajadores;