CREATE SEQUENCE hibernate_sequence START 1;

CREATE SEQUENCE user_id_seq;

CREATE TABLE user_details
(
    user_id bigint NOT NULL DEFAULT nextval('user_id_seq'),
    email varchar(20) NULL,
    password varchar(20) NULL,
    phonenumber varchar(20) NULL,
    name varchar(20) NULL,
    dateofbirth varchar(20) NULL,
    gender varchar(20) NULL

);

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE user_details ADD CONSTRAINT "PK_user_details"
	PRIMARY KEY (user_id);