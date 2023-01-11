CREATE
DATABASE students
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = FALSE;

CREATE TABLE students(
    student_id      serial      NOT NULL PRIMARY KEY,
    student_name    varchar(32) NOT NULL,
    document_serial varchar(8)  NOT NULL,
    document_number int         NOT NULL,
    CONSTRAINT unique_document UNIQUE (document_number, document_serial)
);

CREATE TABLE subjects(
    subject_id   serial NOT NULL PRIMARY KEY,
    subject_name text   NOT NULL
);

CREATE TABLE progress(
    progress_id serial   NOT NULL PRIMARY KEY,
    student_id  int      NOT NULL REFERENCES students (student_id) ON DELETE CASCADE,
    subject_id  int      NOT NULL REFERENCES subjects (subject_id),
    mark        smallint NOT NULL CHECK (mark >= 2 AND mark <= 5)
);
CREATE INDEX student_index ON progress (student_id);
CREATE INDEX subject_index ON progress (subject_id);
