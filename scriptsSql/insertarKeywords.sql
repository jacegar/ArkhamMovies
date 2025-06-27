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

-- 2. Asociar keywords con películas

INSERT INTO moviekeyword (keyword_id, movie_id) VALUES
-- Minecraft: the movie (keywords 1 a 4, y la 35)
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(35, 1),

-- La comunidad del anillo (keywords 5 a 8)
(5, 2),
(6, 2),
(7, 2),
(8, 2),

-- Dragon Ball Evolution (keywords 9 a 11)
(9, 3),
(10, 3),
(11, 3),

-- Ceuta, prison by the sea (keywords 12 a 14)
(12, 4),
(13, 4),
(14, 4),

-- Thunderbolts* (keywords 15 a 17)
(15, 5),
(16, 5),
(17, 5),

-- El viaje de Chihiro (keywords 18 a 21)
(18, 6),
(19, 6),
(20, 6),
(21, 6),

-- Inception (keywords 22 a 24)
(22, 7),
(23, 7),
(24, 7),

-- Interstellar (keywords 25 a 27)
(25, 8),
(26, 8),
(27, 8),

-- The Dark Knight (keywords 28 a 30)
(28, 9),
(29, 9),
(30, 9),

-- Parasite (keywords 31 a 34)
(31, 10),
(32, 10),
(33, 10),
(34, 10);
