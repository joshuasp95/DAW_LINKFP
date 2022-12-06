create database if not exists EJ3_M07_PHP;

USE EJ3_M07_PHP;


create table if not exists usuario(
id int auto_increment primary key,
nombre varchar(255),
pass varchar(255),
tipo_usuario tinyint
);

create table if not exists proyecto(
id int primary key,
nombre varchar(255)
);

create table if not exists tarea(
fk_proyecto int,
fk_usuario int,
nombre varchar(255),
estado tinyint,
primary key(fk_proyecto, fk_usuario),
foreign key (fk_proyecto) references proyecto(id) on delete cascade on update cascade,
foreign key (fk_usuario) references usuario(id) on delete cascade on update cascade
);
