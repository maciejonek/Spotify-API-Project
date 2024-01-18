SELECT * FROM playlist;
SELECT * FROM track;
SELECT * FROM User;
SELECT * FROM playlist_track;
SELECT * FROM playlist_track where playlist_id = 16;
DELETE FROM playlist where id=2;
SHOW CREATE TABLE playlist;
DELETE FROM playlist_track;
DELETE FROM playlist;
DELETE FROM User;
DELETE FROM track;
ALTER TABLE track AUTO_INCREMENT = 1;
ALTER TABLE playlist_track AUTO_INCREMENT = 1;
ALTER TABLE playlist AUTO_INCREMENT = 1;
ALTER TABLE User AUTO_INCREMENT = 1;


ALTER TABLE playlist
    ADD user_id int NOT NULL,
    ADD FOREIGN KEY (user_id) REFERENCES User(user_id);