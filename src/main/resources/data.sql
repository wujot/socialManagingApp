INSERT INTO USER(user_name, password, first_name, surname, dob) VALUES ('user', 'user', 'Adam', 'Malysz', '1987-07-29 00:00:00');
INSERT INTO USER(user_name, password, first_name, surname, dob) VALUES ('admin', 'admin', 'Antoni', 'Macierewicz', '1987-07-29 00:00:00');
INSERT INTO USER(user_name, password, first_name, surname, dob) VALUES ('rysiek', 'user', 'Ryszard', 'Bogucki', '1980-05-09 00:00:00');
INSERT INTO USER(user_name, password, first_name, surname, dob) VALUES ('grubecki', 'user', 'Daniel', 'Cieslak', '1987-07-29 00:00:00');


INSERT INTO FRIEND(username) VALUES ('Group One');
INSERT INTO FRIEND(username) VALUES ('Group Two');
INSERT INTO FRIEND(username) VALUES ('Group Three');

INSERT INTO USER_GROUP(user_id, friend_id) VALUES (1, 1);
INSERT INTO USER_GROUP(user_id, friend_id) VALUES (1, 2);
INSERT INTO USER_GROUP(user_id, friend_id) VALUES (2, 2);
INSERT INTO USER_GROUP(user_id, friend_id) VALUES (3, 3);
INSERT INTO USER_GROUP(user_id, friend_id) VALUES (4, 3);
INSERT INTO USER_GROUP(user_id, friend_id) VALUES (1, 3)

;



