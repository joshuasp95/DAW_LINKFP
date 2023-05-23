create database ARTISTAS;
use ARTISTAS;

-- Genero:{nombre(pk),comentario}
-- Ciudad:{nombre(pk),numHabitantes,rentaCapita}
-- Artista:{dni(pk),nombre,apellidos,direccion,fechaNacimiento}
-- BandaSonora:{nombre(pk),director, numCopias,fechaPublicacion}
-- Temas:{nombre(pk), duracion}
-- Peliculas:{nombre(pk),director,duracion,numArtista,artistaDNI(fk), ciudadNombre(fk),generoNombre(fk), bandaSonoraNombre(fk)}
-- Actuar:{artistaDNI(pk,fk),peliculaNombre(pk,fk)}
-- Formada:{bandaSonoraNombre(pk,fk),temasNombre(pk,fk)}--

create table genero(
	nombre varchar(20) primary key,
	comentario varchar(200)
);

create table ciudad(
	nombre varchar(30) primary key,
    numhabitantes int,
    rentacapita smallint
);

create table artista(
DNI char(9) primary key,
nombre varchar(20),
apellidos varchar(40),
direccion varchar(30),
fechanacimiento date
);

create table BSO(
nombre varchar(20) primary key,
director varchar(30),
numcopias int,
fechapublicacion date
);

create table temas(
nombre varchar(40) primary key,
duracion smallint
);

-- Peliculas:{nombre(pk),director,duracion,numArtista,artistaDNI(fk), ciudadNombre(fk),generoNombre(fk), bandaSonoraNombre(fk)}

create table peliculas(
nombre varchar(40) primary key,
director varchar(30),
duracion smallint,
numartistas tinyint,
artistaDNI char(9), -- fk artista
ciudadnombre varchar(30), -- fk ciudad
generonombre varchar(20), -- fk genero
BSOnombre varchar(20), -- fk BSO
constraint fk_peliculas_artista foreign key(artistaDNI) references artista(DNI),
constraint fk_peliculas_ciudad foreign key(ciudadnombre) references ciudad(nombre),
constraint fk_peliculas_genero foreign key(generonombre) references genero(nombre),
constraint fk_peliculas_BSO foreign key(BSOnombre) references BSO(nombre)
);

-- Actuar:{artistaDNI(pk,fk),peliculaNombre(pk,fk)}
create table actuar(
artistaDNI char(9), -- fk artista,
peliculasnombre varchar(40), -- fk peliculas
primary key(artistaDNI,peliculasnombre),
constraint fk_actuar_artista foreign key(artistaDNI) references artista(DNI),
constraint fk_actuar_peliculas foreign key(peliculasnombre) references peliculas(nombre)
);

-- Formada:{bandaSonoraNombre(pk,fk),temasNombre(pk,fk)}-
create table formada(
BSOnombre varchar(20), -- fk BSO
temasnombre varchar(40), -- fk temas
primary key(BSOnombre,temasnombre),
constraint fk_formada_BSO foreign key(BSOnombre) references BSO(nombre),
constraint fk_formada_temas foreign key(temasnombre) references temas(nombre)
);

-- mostrar indices de una tabla

show index from peliculas;

-- crear indices de una tabla
-- se hacen muchas consultas sobre nombre y primer apellido del artista

create index indicenartistanombre on artista(nombre);

create fulltext index indiceartista_apellidos on artista(apellidos);

-- mostrar indices artista

show index from artista;

-- borrar indices 
drop index indiceartistaapellidos on artista