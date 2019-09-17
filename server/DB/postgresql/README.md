# PostgreSQL 설치
> 에디터는 `vim` 사용

## root(super user) 접속
- `su`
  - input password

<br>

## PostgreSQL 설치
- `apt-get install postgresql -y`

<br>

## 원격접속을 위해 슈퍼 계정에 패스워드 설정
- `sudo –u postgres psql`

- postgres=# `ALTER USER postgres WITH ENCRYPTED PASSWORD ‘패스워드’;`

<br>

## 원격 접근을 위한 파일 수정
- `sudo vim /etc/postgresql/9.1/main/postgresql.conf`
    ```
    #listen_addresses = ‘localhost’
    listen_addresses = ‘*’ 로 수정
    ```

- `sudo vim /etc/postgresql/9.1/main/pg_hba.conf`
    ```sh
    # Database administrative login by Unix domain socket
    local   all             postgres                                trust

    # TYPE  DATABASE        USER            ADDRESS                 METHOD
    local   all             all                                     md5

    # "local" is for Unix domain socket connections only
    local   all             all                                     peer
    # IPv4 local connections:
    host    all             all             127.0.0.1/32            md5
    # IPv6 local connections:
    host    all             all             ::1/128                 md5
    # Allow replication connections from localhost, by a user with the
    # replication privilege.
    #local   replication     all                                     peer
    #host    replication     all             127.0.0.1/32            md5
    #host    replication     all             ::1/128                 md5

    host    all             all             0.0.0.0/0               md5
    ```