CREATE DATABASE AESGRP4;
USE AESGRP4;
SET foreign_key_checks = 0;
DROP TABLE TEACHER;
DROP TABLE TEACH_FOR;
DROP TABLE SUBJECT;
DROP TABLE STUDENT;
DROP TABLE EXAM;
DROP TABLE QUESTION;
DROP TABLE OFFERED_IN;
DROP TABLE CLASS;
DROP TABLE TEACH_IN;
DROP TABLE EXAM_LIST;
DROP TABLE SCORE_LIST;
DROP TABLE STUDENT_ANSWER;
DROP TABLE ACCOUNT;
DROP TABLE LOGGER;
SET foreign_key_checks = 1;
COMMIT;


CREATE TABLE TEACHER(
    TEACHER_ID NUMERIC(6,0),
    TEACHER_NAME VARCHAR(30) NOT NULL,
    PRIMARY KEY(TEACHER_ID)
);

CREATE TABLE CLASS(
    CLASS_NO NUMERIC(6,0),
    CLASS_TEACHER_ID NUMERIC(6,0) NOT NULL,
    PRIMARY KEY(CLASS_NO),
    FOREIGN KEY(CLASS_TEACHER_ID) REFERENCES TEACHER(TEACHER_ID)
);

CREATE TABLE SUBJECT(
    SUBJECT_ID NUMERIC(6,0),
    SUBJECT_NAME VARCHAR(30) NOT NULL,
    PRIMARY KEY(SUBJECT_ID)
);


CREATE TABLE STUDENT(
    STU_ID NUMERIC(6,0),
    STU_NAME VARCHAR(30) NOT NULL,
    CLASS_NO NUMERIC(6,0) NOT NULL,
    PRIMARY KEY(STU_ID),
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS(CLASS_NO)
);


-- TIME?
CREATE TABLE EXAM(
    TEST_ID NUMERIC(6,0),
    TEST_DURATION NUMERIC(10, 0), -- in milliseconds
    START_TIME TIMESTAMP,
    CLASS_NO NUMERIC(6,0) NOT NULL,
    SUBJECT_ID NUMERIC(6,0) NOT NULL,
    ARRANGER_ID NUMERIC(6,0) NOT NULL,
    UNIQUE (SUBJECT_ID,CLASS_NO,ARRANGER_ID),
    PRIMARY KEY(TEST_ID),
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS(CLASS_NO),
    FOREIGN KEY(SUBJECT_ID) REFERENCES SUBJECT(SUBJECT_ID),
    FOREIGN KEY(ARRANGER_ID ) REFERENCES TEACHER(TEACHER_ID)
);

CREATE TABLE QUESTION(
    QUESTION_NO NUMERIC(6,0),
    TEST_ID NUMERIC(6,0) NOT NULL,
    Q_DESCRIPTION VARCHAR(128),
    COMPULSORY BOOLEAN NOT NULL DEFAULT FALSE,
    TYPE VARCHAR(2) NOT NULL DEFAULT 'MC',
    ANSWER VARCHAR(64) NOT NULL,
    SCORE NUMERIC(3,0) NOT NULL DEFAULT 0,
    CHECK (TYPE IN('MC','FB','FL')), -- MULTIPLE CHOICE, FILL IN BLANK, FULL-LENGTH
    PRIMARY KEY(QUESTION_NO, TEST_ID),
    FOREIGN KEY(TEST_ID) REFERENCES EXAM(TEST_ID)
);

CREATE TABLE OFFERED_IN(
    CLASS_NO NUMERIC(6,0) NOT NULL,
    SUBJECT_ID NUMERIC(6,0) NOT NULL,
    PRIMARY KEY(CLASS_NO, SUBJECT_ID),
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS(CLASS_NO),
    FOREIGN KEY(SUBJECT_ID) REFERENCES SUBJECT(SUBJECT_ID)
);

CREATE TABLE TEACH_FOR(
    SUBJECT_ID NUMERIC(6,0) NOT NULL,
    TEACHER_ID NUMERIC(6,0) NOT NULL,
    PRIMARY KEY(TEACHER_ID, SUBJECT_ID),
    FOREIGN KEY(TEACHER_ID) REFERENCES TEACHER(TEACHER_ID),
    FOREIGN KEY(SUBJECT_ID) REFERENCES SUBJECT(SUBJECT_ID)
);

CREATE TABLE TEACH_IN(
    TEACHER_ID NUMERIC(6,0) NOT NULL,
    CLASS_NO NUMERIC(6,0) NOT NULL,
    PRIMARY KEY(TEACHER_ID, CLASS_NO),
    FOREIGN KEY(TEACHER_ID) REFERENCES TEACHER(TEACHER_ID),
    FOREIGN KEY(CLASS_NO) REFERENCES CLASS(CLASS_NO)
);

CREATE TABLE EXAM_LIST(
    STU_ID NUMERIC(6,0) NOT NULL,
    TEST_ID NUMERIC(6,0) NOT NULL,
    PRIMARY KEY(STU_ID, TEST_ID),
    FOREIGN KEY(STU_ID) REFERENCES STUDENT(STU_ID),
    FOREIGN KEY(TEST_ID) REFERENCES EXAM(TEST_ID) ON DELETE CASCADE
);

CREATE TABLE SCORE_LIST(
    STU_ID NUMERIC(6,0) NOT NULL,
    TEST_ID NUMERIC(6,0) NOT NULL,
    SCORE NUMERIC(3,0) DEFAULT 0, -- stored as original score, but to be displayed as grade.
    FEEDBACK VARCHAR(50),
    PRIMARY KEY(STU_ID, TEST_ID),
    FOREIGN KEY(STU_ID) REFERENCES STUDENT(STU_ID),
    FOREIGN KEY(TEST_ID) REFERENCES EXAM(TEST_ID) ON DELETE CASCADE
);

CREATE TABLE ACCOUNT(
    USER_ID NUMERIC(6,0) NOT NULL,
    PASSWORD VARCHAR(32) NOT NULL,
    PRIVILEGE NUMERIC(1,0) NOT NULL, -- 0: ADMIN 1: TEACHER 2: STUDENT
    PRIMARY KEY (USER_ID),
    CHECK (PRIVILEGE IN(0,1,2))
);

CREATE TABLE STUDENT_ANSWER( -- each record is a question for an exam that a student answered
    STU_ID NUMERIC(6,0),
    TEST_ID NUMERIC(6,0),
    QUESTION_NO NUMERIC(6,0),
    ANSWER VARCHAR(64),
    SCORE NUMERIC(3,0),
    PRIMARY KEY (STU_ID,TEST_ID,QUESTION_NO),

    FOREIGN KEY(STU_ID) REFERENCES STUDENT(STU_ID),
    FOREIGN KEY(TEST_ID) REFERENCES EXAM(TEST_ID) ON DELETE CASCADE,
    FOREIGN KEY(QUESTION_NO) REFERENCES QUESTION(QUESTION_NO) ON DELETE CASCADE
);

CREATE TABLE LOGGER(
    LOG_ID NUMERIC(6,0),
    EVENT VARCHAR(128),

    PRIMARY KEY (LOG_ID)
);

COMMIT;

DESC TEACHER;
DESC TEACH_FOR;
DESC SUBJECT;
DESC STUDENT;
DESC EXAM;
DESC QUESTION;
DESC OFFERED_IN;
DESC CLASS;
DESC TEACH_IN;
DESC EXAM_LIST;
DESC SCORE_LIST;
DESC ACCOUNT;
DESC STUDENT_ANSWER;
DESC LOGGER;

CREATE INDEX ACCOUNT_INDEX
ON ACCOUNT(USER_ID);

CREATE INDEX TEACHER_INDEX
ON TEACHER(TEACHER_ID);

CREATE INDEX STU_INDEX
ON STUDENT(STU_ID);

CREATE INDEX QUESTION_INDEX
ON QUESTION(TEST_ID,QUESTION_NO);

CREATE INDEX ANS_INDEX
ON STUDENT_ANSWER(TEST_ID,QUESTION_NO,STU_ID);