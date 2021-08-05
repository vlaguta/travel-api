DROP TABLE IF EXISTS TRAVEL;

CREATE TABLE IF NOT EXISTS TRAVEL
(
    id          bigserial
        constraint  travel_pk
            primary key ,
    username    varchar,
    country     varchar,
    description text,
    travel_date date
);

INSERT INTO TRAVEL (id, username, country, description, travel_date)
VALUES (1, 'user-1', 'Belarus', 'some description', '2003-04-12');
INSERT INTO TRAVEL (id, username, country, description, travel_date)
VALUES (2, 'user-1', 'Russia', 'some description', '2004-03-12');
INSERT INTO TRAVEL (id, username, country, description, travel_date)
VALUES (3, 'user-3', 'Germany', 'some description', '2012-04-16');
INSERT INTO TRAVEL (id, username, country, description, travel_date)
VALUES (4, 'user-4', 'USA', 'some description', '2003-02-10');
INSERT INTO TRAVEL (id, username, country, description, travel_date)
VALUES (5, 'user-5', 'Poland', 'some description', '2020-12-11');
INSERT INTO TRAVEL (id, username, country, description, travel_date)
VALUES (6, 'user-5', 'Poland', 'some description', '2020-12-11');



