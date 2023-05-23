

--1	Crear un tablespace de 400MB llamado empresa donde se almacenarán todos los datos.

create tablespace empresa 
datafile 'empresa.dbf'
size 400M
autoextend on;