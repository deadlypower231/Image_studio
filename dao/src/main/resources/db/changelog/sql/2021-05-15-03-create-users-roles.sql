INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev@gmail.com', 'Евгений', 'Миронов', 80295804033, 'admin', '$2a$10$iVqQrtYCh30Eecn6AhC6Mu3Bd7KHC49ZM41Eh9X5RKTTgd8uiiM/u', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev0@gmail.com', 'Анатолий', 'Миронов', 80295804032, 'evgen', '$2a$10$h8w//JL22HjKz.R8huxvm.c9z56QJ8RMiJ/wOOb4ahws7XDpBnara', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev1@gmail.com', 'Сергей', 'Яроцкий', 80295804034, 'iron', '$2a$10$h8w//JL22HjKz.R8huxvm.c9z56QJ8RMiJ/wOOb4ahws7XDpBnara', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev2@gmail.com', 'Анастасия', 'Макаревич', 80295804031, 'Nastia', '$2a$10$h8w//JL22HjKz.R8huxvm.c9z56QJ8RMiJ/wOOb4ahws7XDpBnara', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev3@gmail.com', 'Татьяна', 'Шейпак', 80295804030, 'Tanka', '$2a$10$h8w//JL22HjKz.R8huxvm.c9z56QJ8RMiJ/wOOb4ahws7XDpBnara', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev4@gmail.com', 'Алена', 'Сальвончик', 80295804035, 'SAlvo', '$2a$10$h8w//JL22HjKz.R8huxvm.c9z56QJ8RMiJ/wOOb4ahws7XDpBnara', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev5@gmail.com', 'Евгений', 'Звержевич', 80295804036, 'franc', '$2a$10$h8w//JL22HjKz.R8huxvm.c9z56QJ8RMiJ/wOOb4ahws7XDpBnara', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev6@gmail.com', 'Руслан', 'Снацкий', 80295804037, 'rusel', '$2a$10$h8w//JL22HjKz.R8huxvm.c9z56QJ8RMiJ/wOOb4ahws7XDpBnara', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev7@gmail.com', 'Александр', 'Смирнов', 80295804038, 'sims', '$2a$10$iVqQrtYCh30Eecn6AhC6Mu3Bd7KHC49ZM41Eh9X5RKTTgd8uiiM/u', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev8@gmail.com', 'Евгений', 'Смирнов', 80295804039, 'admiral', '$2a$10$iVqQrtYCh30Eecn6AhC6Mu3Bd7KHC49ZM41Eh9X5RKTTgd8uiiM/u', 1, current_timestamp());

INSERT INTO test1.`user`
(email, first_name, last_name, phone, username, password, status, created_date)
VALUES('evgeniymironovdev9@gmail.com', 'Антон', 'Иванов', 80295804040, 'ivanov', '$2a$10$iVqQrtYCh30Eecn6AhC6Mu3Bd7KHC49ZM41Eh9X5RKTTgd8uiiM/u', 1, current_timestamp());

INSERT INTO test1.`role`
(`role`)
VALUES('ROLE_USER');

INSERT INTO test1.`role`
(`role`)
VALUES('ROLE_ADMIN');

INSERT INTO test1.`role`
(`role`)
VALUES('ROLE_MASTER');

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(1, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(1, 2);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(1, 3);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(2, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(2, 3);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(3, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(3, 3);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(4, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(4, 3);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(5, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(6, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(7, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(8, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(9, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(10, 1);

INSERT INTO test1.user_role
(user_id, role_id)
VALUES(11, 1);


