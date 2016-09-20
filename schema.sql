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
    , 'โรงพยาบาลบำรุงราษฎร์'
    , '-'
    , '-'
    , 13.746166
    , 100.552348
    , '-')
    ,(1
    , 'โรงพยาบาลกรุงเทพ'
    , '-'
    , '-'
    , 13.7485809
    , 100.5832588
    , '-')
    ,(2
    , 'โรงพยาบาลสมิติเวช สุขุมวิท'
    , '-'
    , '-'
    , 13.735052
    , 100.576692
    , '-')
    ,(3
    , 'โรงพยาบาลเวชธานี'
    , '-'
    , '-'
    , 13.7717777
    , 100.6361304
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
    , 'https://humblebumbledumble.files.wordpress.com/2014/09/jung-eun-ji.jpg'
    , '0912345678'
    , '2016-07-01 10:30')
    , (1
    , 'Kim'
    , 'Ji-won'
    , 'kimjiwon@mail.com'
    , 'http://4.bp.blogspot.com/-tCUxygN6W04/UnOZBPt3mLI/AAAAAAAADhs/hg-pqGNuY1E/s1600/Kim_Ji_Won_003.jpg'
    , '0922345678'
    , '2016-07-01 10:30')
    , (2
    , 'Yuri'
    , 'Kwan'
    , 'yurikwan@mail.com'
    , ''
    , '0812345678'
    , '2016-07-01 10:30')
    , (3
    , 'Song'
    , 'Hye Kyo'
    , 'songhyekyo@mail.com'
    , 'http://miner8.com/en/wp-content/uploads/2016/04/20160127-J-ESTINA-SONG-HYE-KYO.gif'
    , '0712345678'
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
    symptom_id           BIGINT NOT NULL,
    status               VARCHAR(256) NOT NULL,
    created              TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT           booking_pk PRIMARY KEY (id)
);
ALTER TABLE booking OWNER TO begdoctor;

INSERT INTO booking(id
    , user_id
    , hospital_time_id
    , symptom_id
    , status
    , created)
VALUES (0
    , 0
    , 1
    , 0
    , 'confirmed'
    , '2016-07-01 10:30')
    ,(1
    , 1
    , 1
    , 1
    , 'confirmed'
    , '2016-07-01 10:30')
    ,(2
    , 2
    , 1
    , 2
    , 'confirmed'
    , '2016-07-01 10:30')
    ,(3
    , 3
    , 1
    , 3
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
   , 'ไข้หวัด')
   , (1
   , 'ท้องเสีย')
   , (2
   , 'ปวดศรีษะ')
   , (3
   , 'อาหารเป็นพิษ')
   , (4
   , 'ปวดหัวไมเกรน')
   , (5
   , 'คออักเสบ')
   , (6
   , 'ตาแดง')
   , (7
   , 'เยี่อบุตาอักเสบ')
   , (8
   , 'หอบหืด')
   , (9
   , 'ท้องอืดท้องเฟ้อ')
   , (10
   , 'ภูมิแพ้')
   , (11
   , 'ขูดหินปูน')
   , (12
   , 'ทันตกรรมรากฟัน')
   , (13
   , 'ปวดเมื่อยกล้ามเนื้อ')
   , (14
   , 'หอบหืด')
   , (15
   , 'อีสุกอีใส')
   , (16
   , 'ผื่นแดงตามผิวหนัง')
   , (17
   , 'โรคซึมเศร้า')
   , (18
   , 'นอนไม่หลับ')
   , (19
   , 'กายภาพบำบัด')
   , (20
   , 'ตรวจสุขภาพ')
   , (21
   , 'ตรวจเลือด')
   , (22
   , 'ภาวะเครียด')
   , (23
   , 'ผดุงครรถ์')
   , (24
   , 'ไข้เลือดออก');

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



