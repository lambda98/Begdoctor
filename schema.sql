DROP TABLE IF EXISTS hospitals;
DROP TABLE IF EXISTS users;

CREATE TABLE hospitals
(
    id              BIGINT NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL UNIQUE,
    url             VARCHAR(256) NOT NULL UNIQUE,
    location        VARCHAR(256) NOT NULL UNIQUE,
    doctorName      VARCHAR(256) NOT NULL UNIQUE,
    CONSTRAINT      accounts_pk PRIMARY KEY (id)
);
ALTER TABLE hospitals OWNER TO begdoctor;

INSERT INTO hospitals(id
    , name
    , url
    , location
    , doctorName )
VALUES (0
    , 'test hospital name'
    , 'https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png'
    , 'Seoul Korea'
    , 'Kang Moyeon' );

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
