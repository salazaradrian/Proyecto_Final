CREATE DATABASE Proyecto_SGV;
USE Proyecto_SGV;

-- USUARIOS
CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol ENUM('UsuarioGeneral', 'Mecanico') NOT NULL
);

-- VEHICULOS
CREATE TABLE Vehiculo (
    placa VARCHAR(20) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    tipoVehiculo ENUM('Carro', 'Moto', 'Camion') NOT NULL,
    idUsuario INT,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- ORDEN DE PIEZA
CREATE TABLE OrdenPieza (
    idOrdenPieza INT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    precioTotal FLOAT NOT NULL,
    estado ENUM('Pendiente', 'En Proceso', 'Completada') NOT NULL
);

-- ORDEN DE REPARACIÓN
CREATE TABLE OrdenReparacion (
    idOrdenReparacion INT AUTO_INCREMENT PRIMARY KEY,
    idVehiculo VARCHAR(20),
    precioReparacion DOUBLE,
    descripcionProblema VARCHAR(2500),
    estado ENUM('Pendiente', 'En Proceso', 'Completada') NOT NULL,
    FOREIGN KEY (idVehiculo) REFERENCES Vehiculo(placa)
);

-- CLIENTES
CREATE TABLE Cliente (
    idUsuario INT,
    PRIMARY KEY (idUsuario),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- MECANICOS
CREATE TABLE Mecanico (
    idUsuario INT,
    PRIMARY KEY (idUsuario),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
); 
