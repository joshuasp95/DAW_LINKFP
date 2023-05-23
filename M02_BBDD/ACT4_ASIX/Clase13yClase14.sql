create database escuela23;
use escuela23;

create table alumno (
codigo varchar(5) primary key,
nombre varchar(20),
ciudad varchar(20),
edad tinyint
);

create table profesor (
dni char(9) primary key,
nombre varchar(20),
fechaAlta date
);

create table modulo(
codigo varchar(20) primary key,
nombre varchar(20),
aula varchar(20),
profesordni char(9),
foreign key (profesordni) references profesor(dni)
);

create table notas(
semestre varchar(10),
modulocodigo varchar(20),
alumnocodigo varchar(5),
nota decimal (4,2),
primary key (semestre, modulocodigo, alumnocodigo),
constraint fk_notas_alumno foreign key (alumnocodigo) references alumno(codigo),
constraint fk_notas_modulo foreign key (modulocodigo) references modulo(codigo)
);


insert into alumno values('001','Juan', 'Jaen', 22);
insert into alumno values('002','Javi', 'Lugo', 27);
insert into alumno values('003','Jano', 'Bilbao', 29);
insert into alumno values('004','Asier', 'Zaragoza', 17);
insert into alumno values('005','Carlos', 'Zaragoza', 15);
insert into alumno values('006','Santiago', 'Bilbao', 19);
insert into alumno values('007','Isabel', 'Balmaseda', 23);
insert into alumno values('008','Asier', 'Barcelona', 23);
insert into alumno values('009','Jose', 'Madrid', 45);
insert into alumno values('010','Jandro', 'Valencia', 33);

insert into profesor values('123A', 'Ester', '2005-8-12');
insert into profesor values('123B', 'Maribel', '2004-1-11');
insert into profesor values('123C', 'Ana', '2012-3-9');
insert into profesor values('123D', 'Begoña', '2007-2-11');
insert into profesor values('123E', 'Maribel', '2018-4-16');

insert into modulo values('AAA', 'BBDD', 'Aula 1M', '123B');
insert into modulo values('AAB', 'Implantacion de SO', 'Aula 5A', '123C');
insert into modulo values('AAC', 'HTML', 'Aula 4P', '123A');
insert into modulo values('AAD', 'Hardware', 'Aula 2N', '123D');
insert into modulo values('AAE', 'FOL', 'Aula 2H', '123E');


insert into notas values('Primero', 'AAD', '005', 6.5);
insert into notas values('Primero', 'AAB', '001', 6.5);
insert into notas values('Primero', 'AAC', '007', 7.5);
insert into notas values('Primero', 'AAE', '009', 8.5);
insert into notas values('Primero', 'AAA', '010', 8.5);
insert into notas values('Primero', 'AAB', '005', 5.5);
insert into notas values('Primero', 'AAC', '004', 7.4);


insert into notas values('Segundo', 'AAA', '003', 6.5);
insert into notas values('Segundo', 'AAB', '006', 4.5);
insert into notas values('Segundo', 'AAC', '010', 7.5);
insert into notas values('Segundo', 'AAA', '001', 8.5);
insert into notas values('Segundo', 'AAC', '008', 6.5);
insert into notas values('Segundo', 'AAA', '007', 4.5);
insert into notas values('Segundo', 'AAB', '003', 6.2);
insert into notas values('Segundo', 'AAD', '005', 6.1);
insert into notas values('Segundo', 'AAE', '004', 8.0);
insert into notas values('Segundo', 'AAD', '002', 6.5);

-- insertar valores null
update notas set nota = null where alumnocodigo ='009' and semestre = 'Segundo' and modulocodigo = 'AAB';
insert into notas values('Segundo', 'AAB', '009', null);

select*from notas;
select*from alumno;
select count(*) from alumno;

-- muestra el nombre del alumno la edad el modulo y la nota

select A.nombre, A.edad, M.nombre, N.nota from alumno A
	inner join notas N on A.codigo = N.alumnocodigo
    inner join modulo M on M.codigo = N.modulocodigo;
    
    -- y el semestre
    
select A.nombre, A.edad, M.nombre, N.nota, N.semestre from alumno A
	inner join notas N on A.codigo = N.alumnocodigo
    inner join modulo M on M.codigo = N.modulocodigo;


-- mostrar el nombre de alumno y el nombre de su profesor

select A.nombre, P.nombre from Alumno A
	inner join notas N on A.codigo = N.alumnocodigo
    inner join modulo M on M.codigo = N.modulocodigo
    inner join profesor P on P.dni = M.profesordni
    order by A.nombre;
    
    -- hay mas de 2 asier en la consulta entonces se repiten los semestres?
    
    select A.nombre, P.nombre, N.semestre from Alumno A
	inner join notas N on A.codigo = N.alumnocodigo
    inner join modulo M on M.codigo = N.modulocodigo
    inner join profesor P on P.dni = M.profesordni
    order by A.nombre;
    
    -- muestra de cada nombre de alumno que profesor le ha suspendido
    
    select A.nombre, P.nombre, N.nota, M.nombre from Alumno A
	inner join notas N on A.codigo = N.alumnocodigo
    inner join modulo M on M.codigo = N.modulocodigo
    inner join profesor P on P.dni = M.profesordni
    where nota < 5;
    
    -- mostras alumnos que no tienen nota
    
    select A.codigo, A.nombre, A.edad, N.nota, N.semestre, M.nombre, M.codigo from Alumno A
		left join notas N on A.codigo = N.alumnocodigo
        left join modulo M on M.codigo = N.modulocodigo
        where N.nota is null;
	
    select A.nombre, A.codigo, N.nota, N.semestre from Alumno A
		Left join Notas N on A.codigo = N.alumnocodigo;
        
        
	use escuela;
    
    
    -- añadir alumnos y que aparezcan notas con valores null
    
    insert into alumno values('011', 'German', 'Granada', 40); 
    
    -- SI se cumple
    
    -- mostrar profesor que no tenga modulo
    
    insert into profesor values('123H','Marina', '2009-2-22');
    
    -- que profesores no imparten ningun modulo
    select P.nombre, P.dni, M.nombre, M.codigo from Profesor P
		left join Modulo M on P.dni = M.profesordni
        where profesordni is null;
        
	-- ahorrar tiempo haciendo la consulta mejor, donde hay profesoresdni con valor null en la tabla modulo, que modulos no tienen ningun profesor
    
    select nombre from modulo where profesordni is null;
    
    insert into modulo values('AAZ', 'ASIR', '2W', null);
    
-- mostrar alumnos con mayor edad
    select nombre, edad from alumno 
		where edad = (select max(edad) from Alumno);

	
-- mostrar alumnos con mas edad que los de un grupo en concreto, por ejemplo, mostrar alumnos de mayor edad que la edad de los alumnos que viven en Bilbao

select nombre, edad from alumno
	where edad > ALL (select edad from alumno where ciudad = 'Bilbao');
    
-- mostrar con any (alguno que cumpla esa condicion) (>19)

select nombre, edad from alumno
	where edad > any (select edad from alumno where ciudad = 'Bilbao');
    
-- mostrar con in, mostrar los que son iguales

select nombre, edad, ciudad from alumno
	where edad in (select edad from alumno where ciudad = 'Barcelona');
    
    
-- mostrar alumnos con maxima nota

select A.nombre, A.codigo, N.nota from Notas N
	inner join Alumno A on A.codigo = N.alumnocodigo
    where nota = (select max(nota) from notas);

-- comprobacion en 2 partes, si las unes tendrias la consulta de arriba
select max(nota) from notas;

select alumnocodigo from notas where nota = 8.5;

-- cual es el nombre de los alumnos que superan la media de edad de la clase

select avg(edad) from alumno; -- 26,63

select*from alumno;

-- mostrar alumnos on edad supeior a la media
select nombre, edad from alumno where edad > (select avg(edad) from alumno);

-- muestra el nombre de aquellos alumnos que sean de la misma ciudad que los alumnos con codigo 006 y 004

select ciudad from alumno where codigo in ('006', '004'); -- parte 1 de la consulta, devuelve las ciudades de los alumnos con esos codigos

select nombre, ciudad from alumno where ciudad in (select ciudad from alumno where codigo in ('006', '004'));

-- cuantos alumnos hay en la tabla

select count(*) as total from alumno;

-- cuantos alumnos hay por ciudad

select count(*), ciudad as total from alumno
	group by ciudad;

-- ordenados descen

select count(*), ciudad as total from alumno
	group by ciudad
    order by total asc;
    
select ciudad, count(*) as total from alumno
	where ciudad not in ('zaragoza','madrid') and ciudad not like 'b%'
    group by ciudad;
    
    
-- mostrar media de nota por modulo

select avg(nota), modulocodigo from notas 
	group by modulocodigo;
    
-- inner join

select M.nombre, N.modulocodigo, round(avg(N.nota),2) as Notamedia from notas N
	inner join modulo M on M.codigo = N.modulocodigo
	group by N.modulocodigo;
    
-- mostrar ciudades con mas de 2 alumnos

select ciudad, count(*) as total from alumno
	group by ciudad; -- primera parte de la consulta

select ciudad, count(*) as total from alumno
	group by ciudad
    having total > 1;



-- clase 13

-- eliminar notas de alumnos con codigo tal y tal
    
delete from notas where alumnocodigo in ('001','002');


-- eliminar alumnos de barcelona con edad 20-30 

delete from alumno where ciudad ='Barcelona' and edad between 10 and 60;

alter table notas drop foreign key fk_notas_alumno; 

alter table notas add constraint fk_notas_alumno foreign key (alumnocodigo) references alumno(codigo)
	on delete cascade;
    


-- eliminar con inner join

delete N.* from notas N
	inner join alumno A on A.codigo=N.alumnocodigo
	where A.nombre='Jandro';

select*from notas;

-- inner join en 3 entidades

delete N.* from notas N
	inner join modulo M on M.codigo=N.modulocodigo
    inner join profesor P on P.dni=M.profesordni
    where N.semestre='Primero' and P.nombre='Maribel';
    
    
-- crea una vista donde se vea el nombre del alumno la nota y el nombre del modulo

select A.nombre, N.nota, N.semestre, M.nombre from alumno A
	inner join notas N on A.codigo=N.alumnocodigo
    inner join modulo M on M.codigo=N.modulocodigo;

-- crear vista

create view notasalumnosmodulo as 
select A.nombre as Alumno, N.nota, N.semestre, M.nombre as Modulo from alumno A
	inner join notas N on A.codigo=N.alumnocodigo
    inner join modulo M on M.codigo=N.modulocodigo;

select*from notasalumnosmodulo where nota < 8;


select*from alumno;

/*begin ; 
insert into alumno values ('054', 'EVARISTO', 'Barcelona',19);
insert into alumno values ('053', 'EVARISTO1', 'Barcelona',19);
savepoint paso1;
insert into alumno values ('052', 'EVARISTO2', 'Barcelona',19);
insert into alumno values ('051', 'EVARISTO3', 'Barcelona',19);
select*from alumno;
rollback to savepoint paso1
);*/

-- bloqueo tipo read
-- sesion propietaria leer si escrib no
-- sesion no prop leer si escrib no

lock table notas read;


select semestre from notas;

insert into notas(semestre) values ('Primero');


unlock tables;


lock table alumno write;
select*from alumno;
insert into alumno values('A001', 'MANOLO', 'BARNA', '29');


