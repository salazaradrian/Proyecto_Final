CREATE DATABASE Proyecto_SGV;
USE Proyecto_SGV;

CREATE TABLE Administrador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL
);

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL
);

CREATE TABLE Vehiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    placa VARCHAR(20) NOT NULL,
    ubicacion VARCHAR(100),
    usuario_id INT NOT NULL,
    cargamaxima INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id)
);

CREATE TABLE Camion (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Vehiculo(id)
);

CREATE TABLE Moto (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Vehiculo(id)
);

CREATE TABLE Poliza (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numeropoliza INT NOT NULL,
    vencimiento DATE NOT NULL,
    estado INT NOT NULL
);

CREATE TABLE SolicitudPieza (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pieza VARCHAR(100) NOT NULL,
    estado INT NOT NULL,
    cantidad INT NOT NULL
);

CREATE TABLE Mecanico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(100)
);

CREATE TABLE OrdenReparacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    orden INT NOT NULL,
    estado VARCHAR(50) NOT NULL,
    vehiculo_id INT NOT NULL,
    FOREIGN KEY (vehiculo_id) REFERENCES Vehiculo(id)
);

CREATE TABLE Ruta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destino VARCHAR(100) NOT NULL,
    fechaasignada DATE NOT NULL,
    fechafinalizada DATE,
    estado INT NOT NULL,
    usuario_id INT NOT NULL,
    vehiculo_id INT NOT NULL,
    carga INT NOT NULL,
    tipocarga ENUM('Ligera', 'EnRegla', 'Pesada') NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (vehiculo_id) REFERENCES Vehiculo(id)
);

