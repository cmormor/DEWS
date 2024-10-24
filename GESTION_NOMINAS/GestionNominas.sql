
CREATE DATABASE GestionNominas;

use GestionNominas;

CREATE TABLE Empleados (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sexo CHAR(1) CHECK (sexo IN ('M', 'F')),
    categoria INT CHECK (categoria BETWEEN 1 AND 10),
    anyos_trabajados INT CHECK (anyos_trabajados >= 0)
);

CREATE TABLE Nominas (
    dni VARCHAR(9) PRIMARY KEY,
    sueldo INT,
    FOREIGN KEY (dni) REFERENCES Empleados(dni) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Empleados (nombre, dni, sexo, categoria, anyos_trabajados)
VALUES 
    ('Ale', '32000032G', 'M', 4, 7),
    ('Ari', '32000031R', 'F', 1, 1),
    ('Angel', '32000030K', 'M', 2, 5),
    ('Pilar', '32000029C', 'F', 3, 9),
    ('Carmen', '32000028A', 'F', 5, 10),
    ('Sandro', '32000027B', 'M', 6, 8),
    ('Javi', '32000026D', 'M', 2, 4),
    ('Cristian', '32000025E', 'M', 7, 6),
    ('Paula', '32000024F', 'F', 10, 12),
    ('Alvaro', '32000023H', 'M', 8, 3);

INSERT INTO Nominas (dni, sueldo)
VALUES 
    ('32000032G', 145000),
    ('32000031R', 55000),
    ('32000030K', 70000),
    ('32000029C', 90000),
    ('32000028A', 160000),
    ('32000027B', 120000),
    ('32000026D', 80000),
    ('32000025E', 95000),
    ('32000024F', 200000),
    ('32000023H', 110000);

SELECT * FROM Empleados;

SELECT * FROM Nominas;
