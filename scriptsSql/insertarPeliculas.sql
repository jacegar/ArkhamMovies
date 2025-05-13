INSERT INTO movie (title, popularity, runtime, budget, revenue, overview, release_date, status, homepage, tagline, photo_url)
VALUES
    ('Minecraft: the movie', 100, 115, 20000000, 50000000, 'Un aventurero y su grupo deben salvar su mundo de bloques enfrentándose a las fuerzas del Nether que amenazan con destruir todo lo que conocen.', '2025-04-04 00:00:00', 'Estrenada', 'https://www.minecraft.net', 'Construye tu propia aventura.', 'https://image.tmdb.org/t/p/original/rZYYmjgyF5UP1AVsvhzzDOFLCwG.jpg'),

    ('La comunidad del anillo', 99, 178, 93000000, 897000000, 'Un hobbit debe emprender un peligroso viaje para destruir un anillo maligno que podría condenar a toda la Tierra Media a la oscuridad eterna.', '2001-12-19 00:00:00', 'Estrenada', 'https://www.lordoftherings.net', 'Un anillo para gobernarlos a todos.', 'https://image.tmdb.org/t/p/original/9xtH1RmAzQ0rrMBNUMXstb2s3er.jpg'),

    ('Dragon Ball Evolution', 55, 100, 30000000, 57000000, 'Goku descubre su destino como defensor de la Tierra y debe reunir a sus amigos para enfrentarse a poderosas fuerzas oscuras que amenazan su mundo.', '2009-04-10 00:00:00', 'Estrenada', 'N/A', 'El destino del mundo está en sus manos.', 'https://image.tmdb.org/t/p/original/wMCD0CYAFlVxbP33Xgh7sexhRkC.jpg'),

    ('Ceuta, prison by the sea', 60, 95, 500000, 1200000, 'Un retrato íntimo de la vida en un centro de internamiento para inmigrantes en Ceuta, una ciudad española en la costa de África, rodeada por el mar y la incertidumbre.', '2014-01-29 00:00:00', 'Estrenada', 'N/A', 'La frontera del sueño europeo.', 'https://static.filmin.es/images/es/media/11469/2/poster_0_3.webp'),

    ('Thunderbolts*', 100, 127, 180000000, 271301744, 'Un grupo de supervillanos poco convencional es reclutado para hacer misiones para el gobierno. Después de verse atrapados en una trampa mortal, estos marginados deben embarcarse en una peligrosa misión que les obligará a enfrentarse a los recovecos más oscuros de su pasado.', '2025-04-30 00:00:00', 'Estrenada', 'https://www.marvel.com/movies/thunderbolts', 'La venganza nunca fue tan divertida.', 'https://a.ltrbxd.com/resized/film-poster/8/8/7/6/5/3/887653-thunderbolts-0-1000-0-1500-crop.jpg?v=80380bc773'),

    ('El viaje de Chihiro', 98, 125, 19000000, 395000000, 'Una niña queda atrapada en un mundo mágico gobernado por espíritus y dioses, donde debe encontrar la forma de rescatar a sus padres y volver al mundo real.', '2001-07-20 00:00:00', 'Estrenada', 'https://www.studioghibli.com.au/spirited-away/', 'Nada de lo que ves es lo que parece.', 'https://image.tmdb.org/t/p/original/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg'),

    ('Inception', 97, 148, 160000000, 836800000, 'Un ladrón especializado en extraer secretos del subconsciente es contratado para realizar la tarea inversa: implantar una idea.', '2010-07-16 00:00:00', 'Estrenada', 'https://www.warnerbros.com/movies/inception', 'Tu mente es la escena del crimen.', 'https://www.themoviedb.org/t/p/w1280/tXQvtRWfkUUnWJAn2tN3jERIUG.jpg'),

    ('Interstellar', 95, 169, 165000000, 701000000, 'Un grupo de exploradores viaja a través de un agujero de gusano en el espacio con la esperanza de asegurar el futuro de la humanidad.', '2014-11-07 00:00:00', 'Estrenada', 'N/A', 'El fin de la Tierra no será nuestro fin.', 'https://image.tmdb.org/t/p/original/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg'),

    ('The Dark Knight', 94, 152, 185000000, 1005000000, 'Batman se enfrenta a su mayor reto cuando el Joker emerge para sembrar el caos en Gotham. El Caballero Oscuro debe decidir hasta dónde está dispuesto a llegar.', '2008-07-18 00:00:00', 'Estrenada', 'https://www.warnerbros.com/movies/dark-knight', '¿Por qué tan serio?', 'https://image.tmdb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg'),

    ('Parasite', 92, 132, 11400000, 258000000, 'Toda la familia de Ki-taek está desempleada y se interesa mucho por la adinerada familia Park. Pero pronto se enredan en una serie de incidentes inesperados.', '2019-05-30 00:00:00', 'Estrenada', 'N/A', 'Actúa como si pertenecieras allí.', 'https://image.tmdb.org/t/p/original/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg');


INSERT INTO genre (name)
VALUES
    ('Acción'),
    ('Aventura'),
    ('Animación'),
    ('Ciencia ficción'),
    ('Comedia'),
    ('Crimen'),
    ('Documental'),
    ('Drama'),
    ('Fantasía'),
    ('Historia'),
    ('Misterio'),
    ('Romance'),
    ('Suspenso'),
    ('Terror'),
    ('Musical'),
    ('Bélica'),
    ('Western'),
    ('Familia');

-- Minecraft: the movie → Aventura, Animación, Fantasía
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'Minecraft: the movie' AND g.name IN ('Aventura', 'Animación', 'Fantasía');

-- La comunidad del anillo → Aventura, Fantasía
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'La comunidad del anillo' AND g.name IN ('Aventura', 'Fantasía');

-- Dragon Ball Evolution → Acción, Ciencia ficción
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'Dragon Ball Evolution' AND g.name IN ('Acción', 'Ciencia ficción');

-- Ceuta, prison by the sea → Documental, Drama
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'Ceuta, prison by the sea' AND g.name IN ('Documental', 'Drama');

-- Thunderbolts* → Acción, Ciencia ficción, Suspenso
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'Thunderbolts*' AND g.name IN ('Acción', 'Ciencia ficción', 'Suspenso');

-- El viaje de Chihiro → Animación, Fantasía, Familia
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'El viaje de Chihiro' AND g.name IN ('Animación', 'Fantasía', 'Familia');

-- Inception → Acción, Ciencia ficción, Suspenso, Misterio
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'Inception' AND g.name IN ('Acción', 'Ciencia ficción', 'Suspenso', 'Misterio');

-- Interstellar → Ciencia ficción, Drama, Aventura
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'Interstellar' AND g.name IN ('Ciencia ficción', 'Drama', 'Aventura');

-- The Dark Knight → Acción, Crimen, Suspenso
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'The Dark Knight' AND g.name IN ('Acción', 'Crimen', 'Suspenso');

-- Parasite → Drama, Suspenso, Misterio
INSERT INTO moviegenre (genre_id, movie_id)
SELECT g.genre_id, m.movie_id
FROM movie m, genre g
WHERE m.title = 'Parasite' AND g.name IN ('Drama', 'Suspenso', 'Misterio');