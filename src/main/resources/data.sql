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

INSERT INTO DISH (NAME, PRICE, INPUT_DATE, RESTAURANT_ID)
VALUES ('STEAK', 400, NOW(), 1),
       ('RIBS', 300.00, NOW(), 1),
       ('MILK', 35.00, NOW(), 2),
       ('EGGS', 40.00, NOW(), 2);

INSERT INTO VOTE (USER_ID, RESTAURANT_ID, VOTING_TIME)
VALUES (1, 2, '2021-01-30 10:00:00'),
       (2, 2, '2021-01-30 9:00:00');