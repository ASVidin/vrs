INSERT INTO users (name, email, password)
VALUES ('User_First', 'user@gmail.com', '{noop}password'),
       ('Admin_First', 'admin@ya.ru', '{noop}admin');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurant (name)
VALUES ('Karlson'),
       ('White_rabbit'),
       ('Il_patio');

INSERT INTO menu (date, restaurant_id)
VALUES (NOW(), 1),
       ('2021-01-30', 2),
       ('2021-01-30', 3);

INSERT INTO dish (name, price, menu_id)
VALUES ('STEAK', 400, 1),
       ('RIBS', 300.00, 1),
       ('RIBS', 350.00, 3),
       ('MILK', 35.00, 2),
       ('MILK', 35.00, 3),
       ('EGGS', 40.00, 2);

INSERT INTO vote (user_id, restaurant_id, voting_date)
VALUES (1, 1, '2021-01-30'),
       (2, 1, '2021-01-30'),
       (2, 2, NOW()),
       (1, 3, '2021-01-28'),
       (1, 2, '2021-01-27'),
       (2, 3, '2021-01-27');