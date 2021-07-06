CREATE TABLE users
(
    id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username     VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email   VARCHAR(255) NOT NULL,
    city   VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(100) NOT NULL
)
    ENGINE = InnoDB;


INSERT INTO users
VALUES (1, 'Stanislav', '12345','Stas@mail.ru','Moscow');
INSERT INTO users
VALUES (2, 'Tom', 'q123','Mot@gmail.com','Saransk');
INSERT INTO users
VALUES (3, 'Jack', 'z123','Zloy@mail.com','Penza');

INSERT INTO roles
VALUES (1, 'USER');
INSERT INTO roles
VALUES (2, 'ADMIN');


