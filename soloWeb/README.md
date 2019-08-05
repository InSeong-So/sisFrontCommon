# soloWeb Drill
## 애로사항
- web.xml이 존재하지 않을 때
  - 프로젝트 마우스 오른쪽 클릭
  - Java EE Tools > Generate Deployment Descriptor Stub
  - 기본적인 web.xml 파일이 생성됨
  
## web.xml 설정
web.xml이란...

Web Application의 구조
                                               Root                          
                                                  |
 
html                  img                 WEB_INF(web.xml)
 
                                                |             |            |
 
                                        classes          tld         lib
 
DD:(Deployment Descriptor)로 각 어플리케이션의 환경을 설정하는 부분을 담당한다. (deploy)'배치'

      WAR 파일이 패키지 될 때 같이 포함되며 root directory 밑에 /WEB-INF 디렉토리에 위치한다.

web.xml 의 구조

xml 정의와 schema 선언

<?xml version="1.0" encoding="EUC-KR"?>

<!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
    "http://java.sun.com/dtd/web-app_2_3.dtd>
위 스키마는 sun 사에서 미리 정의된것이다.


웹 어플리케이션의 환경 설정

<web-app>    <servlet>
     <servlet-name>       사용되는 클래스명         </servlet-name> "가"
      <servlet-class>클래스 경로(패키지명.클래스) </servlet-class> "나"
    </servlet>
    <servlet-mapping>
      <servlet-name>       사용되는 클래스명            <servlet-name> "다"
      <url-pattern>서버에서 사용할 url-pattern ( /url )</url-pattern> "라"
   </servlet-mapping>
실행순서.  라 -->다--->가--->나(패키지명.클래스 실행)
 
 
 
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

web.xml은 xml파일이다. 따라서 xml 작성과 동일한 규칙이 적용된다.
환경설정은 <web-app>으로 시작하고 </web-app>로 끝난다. 그외 삽입되는 요소로는 다음과 같다.

.ServletContext Init Parameters
.Session Configuration
.Servlet/JSP Definitions
.Servlet/JSP Mappings
.Mime Type Mappings
.Welcom File list
.Error Pages


web.xml의 elements의 순서각 element의 순서는 아래 순서에 따른다.

<icon?>,
<display-name?>,
<description?>,
<distributable?>,
<context-param*>,
<filter*>,
<filter-mapping*>,
<listener*>,
<servlet*>,
<servlet-mapping*>,
<session-config?>,
<mime-mapping*>,
<welcome-file-list?>,
<error-page*>,
<taglib*>,
<resource-env-ref*>,
<resource-ref*>,
<security-constraint*>,
<login-config?>,
<security-role*>,
<env-entry*>,
<ejb-ref*>,
<ejb-local-ref*>

자주 쓰이는 elements 예제

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



invoker 서블릿의 매핑이 보안문제로 막혀있어서 발생하는 문제로 $CATALINA_HOME/conf/web.xml를 열고 해당 부분의 주석을 제거한다.

<!-- The mapping for the invoker servlet -->
<servlet-mapping>
  <servlet-name>invoker</servlet-name>
  <url-pattern>/servlet/*</url-pattern>
</servlet-mapping>

security-constraint 엘리먼트를 $CATALINA_HOME/conf/web.xml 파일의 welcome-file-list 엘리먼트 아래쪽 <web-app> 에 중첩되게 복사합니다.

<welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
</welcome-file-list>
 
<security-constraint>
  <display-name>Default Servlet</display-name>
  <!-- Disable direct alls on the Default Servlet -->
  <web-resource-collection>
    <web-resource-name>Disallowed Location</web-resource-name>
    <url-pattern>/servlet/org.apac.
