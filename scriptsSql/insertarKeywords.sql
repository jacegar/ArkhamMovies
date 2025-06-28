-- 1. Insertar keywords
INSERT INTO keyword (name) VALUES
('exploración'),
('construcción'),
('supervivencia'),
('herramientas'),
('corrupción'),
('alianza'),
('viaje'),
('naturaleza'),
('energía'),
('entrenamiento'),
('profecía'),
('frontera'),
('encierro'),
('vigilancia'),
('manipulación'),
('control'),
('operación encubierta'),
('transformación'),
('liminalidad'),
('pérdida'),
('ritual'),
('percepción'),
('ilusión'),
('niveles'),
('relatividad'),
('tiempo dilatado'),
('vacío'),
('anarquía'),
('miedo'),
('moralidad'),
('desigualdad'),
('invasión'),
('aspiración'),
('engaño'),
('videojuego');

-- Suponiendo que las películas ya están insertadas y tienen ids del 1 al 10,
-- en el orden:
-- 1 = Minecraft: the movie
-- 2 = La comunidad del anillo
-- 3 = Dragon Ball Evolution
-- 4 = Ceuta, prison by the sea
-- 5 = Thunderbolts*
-- 6 = El viaje de Chihiro
-- 7 = Inception
-- 8 = Interstellar
-- 9 = The Dark Knight
-- 10 = Parasite

-- 2. Limpiar la tabla moviekeyword antes de insertar nuevos datos
DELETE FROM moviekeyword WHERE movie_id >= 1;

-- 3. Asociar keywords con películas

INSERT INTO moviekeyword (keyword_id, movie_id) VALUES
-- Minecraft: the movie
(1, 1),  -- exploración
(2, 1),  -- construcción
(3, 1),  -- supervivencia
(4, 1),  -- herramientas
(7, 1),  -- viaje
(8, 1),  -- naturaleza
(9, 1),  -- energía
(18, 1), -- transformación
(35, 1), -- videojuego

-- La comunidad del anillo
(1, 2),  -- exploración
(5, 2),  -- corrupción
(6, 2),  -- alianza
(7, 2),  -- viaje
(8, 2),  -- naturaleza
(11, 2), -- profecía
(18, 2), -- transformación
(20, 2), -- ritual
(21, 2), -- percepción
(25, 2), -- tiempo dilatado
(31, 2), -- aspiración

-- Dragon Ball Evolution
(9, 3),  -- energía
(10, 3), -- entrenamiento
(11, 3), -- profecía
(18, 3), -- transformación
(20, 3), -- ritual
(21, 3), -- percepción
(30, 3), -- moralidad
(35, 3), -- videojuego

-- Ceuta, prison by the sea
(12, 4), -- frontera
(13, 4), -- encierro
(14, 4), -- vigilancia
(15, 4), -- manipulación
(16, 4), -- control
(19, 4), -- liminalidad
(28, 4), -- miedo
(30, 4), -- moralidad
(31, 4), -- desigualdad
(33, 4), -- engaño

-- Thunderbolts*
(6, 5),  -- alianza
(15, 5), -- manipulación
(16, 5), -- control
(17, 5), -- operación encubierta
(18, 5), -- transformación
(27, 5), -- anarquía
(28, 5), -- miedo
(29, 5), -- moralidad
(30, 5), -- desigualdad

-- El viaje de Chihiro
(7, 6),  -- viaje
(8, 6),  -- naturaleza
(18, 6), -- transformación
(19, 6), -- liminalidad
(20, 6), -- ritual
(21, 6), -- percepción
(22, 6), -- ilusión
(30, 6), -- moralidad

-- Inception
(21, 7), -- percepción
(22, 7), -- ilusión
(23, 7), -- niveles
(24, 7), -- relatividad
(25, 7), -- tiempo dilatado
(26, 7), -- vacío
(33, 7), -- engaño
(30, 7), -- moralidad

-- Interstellar
(1, 8),  -- exploración
(7, 8),  -- viaje
(8, 8),  -- naturaleza
(24, 8), -- relatividad
(25, 8), -- tiempo dilatado
(26, 8), -- vacío
(27, 8), -- anarquía
(28, 8), -- miedo
(31, 8), -- aspiración

-- The Dark Knight
(15, 9), -- manipulación
(16, 9), -- control
(17, 9), -- operación encubierta
(27, 9), -- anarquía
(28, 9), -- miedo
(29, 9), -- moralidad
(30, 9), -- desigualdad
(33, 9), -- engaño

-- Parasite
(14, 10), -- vigilancia
(15, 10), -- manipulación
(16, 10), -- control
(28, 10), -- miedo
(29, 10), -- moralidad
(30, 10), -- desigualdad
(32, 10), -- invasión
(33, 10), -- engaño
(34, 10); -- aspiración
