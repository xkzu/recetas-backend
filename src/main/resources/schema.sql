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
