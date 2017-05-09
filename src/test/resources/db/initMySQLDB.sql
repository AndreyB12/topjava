DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `registered` DATETIME NOT NULL DEFAULT now(),
  `enabled` TINYINT(1) NOT NULL DEFAULT 1,
  `calories_per_day` INT NOT NULL DEFAULT 2000,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

Alter TABLE `users` AUTO_INCREMENT = 100000;


CREATE TABLE `user_roles` (
  `user_id` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  INDEX `user_roles_idx` (`user_id` ASC, `role` ASC),
  CONSTRAINT `user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);


CREATE TABLE `meals` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dateTime` DATETIME NOT NULL DEFAULT now(),
  `description` VARCHAR(255) NOT NULL,
  `calories` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `userId_idx` (`userId` ASC),
  CONSTRAINT `userId`
  FOREIGN KEY (`userId`)
  REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

Alter TABLE `meals` AUTO_INCREMENT = 100000;

DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM meals;


INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

SET @userId := (SELECT id
                FROM users
                WHERE email = 'user@yandex.ru'
                LIMIT 1);

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');
SET @adminId := (SELECT id
                 FROM users
                 WHERE email = 'admin@gmail.com'
                 LIMIT 1);


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', @userId),
  ('ROLE_ADMIN', @adminId);


INSERT INTO meals (dateTime, description, calories, userId)
VALUES ('2017.01.01 10:00', 'Завтрак', 500, @userId),
  ('2017.01.01 13:00', 'Обед', 750, @userId),
  ('2017.01.01 18:00', 'Ужин', 750, @userId),
  ('2017.01.02 10:30', 'Завтрак', 450, @userId),
  ('2017.01.02 13:20', 'Обед', 750, @userId),
  ('2017.01.02 18:10', 'Ужин', 850, @userId),
  ('2015.06.01 14:00', 'Админ ланч', 510, @adminId),
  ('2015.06.01 21:00', 'Админ ужин', 1500, @adminId);