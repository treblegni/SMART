DROP IF EXISTS DATABASE smart_database;

CREATE DATABASE smart_database;

USE smart_database;

CREATE TABLE users(
	id INT AUTO_INCREMENT,
	username VARCHAR(30) NOT NULL,
	password VARCHAR(16) NOT NULL,
	age INT,
	PRIMARY KEY (id)
);