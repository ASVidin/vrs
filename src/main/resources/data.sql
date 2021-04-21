INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User_First', 'user@gmail.com', '{noop}password'),
       ('Admin_First', 'admin@ya.ru', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (NAME)
VALUES ('Karlson'),
       ('White_rabbit'),
       ('Il_patio');

INSERT INTO MENU (INPUT_DATE, RESTAURANT_ID)
VALUES (NOW(), 1),
       ('2021-01-30', 2),
       ('2021-01-30', 3);

INSERT INTO DISH (NAME, PRICE, MENU_ID)
VALUES ('STEAK', 400, 1),
       ('RIBS', 300.00, 1),
       ('RIBS', 350.00, 3),
       ('MILK', 35.00, 2),
       ('MILK', 35.00, 3),
       ('EGGS', 40.00, 2);

INSERT INTO VOTE (USER_ID, RESTAURANT_ID, VOTING_DATE)
VALUES (1, 1, '2021-01-30'),
       (2, 1, '2021-01-30'),
       (1, 2, '2021-01-29'),
       (1, 3, '2021-01-28'),
       (1, 2, '2021-01-27'),
       (2, 3, '2021-01-27');