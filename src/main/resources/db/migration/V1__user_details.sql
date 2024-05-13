CREATE SEQUENCE hibernate_sequence START 1;

CREATE SEQUENCE user_id_seq;

CREATE TABLE user_details
(
    user_id bigint NOT NULL DEFAULT nextval('user_id_seq'),
    user_name varchar(100) NULL,
    phone_number varchar(20) NULL,
    password varchar(20) NULL
);

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE user_details ADD CONSTRAINT "PK_user_details"
	PRIMARY KEY (user_id);