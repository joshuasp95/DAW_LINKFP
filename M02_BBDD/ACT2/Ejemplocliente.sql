CREATE TABLE Trabajador(
	DNI CHAR(9),
    nombre VARCHAR(20),
    apellidos VARCHAR(50),
    fechaNacimiento DATE,
    direccion CHAR(5),
    poblacion VARCHAR(20),
    telefono CHAR(9),
    salario DECIMAL(6,2), -- 9999.99
    estadoCivil VARCHAR(20),
    sexo ENUM('Masculino', 'Femenino', 'Otros'),
    numHijos TINYINT,
    PRIMARY KEY(DNI)


);