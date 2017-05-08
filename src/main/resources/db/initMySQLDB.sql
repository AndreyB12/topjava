DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS meals;

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