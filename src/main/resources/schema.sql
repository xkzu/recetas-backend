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
    video VARCHAR(255),
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

CREATE TABLE comentarios (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             receta_id BIGINT NOT NULL,
                             usuario_id BIGINT NOT NULL,
                             contenido VARCHAR(500) NOT NULL,
                             fecha_creacion DATETIME NOT NULL,
                             visible BOOLEAN NOT NULL DEFAULT false,
                             CONSTRAINT fk_comentarios_receta
                                 FOREIGN KEY (receta_id) REFERENCES receta(id),
                             CONSTRAINT fk_comentarios_usuario
                                 FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


-- ALTER TABLE comentarios
--     ADD CONSTRAINT fk_comentario_usuario
--         FOREIGN KEY (usuario_id)
--             REFERENCES usuario (id);
--
-- ALTER TABLE comentarios
--     ADD CONSTRAINT fk_receta_usuario
--         FOREIGN KEY (receta_id)
--             REFERENCES receta (id);

CREATE TABLE valoraciones (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              receta_id BIGINT NOT NULL,
                              usuario_id BIGINT NOT NULL,
                              puntaje INT NOT NULL,
                              CONSTRAINT fk_valoraciones_receta
                                  FOREIGN KEY (receta_id) REFERENCES receta(id),
                              CONSTRAINT fk_valoraciones_usuario
                                  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- ALTER TABLE valoraciones
--     ADD CONSTRAINT fk_valoracion_usuario
--         FOREIGN KEY (usuario_id)
--             REFERENCES usuario (id);
--
-- ALTER TABLE valoraciones
--     ADD CONSTRAINT fk_receta_valoracion
--         FOREIGN KEY (receta_id)
--             REFERENCES receta (id);