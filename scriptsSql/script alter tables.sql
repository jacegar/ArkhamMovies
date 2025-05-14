ALTER TABLE moviedb.movie 
ADD COLUMN photo_url VARCHAR(256) NULL AFTER tagline;

ALTER TABLE moviedb.person 
ADD COLUMN photo_url VARCHAR(256) NULL AFTER age;

ALTER TABLE moviedb.character 
ADD COLUMN photo_url VARCHAR(256) NULL AFTER person_id;

ALTER TABLE moviedb.character 
RENAME TO  moviedb.moviecharacter ;

ALTER TABLE `moviedb`.`user` 
ADD UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE;
