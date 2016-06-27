DROP TABLE IF EXISTS hospitals;
DROP TABLE IF EXISTS users;

CREATE TABLE hospitals
(
    id              BIGINT NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL UNIQUE,
    CONSTRAINT      accounts_pk PRIMARY KEY (id)
);
ALTER TABLE hospitals OWNER TO begdoctor;

INSERT INTO hospitals(id
    , name)
VALUES (0
    , 'test hospital name');

CREATE TABLE users
(
    id              SERIAL NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL,
    surname         VARCHAR(256) NOT NULL,
    email           VARCHAR(256) NOT NULL UNIQUE,
    CONSTRAINT      users_pk PRIMARY KEY (id)
);
ALTER TABLE users OWNER TO begdoctor;

INSERT INTO users(id
    , name
    , surname
    , email)
VALUES (0
    , 'patientName'
    , 'patientSurname'
    , 'patient@mail.com');
