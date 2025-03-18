CREATE SEQUENCE IF NOT EXISTS course_seq;

CREATE TABLE IF NOT EXISTS coursecatalog (

id integer NOT NULL DEFAULT nextval('course_seq') PRIMARY KEY,
course VARCHAR(200) NOT NULL,
coursecode VARCHAR(200) NOT NULL,
credit integer NOT NULL,
descr VARCHAR(200) NOT NULL
);