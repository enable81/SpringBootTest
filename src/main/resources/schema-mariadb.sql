DROP TABLE IF EXISTS AA_TEST;
CREATE TABLE AA_TEST (
    id      VARCHAR (20) NOT NULL,
    name    VARCHAR (50) NOT NULL,
    email   VARCHAR (50),
    regist_date DATETIME,
    primary key (id)
) COMMENT = '로그 데이터 관리';
