# 라즈베리 파이로 개인 서버 구축하기
> 버전 : Raspberry Pi 3B+
>> Micro SD Card : 16GB

# 목차

# 운영체제 설치
## [라즈비안 이미지 파일](https://www.raspberrypi.org/downloads/raspbian/)
- `Desktop` : GUI를 포함한 여러 모듈 추가 버전, `Lite` : Minimal 버전

<br>

## [SD카드 이미지 Write 프로그램](https://etcher.io)
- 실행 후 다운받은 라즈비안 이미지 파일 Select, SD카드 Select 후 Flash 시작
  - Flash가 완료되면 SD카드 제거 후 재삽입

<br>

## boot(E): 디스크에 파일 생성
### SSH enable 설정 파일
- SSH enable 을 위해 파일명이 `ssh`인 파일을 만들어 boot 드라이브 최상위 폴더에 위치시킨다.

- ssh 파일은 확장자가 없어야 하며 파일은 비어있어도 된다.

- RASIBIAN이 부팅 시, boot 파티션에 ssh 파일이 존재하면 이를 인식하고 ssh enable을 설정한다.

<br>

### WiFi 접속 설정 파일
```conf
ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev
update_config=1
network={
    ssid="접속할 WIFI 이름"
    psk="접속할 WIFI 암호"
}
```

- boot 드라이브 최상위 폴더에 `wpa_supplicant.conf` 파일을 생성하고 아래 내용을 파일에 기재한다.

- network 란에 접속할 WiFi 이름과 암호를 기재한다(따옴표는 남길 것).

- 라즈베리파이3 B+ 모델은 랜포트가 있기에 랜선을 사용하려면 WiFi 접속 설정 파일은 필요없다.

- 라즈베리파이 제로W 모델은 랜포트가 없기 때문에 WiFi 접속 설정이 필요하다.

<br>

## 라즈베리 파이 부팅 후 SSH 접속
- SD 카드를 라즈베리 파이에 삽입하고 전원 연결

- 약 1분 대기 후 부팅이 완료되면 공유기 관리(일반적으로 192.168.0.1)에 접속하여 해당 라즈베리 파이 IP를 확인

- 확인된 IP로 ssh를 이용하여 접속

- 라즈베리파이는 기본 id와 password가 정해져 있으므로 반드시 본인 암호로 교체할 것

- 라즈베리파이 초기 암호(default id, password)
    ```
    id : pi
    password : raspberry
    ```

<br>

## shell에서 라즈베리 파이 설정 메뉴 실행
- `sudo raspi-config`
  - 비밀번호 변경 등 여러 설정을 진행할 수 있음

<br>

## [Ref. Pi Guide: 라즈베리파이 이야기 한눈에 보기](https://geeksvoyage.com/pi-guide/)

<br>

# 네트워크 설정
## SSH 원격 접속
- 터미널 프로그램으로 ssh port 22번을 사용하여 접속
  - ssh 초기 설정 port 번호는 22번이며 변경 가능

- 패키지 버전 정보 최신 업데이트(root 계정으로 실행)
  - `sudo apt-get update -y && apt-get upgrade -y`

<br>

## 리눅스 SSH를 통한 해킹 방어
### ssh port 변경
- `sudo vi /etc/ssh/sshd_config`

- sshd_config 파일의 Port 항목을 원하는 번호로 변경
  - 이미 사용 중인 번호는 피해야 함

    ```sh
    # This sshd was compiled with PATH=/usr/bin:/bin:/usr/sbin:/sbin

    # The strategy used for options in the default sshd_config shipped with
    # OpenSSH is to specify options with their default value where
    # possible, but leave them commented.  Uncommented options override the
    # default value.

    #Port 22
    Port XXXXX # 원하는 포트 번호
    #AddressFamily any
    #ListenAddress 0.0.0.0
    #ListenAddress ::

    #HostKey /etc/ssh/ssh_host_rsa_key
    #HostKey /etc/ssh/ssh_host_ecdsa_key
    ```

### services 파일 port 변경
- `sudo vi /etc/services`
  - 리눅스 services 파일에는 모든 네트워크 서비스의 포트 번호와 용도가 기재되어 있음
  - 기본적으로 네트워크 프로그램들은 모두 이 파일(/etc/services)의 서비스/포트번호/프로토콜을 확인
  - 따라서 services 파일의 ssh 항목도 위에서 변경한 포트 번호로 동일하게 변경

    ```sh
    qotd		17/tcp		quote
    msp		    18/tcp				    
    msp		    18/udp
    chargen		19/tcp		ttytst source
    chargen		19/udp		ttytst source
    ftp-data	20/tcp
    ftp		    21/tcp
    fsp		    21/udp		fspd
    ssh		    XXXXX/tcp	## 여기 변경
    telnet		23/tcp
    smtp		25/tcp		mail
    time		37/tcp		timserver
    time		37/udp		timserver
    rlp		    39/udp		resource	
    nameserver	42/tcp		name		
    whois		43/tcp		nicname
    tacacs		49/tcp				    
    ```

- 설정이 완료되면 라즈베리 파이 재부팅
  - `sudo reboot`

<br>

### fail2ban 설치
- fail2ban은 시스템 로그를 모니터링한다.
  - 일정 횟수의 로그인 실패 시 해당 IP를 iptables 명령으로 차단
  - 침입자가 brute-force 공격을 시도할 경우의 대비책임

- `sudo apt install fail2ban -y`

- `sudo cp /etc/fail2ban/jail.conf /etc/fail2ban/jail.local`

- `sudo vi /etc/fail2ban/jail.local`

- jail.local 파일의 `[sshd]` 항목을 찾아 기재
  - `port` : ssh 기본 포트가 기록되어 있으며 변경한 포트 번호로 교체
  - `logpath` : %(sshd_log) 변수로 되어 있으며 이 변수가 지정하는 파일이 /var/log/auth.log이다. auth.log 파일을 모니터링하여 login fail 여부를 확인
  - `maxretry` : 허용할 fail 횟수. 이를 초과하면 bantime만큼 차단
  - `bantime` : 차단을 수행할 초 단위의 시간. -1은 영구적인 차단을 의미

- `sudo service fail2ban restart`

- `sudo iptables -L`

- `sudo tail -10 /var/log/fail2ban.log` : 확인

- `sudo iptables -L`

<br>

# 추가 설정
## 기기 이름 변경
- 라즈베리파이의 기본 기기 이름(hostname)은 raspberrypi

- `sudo raspi-config`
  - `Network Options` 선택
  - `Hostname` 선택
  - 변경하고자 하는 Hostname 기재 후 OK

- `sudo reboot`

<br>

## root 계정 비밀번호 설정
- `sudo passwd root`

<br>

## vim 설치
- `sudo apt-get install vim -y`

- `sudo vim /etc/vim/vimrc`
    ```sh
    set number              # line 표시를 해줍니다.
    set ai                  # auto index
    set si                  # smart index
    set cindent             # c style index
    set shiftwidth=4        # shift를 4칸으로 ( >, >>, <, << 등의 명령어)
    set tabstop=4           # tab을 4칸으로
    set ignorecase          # 검색시 대소문자 구별하지않음
    set hlsearch            # 검색시 하이라이트(색상 강조)
    set expandtab           # tab 대신 띄어쓰기로
    set background=dark   # 검정배경을 사용할 때, (이 색상에 맞춰 문법 하이라이트 색상이 달라집니다.)
    set nocompatible        # 방향키로 이동가능
    set fileencodings=utf-8,euc-kr    # 파일인코딩 형식 지정
    set bs=indent,eol,start    # backspace 키 사용 가능
    set history=1000        # 히스토리를 1000개까지 출력
    set ruler               # 상태표시줄에 커서의 위치 표시
    set nobackup            # 백업파일을 만들지 않음
    set title               # 제목을 표시
    set showmatch           # 매칭되는 괄호를 보여줌
    set nowrap              # 자동 줄바꿈 하지 않음
    set wmnu                # tab 자동완성 가능한 목록을 출력

    syntax on               # 문법 하이라이트 킴
    ```

- [참고1](http://norus.tistory.com/13)

- [참고2](http://egloos.zum.com/daftcoder/v/245008)

<br>

## 스크린 세이버 설정
- 라즈베리파이는 기본 이미지에 스크린 세이버가 설정되어 있음
  - 일정 시간(10분) 사용자의 입력이 없을 경우, 자동으로 화면이 꺼짐

- 스크린 세이버
  - `sudo apt install xscreensaver -y`

- 커널 cmdline
  - `sudo vi /boot/cmdline.txt`
  - `consoleblank=0`을 행의 마지막에 추가

<br>

## IP 접속 주소(지역) 확인
- `sudo apt install jwhois -y`

- `whois [ip]`

<br>

## IP 주소 확인
- `curl bot.whatismyipaddress.com` : 공인 IP 확인

- `ifconfig` 또는 `ipconfig` : 내부 IP 확인

<br>

## 방화벽 설치
- `sudo apt install ufw -y`

- `sudo ufw status` : 상태 확인

- `sudo ufw enable` : 방화벽 활성화

- `sudo ufw allow [port]` : 방화벽 포트 해제

<br>

# [PostgreSQL 설치](sisFrontCommon/server/DB/postgresql/README.md)

<br>

# 라즈베리 파이에 외장하드로 NAS 구성하기
- NTFS 시스템 패키지 설치
  - `sudo apt-get install ntfs-3g -y`

- 외장하드 UUID 정보 확인
  - sudo blkid

- 마운트 디렉토리 생성
  - mkdir /home/pi/hdd_storage

- 외장하드 마운트
  - sudo mount /dev/sda1 /home/pi/hdd_storage

- 부팅 후에도 마운트 유지 설정
  - sudo vim /etc/fstab
  - UUID=[]