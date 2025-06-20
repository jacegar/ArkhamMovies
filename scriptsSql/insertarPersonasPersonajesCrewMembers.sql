-- 1. Insertar personas (actores) con fotos reales
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
-- Interstellar: Matthew McConaughey
('Matthew', 'McConaughey', NULL, 'M', 54, 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Matthew_McConaughey_2019_%2848648344772%29.jpg/1200px-Matthew_McConaughey_2019_%2848648344772%29.jpg'),

-- Inception: Leonardo DiCaprio
('Leonardo', 'DiCaprio', NULL, 'M', 49, 'https://www.lavanguardia.com/peliculas-series/images/profile/1974/11/w300/wo2hJpn04vbtmh0B9utCFdsQhxM.jpg'),

-- The Dark Knight: Christian Bale
('Christian', 'Bale', NULL, 'M', 50, 'https://upload.wikimedia.org/wikipedia/commons/0/0a/Christian_Bale-7837.jpg'),

-- El viaje de Chihiro (doblaje): Rumi Hiiragi
('Rumi', 'Hiiragi', NULL, 'F', 36, 'https://m.media-amazon.com/images/M/MV5BOTE2ZTYxNjgtNGM0MC00ZmUxLTkwOGMtNTY4ZTFjNTg3MWE5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg'),

-- Parasite: Song Kang-ho
('Kang-ho', 'Song', NULL, 'M', 57, 'https://asianwiki.com/images/0/0a/Parasite_%28Korean_Movie%29-a00.jpg'),

-- Minecraft: Jack Black
('Jack', 'Black', NULL, 'M', 55, 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSb4tMugNk2Y5Is0JSpjmW0GKMPBxqZ5ep5jlgOpqQujzegGscOZuZPZnDrLDsWEqkx9uT57ur6DUYQSrUo8up1lA'),

-- Minecraft: Jason Momoa (actor confirmado)
('Jason', 'Momoa', NULL, 'M', 45, 'https://cdn.britannica.com/70/231870-050-299010C8/Jason-Momoa-2021.jpg'),

-- Thunderbolts: Florence Pugh (como Yelena Belova)
('Florence', 'Pugh', NULL, 'F', 29, 'https://m.media-amazon.com/images/M/MV5BZmIxMTFkZTctYzhkZi00MmQ4LThhYzEtMmQwMGZjNzA5MDg0XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg');

-- 2. Insertar personajes con nombres y fotos neutras
-- Cooper en Interstellar
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Joseph', 'Cooper', NULL, 'https://i.pinimg.com/736x/9e/33/bc/9e33bc111098a825398855258421d50c.jpg',
        (SELECT movie_id FROM movie WHERE title = 'Interstellar'),
        (SELECT person_id FROM person WHERE name = 'Matthew' AND surname1 = 'McConaughey'));

-- Cobb en Inception
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Dominick', 'Cobb', NULL, 'https://i.pinimg.com/736x/c8/4f/b1/c84fb117eb5c81a1438a0a2e01f21156.jpg',
        (SELECT movie_id FROM movie WHERE title = 'Inception'),
        (SELECT person_id FROM person WHERE name = 'Leonardo' AND surname1 = 'DiCaprio'));

-- Bruce Wayne en The Dark Knight
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Batman', NULL, NULL, 'https://www.burlingtoncountytimes.com/gcdn/authoring/2013/09/26/NBCO/ghows-PA-e49d36f9-2850-4634-8256-4a7668775292-8cf3995e.jpeg?width=1200&disable=upscale&format=pjpg&auto=webp',
        (SELECT movie_id FROM movie WHERE title = 'The Dark Knight'),
        (SELECT person_id FROM person WHERE name = 'Christian' AND surname1 = 'Bale'));

-- Chihiro en El viaje de Chihiro (voz: Rumi Hiiragi)
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Chihiro', 'Ogino', NULL, 'https://i.redd.it/f4vm9uxg6ue51.png',
        (SELECT movie_id FROM movie WHERE title = 'El viaje de Chihiro'),
        (SELECT person_id FROM person WHERE name = 'Rumi' AND surname1 = 'Hiiragi'));

-- Kim Ki-taek en Parasite
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Kim', 'Ki-taek', NULL, 'https://static.tvtropes.org/pmwiki/pub/images/pac8.png',
        (SELECT movie_id FROM movie WHERE title = 'Parasite'),
        (SELECT person_id FROM person WHERE name = 'Kang-ho' AND surname1 = 'Song'));

-- Steve (personaje genérico de Minecraft): Jack Black
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Steve', NULL, NULL, 'https://a.allegroimg.com/original/1173fd/36df26d347dda51abbe5fca6f612/MINECRAFT-MOVIE-Steve-2025-Plakat-UNIQUE-PREMIUM-Jack-Black-Autograf',
        (SELECT movie_id FROM movie WHERE title = 'Minecraft: the movie'),
        (SELECT person_id FROM person WHERE name = 'Jack' AND surname1 = 'Black'));

-- Gabriel: Jason Momoa
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Garret', 'Garrison', NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBpN3zYZfGDLBn6I2aCsRu1IiNBXYMRodHMMNnxun0-W1gqw6n1_k0oLAnYYMpQFZxv0A&usqp=CAU',
        (SELECT movie_id FROM movie WHERE title = 'Minecraft: the movie'),
        (SELECT person_id FROM person WHERE name = 'Jason' AND surname1 = 'Momoa'));

-- Yelena Belova en Thunderbolts*: Florence Pugh
INSERT INTO moviecharacter (name, surname1, surname2, photo_url, movie_id, person_id)
VALUES ('Yelena', 'Belova', NULL, 'https://i.redd.it/upxttjxx72ze1.jpeg',
        (SELECT movie_id FROM movie WHERE title = 'Thunderbolts*'),
        (SELECT person_id FROM person WHERE name = 'Florence' AND surname1 = 'Pugh'));

-- 3. Insertar crewmembers (directores)
-- Director de Interstellar: Christopher Nolan
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Christopher', 'Nolan', NULL, 'M', 54, 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Christopher_Nolan_Cannes_2018.jpg/1200px-Christopher_Nolan_Cannes_2018.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Interstellar'),
           (SELECT person_id FROM person WHERE name = 'Christopher' AND surname1 = 'Nolan'),
           'Director'
       );

-- Director de Inception: Christopher Nolan
INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Inception'),
           (SELECT person_id FROM person WHERE name = 'Christopher' AND surname1 = 'Nolan'),
           'Director'
       );

-- Director de The Dark Knight: Christopher Nolan
INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'The Dark Knight'),
           (SELECT person_id FROM person WHERE name = 'Christopher' AND surname1 = 'Nolan'),
           'Director'
       );

-- Director de El viaje de Chihiro: Hayao Miyazaki
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Hayao', 'Miyazaki', NULL, 'M', 83, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Hayao_Miyazaki_cropped_1_Hayao_Miyazaki_201211.jpg/1200px-Hayao_Miyazaki_cropped_1_Hayao_Miyazaki_201211.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'El viaje de Chihiro'),
           (SELECT person_id FROM person WHERE name = 'Hayao' AND surname1 = 'Miyazaki'),
           'Director'
       );

-- Director de Parasite: Bong Joon-ho
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Joon-ho', 'Bong', NULL, 'M', 55, 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Bong_Joon-ho_2017.jpg/1200px-Bong_Joon-ho_2017.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Parasite'),
           (SELECT person_id FROM person WHERE name = 'Joon-ho' AND surname1 = 'Bong'),
           'Director'
       );

-- Director de Minecraft: the movie: Jared Hess
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Jared', 'Hess', NULL, 'M', 45, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTO3DgZn1ZT98IWiMMVMcQZbCbSgoS0P4qmQw&s');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Minecraft: the movie'),
           (SELECT person_id FROM person WHERE name = 'Jared' AND surname1 = 'Hess'),
           'Director'
       );

-- Directora de Thunderbolts*: Jake Schreier (cambiado por ser el director oficial)
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Jake', 'Schreier', NULL, 'M', 43, 'https://www.lavanguardia.com/peliculas-series/images/profile/1981/9/w1280/b6KZmRmoaws0qGUZwMIblalJC78.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Thunderbolts*'),
           (SELECT person_id FROM person WHERE name = 'Jake' AND surname1 = 'Schreier'),
           'Director'
       );

-- 4. Insertar crewmembers (guionistas y compositores)
-- Guionista de The Dark Knight: Jonathan Nolan
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Jonathan', 'Nolan', NULL, 'M', 49, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7Ol09Efr2AUa-MefjU3IkgtZPLmRSJSLa4Q&s');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'The Dark Knight'),
           (SELECT person_id FROM person WHERE name = 'Jonathan' AND surname1 = 'Nolan'),
           'Guionista'
       );

-- Compositor de Interstellar y Inception: Hans Zimmer
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Hans', 'Zimmer', NULL, 'M', 67, 'https://upload.wikimedia.org/wikipedia/commons/2/2b/Hans-Zimmer-profile.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Interstellar'),
           (SELECT person_id FROM person WHERE name = 'Hans' AND surname1 = 'Zimmer'),
           'Compositor'
       );

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Inception'),
           (SELECT person_id FROM person WHERE name = 'Hans' AND surname1 = 'Zimmer'),
           'Compositor'
       );

-- Compositor de El viaje de Chihiro: Joe Hisaishi
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Joe', 'Hisaishi', NULL, 'M', 74, 'https://upload.wikimedia.org/wikipedia/commons/6/65/Joe_Hisaishi_2011.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'El viaje de Chihiro'),
           (SELECT person_id FROM person WHERE name = 'Joe' AND surname1 = 'Hisaishi'),
           'Compositor'
       );

-- Director de fotografía de Parasite: Hong Kyung-pyo
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Kyung-pyo', 'Hong', NULL, 'M', 55, 'https://m.media-amazon.com/images/M/MV5BZjc1NjdjYTUtNzRhZi00YzBmLWIzNWQtMWFmNzMyZGY0ZWZhXkEyXkFqcGc@._V1_.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Parasite'),
           (SELECT person_id FROM person WHERE name = 'Kyung-pyo' AND surname1 = 'Hong'),
           'Director de fotografía'
       );

-- Productor de Minecraft: the movie: Roy Lee
INSERT INTO person (name, surname1, surname2, gender, age, photo_url) VALUES
    ('Roy', 'Lee', NULL, 'M', 55, 'https://m.media-amazon.com/images/M/MV5BZjAzMjgzYmEtMTJmNi00OTVhLTkxY2YtNmNmNjEyODk0ZGVhXkEyXkFqcGc@._V1_.jpg');

INSERT INTO moviecrew (movie_id, person_id, job)
VALUES (
           (SELECT movie_id FROM movie WHERE title = 'Minecraft: the movie'),
           (SELECT person_id FROM person WHERE name = 'Roy' AND surname1 = 'Lee'),
           'Productor'
       );
