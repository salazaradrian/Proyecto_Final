CREATE DATABASE Proyecto_SGV;
USE Proyecto_SGV;

CREATE TABLE inventario_piezas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    totalMotor INT NOT NULL DEFAULT 0,
    totalChasis INT NOT NULL DEFAULT 0,
    totalFrenos INT NOT NULL DEFAULT 0,
    totalCaja INT NOT NULL DEFAULT 0,
    totalFocos INT NOT NULL DEFAULT 0,
    totalLlantas INT NOT NULL DEFAULT 0,
    totalBateria INT NOT NULL DEFAULT 0
);

INSERT INTO inventario_piezas (
    totalMotor, 
    totalChasis, 
    totalFrenos, 
    totalCaja, 
    totalFocos, 
    totalLlantas, 
    totalBateria
) VALUES (
    0, 0, 0, 0, 0, 0, 0
);

-- Table: orden_de_piezas
CREATE TABLE orden_de_piezas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    precio FLOAT NOT NULL,
    cantidadMotor INT NOT NULL,
    cantidadChasis INT NOT NULL,
    cantidadFrenos INT NOT NULL,
    cantidadCaja INT NOT NULL,
    cantidadFocos INT NOT NULL,
    cantidadLlantas INT NOT NULL,
    cantidadBateria INT NOT NULL,
    estado ENUM('Pendiente', 'Completado') NOT NULL
);

-- Table: orden_reparacion
CREATE TABLE orden_reparacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehiculo_id INT NOT NULL,
    descripcion_problema VARCHAR(255) NOT NULL,
    piezas TEXT,
    estado ENUM('EnProceso', 'Finalizado') NOT NULL DEFAULT 'EnProceso',
    precio VARCHAR(45) NOT NULL,
    FOREIGN KEY (vehiculo_id) REFERENCES Vehiculo(id)
);

-- Table: usuario
CREATE TABLE usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    rol ENUM('Mecanico', 'UsuarioGeneral') NOT NULL
);

-- Table: Vehiculo
CREATE TABLE Vehiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    tipoVehiculo ENUM('Carro', 'Motocicleta', 'Camion') NOT NULL,
    modelo VARCHAR(45) NOT NULL
);
