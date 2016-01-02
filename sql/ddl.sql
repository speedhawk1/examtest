DROP DATABASE IF EXISTS exam;
CREATE DATABASE exam;

# user
DROP TABLE IF EXISTS exam.user;
CREATE TABLE exam.user
(
  id       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  password VARCHAR(255)
);

INSERT INTO exam.user VALUES (NULL, 'a', 'a');

USE exam;
