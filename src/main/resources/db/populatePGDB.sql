DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM meals;


INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');



INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');



INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2017-01-01 10:00:00', 'Завтрак', 500, 100000),
  ('2017-01-01 13:00:00', 'Обед', 750, 100000),
  ('2017-01-01 18:00:00', 'Ужин', 750, 100000),
  ('2017-01-02 10:30:00', 'Завтрак', 450, 100000),
  ('2017-01-02 13:20:00', 'Обед', 750, 100000),
  ('2017-01-02 18:10:00', 'Ужин', 850, 100000);