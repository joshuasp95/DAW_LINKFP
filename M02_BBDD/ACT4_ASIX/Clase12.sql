create database empresa2;
use empresa2;
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

/**
select*from trabajadores;
-- muestra los registros con las columnas elegidas
select nombre, salaraio, ciudad from trabajadores;
--  muestra registros entre tal y tal
select*from trabajadores limit 4,6;
-- trabajdores departamento numero 8
select*from trabajadores where numdep = 8; */

select*from trabajadores;

alter table trabajadores change numdep t_numdep int;
alter table trabajadores change salaraio salario decimal(8,2);

create table departamento (
numdep int primary key,
nombredep varchar(20) unique
);

insert into departamento values (1,'informatica');
insert into departamento values (7,'marketing');
insert into departamento values (5,'rrhh');
insert into departamento values (8,'serviciotecnico');
insert into departamento values (12,'ventas');
insert into departamento values (10,'recepcion');

-- no habia puesto valores a t_numdep al cambiarle a la tabla el nombre del campo

update trabajadores set t_numdep=1 where dni='1A';
update trabajadores set t_numdep=5 where dni='2A';
update trabajadores set t_numdep=7 where dni='3A';
update trabajadores set t_numdep=8 where dni='4A';
update trabajadores set t_numdep=12 where dni='5A';
update trabajadores set t_numdep=10 where dni='6A';

alter table trabajadores drop t_numdep;

alter table trabajadores add t_numdep int not null;

alter table trabajadores add constraint fk_trabajadores_departamento foreign key (t_numdep) references departamento(numdep);

insert into trabajadores values ('7A','Teresa', 'Madrid', 8, 50000, 3);
insert into departamento values (3,'gestion');

select*from trabajadores;

-- siempre tiene que tener t_numdep un valor para crear un nuevo registro

insert into departamento values (4,'sistema');

insert into trabajadores (dni,nombre,t_numdep) values ('8A','Francisco',4); -- el resto de los datos con valor null

-- hacer lo mismo con set 


-- crear copia tabla 
create table trabajadorescopia like trabajadores;

select*from trabajadoresbarcelona;

select*from trabajadores where ciudad='Bilbao';

-- combinar las 2 para volcar datos de una tabla a otra, en este caso barcelona = bilbao

insert into trabajadoresbarcelona select*from trabajadores where ciudad='Bilbao'; 

insert into trabajadoresbarcelona select*from trabajadores;

drop table trabajadoresbarcelona;

insert into trabajadorescopia select*from trabajadores;

select*from trabajadorescopia;

-- parametros a cofigurar con alter table

alter table trabajadorescopia rename trabajadorescopiatabla;

alter table trabajadorescopiatabla rename trabajadorescopy;

alter table trabajadorescopy add numhijos int;

alter table trabajadorescopy modify numhijos tinyint;

alter table trabajadorescopy change column numhijos numerodehijos int;

select*from trabajadorescopy;

alter table trabajadorescopy change column numerodehijos numehijos int;

alter table trabajadorescopy modify column numehijos tinyint primary key;

alter table trabajadorescopy drop primary key;

alter table trabajadorescopy add primary key (numehijos);

insert into trabajadorescopy (numehijos) values (1), (2), (3), (4), (5), (6), (7), (8);

alter table trabajadores add column pagaextra decimal(5,2);

update trabajadores set pagaextra=100
	where antiguedad >= 10;
    
use empresa2;

select*from trabajadores;

update trabajadores set antiguedad = antiguedad + 2 
	where ciudad in ('bilbao','valencia');



update trabajadores set pagaextra=200 
	where ciudad in ('madrid','valencia');
    
update trabajadores set pagaextra=salario*0.01
	where ciudad not in ('madrid', 'sevilla');

-- mezclando 2 tablas inner join, subir pagaextra 100 a los que sean de marketing

select*from departamento;

update trabajadores T
	inner join departamento D on D.numdep=T.t_numdep
set pagaextra = pagaextra-173 
	where D.nombredep='marketing';



update trabajadores set dni='1B' where t_numdep=1;

update trabajadores set antiguedad=antiguedad+50, ciudad='Barcelona'
	where dni='1B';



-- incrementar pagaextra a trabajadores que tengan una antiguedad igual a teresa

select antiguedad from trabajadores where nombre='Teresa';

update trabajadores set pagaextra = pagaextra+100
 where antiguedad = 8;


