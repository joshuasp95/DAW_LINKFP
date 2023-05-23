/** Ejemplo**/
create database ejemplo;
use ejemplo;

create table modulo(
codigo varchar(20) primary key,
nombre varchar(20),
aula varchar(20)
);

insert into modulo values ('1','M01','A');
insert into modulo values ('2','M02','B');
insert into modulo values ('3','M04','C');

create table profesor(
dni char(9) primary key,
nombre varchar(20),
fechaAlta date
);

insert into profesor values ('1A','Juan','03-08-20');
insert into profesor values ('2B','Pedro','20-12-15');
insert into profesor values ('3C','Javier','15-2-08');

select*from modulo;
select*from profesor;

/**
update profesor set fechaAlta = '20-12-15' where dni = '2B';

alter table modulo add profesordni char(9);
-- alter table modulo drop profedordni; --

alter table modulo add constraint fk_modulo_profesor foreign key (profesordni) references profesor(dni); */


select nombre as 'Nombre del modulo', aula, nombre from modulo inner join profesor on dni = profesordni;

select nombre as 'M.' from modulo;
select nombre as 'P.' from profesor;

select*from modulo;
select*from profesor;

alter table profesor change nombre nomb varchar(20);

select nombre, aula, nomb from modulo inner join profesor on dni = profesordni;

update modulo set profesordni = '1A' where codigo = '2';
update modulo set profesordni = '2B' where codigo = '3';
update modulo set profesordni = '3C' where codigo = '1';

select nombre, aula, nomb from modulo inner join profesor on dni = profesordni;

select nomb, fechaAlta from profesor;

select nombre, nomb, aula, fechaAlta from modulo inner join profesor on dni = profesordni;

use ejemplo;