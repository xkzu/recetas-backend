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
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario BIGINT NOT NULL
    );


CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre_usuario VARCHAR(255) UNIQUE NOT NULL,
                         nombre_completo VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         contrasena VARCHAR(255) NOT NULL,
                         rol BOOLEAN DEFAULT FALSE
);

ALTER TABLE receta
    ADD CONSTRAINT fk_receta_usuario
        FOREIGN KEY (id_usuario)
            REFERENCES usuario (id);


CREATE UNIQUE INDEX usuario_nombre_usuario_uindex ON usuario (nombre_usuario);
CREATE UNIQUE INDEX usuario_email_uindex ON usuario (email);


CREATE TABLE IF NOT EXISTS banner (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      empresa_nombre VARCHAR(255) NOT NULL,
    mensaje VARCHAR(1000) NOT NULL,
    url_imagen VARCHAR(255),
    link_website VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

