# soloWeb Drill
## 애로사항
- web.xml이 존재하지 않을 때 해결방법
  - 프로젝트 마우스 오른쪽 클릭
  - Java EE Tools > Generate Deployment Descriptor Stub
  - 기본적인 web.xml 파일이 생성됨

<br>
<hr>

## web.xml
- Web Application의 구조
  ```
                        Root                          
                        │
  html ─ img ─┬──── WEB_INF(web.xml) ────┐
              │          │               │
          classes      tld             lib
  ```

- DD(Deployment Descriptor), 배치파일 : 각 어플리케이션의 환경설정을 담당
  - WAR 파일이 패키지 될 때 같이 포함되며 ROOT Directory 밑 /WEB-INF 에 위치

<br>
<hr>

### web.xml 설정
- xml 정의와 schema 선언
  ```xml
  <?xml version="1.0" encoding="EUC-KR"?>
  <!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
      "http://java.sun.com/dtd/web-app_2_3.dtd>
  <!-- 위 스키마는 sun 사에서 미리 정의된 것 -->

  <!-- 웹 어플리케이션의 환경 설정 -->
  <!-- 실행순서 : 라 → 다 → 가 → 나(패키지명.클래스 실행) -->
  <web-app>
    <servlet>
      <servlet-name>사용되는 클래스명</servlet-name><!-- "가" -->
      <servlet-class>클래스 경로(패키지명.클래스)</servlet-class><!-- "나" -->
    </servlet>
    <servlet-mapping>
      <servlet-name>사용되는 클래스명<servlet-name><!-- "다" -->
      <url-pattern>서버에서 사용할 url-pattern(/url)</url-pattern><!-- "라" -->
    </servlet-mapping>
    <mime-mapping>
      <extension>txt</extension>
      <mime-type>text/plain</mime-type>
    </mime-mapping>
      <welcome-file-list>
        <welcome-file>기본 파일 경로</welcome-file>
        <welcome-file>두번째 시작하는 파일 경로</welcome-file>
      </welcome-file-list>
      <taglib>
        <taglib-uri>태그라이브러리</taglib-uri>
        <taglib-location>경로</taglib-location>
      </taglib>
  </web-app>
  ```

<br>
<hr>

### web.xml 요소 순서
- 각 요소는 아래 순서에 따른다.
  ```xml
  <icon></icon>
  <display-name></display-name>
  <description></description>
  <distributable></distributable>
  <context-param></context-param>
  <filter></filter>
  <filter-mapping></filter-mapping>
  <listener></listener>
  <servlet></servlet>
  <servlet-mapping></servlet-mapping>
  <session-config></session-config>
  <mime-mapping></mime-mapping>
  <welcome-file-list></welcome-file-list>
  <error-page></error-page>
  <taglib></taglib>
  <resource-env-ref></resource-env-ref>
  <resource-ref></resource-ref>
  <security-constraint></security-constraint>
  <login-config></login-config>
  <security-role></security-role>
  <env-entry></env-entry>
  <ejb-ref></ejb-ref>
  <ejb-local-ref></ejb-local-ref>
  ```

<br>
<hr>

### 자주 쓰이는 elements 예제
```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
    "http://java.sun.com/dtd/web-app_2_3.dtd>

<web-app>
  <display-name>어플리케이션 이름</display-name>
  <description>어플리케이션 설명</desccription>
  <!-- 서블릿 매핑 : 보안과 주소를 간략화 하기 위해 사용
      http://localhost/servlet/KCount 이렇게 사용가능  -->
  <servlet>
    <servlet-name>KCount</servlet-name>
    <servlet-class>kr.pe.kkaok.mycount.KCount</servlet-class>
  </servlet>
  <!-- load-on-startup 옵션은 서버 구동시 자동으로 시작 되도록 하는 것이다. -->
  <servlet>
    <servlet-name>PoolManager</servlet-name>
    <servlet-class>kr.pe.kkaok.jdbc.PoolManager</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <!-- 서블릿 매핑 : 위에서 servlet 부분을 삭제한다.
      http://localhost/KCount 이렇게 사용가능  -->
  <servlet-mapping>
    <servlet-name>KCount</servlet-name>
    <url-pattern>/KCount</url-pattern>
  </servlet-mapping>
  <!-- /servlet/* 과 동일한 패턴의 요청이 들어오면 servlet으로 처리 -->
  <servlet-mapping>
    <servlet-name>invoker</servlet-name>
    <url-pattern>/servlet/*</url-pattern>
  </servlet-mapping>
  <!-- 세션 기간 설정 -->
  <session-config>
    <session-timeout>
      30
    </session-timeout>
  </session-config>
  <!-- mime 매핑 -->
  <mime-mapping>
    <extension>txt</extension>
    <mime-type>text/plain</mime-type>
  </mime-mapping>
  <!-- 시작페이지 설정 -->
  <welcome-file-list>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>index.html</welcome-file>
  </welcome-file-list>
  <!-- 존재하지 않는 페이지, 404에러시 처리 페이지 설정 -->
  <error-page>
    <error-code>404</error-code>
    <location>/error.jsp</location>
  </error-page>
  <!-- 태그 라이브러리 설정 -->
  <taglib>
    <taglib-uri>taglibs</taglib-uri>
    <taglib-location>/WEB-INF/taglibs-cache.tld</taglib-location>
  </taglib>
  <!-- resource 설정 -->
  <resource-ref>
    <res-ref-name>jdbc/jack1972</res-ref-name>
    <res-type>javax.sql.DataSource</res-type>
    <res-auth>Container</res-auth>
  </resource-ref>
</web-app>
```

<br>
<hr>