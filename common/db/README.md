# Oracle Database
## TEMP01.DBF 용량 변경
```sql
temp를 어떤파일로 어떤티비이름으로 쓰는지 확인하고
select * from dba_temp_files;

# 임시로 템프티비 하나 생성하고
create temporary tablespace imsi_temp
tempfile '/opt/oracle/oradata/ORCL/imsi_temp.dbf' size 1024M reuse
autoextend on next 1M maxsize unlimited extent management local uniform size 1M;

# 기본 템프티비를 새로 생성한 임시템프로 지정
alter database default temporary tablespace imsi_temp;

# 기존 템프티비 삭제
drop tablespace temp including contents and datafiles;

# 실제 사용할 템프티비 다시 생성하고
create temporary tablespace temp
tempfile '/opt/oracle/oradata/ORCL/temp01.dbf' size 1024M reuse
autoextend on next 1M maxsize unlimited extent management local uniform size 1M;

# 기본 템프티비를 다시 생성한 실제 티비로 지정
alter database default temporary tablespace temp;

# 임시로 생성했던 임시템프티비를 삭제
drop tablespace imsi_temp including contents and datafiles;

# 잘되었나 확인하작
select * from dba_temp_files;
```

<hr>
<br>

## UNDOTBS01.DBF 용량 변경
```sql
# undo tablespace를 확인
select tablespace_name, contents, extent_management
from dba_tablespaces 
where contents = 'UNDO';

# undo tablespace에 설정된 rollback segment를 확인 
select segment_name, tablespace_name, status
from dba_rollback_segs
order by 2;

# 임시로 새로운 빈 테이블스페이스 만들어주고
CREATE UNDO TABLESPACE UNDOTBS2 DATAFILE 
  '/Oracle/oradata/orcl/undotbs02.dbf' SIZE 10M;

# 그걸로 사용하게 바꿔주고
ALTER SYSTEM SET UNDO_TABLESPACE=UNDOTBS2;

# 기존 undo 테이블스페이스 지워주고
DROP TABLESPACE UNDOTBS1 INCLUDING CONTENTS AND DATAFILES;

# 지운 기존 undo 테이블스페이스명으로 새로 하나 만들어주고
CREATE UNDO TABLESPACE UNDOTBS1 DATAFILE 
'/Oracle/oradata/orcl/undotbs01.dbf' SIZE 10M REUSE AUTOEXTEND ON NEXT 5M MAXSIZE UNLIMITED
ONLINE
RETENTION NOGUARANTEE
BLOCKSIZE 8K
FLASHBACK ON;

# 방금 새로 만든걸로 사용하게 바꿔주고
ALTER SYSTEM SET UNDO_TABLESPACE=UNDOTBS1;

# 임시로 만들어준 테이블스페이스 날려준다.
DROP TABLESPACE UNDOTBS2 INCLUDING CONTENTS AND DATAFILES;
```

<br>
<hr>

## 데이터베이스 전체 테이블 비우기
```sql
declare

begin

for c1 in (select table_name, constraint_name from user_constraints) loop
    begin
        execute immediate ('alter table '||c1.table_name||' disable constraint '||c1.constraint_name);
    end;
end loop;

for t1 in (select table_name from user_tables) loop
    begin
        execute immediate ('truncate table '||t1.table_name);
    end;
end loop;

for c2 in (select table_name, constraint_name from user_constraints) loop
    begin
        execute immediate ('alter table '||c2.table_name||' enable constraint '||c2.constraint_name);
    end;
end loop;

end;
/
```

<br>
<hr>

## 오브젝트 DDL 확인
```sql
-- 1
  SELECT OBJECT_TYPE
        ,OBJECT_NAME
        ,STATUS
        ,TO_CHAR(CREATED, 'YYYY.MM.DD') AS CREATED
        ,TO_CHAR(LAST_DDL_TIME, 'YYYY.MM.DD') AS LAST_DDL_TIME
    FROM USER_OBJECTS
   WHERE OBJECT_TYPE IN ('FUNCTION'
                        ,'PROCEDURE')
     AND TO_CHAR(LAST_DDL_TIME, 'YYYYMMDD') > '20151006'
     AND OBJECT_NAME = 'P_PM2_COPY_APP_BAS_SET'
ORDER BY OBJECT_TYPE
        ,OBJECT_NAME;
        
-- 2
  SELECT TYPE
        ,NAME
        ,LINE
        ,TEXT
    FROM USER_SOURCE B
   WHERE TYPE IN ('FUNCTION'
                 ,'PROCEDURE')
     AND TEXT LIKE '%''P_PM2_COPY_APP_BAS_SET''%'
ORDER BY TYPE
        ,NAME
        ,LINE;
```
