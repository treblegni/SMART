DROP DATABASE IF EXISTS smart_database;

CREATE DATABASE smart_database;

USE smart_database;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_tracks;


CREATE TABLE users(
	id INT AUTO_INCREMENT,
	username VARCHAR(30) NOT NULL,
	password VARCHAR(16) NOT NULL,
	age INT,
	host BOOL,
	PRIMARY KEY (id)
);

CREATE TABLE rooms(
	id INT AUTO_INCREMENT,
	room_name VARCHAR(30),
	url VARCHAR(255),
	PRIMARY KEY(id)
);

CREATE TABLE room_tracks(
	track_id VARCHAR(30),
	room_id INT NOT NULL,
	PRIMARY KEY(track_id)
);
