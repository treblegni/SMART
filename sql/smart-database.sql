DROP DATABASE IF EXISTS smart_database;

CREATE DATABASE smart_database;

USE smart_database;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS room_tracks;


CREATE TABLE users(
	id INT AUTO_INCREMENT,
	username VARCHAR(30) NOT NULL,
	password VARCHAR(16) NOT NULL,
	age INT,
	host BOOL,
	PRIMARY KEY (id)
);

CREATE TABLE rooms(
	room_id INT AUTO_INCREMENT,
	room_name VARCHAR(30),
	room_host VARCHAR(255),
	PRIMARY KEY(room_id)
);

CREATE TABLE room_tracks(
	track_id VARCHAR(30),
	track_name VARCHAR(255),
	track_artist VARCHAR(255),
	room_host VARCHAR(30) NOT NULL
);
