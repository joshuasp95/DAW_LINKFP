create database if not exists EJ4_M07_PHP;
USE EJ4_M07_PHP;
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    pass VARCHAR(255),
    tipo_usuario TINYINT
);
CREATE TABLE IF NOT EXISTS proyecto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS tarea (
    fk_proyecto INT,
    fk_usuario INT,
    nombre VARCHAR(255),
    estado TINYINT,
    PRIMARY KEY (fk_proyecto , fk_usuario),
    FOREIGN KEY (fk_proyecto)
        REFERENCES proyecto (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_usuario)
        REFERENCES usuario (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

insert into usuario(nombre, pass, tipo_usuario)
values ('John_Doe', '12345', 1);

insert into usuario(nombre, pass, tipo_usuario)
values ('Admin', '1234', 0);

insert into usuario(nombre, pass, tipo_usuario)
values ('Neo', '9999', 0);

insert into proyecto(nombre) values ('M02 Gestion de BBDD');
insert into proyecto(nombre) values ('M04 Lenguaje de Marcas');
insert into proyecto(nombre) values ('M07 Lenguajes de Servidor(PHP)');
insert into proyecto(nombre) values ('M06 Lenguajes Frontend(JS)');
insert into tarea
values (1, 2, "Hacer un BackUP de la BBDD", 1);

insert into tarea
values (2, 1, "Maquetar una PokeWeb", 2);

insert into tarea
values (4, 3, "Crear Snake Game en JS", 3);

insert into tarea
values (1, 3, "Realiza el Diagrama ER de una BBDD", 1);

CREATE OR REPLACE VIEW vista_tareas AS
    SELECT 
        proyecto.nombre AS Proyecto,
        usuario.nombre AS Usuario,
        tarea.nombre AS Tarea,
        tarea.estado AS Estado
    FROM
        tarea
            INNER JOIN
        proyecto ON proyecto.id = tarea.fk_proyecto
            INNER JOIN
        usuario ON usuario.id = tarea.fk_usuario
    ORDER BY proyecto.nombre;

