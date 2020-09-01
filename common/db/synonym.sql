--# 테이블 스페이스 TS_SKCCPTBHR
--SKCCPTBHR
--SKCCPTBHR_WAS
--SKCCPTBHR_DEV

--# 테이블 스페이스 TS_SKCCPTBHR_RET
--SKCCPTBHR_RET
--SKCCPTBHR_RET_WAS
--SKCCPTBHR_RET_DEV

--# 디렉토리 권한 부여 : 기본 데이터베이스
CREATE DIRECTORY DIR_SKCCPTBHR AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR';
CREATE DIRECTORY DIR_SKCCPTBHR_DEV AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_DEV';
CREATE DIRECTORY DIR_SKCCPTBHR_WAS AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_WAS';
--# 디렉토리 권한 부여 : 개인정보분리보관 데이터베이스
CREATE DIRECTORY DIR_SKCCPTBHR_RET AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_RET';
CREATE DIRECTORY DIR_SKCCPTBHR_RET_WAS AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_RET_WAS';
CREATE DIRECTORY DIR_SKCCPTBHR_RET_DEV AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_RET_DEV';

--# 라이센스 위치 : 기본 데이터베이스
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR/license.dat
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_DEV/license.dat
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_WAS/license.dat
--# 라이센스 위치 : 개인정보분리보관 데이터베이스
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_RET/license.dat
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_RET_WAS/license.dat
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_RET_DEV/license.dat

--# 읽기 권한 부여 : PUBLIC 계정
GRANT READ ON DIRECTORY DIR_SKCCPTBHR TO PUBLIC;
--# 읽기 권한 부여 : 기본 데이터베이스
GRANT READ ON DIRECTORY DIR_SKCCPTBHR TO SKCCPTBHR;
GRANT READ ON DIRECTORY DIR_SKCCPTBHR_DEV TO SKCCPTBHR;
GRANT READ ON DIRECTORY DIR_SSKCCPTBHR_WAS TO SKCCPTBHR;
--# 읽기 권한 부여 : 개인정보분리보관 데이터베이스
GRANT READ ON DIRECTORY DIR_SKCCPTBHR_RET TO SKCCPTBHR;
GRANT READ ON DIRECTORY DIR_SKCCPTBHR_RET_WAS TO SKCCPTBHR;
GRANT READ ON DIRECTORY DIR_SKCCPTBHR_RET_DEV TO SKCCPTBHR;

--# 실행 권한 부여 : 기본 데이터베이스
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR;
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_DEV;
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_WAS;
--# 실행 권한 부여 : 개인정보분리보관 데이터베이스
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_RET;
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_RET_WAS;
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_RET_DEV;

--# SMTP 서버 설정
ALTER SYSTEM SET SMTP_OUT_SERVER ='10.250.71.35';

--# 우편번호계정 권한 부여
GRANT CREATE SESSION TO UPDATE_ZIP;
GRANT CONNECT, RESOURCE TO UPDATE_ZIP;

--# 우편번호 배치 테이블 권한 부여
GRANT INSERT, SELECT ON SY5035_ADDINFO_TMP TO UPDATE_ZIP ;
GRANT INSERT, SELECT ON SY5035_JIBUN_TMP TO UPDATE_ZIP ;
GRANT INSERT, SELECT ON SY5035_JUSO_TMP TO UPDATE_ZIP ;
GRANT INSERT, SELECT ON SY5035_ROAD_TMP TO UPDATE_ZIP ;

--# 분리보관 계정 권한 부여
WITH TRG_OWNER_LIST AS (SELECT 'SKCCPTBHR' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'RET_HUNEL_IF' TRG_OWNER FROM DUAL--UNION ALL SELECT 'SKCCPTBHR_RET_WAS' TRG_OWNER FROM DUAL
                                                                 --UNION ALL SELECT 'SKCCPTBHR_RET_DEV' TRG_OWNER FROM DUAL
    )
SELECT --'CREATE PUBLIC SYNONYM ' || T1.TABLE_NAME || ' FOR '|| T1.OWNER || '.' || T1.TABLE_NAME || ';' SQL_SYNONYM
       'GRANT DELETE, INSERT, SELECT, UPDATE ON ' || T1.OWNER || '.' || T1.TABLE_NAME || ' TO ' || T2.TRG_OWNER || ';'
  FROM ALL_TABLES T1
      ,TRG_OWNER_LIST T2
 WHERE T1.OWNER = 'SKCCPTBHR_RET'
   --AND T2.TRG_OWNER IN ('SKCCPTBHR') --CREATE PUBLIC SYNONYM
   --AND NOT EXISTS (SELECT T3.OWNER FROM DBA_SYNONYMS T3 WHERE T3.OWNER = 'PUBLIC' AND T3.SYNONYM_NAME = T1.TABLE_NAME) --CREATE PUBLIC SYNONYM
   AND T2.TRG_OWNER NOT IN ('SKCCPTBHR')                                                                                                                                                         --GRANT
   AND NOT EXISTS (SELECT T3.GRANTEE
                     FROM DBA_TAB_PRIVS T3
                    WHERE T3.OWNER = T1.OWNER
                      AND T3.GRANTEE = T2.TRG_OWNER
                      AND T3.TABLE_NAME = T1.TABLE_NAME)                                                                                                                                     --GRANT ALL
;

--# DBA권한 부여 : 전체 계정
GRANT DBA TO SKCCPTBHR;
GRANT DBA TO SKCCPTBHR_RET;
GRANT DBA TO SKCCPTBHR_DEV;
GRANT DBA TO SKCCPTBHR_WAS;

--# 아이피가 등록된 서버의 휴넬 라이센스 파일 등록(폴더를 먼저 생성) : 전체 계정
CREATE DIRECTORY DIR_SKCCPTBHR AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR';
CREATE DIRECTORY DIR_SKCCPTBHR_RET AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_RET';
CREATE DIRECTORY DIR_SKCCPTBHR_DEV AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_DEV';
CREATE DIRECTORY DIR_SKCCPTBHR_WAS AS '/db_backup/HCG_PKG_DIR/SKCCPTBHR_WAS';

--# 아이피가 등록된 서버의 휴넬 라이센스 파일 등록 : 전체 계정
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR/license.dat
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_RET/license.dat
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_DEV/license.dat
-- /db_backup/HCG_PKG_DIR/SKCCPTBHR_WAS/license.dat

--# 메인 계정의 폴더 권한 설정
GRANT READ ON DIRECTORY DIR_SKCCPTBHR TO PUBLIC;

--# 메인 계정에 각 계정의 폴더 권한 부여
GRANT READ ON DIRECTORY DIR_SKCCPTBHR TO SKCCPTBHR;
GRANT READ ON DIRECTORY DIR_SKCCPTBHR_RET TO SKCCPTBHR;
GRANT READ ON DIRECTORY DIR_SKCCPTBHR_DEV TO SKCCPTBHR;
GRANT READ ON DIRECTORY DIR_SSKCCPTBHR_WAS TO SKCCPTBHR;

--# 각 계정에서 라이센스 파일을 잘 읽을 수 있는지 확인
DECLARE
   FHANDLE             UTL_FILE.FILE_TYPE;
   FP_BUFFER           VARCHAR2 (4000);
BEGIN
   FHANDLE          := UTL_FILE.FOPEN ('DIR_SKCCPTBHR', 'license.dat', 'R');

   UTL_FILE.GET_LINE (FHANDLE, FP_BUFFER);
   DBMS_OUTPUT.PUT_LINE (FP_BUFFER);
   UTL_FILE.GET_LINE (FHANDLE, FP_BUFFER);
   DBMS_OUTPUT.PUT_LINE (FP_BUFFER);
   UTL_FILE.FCLOSE (FHANDLE);
END;
/

--# 각 계정에 암호화 관련 권한을 부여합니다.
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR;
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_RET;
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_DEV;
GRANT EXECUTE ON DBMS_CRYPTO TO SKCCPTBHR_WAS;

--# 패키지 loadjava 폴더를 지정하여 실행
dropjava -u SKCCPTBHR/pw@10.250.71.53:1521/HUNELDEV -v -r -t *.*
-- loadjava -u SKCCPTBHR/pw@10.250.71.53:1521/HUNELDEV
--  -v -r -t D:/CCHRPYTMBN/SKCC_PTBHR_WEB/src/main/resources/db/loadjava/*.*

--# 암호화 관련 권한 실행 : 전체 계정
EXEC dbms_java.grant_permission('SKCCPTBHR', 'SYS:java.io.FilePermission', '/db_backup/HCG_PKG_DIR/SKCCPTBHR/license.dat', 'read');
EXEC dbms_java.grant_permission('SKCCPTBHR_RET', 'SYS:java.io.FilePermission', '/db_backup/HCG_PKG_DIR/SKCCPTBHR_RET/license.dat', 'read');
EXEC dbms_java.grant_permission('SKCCPTBHR_DEV', 'SYS:java.io.FilePermission', '/db_backup/HCG_PKG_DIR/SKCCPTBHR_DEV/license.dat', 'read');
EXEC dbms_java.grant_permission('SKCCPTBHR_WAS', 'SYS:java.io.FilePermission', '/db_backup/HCG_PKG_DIR/SKCCPTBHR_WAS/license.dat', 'read');

EXEC dbms_java.grant_permission('SKCCPTBHR', 'SYS:java.security.SecurityPermission', 'putProviderProperty.BC', '' );
EXEC dbms_java.grant_permission('SKCCPTBHR_RET', 'SYS:java.security.SecurityPermission', 'putProviderProperty.BC', '' );
EXEC dbms_java.grant_permission('SKCCPTBHR_DEV', 'SYS:java.security.SecurityPermission', 'putProviderProperty.BC', '' );
EXEC dbms_java.grant_permission('SKCCPTBHR_WAS', 'SYS:java.security.SecurityPermission', 'putProviderProperty.BC', '' );

EXEC dbms_java.grant_permission('SKCCPTBHR', 'SYS:java.security.SecurityPermission', 'insertProvider.BC', '' ); 
EXEC dbms_java.grant_permission('SKCCPTBHR_RET', 'SYS:java.security.SecurityPermission', 'insertProvider.BC', '' ); 
EXEC dbms_java.grant_permission('SKCCPTBHR_DEV', 'SYS:java.security.SecurityPermission', 'insertProvider.BC', '' ); 
EXEC dbms_java.grant_permission('SKCCPTBHR_WAS', 'SYS:java.security.SecurityPermission', 'insertProvider.BC', '' ); 

--# 암호화 적용이 잘 되었는지 확인
SELECT PKG_CRYPTO_CORE.F_SET_ENC_DATA_HCG ('1') FROM DUAL

--# 메일 서버 설정
ALTER SYSTEM SET SMTP_OUT_SERVER ='10.250.71.35';
--# 메일전용 PACKAGE 실행 권한 부여 : 기본 데이터베이스
GRANT EXECUTE ON UTL_MAIL TO SKCCPTBHR;
GRANT EXECUTE ON UTL_MAIL TO SKCCPTBHR_DEV;
GRANT EXECUTE ON UTL_MAIL TO SKCCPTBHR_WAS;

--# SKCCPTBHR 계정으로 PUBLIC SYNONYM을 주고 다른 계정에게 GRANT ALL을 부여
WITH TRG_OWNER_LIST AS (SELECT 'SKCCPTBHR' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_WAS' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_DEV' TRG_OWNER FROM DUAL)
  SELECT --'CREATE PUBLIC SYNONYM ' || T1.OBJECT_NAME || ' FOR '|| T1.OWNER || '.' || T1.OBJECT_NAME || ';' SQL_SYNONYM
         'GRANT ALL ON ' || T1.OBJECT_NAME || ' TO ' || T2.TRG_OWNER || ';' SQL_GRANT
    --'DROP SYNONYM ' || T1.OBJECT_NAME || ';' SQL_DROP_SYNONYM
    --'REVOKE SELECT ON ' || T1.OBJECT_NAME || ' FROM ' || T2.TRG_OWNER || ';' SQL_REVOKE_GRANT
    FROM DBA_OBJECTS T1
        ,TRG_OWNER_LIST T2
   WHERE T1.OWNER = 'SKCCPTBHR'
     --AND T2.TRG_OWNER IN ('SKCCPTBHR') --CREATE PUBLIC SYNONYM
     AND T2.TRG_OWNER NOT IN ('SKCCPTBHR')                                                                                                                                                   --GRANT ALL
     AND T1.OBJECT_TYPE IN ('TABLE')
     AND T1.OBJECT_TYPE NOT IN ('PACKAGE', 'FUNCTION', 'PROCEDURE', 'VIEW', 'PACKAGE BODY')
     AND T1.OBJECT_TYPE NOT IN ('SYNONYM', 'INDEX', 'TYPE', 'SEQUENCE', 'LOB', 'JAVA CLASS', 'DATABASE LINK', 'TYPE BODY', 'TRIGGER', 'JAVA RESOURCE')
     --AND NOT EXISTS (SELECT T3.OWNER FROM DBA_SYNONYMS T3 WHERE T3.OWNER = 'PUBLIC' AND T3.SYNONYM_NAME = T1.OBJECT_NAME) --CREATE PUBLIC SYNONYM
     AND NOT EXISTS (SELECT T3.GRANTEE
                       FROM DBA_TAB_PRIVS T3
                      WHERE T3.OWNER = T1.OWNER
                        AND T3.GRANTEE = T2.TRG_OWNER
                        AND T3.TABLE_NAME = T1.OBJECT_NAME)                                                                                                                                  --GRANT ALL
ORDER BY T1.OBJECT_NAME;

--# SKCCPTBHR 계정으로 PUBLIC SYNONYM을 주고 다른 계정에게 GRANT SELECT을 부여
WITH TRG_OWNER_LIST AS (SELECT 'SKCCPTBHR' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_WAS' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_DEV' TRG_OWNER FROM DUAL)
  SELECT --'CREATE PUBLIC SYNONYM ' || T1.OBJECT_NAME || ' FOR '|| T1.OWNER || '.' || T1.OBJECT_NAME || ';' SQL_SYNONYM
         'GRANT SELECT ON ' || T1.OWNER || '.' || T1.OBJECT_NAME || ' TO ' || T2.TRG_OWNER || ' ;' SQL_VIEW_SEQ
    --'DROP SYNONYM ' || T1.OBJECT_NAME || ';' SQL_DROP_SYNONYM
    --'REVOKE SELECT ON ' || T1.OBJECT_NAME || ' FROM ' || T2.TRG_OWNER || ';' SQL_REVOKE_GRANT
    FROM DBA_OBJECTS T1
        ,TRG_OWNER_LIST T2
   WHERE T1.OWNER = 'SKCCPTBHR'
     --AND T2.TRG_OWNER IN ('SKCCPTBHR') --CREATE PUBLIC SYNONYM
     AND T2.TRG_OWNER NOT IN ('SKCCPTBHR')                                                                                                                                                       --GRANT
     AND T1.OBJECT_TYPE IN ('VIEW')
     AND T1.OBJECT_TYPE NOT IN ('TABLE', 'PACKAGE', 'FUNCTION', 'PROCEDURE', 'PACKAGE BODY', 'SEQUENCE')
     AND T1.OBJECT_TYPE NOT IN ('SYNONYM', 'INDEX', 'TYPE', 'LOB', 'JAVA CLASS', 'DATABASE LINK', 'TYPE BODY', 'TRIGGER', 'JAVA RESOURCE')
     --AND NOT EXISTS (SELECT T3.OWNER FROM DBA_SYNONYMS T3 WHERE T3.OWNER = 'PUBLIC' AND T3.SYNONYM_NAME = T1.OBJECT_NAME) --CREATE PUBLIC SYNONYM
     AND NOT EXISTS (SELECT T3.GRANTEE
                       FROM DBA_TAB_PRIVS T3
                      WHERE T3.OWNER = T1.OWNER
                        AND T3.GRANTEE = T2.TRG_OWNER
                        AND T3.TABLE_NAME = T1.OBJECT_NAME
                        AND T3.PRIVILEGE = 'SELECT')                                                                                                                                             --GRANT
ORDER BY T1.OBJECT_NAME;

--# SKCCPTBHR 계정으로 PUBLIC SYNONYM을 주고 다른 계정에게 GRANT SELECT,ALTER 을 부여
WITH TRG_OWNER_LIST AS (SELECT 'SKCCPTBHR' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_WAS' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_DEV' TRG_OWNER FROM DUAL)
SELECT --'CREATE PUBLIC SYNONYM ' || T1.OBJECT_NAME || ' FOR '|| T1.OWNER || '.' || T1.OBJECT_NAME || ';' SQL_SYNONYM
       'GRANT SELECT,ALTER ON ' || T1.OWNER || '.' || T1.OBJECT_NAME || ' TO ' || T2.TRG_OWNER || ' ;' SQL_VIEW_SEQ
  --'DROP SYNONYM ' || T1.OBJECT_NAME || ';' SQL_DROP_SYNONYM
  --'REVOKE SELECT ON ' || T1.OBJECT_NAME || ' FROM ' || T2.TRG_OWNER || ';' SQL_REVOKE_GRANT
  FROM DBA_OBJECTS T1
      ,TRG_OWNER_LIST T2
 WHERE T1.OWNER = 'SKCCPTBHR'
   --AND T2.TRG_OWNER IN ('SKCCPTBHR') --CREATE PUBLIC SYNONYM
   AND T2.TRG_OWNER NOT IN ('SKCCPTBHR')                                                                                                                                                         --GRANT
   AND T1.OBJECT_TYPE IN ('SEQUENCE')
   AND T1.OBJECT_TYPE NOT IN ('TABLE', 'PACKAGE', 'FUNCTION', 'PROCEDURE', 'PACKAGE BODY', 'VIEW')
   AND T1.OBJECT_TYPE NOT IN ('SYNONYM', 'INDEX', 'TYPE', 'LOB', 'JAVA CLASS', 'DATABASE LINK', 'TYPE BODY', 'TRIGGER', 'JAVA RESOURCE')
   --AND NOT EXISTS (SELECT T3.OWNER FROM DBA_SYNONYMS T3 WHERE T3.OWNER = 'PUBLIC' AND T3.SYNONYM_NAME = T1.OBJECT_NAME) --CREATE PUBLIC SYNONYM
   AND NOT EXISTS (SELECT T3.GRANTEE
                     FROM DBA_TAB_PRIVS T3
                    WHERE T3.OWNER = T1.OWNER
                      AND T3.GRANTEE = T2.TRG_OWNER
                      AND T3.TABLE_NAME = T1.OBJECT_NAME
                      AND T3.PRIVILEGE IN ('SELECT', 'ALTER'))                                                                                                                                   --GRANT
;

--# SKCCPTBHR 계정으로 PUBLIC SYNONYM을 주고 다른 계정에게 EXECUTE 을 부여
WITH TRG_OWNER_LIST AS (SELECT 'SKCCPTBHR' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_WAS' TRG_OWNER FROM DUAL
                        UNION ALL
                        SELECT 'SKCCPTBHR_DEV' TRG_OWNER FROM DUAL)
SELECT --'CREATE PUBLIC SYNONYM ' || T1.OBJECT_NAME || ' FOR '|| T1.OWNER || '.' || T1.OBJECT_NAME || ';' SQL_SYNONYM
       'GRANT EXECUTE ON ' || T1.OWNER || '.' || T1.OBJECT_NAME || ' TO ' || T2.TRG_OWNER || ' ;' SQL_VIEW_SEQ
  --'DROP SYNONYM ' || T1.OBJECT_NAME || ';' SQL_DROP_SYNONYM
  --'REVOKE EXECUTE ON ' || T1.OBJECT_NAME || ' FROM ' || T1.OWNER || ';' SQL_REVOKE_GRANT
  --'REVOKE EXECUTE ON ' || T1.OBJECT_NAME || ' FROM ' || T2.TRG_OWNER || ';' SQL_REVOKE_GRANT
  FROM DBA_OBJECTS T1
      ,TRG_OWNER_LIST T2
 WHERE T1.OWNER = 'SKCCPTBHR'
   --AND T2.TRG_OWNER IN ('SKCCPTBHR') --CREATE PUBLIC SYNONYM
   AND T2.TRG_OWNER NOT IN ('SKCCPTBHR')                                                                                                                                                         --GRANT
   AND T1.OBJECT_TYPE IN ('PACKAGE', 'FUNCTION', 'PROCEDURE', 'PACKAGE BODY', 'TYPE', 'TYPE BODY')
   AND T1.OBJECT_TYPE NOT IN ('TABLE', 'SEQUENCE', 'VIEW')
   AND T1.OBJECT_TYPE NOT IN ('SYNONYM', 'INDEX', 'LOB', 'JAVA CLASS', 'DATABASE LINK', 'TRIGGER', 'JAVA RESOURCE')
   --AND NOT EXISTS (SELECT T3.OWNER FROM DBA_SYNONYMS T3 WHERE T3.OWNER = 'PUBLIC' AND T3.SYNONYM_NAME = T1.OBJECT_NAME) --CREATE PUBLIC SYNONYM
   AND NOT EXISTS (SELECT T3.GRANTEE
                     FROM DBA_TAB_PRIVS T3
                    WHERE T3.OWNER = T1.OWNER
                      AND T3.GRANTEE = T2.TRG_OWNER
                      AND T3.TABLE_NAME = T1.OBJECT_NAME
                      AND T3.PRIVILEGE = 'EXECUTE')                                                                                                                                              --GRANT
;