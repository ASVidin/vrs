INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('user@gmail.com', 'User_First', 'password'),
       ('admin@javaops.ru', 'Admin_First', 'admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);