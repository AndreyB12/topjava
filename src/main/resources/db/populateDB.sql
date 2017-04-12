#DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;


INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');



INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

set @userId := (select id from users where email = 'user@yandex.ru' limit 1);
set @adminId := (select  id from users where email = 'admin@gmail.com' limit 1);


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', @userId),
  ('ROLE_ADMIN', @adminId );


INSERT INTO meals (dateTime, description, calories, userId)
    VALUES ('2017.01.01 10:00','Завтрак',500,@userId);

INSERT INTO meals (dateTime, description, calories, userId)
    VALUES ('2017.01.01 13:00','Обед',750,@userId);

INSERT INTO meals (dateTime, description, calories, userId)
    VALUES ('2017.01.01 18:00','Ужин',750,@userId);


INSERT INTO meals (dateTime, description, calories, userId)
VALUES ('2017.01.02 10:30','Завтрак',450,@userId);

INSERT INTO meals (dateTime, description, calories, userId)
VALUES ('2017.01.02 13:20','Обед',750,@userId);

INSERT INTO meals (dateTime, description, calories, userId)
VALUES ('2017.01.02 18:10','Ужин',850,@userId);