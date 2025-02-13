-- Crear base de datos (opcional)
CREATE DATABASE IF NOT EXISTS mi_tienda_springboot;
USE mi_tienda_springboot;

-- Tabla Cliente
CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    nickname VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(15),
    domicilio VARCHAR(100)
);

-- Tabla Producto
CREATE TABLE Producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    tipo_producto VARCHAR(100) NOT NULL DEFAULT 'estandar',
    stock INT
);

-- Tabla intermedia Compra
CREATE TABLE Compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    producto_id INT NOT NULL,
    fecha_compra TEXT NOT NULL,
    cantidad TEXT NOT NULL,
    precio_compra INT NOT NULL,

    -- Definir claves foráneas para relacionar Cliente y Producto
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE
);

CREATE TABLE Devoluciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    compra_id INT NOT NULL,
    stock INT NOT NULL,

    -- Definir claves foráneas
    FOREIGN KEY (compra_id) REFERENCES Compra(id) ON DELETE CASCADE
);

-- Insertar clientes
INSERT INTO Cliente (nombre, apellido, nickname, password, telefono, domicilio) VALUES
('Carlos', 'González', 'carlosg', 'pass123', '123456789', 'Calle 123'),
('Ana', 'Pérez', 'anap', 'pass456', '987654321', 'Avenida 456'),
('Luis', 'Martínez', 'luism', 'pass789', '555555555', 'Boulevard 789');

-- Insertar productos
INSERT INTO Producto (nombre, descripcion, precio, tipo_producto, stock) VALUES
                                                                             ( 'Laptop', 'Laptop de alto rendimiento', 1500.00, 'calidad', 50),
                                                                             ( 'Teléfono', 'Smartphone de última generación', 800.00, 'calidad', 50),
                                                                             ( 'Tablet', 'Tablet con pantalla HD', 300.00, 'calidad', 50),
                                                                             ( 'Auriculares', 'Auriculares inalámbricos', 100.00, 'estandar', 50),
                                                                             ( 'Cámara', 'Cámara profesional', 1200.00, 'calidad', 50),
                                                                             ( 'Monitor', 'Monitor 4K', 400.00, 'calidad', 50),
                                                                             ( 'Teclado', 'Teclado mecánico', 80.00, 'estandar', 50),
                                                                             ( 'Mouse', 'Mouse óptico', 10.00, 'oferta', 50),
                                                                             ( 'Impresora', 'Impresora multifuncional', 200.00, 'calidad', 50),
                                                                             ( 'Parlantes', 'Parlantes Bluetooth', 150.00, 'estandar', 50);
