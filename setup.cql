CREATE KEYSPACE testks WITH REPLICATION = {
	'class': 'SimpleStrategy',
	'replication_factor': 1
};

use testks;

CREATE TABLE user(
	name TEXT,
	lastname TEXT,
	email TEXT,
	password TEXT,
	PRIMARY KEY (email)
);

INSERT INTO user (name, lastname, email, password) VALUES ('john', 'smith', 'john@example.com', 'pw1');
INSERT INTO user (name, lastname, email, password) VALUES ('james', 'doe', 'james@example.com', 'pw2');