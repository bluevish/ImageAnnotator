DROP DATABASE IF EXISTS image_annotator;

CREATE DATABASE IF NOT EXISTS image_annotator;

USE image_annotator;


SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users(
	id INT NOT NULL AUTO_INCREMENT,
	email VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL,
    PRIMARY KEY(id),
    UNIQUE KEY(email)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS authorities;

CREATE TABLE IF NOT EXISTS authorities(
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY(id),
    UNIQUE KEY ix_auth_userid (user_id, authority),
    CONSTRAINT fk_authorities_users FOREIGN KEY(user_id) REFERENCES users(id) 
    ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDb AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
