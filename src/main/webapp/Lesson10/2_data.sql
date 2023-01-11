ALTER SEQUENCE students_student_id_seq RESTART WITH 1;
INSERT INTO students (student_name, document_serial, document_number)
VALUES ('Elon', 'US', 1234567),
       ('Vasya', '40 05', 1234567),
       ('Carl', 'US', 1234568),
       ('Max', '41 08', 1234567),
       ('Anthony', 'US', 1234569),
       ('Veniamin', '40 04', 1224567),
       ('Alex', 'UK', 1234567);

ALTER SEQUENCE subjects_subject_id_seq RESTART WITH 1;
INSERT INTO subjects (subject_name)
VALUES ('Math'),
       ('English'),
       ('Physics'),
       ('Biology');

INSERT INTO progress (student_id, subject_id, mark)
VALUES (1, 1, 2),
       (1, 2, 3),
       (1, 3, 4),
       (2, 1, 5),
       (2, 2, 4),
       (2, 3, 3),
       (3, 1, 2),
       (3, 2, 4),
       (3, 3, 5),
       (4, 1, 5),
       (4, 2, 5),
       (4, 3, 5),
       (5, 1, 4),
       (5, 2, 4),
       (5, 3, 4),
       (6, 1, 3),
       (6, 2, 3),
       (6, 3, 3),
       (7, 1, 5),
       (7, 2, 2),
       (7, 3, 5);
