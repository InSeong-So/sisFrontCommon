-- CREATE USER TABLE
CREATE TABLE SY0000(SEQ_NO              NUMBER NOT NULL
                   ,USER_ID             VARCHAR2(20 BYTE)
                   ,USER_NAME           VARCHAR2(40 BYTE)
                   ,USER_PASSWORD       VARCHAR2(60 BYTE)
                   ,PHONE_NUMBER        VARCHAR2(14 BYTE)
                   ,EMAIL               VARCHAR2(200 BYTE)
                   ,ZIP_NO              VARCHAR2(60 BYTE)
                   ,ADDRESS             VARCHAR2(200 BYTE)
                   ,DTL_ADDRESS         VARCHAR2(60 BYTE)
                   ,BIRTHDAY            TIMESTAMP(8)
                   ,REG_DATE            TIMESTAMP(8) WITH TIME ZONE
                   ,LOGIN_FAIL_COUNT    VARCHAR2(60 BYTE) DEFAULT 0 NOT NULL
                   ,CONSTRAINT PK_SY0000 PRIMARY KEY(USER_ID));

-- CREATE B0ARD TABLE
CREATE TABLE BR0010(SEQ_NO              NUMBER DEFAULT 0 NOT NULL
                   ,TITLE               VARCHAR2(100 BYTE)
                   ,CONTENT             VARCHAR2(4000 BYTE)
                   ,WRITER              VARCHAR2(60 BYTE)
                   ,WRITE_NO            NUMBER DEFAULT 0 NOT NULL
                   ,REG_IP              VARCHAR2(30 BYTE) DEFAULT '127.0.0.1' NOT NULL
                   ,REG_DATE            TIMESTAMP WITH TIME ZONE
                   ,VIEW_CNT            NUMBER DEFAULT 0
                   ,CONSTRAINT PK_BR0010 PRIMARY KEY(WRITER, WRITE_NO));

-- DROP TABLE
DROP TABLE SY0000 PURGE;
DROP TABLE BR0010 PURGE;

-- SELECT TABLE
SELECT * FROM SY0000;
SELECT * FROM BR0010;

-- DUMMY DATE INSERT QUERY
INSERT INTO BR0010
            (
                SEQ_NO
               ,TITLE
               ,CONTENT
               ,WRITER
               ,REG_DATE
               ,VIEW_CNT
            )
     VALUES ((SELECT NVL(MAX(SEQ_NO + 1), 1)
                FROM BR0010
               WHERE WRITER = 'ASDFASF1234')
            ,'1234'
            ,'31241ASDF'
            ,'ASDFASF1234'
            ,SYSDATE
            ,0);