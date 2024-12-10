CREATE DATABASE Proyecto_SGV;
USE Proyecto_SGV;

-- Tabla de ESTADOS DE ORDEN
CREATE TABLE EstadoOrden (
    idEstado INT AUTO_INCREMENT PRIMARY KEY,
    estado ENUM('Pendiente', 'En Proceso', 'Completada') NOT NULL
);

-- ROLES
CREATE TABLE Rol (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    tipoRol ENUM('Administrador', 'UsuarioGeneral', 'Mecanico') NOT NULL
);

-- USUARIOS
CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    idRol INT NOT NULL,
    FOREIGN KEY (idRol) REFERENCES Rol(idRol)
);

-- VEHICULOS
CREATE TABLE Vehiculo (
    placa VARCHAR(20) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    tipoVehiculo ENUM('Carro', 'Moto', 'Camion') NOT NULL,
    idUsuario INT,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- CARROS
CREATE TABLE Carro (
    placa VARCHAR(20) PRIMARY KEY,
    FOREIGN KEY (placa) REFERENCES Vehiculo(placa)
);

-- MOTOS
CREATE TABLE Moto (
    placa VARCHAR(20) PRIMARY KEY,
    FOREIGN KEY (placa) REFERENCES Vehiculo(placa)
);

-- CAMION
CREATE TABLE Camion (
    placa VARCHAR(20) PRIMARY KEY,
    FOREIGN KEY (placa) REFERENCES Vehiculo(placa)
);

-- ORDENES
CREATE TABLE Orden (
    idOrden INT AUTO_INCREMENT PRIMARY KEY,
    idEstado INT NOT NULL,
    precio FLOAT NOT NULL,
    FOREIGN KEY (idEstado) REFERENCES EstadoOrden(idEstado)
);

-- PIEZAS
CREATE TABLE Piezas (
    idPieza INT AUTO_INCREMENT PRIMARY KEY,
    nombrePieza ENUM('Motor', 'Chasis', 'Frenos', 'Caja', 'Focos', 'Llantas', 'Bateria') NOT NULL,
    precio FLOAT NOT NULL
);

-- ORDEN DE PIEZA
CREATE TABLE OrdenPieza (
    idOrdenPieza INT AUTO_INCREMENT PRIMARY KEY,
    idOrden INT,
    idPieza INT,
    cantidad INT NOT NULL,
    idEstado INT NOT NULL,
    precioTotal FLOAT NOT NULL,
    FOREIGN KEY (idOrden) REFERENCES Orden(idOrden),
    FOREIGN KEY (idPieza) REFERENCES Piezas(idPieza),
    FOREIGN KEY (idEstado) REFERENCES EstadoOrden(idEstado)
);

-- ORDEN DE REPARACIÓN
CREATE TABLE OrdenReparacion (
    idOrdenReparacion INT AUTO_INCREMENT PRIMARY KEY,
    idOrden INT,
    idEstado INT NOT NULL,
    idVehiculo VARCHAR(20),
    precioReparacion FLOAT NOT NULL,
    descripcionProblema VARCHAR(2500),
    FOREIGN KEY (idOrden) REFERENCES Orden(idOrden),
    FOREIGN KEY (idVehiculo) REFERENCES Vehiculo(placa),
    FOREIGN KEY (idEstado) REFERENCES EstadoOrden(idEstado)
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