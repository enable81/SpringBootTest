DROP TABLE AA_TEST;

CREATE TABLE AA_TEST
(
    id      VARCHAR2 (20) NOT NULL,
    name    VARCHAR2 (50) NOT NULL,
    email   VARCHAR2 (50),
    regist_date DATE  NOT NULL,
    primary key (id)
);
