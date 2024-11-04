CREATE TABLE IF NOT EXISTS receta (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nombre VARCHAR(255) NOT NULL,
    tipo_cocina VARCHAR(255),
    ingredientes VARCHAR(2000),
    pais_origen VARCHAR(255),
    dificultad VARCHAR(255),
    popularidad INT,
    instrucciones_preparacion VARCHAR(5000),
    tiempo_coccion INT,
    porciones INT,
    fotografia_url VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS usuario (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       nombre_usuario VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol BOOLEAN
    );

CREATE TABLE IF NOT EXISTS banner (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      empresa_nombre VARCHAR(255) NOT NULL,
    mensaje VARCHAR(1000) NOT NULL,
    url_imagen VARCHAR(255),
    link_website VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

