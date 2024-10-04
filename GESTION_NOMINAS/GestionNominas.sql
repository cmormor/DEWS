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
VALUES ('James Gosling', '32000032G', 'M', 4, 7);

INSERT INTO Empleados (nombre, dni, sexo, categoria, anyos_trabajados)
VALUES ('Ada Lovelace', '32000031R', 'F', 1, 1);

INSERT INTO Nominas (dni, sueldo)
VALUES ('32000032G', 145000);

INSERT INTO Nominas (dni, sueldo)
VALUES ('32000031R', 55000);

SELECT * FROM Empleados;

SELECT * FROM Nominas;
