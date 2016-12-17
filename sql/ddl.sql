CREATE DATABASE IF NOT EXISTS exam;

# user
DROP TABLE IF EXISTS exam.user;
CREATE TABLE exam.user (
  id       INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  password VARCHAR(255)
);

INSERT INTO exam.user VALUES (NULL, 'a', 'a');

SELECT *
FROM exam.user;