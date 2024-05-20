CREATE SEQUENCE hibernate_sequence START 1;

CREATE SEQUENCE user_id_seq;

CREATE TABLE user_details
(
    user_id bigint NOT NULL DEFAULT nextval('user_id_seq'),
    email varchar(100) NULL,
    password varchar(50) NULL,
    phonenumber varchar(20) NULL,
    name varchar(20) NULL,
    dateofbirth varchar(30) NULL,
    gender varchar(20) NULL

);

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE user_details ADD CONSTRAINT "PK_user_details"
	PRIMARY KEY (user_id);