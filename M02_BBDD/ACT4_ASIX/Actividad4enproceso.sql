use electro;

-- 1: crear tabla de almacenes con las caracteristicas del enunciado

create table almacen (
idalmacen tinyint primary key not null,
nombre varchar(40),
ciudad varchar(30),
responsable varchar(50),
superficie int
);

/* 2: Añadir el campo idalmacen a la tabla tienda, se añade a la tabla tienda porque la relación 1:N de los almacenes
	(1 almacen suministra a N tiendas y 1 tienda solo es suministrada por 1 almacen), la foreign key iría en la tabla tienda */
    
-- añadimos a la tabla tienda la foreign key del idalmacen cuidando que tenga el mismo tipo de dato

alter table tienda add column almacenidalmacen tinyint not null;

-- agregamos la relación 1:N de la tablas almacen y tienda

alter table tienda add constraint fk_tienda_almacen foreign key (almacenidalmacen) references almacen(idalmacen);

-- no permite realizar la relación ya que existen campos vacíos en la tabla almacen, pasamos a insertarlos y repetimos la ejecución

-- 3: Insertar los valores del enunciado de la tabla almacen

insert into almacen values (1, 'Madrid Central', 'Madrid','Sebastián Amaya', 1500), (2, 'Almacén Vallés', 'Barcelona','Carmen Álvarez', 2500);
select*from almacen;
-- ya están los datos insertados, pero para poder añadir el constraint, es decir, la relación, es necesario asignar a la tabla tienda que almacén le corresponde

-- 4: Asignar a cada tienda su almacen

update tienda set almacenidalmacen=1 where ciudad ='Madrid';

update tienda set almacenidalmacen=2 where ciudad='Barcelona';

-- Nos dirijimos a la linea de add constraint y repetimos la operación y ahora si deja realizarla.

select*from tienda; -- para comprobar que se han ajustado los valores correctamente

-- 5:Mostrar para cada frigorifico con altura>180; su marca, modelo, precio, sistema, tienda y almacen

select F.marca, F.modelo, F.precio, F.sistema, T.nombre, A.nombre from frigorificos F
	inner join tienda T on F.tiendaIDtienda=T.IDtienda
    inner join almacen A on A.idalmacen=T.almacenidalmacen
	where F.altura > 180;
    
-- 6: Ampliar superficie hasta los 2500 metros de Electrosol y numero de trabajadores a 12. 

update tienda set superficie=2500, num_trabajadores=12 
	where nombre='ElectroSol';

select*from tienda;

-- 7: Añadir a la tabla frigorificos un campo llamado estrellas despues del precio y rellenarlo con 5 para haier y balay y con 4 para el resto

alter table frigorificos add estrellas tinyint after precio; 

update frigorificos set estrellas = 5 where marca in ('Haier','Balay'); 

update frigorificos set estrellas = 4 where marca not in ('Haier','Balay');

select*from frigorificos;

-- 8: Añadir a frigorificos el campo promocion con un descuento de 100€ en el precio solo a los no frost, con altura>180 y precio<800€

alter table frigorificos add promocion smallint;

update frigorificos set promocion = precio-100
	where altura>180 and precio<800;
    
-- 9: Crear BBDD con nombre copiaseguridad con datos actuales

create database copiaseguridad;
use copiaseguridad;
create table almacencopia like electro.almacen;
create table frigorificoscopia like electro.frigorificos;
create table tiendacopia like electro.tienda;
insert into almacencopia select*from electro.almacen;
insert into frigorificoscopia select*from  electro.frigorificos;
insert into tiendacopia select*from  electro.tienda;

select*from frigorificoscopia;

-- 10: Crear vista con todos los frigorificos de las tiendas de Madrid, mostrando nombre de tienda, y todos los datos del frigorifico, el nombre de la vista será Vistatiendamadrid

-- primero hacemos la consulta para verificar que se muestra todos los parametros que queremos

select T.nombre, F.* from frigorificos F
	inner join tienda T on T.IDtienda=F.tiendaIDtienda
    where T.ciudad='Madrid';
    
create view Vistatiendamadrid as
select T.nombre, F.* from frigorificos F
	inner join tienda T on T.IDtienda=F.tiendaIDtienda
    where T.ciudad='Madrid';
    
select*from Vistatiendamadrid; -- para comprobar que estaría arrojando los mismos resultados

-- 11: Empezar una transacción con BEGIN ,Incrementar 50 € el precio de todos los frigorificos de la marca Haier y hacer un ROLLBACK

begin;
update frigorificos set precio=precio+50 where marca='Haier';
rollback;

select*from frigorificos; -- para comprobar el estado de las modificaciones en la tabla frigorificos

-- 12: Empezar una transacción con BEGIN, Incrementar 50 € el precio de todos los frigorificos de la marca AEG y hacer un COMMIT
begin;
update frigorificos set precio=precio+50 where marca='AEG';
commit;

select*from frigorificos; -- para comprobar el estado de las modificaciones en la tabla frigorificos

-- 13: Empezar una transacción con BEGIN, Incrementar 50€ al precio de frigorificos Balay, define un punto de control llamdo PASO1, borra frigorificos de la marca AEG y hacer un rollback al PASO1 y hacer un COMMIT
/*--*/

begin;
update frigorificos set precio=precio+50 where marca ='Balay';
savepoint PASO1;
delete from frigorificos where marca='AEG';
rollback to savepoint PASO1;
commit;

select*from frigorificos; -- para comprobar el estado de las modificaciones en la tabla frigorificos

-- 14: Bloquear la tabla frigorificos para escritura e intentar modificar alguna fila de datos

lock table frigorificos write;
update frigorificos set altura=215 where marca = 'Balay';
select*from frigorificos; -- para comprobar el estado de las modificaciones en la tabla frigorificos
unlock tables;

-- el bloqueo tipo read permite leer los datos tanto al administrador como al usuario común mientras que prohíbe modificar o insertar datos a ambos
-- el bloqueo tipo write permite leer y modificar datos al administrador pero no permite modificar ni leer datos al usuario común.