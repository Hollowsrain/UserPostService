--liquibase formatted sql

--changeset Donatas:2
ALTER TABLE person ADD first_name varchar(50);
--rollback ALTER TABLE person DROP COLUMN first_name