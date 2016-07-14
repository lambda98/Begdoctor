DROP TABLE IF EXISTS hospitals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS symptoms;
DROP TABLE IF EXISTS booking;

CREATE TABLE hospitals
(
    id              BIGINT NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL UNIQUE,
    url             VARCHAR(256) NOT NULL UNIQUE,
    location        VARCHAR(256) NOT NULL UNIQUE,
    doctorName      VARCHAR(256) NOT NULL UNIQUE,
    types           VARCHAR(256) NOT NULL UNIQUE,
    latitude        FLOAT(20)  NOT NULL UNIQUE,
    longitude       FLOAT(20)  NOT NULL UNIQUE,
    available_time  VARCHAR(256) NOT NULL UNIQUE,
    CONSTRAINT      accounts_pk PRIMARY KEY (id)
);
ALTER TABLE hospitals OWNER TO begdoctor;

INSERT INTO hospitals(id
    , name
    , url
    , location
    , doctorName
    , types
    , latitude
    , longitude
    , available_time)
VALUES (0
    , 'Hae Song'
    , 'https://www.benin2009.com/wp-content/uploads/2015/11/hospital.png'
    , 'Seoul Korea'
    , 'Kang Moyeon'
    , 'Hospital'
    , '13.7854529'
    , '100.5736408'
    , '10:00 - 17:00');

CREATE TABLE users
(
    id              BIGINT NOT NULL UNIQUE,
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

CREATE TABLE booking
(
    id                  BIGINT NOT NULL UNIQUE,
    user_id             BIGINT NOT NULL,
    hospital_time_id    BIGINT NOT NULL,
    CONSTRAINT      booking_pk PRIMARY KEY (id)
);
ALTER TABLE booking OWNER TO begdoctor;

INSERT INTO booking(id
    , user_id
    , hospital_time_id)
VALUES (0
    , 1
    , 1);

CREATE TABLE symptoms
(
    id              SERIAL NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL,
    CONSTRAINT      symptoms_pk PRIMARY KEY (id)
);
ALTER TABLE symptoms OWNER TO begdoctor;

INSERT INTO symptoms(id
    , name)
VALUES (0
   , 'Leukemia')
   , (1
   , 'Thalassemia');