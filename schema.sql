DROP TABLE IF EXISTS hospitals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS symptoms;
DROP TABLE IF EXISTS hospitals_time;

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

CREATE TABLE hospitals_time
(
     id                     SERIAL NOT NULL UNIQUE,
     hospital_id            BIGINT NOT NULL UNIQUE,
     start_datetime         TIMESTAMP WITH TIME ZONE NOT NULL,
     finish_datetime        TIMESTAMP WITH TIME ZONE NOT NULL,
     available              BOOLEAN NOT NULL,
     CONSTRAINT             hospitals_time_pk PRIMARY KEY (hospital_id)
);
ALTER TABLE hospitals_time OWNER TO begdoctor;

INSERT INTO hospitals_time(id
     , hospital_id
     , start_datetime
     , finish_datetime
     , available)
VALUES (1
     , 0
     , '2016-07-01 10:30'
     , '2016-07-01 11:30'
     , 'true')
     , (2
     , 1
     , '2016-07-01 11:30'
     , '2016-07-01 12:30'
     , 'true')
     , (3
     , 2
     , '2016-07-01 13:30'
     , '2016-07-01 14:30'
     , 'true')
     , (4
     , 3
     , '2016-07-02 10:30'
     , '2016-07-02 11:30'
     , 'true')
     , (5
     , 4
     , '2016-07-02 11:30'
     , '2016-07-02 12:30'
     , 'true')
     , (6
     , 5
     , '2016-07-02 13:30'
     , '2016-07-02 14:30'
     , 'true')
     , (7
     , 6
     , '2016-07-03 11:30'
     , '2016-07-03 12:30'
     , 'true')
     , (8
     , 7
     , '2016-07-03 11:30'
     , '2016-07-03 12:30'
     , 'true')
     , (9
     , 8
     , '2016-07-03 13:30'
     , '2016-07-03 14:30'
     , 'true');



