DROP TABLE IF EXISTS hospitals;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS staffs;
DROP TABLE IF EXISTS symptoms;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS hospitals_time;

CREATE extension cube;
CREATE extension earthdistance;

CREATE TABLE hospitals
(
    id              BIGINT NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL UNIQUE,
    url             VARCHAR(256) NULL,
    doctor_name     VARCHAR(256) NULL,
    latitude        FLOAT(20)  NOT NULL,
    longitude       FLOAT(20)  NOT NULL,
    available_time  VARCHAR(256) NULL ,
    CONSTRAINT      accounts_pk PRIMARY KEY (id)
);
ALTER TABLE hospitals OWNER TO begdoctor;

INSERT INTO hospitals(id
    , name
    , url
    , doctor_name
    , latitude
    , longitude
    , available_time)
VALUES (0
    , 'Hae Song'
    , '-'
    , '-'
    , 13.7854529
    , 100.5736408
    , '-')
    ,(1
    , 'Samsung Medical Center'
    , '-'
    , '-'
    , 37.487996
    , 127.084419
    , '-')
    ,(2
    , 'Asan Medical Center'
    , '-'
    , '-'
    , 37.530753
    , 127.107250
    , '-')
    ,(3
    , 'Bagae Hospital'
    , '-'
    , '-'
    , 36.99331
    , 127.089189
    , '-');

CREATE TABLE users
(
    id              BIGINT NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL,
    surname         VARCHAR(256) NOT NULL,
    email           VARCHAR(256) NOT NULL UNIQUE,
    avatar          VARCHAR(256),
    mobile          VARCHAR(10) NOT NULL,
    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT      users_pk PRIMARY KEY (id)
);
ALTER TABLE users OWNER TO begdoctor;

INSERT INTO users(id
    , name
    , surname
    , email
    , avatar
    , mobile
    , created)
VALUES (0
    , 'Kim'
    , 'Eun-ji'
    , 'kimeunji@mail.com'
    , 'http://miner8.com/en/wp-content/uploads/2016/04/20160127-J-ESTINA-SONG-HYE-KYO.gif'
    , '0912345678'
    , '2016-07-01 10:30');

CREATE TABLE staffs
(
    id              BIGINT NOT NULL UNIQUE,
    name            VARCHAR(256) NOT NULL,
    surname         VARCHAR(256) NOT NULL,
    email           VARCHAR(256),
    username        VARCHAR(256) NOT NULL UNIQUE,
    password        VARCHAR(256) NOT NULL,
    hospital_id     BIGINT NOT NULL,
    created         TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT      staffs_pk PRIMARY KEY (id)
);
ALTER TABLE staffs OWNER TO begdoctor;

INSERT INTO staffs(id
    , name
    , surname
    , email
    , username
    , password
    , hospital_id
    , created)
VALUES (0
    , 'Choi'
    , 'Woo-geun'
    , 'choiwoogeun@mail.com'
    , 'choiwoogeun'
    , '$2a$10$iXIfki6AefgcUsPqR.niQ.FvIK8vdcfup09YmUxmzS/sQeuI3QOFG'
    , '1'
    , '2016-07-01 10:30');

CREATE TABLE booking
(
    id                   BIGINT NOT NULL UNIQUE,
    user_id              BIGINT NOT NULL,
    hospital_time_id     BIGINT NOT NULL,
    status               VARCHAR(256) NOT NULL,
    created              TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT           booking_pk PRIMARY KEY (id)
);
ALTER TABLE booking OWNER TO begdoctor;

INSERT INTO booking(id
    , user_id
    , hospital_time_id
    , status
    , created)
VALUES (0
    , 0
    , 1
    , 'confirmed'
    , '2016-07-01 10:30');

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
     , true)
     , (2
     , 1
     , '2016-07-01 11:30'
     , '2016-07-01 12:30'
     , true)
     , (3
     , 2
     , '2016-07-01 13:30'
     , '2016-07-01 14:30'
     , true)
     , (4
     , 3
     , '2016-07-02 10:30'
     , '2016-07-02 11:30'
     , true)
     , (5
     , 4
     , '2016-07-02 11:30'
     , '2016-07-02 12:30'
     , true)
     , (6
     , 5
     , '2016-07-02 13:30'
     , '2016-07-02 14:30'
     , true)
     , (7
     , 6
     , '2016-07-03 11:30'
     , '2016-07-03 12:30'
     , true)
     , (8
     , 7
     , '2016-07-03 11:30'
     , '2016-07-03 12:30'
     , true)
     , (9
     , 8
     , '2016-07-03 13:30'
     , '2016-07-03 14:30'
     , true);



