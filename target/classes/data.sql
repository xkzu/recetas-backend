-- Datos de ejemplo para la tabla usuario

INSERT INTO usuario (nombre_usuario, nombre_completo, email, contrasena, rol) VALUES
    ('alvaro', 'Álvaro Vega', 'alvaro.vegap@duocuc.cl', '1234', 1);

INSERT INTO usuario (nombre_usuario, nombre_completo, email, contrasena, rol) VALUES
    ('maria', 'Maria Gómez', 'maria@example.com', '1234', 0);

INSERT INTO usuario (nombre_usuario, nombre_completo, email, contrasena, rol) VALUES
    ('juan', 'Juan López', 'juan@example.com', 'password', 1);

-- Recetas de Argentina
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Asado Argentino', 'Parrilla', 'Carne de res, sal gruesa, chimichurri', 'Argentina', 'Medio', 95, 'Preparar la parrilla y asar la carne a fuego lento', 120, 4, '/images/asado_argentino.jpg', '2024-11-01 12:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Empanadas de Carne', 'Horno', 'Carne molida, cebolla, huevo duro, aceitunas, especias', 'Argentina', 'Fácil', 80, 'Preparar el relleno y hornear las empanadas', 60, 4, '/images/empanadas_argentinas.jpg', '2024-11-01 12:30:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Milanesa a la Napolitana', 'Frito', 'Milanesa de carne, jamón, queso, tomate, orégano', 'Argentina', 'Medio', 75, 'Freír la milanesa y añadir los ingredientes', 30, 2, '/images/milanesa_napolitana.jpg', '2024-11-01 13:00:00', 1);

-- Recetas de Uruguay
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Chivito Uruguayo', 'Sándwich', 'Pan, lomo de ternera, jamón, queso, tomate, lechuga, huevo, mayonesa', 'Uruguay', 'Fácil', 90, 'Armar el sándwich con los ingredientes listos', 20, 1, '/images/chivito_uruguayo.jpg', '2024-11-01 14:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Asado con Cuero', 'Parrilla', 'Carne con cuero, sal gruesa', 'Uruguay', 'Difícil', 85, 'Cocinar la carne con cuero a fuego lento durante varias horas', 240, 8, '/images/asado_con_cuero.jpg', '2024-11-01 15:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Pancho', 'Rápido', 'Pan de hot dog, salchicha, salsas (ketchup, mayonesa, mostaza), papas fritas', 'Uruguay', 'Fácil', 60, 'Cocinar la salchicha y armar el pancho', 10, 1, '/images/pancho_uruguayo.jpg', '2024-11-01 16:00:00', 1);

-- Recetas de Brasil
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Feijoada', 'Guiso', 'Frijoles negros, carne de cerdo, chorizo, arroz, naranjas', 'Brasil', 'Difícil', 100, 'Cocinar los frijoles y la carne lentamente en una olla', 180, 6, '/images/feijoada.jpg', '2024-11-01 17:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Moqueca de Camarones', 'Estofado', 'Camarones, leche de coco, cebolla, pimientos, tomate, cilantro', 'Brasil', 'Medio', 85, 'Cocinar los camarones con leche de coco y vegetales', 45, 4, '/images/moqueca_camarones.jpg', '2024-11-01 18:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Brigadeiros', 'Postre', 'Leche condensada, chocolate en polvo, mantequilla, chispas de chocolate', 'Brasil', 'Fácil', 70, 'Cocinar la mezcla y hacer bolitas, luego decorar', 20, 15, '/images/brigadeiros.jpg', '2024-11-01 19:00:00', 1);

-- Recetas de Chile
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Pastel de Choclo', 'Horno', 'Choclo, carne molida, cebolla, huevo duro, aceitunas, pasas', 'Chile', 'Medio', 90, 'Preparar la mezcla y hornear en cazuelas', 90, 4, '/images/pastel_choclo.jpg', '2024-11-01 20:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Cazuela', 'Guiso', 'Pollo o carne de res, papa, choclo, zapallo, fideos, cilantro', 'Chile', 'Fácil', 80, 'Hervir los ingredientes en una olla', 60, 4, '/images/cazuela.jpg', '2024-11-01 21:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Completo Italiano', 'Rápido', 'Pan de hot dog, salchicha, tomate, palta, mayonesa', 'Chile', 'Fácil', 75, 'Armar el completo con los ingredientes', 10, 1, '/images/completo_italiano.jpg', '2024-11-01 22:00:00', 1);

-- Recetas de Paraguay
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Sopa Paraguaya', 'Horno', 'Maíz, queso, cebolla, leche, huevos', 'Paraguay', 'Medio', 80, 'Mezclar ingredientes y hornear hasta dorar', 60, 8, '/images/sopa_paraguaya.jpg', '2024-11-01 23:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Chipa', 'Horno', 'Harina de maíz, queso, huevos, leche, manteca', 'Paraguay', 'Medio', 70, 'Amasar y hornear hasta que estén doradas', 45, 6, '/images/chipa.jpg', '2024-11-02 00:00:00', 1);
INSERT INTO receta (nombre, tipo_cocina, ingredientes, pais_origen, dificultad, popularidad, instrucciones_preparacion, tiempo_coccion, porciones, fotografia_url, fecha_creacion, id_usuario) VALUES
    ('Mbeju', 'Sartén', 'Almidón de mandioca, queso, manteca, leche', 'Paraguay', 'Fácil', 65, 'Cocinar en sartén hasta dorar', 30, 4, '/images/mbeju.jpg', '2024-11-02 01:00:00', 1);


INSERT INTO banner (empresa_nombre, mensaje, url_imagen, link_website, fecha_creacion) VALUES
                                                                                           ('TechCorp', 'Aprovecha nuestras últimas ofertas en tecnología', '/images/techcorp.jpg', 'https://www.techcorp.com', '2024-11-01 10:00:00'),
                                                                                           ('EcoGreen', 'Cuidamos el planeta con nuestros productos ecoamigables', '/images/ecogreen.jpg', 'https://www.ecogreen.com', '2024-11-01 11:00:00'),
                                                                                           ('AutoPlus', 'Encuentra el auto de tus sueños al mejor precio', '/images/autoplus.jpg', 'https://www.autoplus.com', '2024-11-01 12:00:00');

INSERT INTO comentarios (receta_id, usuario_id, contenido, fecha_creacion, visible)
VALUES (1, 1, '¡Excelente receta! Muy fácil de seguir y deliciosa.', '2024-11-18 10:30:00', true);

INSERT INTO comentarios (receta_id, usuario_id, contenido, fecha_creacion, visible)
VALUES (2, 3, 'Me encantó, aunque agregué un poco más de especias.', '2024-11-18 11:45:00', true);

INSERT INTO comentarios (receta_id, usuario_id, contenido, fecha_creacion, visible)
VALUES (1, 2, 'No tuve los mismos resultados, pero puede ser error mío.', '2024-11-18 12:15:00', true);

INSERT INTO valoraciones (receta_id, usuario_id, puntaje)
VALUES (1, 1, 5);

INSERT INTO valoraciones (receta_id, usuario_id, puntaje)
VALUES (2, 3, 4);

INSERT INTO valoraciones (receta_id, usuario_id, puntaje)
VALUES (3, 2, 3);
