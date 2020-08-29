설정 엘리먼트에 대한 설명은 아래과 같은 주요 카테고리로 분류됩니다:

최상위 엘리먼트(Top Level Elements) :: <Server>는 전체 설정 파일의 최상위 엘리먼트이며,
<Service>는 하나의 Engine과 결합된 Connector들의 모임을 나타냅니다.
-- Server, Service

커넥터(Connectors) :: 어떤 특정 Service에 요청을 보내는(그리고 응답을 받는)
외부 클라이언트들과의 연결점(interface)을 나타냅니다.
-- JTC Connectors, Coyote HTTP/1.1, Coyote JK 2, HTTP/1.1, JK, Webapp

컨테이너(Containers) :: 수신된 요청을 처리하고 그에 따른 응답을 생성하는 기능을 하는
컴포넌트들을 나타냅니다. 하나의 Engine은 하나의 Service에 대한 모든 요청을 다루며,
하나의 Host는 특정 가상호스트에 대한 모든 요청을 다룹니다. 그리고 하나의 Context는
특정 웹어플리케이션에 대한 모든 요청을 다룹니다.
-- Context, Engine, Host

내부 컴포넌트(Nested Components) :: Container에 속하는 어떤 엘리먼트 안에 중첩되어야
하는 엘리먼트입니다. 어떤 엘리먼트는 모든 Container 엘리먼트 안에 중첩될 수도 있고,
어떤 것은 Context 안에만 중첩가능합니다.
-- Default Context, Global Resources, Loader, Logger, Manager,Realm, Resources, Valve

-->

<!-- Example Server Configuration File -->
<!-- Note: 컴포넌트들은 각각의 부모-자식 관계에 따라 중첩(nested) 되었음 -->

<!-- "Server" 는 전체 JVM 을 나타내는 싱글톤 요소입니다. 이것은 하나 이상의
    "Service" 인스턴스를 갖고 있습니다. 서버는 지정된 포트를 통해 shutdown
    명령을 받습니다.

    Note: "Server" 는 스스로가 "Container" 가 아니기 때문에, "Valves" 또는
    "Loggers" 같은 서브 컴포넌트를 "Server" 와 같은 레벨에서 정의하면 안됩
    니다.
-->

<Server port="8005" shutdown="SHUTDOWN" debug="0">


  <!-- "Service" 는 한 개의 "Container" 를 공유하는 하나 이상의 "Connectors"
      의 집합체입니다. (이 컨테이너 안에서 웹어플리케이션이 돌아갑니다). 보통
      은, 이 컨테이너가 "Engine" 입니다만, 요구되지는 않습니다. 

      Note:  "Service" 는 스스로가 "Container" 가 아니기 때문에, "Valves"
      또는 "Loggers" 같은 서브 컴포넌트를 "Server" 와 같은 레벨에서 정의하면
      안됩니다.
  -->


  <Listener className="org.apache.catalina.mbeans.ServerLifecycleListener"
           debug="0"/>
  <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener"
           debug="0"/>

<!--
 GlobalNamingResources 엘리먼트는 JNDI의 전역자원을 나타내며,
 이 자원들은 Server 안에서 정의됩니다.

 웹애플리케이션에서 환경항목자원(environment entry resources)으로 사용할 수
 있도록 항목의 이름-값 들을 설정할 수 있습니다. 이 설정은 <Environment> 항목을
 이 엘리먼트 내에 중첩시키면 됩니다.
 
 예를 들어 아래와 같이 환경항목을 생성할 수 있습니다:
 
  <GlobalNamingResources ...>
   ...
   <Environment name="maxExemptions" value="10"
    type="java.lang.Integer" override="false"/>
   ...
 </GlobalNamingResources>

 이 설정은 웹애플리케이션 배치 디스크립터(/WEB-INF/web.xml)에서
 다음의 엘리먼트를 포함시킨 것과 동일합니다:
   
  <env-entry>
   <env-entry-name>maxExemptions</param-name>
   <env-entry-value>10</env-entry-value>
   <env-entry-type>java.lang.Integer</env-entry-type>
 </env-entry>

 그러나 이 값을 커스터마이즈하기 위해 배치 디스크립터를 변경할 필요는 없습니다.


 <Environment> 엘리먼트 속성값 --
 
 description :: (선택사항)이 환경항목에 대한 사람이 읽기 쉬운 간단한 설명
 
 name :: (반드시 설정) 생성할 환경항목의 이름.
 java:comp/env 컨텍스트에 대한 상대적인 이름입니다.
 
 override :: 웹애플리케이션 배치 디스크립터에서 <env-entry>으로 같은 이름의
 환경항목을 지정하는 경우, 여기에서 지정한 값을 덮어쓰기(override) 하지 않도록
 하고 싶으면 false로 지정합니다. 이 값을 지정하지 않으면 덮어쓰기가 허용됩니다.
 
 type :: (반드시 설정) 이 환경항목에 대해 웹애플리케이션이 예상하는 완전한(fully qualified) Java 클래스명.
 반드시 웹애플리케이션 배치 디스크립터의 <env-entry-type>의 규칙에 맞는 값이어야 합니다.
 그 규칙에 맞는 값 들은: java.lang.Boolean, java.lang.Byte, java.lang.Character,
 java.lang.Double, java.lang.Float, java.lang.Integer, java.lang.Long, java.lang.Short,
 또는 java.lang.String 입니다.
 
 value :: (반드시 설정) 웹애플리케이션이 JNDI 컨텍스트로부터 요청해서 반환받을 환경항목의 값.
 이 값은 반드시 위의 type에 정의된 Java type으로 변환 가능해야 합니다.
 
 
 Resource Definitions
 웹애플리케이션 배치 디스크립터의 <resource-ref>와 <resource-env-ref> 엘리먼트의
 JNDI 탐색(lookup)에 대해 반환될 자원의 특성도 선언 가능합니다.
 그리고 어떤 자원에 대해서 객체팩토리를 사용하도록(Tomcat이 아직 모르는 경우),
 그리고 그 객체팩토리를 설정하는데 사용할 프로퍼티를 설정하려면, 반드시 그 자원과 같은 이름으로
 자원 파라미터(Resource Parameters)를 추가로 정의해야 합니다.

 예를 들어 다음과 같이 자원정의가 가능합니다:
 
  <GlobalNamingResources ...>
   ...
   <Resource name="jdbc/EmployeeDB" auth="Container"
    type="javax.sql.DataSource"
   description="Employees Database for HR Applications"/>
   ...
 </GlobalNamingResources>

 이 설정은 웹애플리케이션 배치 디스크립터(/WEB-INF/web.xml)에
 다음의 엘리먼트를 포함시킨 것과 동일합니다:
   
  <resource-ref>
   <description>Employees Database for HR Applications</description>
   <res-ref-name>jdbc/EmployeeDB</res-ref-name>
   <res-ref-type>javax.sql.DataSource</res-ref-type>
   <res-auth>Container</res-auth>
 </resource-ref>

  그러나 이 값을 커스터마이즈하기 위해 배치 디스크립터를 변경할 필요는 없습니다.


 <Resource> 엘리먼트 속성값 ---

 auth :: 해당 자원관리자에 인증(sign on)할 때 웹애플리케이션 프로그램의 코드상에서 직접 인증할지,
 또는 애플리케이션의 작동(behalf)에 따라 컨테이너가 직접 인증할지를 지정합니다.
 이 속성의 값은 반드시 Application 또는 Container 중 하나여야 합니다.
 이 속성은, 웹애플리케이션이 웹애플리케이션 배치 디스크립터에서 <resource-ref> 엘리먼트를
 사용하는 경우에는 반드시 필요합니다. 그러나 <resource-env-ref>를 대신 사용하는 경우에는 선택사항입니다.
 
 description :: (선택사항)이 자원에 대한 사람이 읽기 쉬운 간단한 설명
 
 name :: (반드시설정) 생성할 자원의 이름. java:comp/env 컨텍스트에 대한 상대적인 이름입니다.
 
 scope :: 이 자원관리자를 통해 얻어진 연결(connection)의 공유를 허가할 것인지 지정합니다.
 이 속성의 값은 반드시 Shareable 또는 Unshareable 중 하나여야 합니다.
 지정하지 않으면 연결은 공유가능(shareable)이 됩니다.
 
 type :: (반드시설정) 웹애플리케이션이 이 자원에 대해 탐색을 실행할 때 받고자 하는
 Java 타입을 나타내는 완전한 Java 클래스명.
 
 
 Resource Parameters
 이 엘리먼트는 웹애플리케이션에서 해당 자원의 이름에 대해 JNDI 탐색을 수행할 때,
 객체를 반환하는데 사용할 자원관리자(또는 객체팩토리)를 설정하는 역할을 합니다.
 $CATALINA_HOME/conf/server.xml의 <Context>나 <DefaultContext> 엘리먼트 내의
 <Resource> 엘리먼트로 지정된 모든 자원 이름, 그리고/또는 웹애플리케이션 배치 디스크립터에서
 <resource-ref> 나 <resource-env-ref> 엘리먼트에서 선언된 모든 자원 이름에 대해서는 반드시
 자원 파라미터(resource parameters)를 정의해야 그 자원에 성공적으로 액세스할 수 있습니다.

 자원 파라미터는 이름으로 정의되며, 정확하게 어떤 파라미터 이름들의 집합을 지원하는가는 당신이
 사용하고 있는 자원관리자(또는 객체팩토리)에 따라 달라집니다.
 즉 해당 팩토리 클래스의 JavaBeans 프로퍼티 중 설정가능한(settable) 프로퍼티의 이름과 일치해야 합니다.
 JNDI 구현체는 지정한 팩토리 클래스의 인스턴스에 대해 JavaBeans의 모든 해당 속성의
 설정메소드(setter)를 호출함으로써 모든 설정을 마친 다음에야,
 이 팩토리 인스턴스를 JNDI lookup() 호출을 통해 사용가능하도록 할 것입니다.

 예로 JDBC 데이터 소스에 대한 자원 파라미터 설정은 아래와 같이 됩니다:
  
  <GlobalNamingResources ...>
   ...
   <ResourceParams name="jdbc/EmployeeDB">
  <parameter>
    <name>driverClassName</name>
    <value>org.hsql.jdbcDriver</value>
  </parameter>
  <parameter>
    <name>driverName</name>
    </value>jdbc:HypersonicSQL:database</value>
  </parameter>
  <parameter>
    <name>user</name>
    <value>dbusername</value>
  </parameter>
  <parameter>
    <name>password</name>
    <value>dbpassword</value>
  </parameter>
   </ResourceParams>
   ...
 </GlobalNamingResources>


 만약 특정 자원 타입에 대해 팩토리 클래스의 Java 클래스명을 지정할 필요가 있다면,
 <ResourceParams> 엘리먼트 내의 <parameter> 항목에 factory라는 이름을 사용하면 됩니다.


 <ResourceParams> 엘리먼트가 가질 수 있는 속성은 다음과 같습니다:

 name :: (반드시 설정) 설정할 자원의 이름이며, java:comp/env 컨텍스트에 대한 상대적인 이름이 됩니다.
 이 이름은 $CATALINA_HOME/conf/server.xml 내에 <Resource> 엘리먼트로 정의된 자원,
 그리고/또는 웹애플리케이션 배치 디스크립터 내에 <resource-ref> 또는 <resource-env-ref>로
 참조되는 자원의 이름과 반드시 일치해야 합니다.
 
-->

  <!-- Global JNDI resources -->
  <GlobalNamingResources>

   <!-- Test entry for demonstration purposes -->
   <Environment name="simpleValue" type="java.lang.Integer" value="30"/>

   <!-- Editable user database that can also be used by
        UserDatabaseRealm to authenticate users -->
   <Resource name="UserDatabase" auth="Container"
             type="org.apache.catalina.UserDatabase"
      description="User database that can be updated and saved">
   </Resource>
   <ResourceParams name="UserDatabase">
     <parameter>
       <name>factory</name>
       <value>org.apache.catalina.users.MemoryUserDatabaseFactory</value>
     </parameter>
     <parameter>
       <name>pathname</name>
       <value>conf/tomcat-users.xml</value>
     </parameter>
   </ResourceParams>

  </GlobalNamingResources>

  <!-- Tomcat Stand-Alone Service 로 설정하기 -->
  <Service name="Tomcat-Standalone">

   <!-- "Connector" 는 요청을 받아서, 응답이 반환되는 종점(endpoint)을 나타냅니
        다. 각 커넥터는 처리를 담당하는 관련된 "Container"(보통 "엔진")로 요청을
        전달해줍니다.

        기본값으로, 8080포트에 non-SSL HTTP/1.1 커넥터가 설정되어있습니다.
        SSL HTTP/1.1 커넥터 역시 사용하려면 아래에 있는 지시를 따라서 하고, 두번
        째 커넥터 엔트리의 주석표시를 지워주시면 됩니다. SSL 지원은 다음 단계를
        거쳐야 합니다:
        * JSSE 1.0.2 또는 이후 버전을 다운받아서 설치하고, JAR 파일들을
          "$JAVA_HOME/jre/lib/ext" 디렉토리에 복사해 놓습니다.
        * "$JAVA_HOME/jre/lib/security/java.security" 를 편집하고
          security.provider.2=com.sun.net.ssl.internal.ssl.Provider 를 추가합
          니다.
        * 실행: keytool -genkey -alias tomcat -keyalg RSA
          패스워드 값"changeit" 으로 실행합니다.

        기본적으로, DNS lookups 는 웹어플리케이션이 request.getRemoteHost() 를
        부를 때 동적하도록 되어있습니다. 이것은 성능에 영향을 줄 수 있기 때문에,
        "enableLookups" 속성을 "false" 로 바꿔주면 이 기능을 사용하지 않을 수
        있습니다.  DNS lookups 가 사용하지 않게 되면 request.getRemoteHost() 는
        remote client 의 IP 주소의 String 버전을 반환할 것입니다.
   -->

   <!-- 8080 포트에 non-SSL HTTP/1.1 Connector 설정하기 -->
   <Connector className="org.apache.catalina.connector.http.HttpConnector"
              port="80" minProcessors="5" maxProcessors="75"
              enableLookups="true" redirectPort="8443"
              acceptCount="10" debug="0" connectionTimeout="60000"/>
   <!-- Note : 커넥션 타임아웃을 사용하지 않으려면, connectionTimeout 값을 -1로
     수정해 주세요.-->

   <!-- 8443 포트에 SSL HTTP/1.1 Connector 설정하기 -->
   <!--
   <Connector className="org.apache.catalina.connector.http.HttpConnector"
              port="8443" minProcessors="5" maxProcessors="75"
              enableLookups="true"
       acceptCount="10" debug="0" scheme="https" secure="true">
     <Factory className="org.apache.catalina.net.SSLServerSocketFactory"
              clientAuth="false" protocol="TLS"/>
   </Connector>
   -->

   <!-- 8081 포트에 Proxied HTTP/1.1 Connector 설정하기 -->
   <!-- 사용법에 대한 자세한 내용은 proxy 문서를 보십시오. -->
   <!--
   <Connector className="org.apache.catalina.connector.http.HttpConnector"
              port="8081" minProcessors="5" maxProcessors="75"
              enableLookups="true"
              acceptCount="10" debug="0" connectionTimeout="60000"
              proxyPort="80"/>
   -->

   <!-- 8082 포트에 non-SSL HTTP/1.0 Test Connector 설정하기 -->
   <!--
   <Connector className="org.apache.catalina.connector.http10.HttpConnector"
              port="8082" minProcessors="5" maxProcessors="75"
              enableLookups="true" redirectPort="8443"
              acceptCount="10" debug="0"/>
   -->

   <!-- Engine 은 (Catalina 에서) 모든 요청을 처리하는 입력지점을 나타냅니다.
        Tomcat stand alone 용으로 구현된 Engine 은 요청에 포함된 HTTP 헤더를 분
        석하고, 적당한 Host (가상 호스트) 로 전달하는 역할을 합니다. -->

   <!-- 속성값
 defaultHost :: 디폴트 호스트명. 설정 파일에서는 정의되지 않았지만 이 서버 상에 있는 호스트명 중에서
 요청을 처리할 Host를 식별합니다. 이 이름은 반드시 바로 안에 중첩된 Host 엘리먼트 중
 하나의 name 속성과 일치해야 합니다.
 ※ 반드시 설정

 name :: 이 Engine의 논리적인 이름이며, 로그와 에러메시지에서 사용됩니다.
 ※ 반드시 설정

 className :: 사용할 구현체의 Java 클래스명.
 이 클래스는 반드시 org.apache.catalina.Engine를 구현해야 합니다.
 지정하지 않으면 표준값(아래에 정의됨)이 사용됩니다.
 
 jvmRoute :: 로드밸런싱 시나리오에서 세션유지를 위해서 반드시 사용해야 할 식별자.
 이 식별자는 클러스터에 참가하는 모든 Tomcat 4 서버에 대해 반드시 유일해야 합니다.
 생성되는 세션 식별자에는 이 식별자가 추가되어, 가장 앞단의 프록시가 특정 세션을
 항상 같은 Tomcat 4 인스턴스로 포워드 할 수 있게 합니다.
 
 debug :: 이 Engine이 해당 Logger 에 디버깅 로그를 출력하는 상세수준을 의미합니다.
 숫자가 높을 수록 더 자세한 출력을 생성합니다. 지정하지 않는 경우 디버깅 상세수준의 디폴트 값은 0 입니다.

-->

   <!-- 컨테이너 구조에서 top level 컨테이너 설정하기 -->
   <Engine name="Standalone" defaultHost="localhost" debug="0">

     <!-- 요청 dumper 밸브는 Tomcat 의 인스턴스의 모든 요청을 받는 동안 들어온
          요청 헤더와 쿠키, 보내질 응답 헤더와 쿠키에 대해 유용한 디버깅 정보를
          덤프합니다. 만일 특정한 가상호스트, 또는 특정한 어플리케이션에 들어온
          요청에만 만 관심을 갖고자 한다면, 이 요소를 해당하는 <Host> 나 <Context>
          엔트리 아래에 위치시켜주십시오.

          모든 서블릿 2.3 컨테이너에 유동적인 유사한 동작구조를 위해서, 예제
          어플리케이션에 있는 "RequestDumperFilter" 필터를 확인하십시오.
          (소스는 "$CATALINA_HOME/webapps/examples/WEB-INF/classes/filters"
          위치에 있을 것입니다.)

          기본적으로 Request dumping 기능은 사용하지 않는 것으로 되어있습니다.
          다음의 요소에서 주석을 빼면 사용할 수 있습니다. -->
     <!--
     <Valve className="org.apache.catalina.valves.RequestDumperValve"/>
     -->

     <!-- 하위수준에서 지정되지 않았다면 사용되는 Global Logger -->
  <!--
   이 Engine에 대한 모든 로그 메시지를 받아서 처리할 로거(logger)를 설정합니다.
   이 로거는 이 Engine 외에도 이 Engine이 정의된 Service의 Connectors로부터의
   로그도 처리합니다. 또 이 로거는 하위에서 Logger 설정을 따로 하지 않는 경우,
   하위의 Host와 Context에서의 로그도 처리합니다.
 -->
     <Logger className="org.apache.catalina.logger.FileLogger"
             prefix="catalina_log." suffix=".txt"
             timestamp="true"/>

     <!-- 이 Realm 이 여기에 있기 때문에, 인스턴스는 전체적으로 공유됩니다. -->
  <!--
  영역(realm)을 설정하여, 사용자와 사용자의 역할을 저장할 데이터베이스를
  이 Engine에 포함된 모든 Host와 Context 에서 공유할 수 있도록 합니다.
  하위에서 Realm 설정이 정의되면 여기의 설정은 무시됩니다.
  -->
     <Realm className="org.apache.catalina.realm.MemoryRealm" />

     <!-- 데이터베이스에 저장되고 JDBC 를 통해서 접근하는 Realm 을 사용하려면
          위 Realm 을 다음의 Realm 중 하나와 대치하십시오. -->

     <!--
     <Realm  className="org.apache.catalina.realm.JDBCRealm" debug="99"
            driverName="org.gjt.mm.mysql.Driver"
         connectionURL="jdbc:mysql://localhost/authority?user=test;password=test"
             userTable="users" userNameCol="user_name" userCredCol="user_pass"
         userRoleTable="user_roles" roleNameCol="role_name" />
     -->

     <!--
     <Realm  className="org.apache.catalina.realm.JDBCRealm" debug="99"
            driverName="oracle.jdbc.driver.OracleDriver"
         connectionURL="jdbc:oracle:thin:@ntserver:1521:ORCL?user=scott;password=tiger"
             userTable="users" userNameCol="user_name" userCredCol="user_pass"
         userRoleTable="user_roles" roleNameCol="role_name" />
     -->

     <!--
     <Realm  className="org.apache.catalina.realm.JDBCRealm" debug="99"
            driverName="sun.jdbc.odbc.JdbcOdbcDriver"
         connectionURL="jdbc:odbc:CATALINA"
             userTable="users" userNameCol="user_name" userCredCol="user_pass"
         userRoleTable="user_roles" roleNameCol="role_name" />
     -->
 
  <!--
  웹서버를 운영할 때 일반적으로 생성되는 출력 파일중 하나가 액세스 로그(access log)입니다.
  이 로그는 서버가 처리하는 각 요청마다 표준 포맷에 따라 한 라인씩 출력합니다.
  Catalina에서는 Valve 구현을 통해, 웹서버들이 표준 포맷에 따라 생성하는 액세스 로그와 같은
  포맷의 로그를 생성할 수도 있고, 또는 다양한 커스텀 포맷으로 로그를 생성할 수 있도록 하고 있습니다.
  당신은 Catalina에 Engine, Host, 또는 Context가 처리하는 모든 요청에 대한 액세스 로그를 생성하도록
  지시할 수 있는데, 이는 다음과 같이 Valve 엘리먼트를 중첩시키면 됩니다:

  <Valve className="org.apache.catalina.valves.AccessLogValve"
        prefix="catalina_access_log." suffix=".txt"
        pattern="common"/>

  -->

     <!-- default virtual host 설정하기 -->
  <!-- 속성값
  appBase :: 이 가상호스트에 대한 어플케이션의 기준(Application Base) 디렉토리.
  이는 이 가상호스트에 배치될 웹어플리케이션 들을 가지고 있는 디렉토리의 패스명입니다.
  이 디렉토리의 절대경로명을 지정할 수도 있으며, 또는 $CATALINA_HOME 디렉토리에
  상대적인 경로명을 지정할 수도 있습니다.
  ※ 반드시 설정

  autoDeploy :: 이 플래그 값은 이 호스트의 웹어플리케이션 들은
  호스트 설정자(host configurator)에 의해 자동으로 배치(deploy)되어야 함을 나타냅니다.
  이 플래그의 디폴트값은 true 입니다. 

  className :: 사용할 구현체의 Java 클래스명.
  이 클래스는 반드시 org.apache.catalina.Host 인터페이스를 구현해야 합니다.
  지정하지 않으면 표준값(아래에 정의됨)이 사용될 것입니다.

  name :: 당신의 Domain Name Service 서버에 등록된 이 가상호스트의 네트워크 명칭.
  Engine에 포함된 Host 중 하나는 반드시 그 Engine의 defaultHost 세팅과 일치하는 이름을 가져야 합니다.
  ※ 반드시 설정

  debug :: 이 Host가 해당 로거에 디버깅 로그를 출력하는 상세수준을 의미합니다.
  숫자가 높을 수록 더 자세한 출력을 생성합니다. 지정하지 않으면, 디버깅 상세수준의 디폴트 값은 0 입니다

  deployXML :: Context XML 설정 파일을 사용하여 어플리케이션을 배치하는 기능을
  끄고 싶다면 false로 지정하십시오. 배치되는 어플리케이션 들은 Catalina의
  보안권한(security permissions)가 주어집니다. 만약 신뢰할 수 없는(untrusted)
  사용자가 웹어플리케이션에 접근가능한 상황이라면 보안상 false로 할 필요가 있습니다.
  디폴트 값은 true입니다.

  errorReportValveClass :: 이 Host가 사용할 오류보고밸브(error reporting valve)의
  Java 클래스명. 이 밸브의 임무는 에러보고를 출력하는 것입니다.
  이 속성을 설정하면 Tomcat이 생성하는 에러페이지의 외관(look)을 커스터마이즈 할 수
  있습니다. 이 클래스는 반드시 org.apache.catalina.Valve 인터페이스를 구현해야 합니다.
  아무것도 지정하지 않은 경우에는 org.apache.catalina.valves.ErrorReportValve가
  디폴트로 사용됩니다.

  liveDeploy :: Tomcat 운영 도중에 appBase 디렉토리에 새로운 웹어플리케이션을
  추가했을 경우, 이 플래그 값이 true이면 이 웹어플리케이션이 자동으로 배치됩니다.
  디폴트 값은 true입니다.

  unpackWARs :: 웹어플리케이션이 appBase 디렉토리에 웹어플리케이션 아카이브(WAR)
  파일로 존재할 때, WAR 파일을 해당 디스크 디렉토리 구조로 풀어서(unpack) 실행되길
  원하는 경우에는 true로 설정하십시오. false로 설정하면 WAR 파일형태로 직접 실행됩니다.

  workDir :: 이 Host에서 사용할 임시 디렉토리에 대한 경로명입니다.
  이 디렉토리는 관련 웹어플리케이션의 서블릿들이 임시로 읽기-쓰기 작업을 하는 용도로 사용합니다.
  웹어플리케이션의 서블릿들은 이름이 javax.servlet.context.tempdir인
  서블릿-컨텍스트 속성(타입은 java.io.File)을 통해 이 디렉토리를 볼 수 있으며,
  이 내용은 서블릿 스펙에 기술되어 있습니다. 지정하지 않은 경우에는 적절한 디렉토리가
  $CATALINA_HOME/work 아래에 제공됩니다.

  -->
     <Host name="localhost" debug="0" appBase="webapps" unpackWARs="true">
 <!--
  많은 서버 환경에서 네트워크 관리자들은 서버의 IP 주소로 해독(resolve) 되는 하나 이상의
  네트워크 명칭(Domain Name Service (DNS) 서버에 있는)을 설정하곤 합니다.
  보통 이런 네트워크 명칭은 conf/server.xml 에서 별도로 Host 엘리먼트로 설정됩니다.
  이 각각의 Host 엘리먼트는 자신만의 웹어플리케이션 집합을 가지게 됩니다.
  그러나 일부 환경에서는 둘 이상의 네트워크 명칭을 같은 가상호스트로 해독되어야
  좋은 경우가 있습니다. 이런 경우는 대개 기업의 웹사이트에서 많이 발견됩니다.
  즉 사용자가 www.mycompany.com를 써도 company.com를 써도, 정확히 같은 컨텐츠와
  어플리케이션에 액세스되어야 되는 경우입니다.
  이러한 경우에는 하나 또는 그 이상의 Alias 엘리먼트를 Host 엘리먼트 내에 포함시키면 됩니다.
  예를 들면:

  <Alias>mycompany.com</Alias>
 -->

       <!-- 보통, 사용자는 각각의 웹 어플리케이션에 인증을 해줘야만 합니다.
            사용자가 security 제한에 걸려있는 보호된 자원 중에서 처음에 걸리는
            인증을 한번만 통과하고, 이 가상호스트 안의 "모든" 웹어플리케이션에
            통과된 인증으로 접근하게 하려면 아래 엔트리의 주석을 해제하십시오.
       -->
       <!--
       <Valve className="org.apache.catalina.authenticator.SingleSignOn"
                  debug="0"/>
       -->

       <!-- Access log는 이 가상호스트에 접속하는 모든 요청을 처리합니다. 기본값은
            로그 파일은 $CATALINA_HOME 에 상대적인 "logs" 디렉토리에 생성됩니다.
            "directory" 속성을 이용해서 원하는 다른 디렉토리로 지정할 수 있습니다.
            ($CATALINA_HOME 에 대해) 상대적인 디렉토리나 또는 원하는 디렉토리의
            절대 경로를 써주면 됩니다.
       -->
       <Valve className="org.apache.catalina.valves.AccessLogValve"
                directory="logs"  prefix="localhost_access_log." suffix=".txt"
                pattern="common"/>

       <!-- 이 가상 호스트에 관계된 모든 Context 에 의해 공유된 Logger. 기본값은
            (FileLogger 를 사용할 때), 로그 파일들은 $CATALINA_HOME 에 상대적인
            "logs" 디렉토리에 생성됩니다. "directory" 속성을 이용해서 원하는 다른
            디렉토리로 지정할 수 있습니다. ($CATALINA_HOME 에 대해) 상대적인 디렉
            토리나 또는 원하는 디렉토리의 절대 경로를 써주면 됩니다.
       -->
       <Logger className="org.apache.catalina.logger.FileLogger"
                directory="logs"  prefix="localhost_log." suffix=".txt"
        timestamp="true"/>

       <!-- 각각의 웹 어플리케이션에 대한 프로퍼티 설정. 이것은 기본값과는 다른 프로
            퍼티를 설정하기 윈할 때나, 웹어플리케이션 document 루트 디렉토리가 가상
            호스트의 appBase 디렉토리와 다른 곳에 있을 경우에만 필요합니다.
       -->



       <!-- Tomcat Root Context -->
       <!--
         <Context path="" docBase="ROOT" debug="0"/>
       -->

       <!-- Tomcat Examples Context -->
 <!--
  Context 엘리먼트는 특정 가상호스트 내에서 실행되는 웹어플리케이션을 나타냅니다.
  각 웹어플리케이션은 웹어플리케이션 아카이브(Web Application Archive) (WAR) 파일 또는,
  패킹하지 않은 채로 그에 상응하는 내용을 담고 있는 디렉토리를 기준으로 하며,
  이러한 내용은 서블릿 스펙(버전 2.2 또는 그 이상)에 설명되어 있습니다.
  웹어플리케이션 아카이브에 관한 더 많은 정보를 원하시면 서블릿 스펙을 다운로드해서 참고하십시오.
  그리고 Tomcat 어플리케이션 개발자 가이드(Application Developer's Guide)를 검토하시기 바랍니다.

  각 HTTP 요청을 처리하는데 사용할 웹어플리케이션의 선택은, 각각 정의된 Context의 컨텍스트
  경로(context path)에 대해 요청 URI의 가능한 전치어(prefix) 중 가장 길게 매칭가능한 컨텍스트 경로를
  가진 컨텍스트를 선택함으로써 이루어집니다. 선택된 Context는 수신된 요청을 처리하기 위해 적절한
  서블릿을 선택합니다.
  서블릿 선택 작업은 웹어플리케이션 배치 디스크립터(web application deployment descriptor)
  파일(반드시 웹어플리케이션 디렉토리 하위의 /WEB-INF/web.xml에 위치함)에 정의된
  서블릿 매핑 정보에 따라서 이루어집니다.

  Context 엘리먼트는 횟수의 제한 없이 정의할 수 있으며, conf/server.xml의
  Host 엘리먼트 내에 중첩시키면 됩니다.  각각의 Context는 반드시 유일한 컨텍스트
  경로를 가져야 하며, 컨텍스트 경로는 path 속성으로 정의됩니다.
  또 컨텍스트 경로의 문자열 길이가 0인 Context를 추가로 지정해야 하는데,
  이렇게 정의한 Context는 이 가상 호스트에 대하여 default 웹어플리케이션이 되어,
  다른 어떤 Context의 컨텍스트 경로에도 매칭되지 않는 모든 요청을 처리하는데 사용됩니다.

  Context 엘리먼트를 Host 엘리먼트에 중첩시키는 방법 외에도, Host의 appBase로
  지정된 디렉토리 안에 이들을 각각의 파일(확장자는 ".xml")로 저장하는 방법이 있습니다.
  어플리케이션의 자동배치(Automatic Application Deployment)에서 더 자세한 정보를 볼 수 있습니다.

  명시적으로 Context 엘리먼트를 지정하는 방법 뿐만 아니라, 당신을 위해 자동으로
  Context 엘리먼트를 생성해 주는 몇가지 테크닉도 존재합니다.
  어플리케이션의 자동배치(Automatic Application Deployment)와
  사용자 웹어플리케이션(User Web Applications)에서 더 많은 정보를 볼 수 있습니다.

  이하의 설명에서는 $CATALINA_HOME 변수명을 사용하여 당신이 Tomcat 4를 설치한
  디렉토리를 참조하며, 이 디렉토리가 대부분의 상대경로에 대한 기준 디렉토리(base directory)가 됩니다.
  그러나 만약 CATALINA_BASE 디렉토리를 설정하여 Tomcat 4를 여러개 설치했다면,
  이러한 디렉토리 변수 참조에 대해 $CATALINA_HOME 대신 $CATALINA_BASE 를
  사용해야 합니다.

 -->

 <!-- 속성값
  docBase :: 이 웹어플리케이션에 대한 Document Base (Context Root로도
  알려져 있습니다) 디렉토리, 또는 웹어플리케이션 아카이브 파일의 경로명(웹어플리케이션을
  WAR 파일로 직접 실행하는 경우)을 나타냅니다. 이 디렉토리나 WAR 파일에에 대한
  절대경로명을 지정할 수도 있고, 이 Context가 정의된 Host의 appBase 디렉토리에 대한
  상대경로명을 지정할 수도 있습니다.
  ※반드시 설정
 
  path :: 이 웹어플리케이션의 컨텍스트 경로(context path)를 나타내며, 각 요청
  URI의 시작부분이 컨텍스트 경로와 같을 때 해당 웹어플리케이션이 그 요청을 처리하게 됩니다.
  하나의 특정 Host 내의 컨텍스트 경로들은 모두 각각 유일해야 합니다.
  만약 컨텍스트 경로를 빈 스트링("")으로 지정하면, 이 Context는 이 Host에 대한
  디폴트 웹어플리케이션으로 정의된 것입니다.
  디폴트 웹어플리케이션은 다른 Context 들에 해당되지 않는 모든 요청을 처리할 것입니다.
  ※반드시 설정

  className :: 사용할 Java 구현체 클래스의 이름.
  이 클래스는 반드시 org.apache.catalina.Context 인터페이스를 구현해야 합니다.
  지정하지 않으면 표준값 (아래에 정의됩니다)이 사용됩니다.

  cookies :: true(디폴트)로 지정하면 클라이언트가 쿠키를 지원하는 경우
  세션확인의 통신수단(session identifier communication)으로 쿠키를 사용합니다.
  false로 지정하면 세션확인의 통신수단으로 쿠키 사용을 하지 않고,
  어플리케이션에 의한 URL 다시쓰기(URL rewriting)에만 의존한다는 의미입니다.

  crossContext :: true로 지정하면 이 어플리케이션에서 ServletContext.getContext()
  호출을 통해, 이 가상호스트에서 실행중인 다른 웹어플리케이션에 대한
  요청디스패쳐(request dispatcher)를 성공적으로 얻을 수 있습니다.
  보안상의 이유로 false(디폴트)로 지정하면 getContext()는 언제나 null을 반환하게 됩니다.

  override :: 이 Context가 정의된 Host의 DefaultContext에 정의된 각 설정내용을,
  이 Context 엘리먼트에서 재정의(override) 할 수 있도록 하려면 true로 지정합니다.
  디폴트로는 DefaultContext 엘리먼트의 설정이 사용되도록 되어 있습니다.
 
  privileged :: true로 설정하면 이 컨텍스트는 관리자서블릿(manager servlet) 같은
  컨테이너 서블릿을 사용할 수 있습니다.    

  reloadable :: true로 지정하면, Catalina는 /WEB-INF/classes/와 /WEB-INF/lib 안
  클래스 들의 변경여부를 감시하다가, 변경이 발견되면 웹어플리케이션을 자동으로 재적재(reload)합니다.
  이 기능은 개발중에는 매우 유용하지만 얼마간의 실행부하(runtime overhead)가 발생하므로,
  실제 운영할 용도로 어플리케이션을 배치(deploy)할 때는 사용하지 않도록 합니다.
  그러나 이미 배치가 끝난 어플리케이션이라도 Manager 웹어플리케이션을 이용하면 필요할 때
  재적재 하도록 할 수 있습니다.

  wrapperClass :: 이 Context로 관리할 서블릿 들에 대해 사용할 org.apache.catalina.Wrapper 구현체
  클래스의 Java 클래스명입니다. 지정하지 않으면 표준값이 사용됩니다.

  useNaming :: 이 웹어플리케이션에서 Java2 Enterprise Edition (J2EE) 플랫폼 규약에 맞는
  JNDI InitialContext를 사용가능하게 하도록 설정하려면 true(디폴트값)로 지정합니다.

 -->
       <Context path="/examples" docBase="examples" debug="0"
                reloadable="true">

   <!--
   이 Context에 대한 모든 로그 메시지를 받아서 처리할 로거(logger)를 설정합니다.
   이 로거는 ServletContext.log() 호출을 통해 기록될 어플리케이션 로그 메시지도 처리합니다.

   Logger 엘리먼트는, Catalina 컨테이너(Engine, Host, 또는 Context)의 로깅, 디버깅,
   그리고 에러메시지(스택 트레이스 포함)의 목적지(destination)를 나타냅니다.
   또한 어떤 Engine이나 Host와 연결된 Logger 들은 명시적으로 재설정하지 않으면
   자동적으로 하위의 컨테이너로부터 설정을 물려 받습니다.

   웹서버같이 액세스로그를 만드는데 관심이 있다면(가령 히트수 분석 소프트웨어를 돌리려고),
   그 Engine, Host, 또는 Context의 액세스로그 밸브(Access Log Valve) 컴포넌트 설정이 필요할 것입니다.


   -- 속성값 --

   className :: (반드시 설정) 사용할 Java 구현체 클래스의 이름.
   이 클래스는 반드시 org.apache.catalina.Logger 인터페이스를 구현해야 합니다.
   지정하지 않으면 표준값(아래에 정의됩니다)이 사용됩니다.

   verbosity :: 이 로거에 대한 상세수준(verbosity level).
   지정한 값보다 높은 상세수준의 메시지는 조용히 무시됩니다.
   가능한 값은 0 (중요 메시지만), 1 (에러), 2 (경고), 3 (정보), 그리고 4 (디버그) 입니다.
   지정하지 않은 경우 1 (에러) 입니다.

   NOTE - 명시적 상세수준을 가진 메시지만 이 값과 비교하여 로그여부를 결정합니다.
   명시적 상세수준이 없는 메시지는 무조건 로그됩니다.

   
   대부분의 Catalina 컴포넌트와는 달리 Logger의 경우에는, 사용할 수 있는
   표준 구현체가 여러개입니다. 따라서 사용하고자 하는 구현체를 반드시
   className 속성을 사용해서 지정해야 합니다.

   
   File Logger (org.apache.catalina.logger.FileLogger)
   File Logger는 한 지정된 디렉토리 안의 파일에 로그되는 메시지를 기록합니다.
   로그파일의 실제 파일명은 설정된 prefix, YYYY-MM-DD 포맷의 현재 날짜,
   그리고 설정된 suffix로 이루어집니다. 각 날의 자정을 지난 이후에 어떤 메시지가 로그되면,
   이 메시지가 발생하자마자 현재의 로그파일은 닫혀지고 새 로그파일이 새 날짜로 생성되어 열립니다.
   따라서 이런 로그파일의 전환 때문에 Catalina를 재부팅시킬 필요는 없습니다.

   
   File Logger가 지원하는 속성은 다음과 같습니다:

   directory :: 이 로거가 생성할 로그파일이 위치할 디렉토리의 절대/상대경로명.
   상대경로로 지정되면 $CATALINA_HOME을 기준으로 해석합니다.
   directory 속성을 지정하지 않으면 디폴트 값은 "logs"($CATALINA_HOME에 상대적임) 입니다.
   
   prefix  :: 각 로그파일명의 시작부분에 붙여질 prefix. 지정하지 않으면 "catalina."이 됩니다.
   아무 prefix도 붙이지 않으려면 길이가 0인 문자열("")을 사용하면 됩니다.
   
   suffix :: 각 로그파일명의 끝부분에 붙여질 suffix. 지정하지 않으면 ".log"가 됩니다.
   아무 suffix도 붙이지 않으려면 길이가 0인 문자열("")을 사용하면 됩니다.
   
   timestamp :: 모든 로그메시지에 날짜와 시간을 붙이려면 true로 지정하십시오.
   false(디폴트)로 지정하면 날짜와 시간은 찍히지 않습니다.
   

   Standard Error Logger (org.apache.catalina.logger.SystemErrLogger)
   Standard Error Logger는, Catalina의 표준 에러출력스트림으로 정해진 스트림으로 로그되는
   모든 메시지를 기록합니다. Catalina의 디폴트 기동 스크립트에서는 표준 에러출력스트림을
   $CATALINA_HOME 아래의 logs/catalina.out 파일로 지정해 놓습니다.
   이 로거에서 지원하는 추가 속성은 없습니다.

   Standard Output Logger (org.apache.catalina.logger.SystemOutLogger)
   Standard Output Logger는, Catalina의 표준 출력스트림으로 정해진 스트림으로 로그되는
   모든 메시지를 기록합니다. Catalina의 디폴트 기동 스크립트에서는 표준 출력스트림을
   $CATALINA_HOME 아래의 logs/catalina.out 파일로 지정해 놓습니다.
   이 로거에서 지원하는 추가 속성은 없습니다.


   -->
         <Logger className="org.apache.catalina.logger.FileLogger"
                    prefix="localhost_examples_log." suffix=".txt"
          timestamp="true"/>
         <Ejb   name="ejb/EmplRecord" type="Entity"
                home="com.wombat.empl.EmployeeRecordHome"
              remote="com.wombat.empl.EmployeeRecord"/>
         <!-- PersistentManager: 영속적인 세션을 테스트 하기위해서는 아래
              섹션의 주석을 지워주십시오.
                       
              saveOnRestart: true 값일 경우, Catalina 가 shutdown 될 때
                모든 살아있는 세션들은 다른 세팅과는 상관없이, Store 에
                저장될 것입니다. startup 할 때 Store 에 있는 모든 세션들
                은 자동으로 로드됩니다. expiration 이 지난 세션들은 양쪽
                의 경우에 무시됩니다.
              maxActiveSessions: 0 이상의 값일 경우, 너무 많은 살아 있는 세
                션이 있다면 몇몇은 없어져버리는 결과가 있을 수 있습니다.
                minIdleSwap 은 이것을 제한합니다. -1 은 무한 세션을 허가한
                다는 뜻입니다. 0 은 사용 후 세션은 거의 모두 없어져 버립니다
                - 사용자들에게 인지될 정도로 느리게 될 것입니다.
              minIdleSwap: 세션은 적어도 이기간 동안 idle 상태이어야 합니다.
                (초 단위로) 없어지기 전에 말이죠.
              maxActiveSessions. 이것은 사이트가 아주 활발할 때 thrashing 을
                피하게 합니다. -1 이나 0 은 minimum 이 없다는 뜻입니다 - 세션
                은 어느때라도 소멸될 수 있습니다.
              maxIdleSwap: (초 단위로) 세션은 이 기간동안 idle 상태면 소멸됩
                니다. minIdleSwap 이 보다 높다면, 그것으로 바꿔집니다.
                이것은 정확하지 않습니다: 주기적으로 확인합니다.
                -1 은 maxActiveSessions 값으로 인해 소멸되어야 해도, 세션은
                소멸되지 않음을 의미합니다. 0 이상으로 세팅되면, startup 할 때
                Store 에 있는 모든 세션은 로드될 것을 보장합니다.
              maxIdleBackup: (Store 에 저장되었지만, active 메모리에 남아있는)
                세션은 백업될 것입니다. 이 기간동안 idle 상태고, startup 할 때
                Store 에 있는 모든 세션들이 로드될 것입니다. -1 로 설정되었다면
                세션은 백업되지 않을 것이고, 0 은 사용된 뒤에 잠깐 백업된다는
                것을 의미합니다.

              Store 에 있는 세션을 지우려면, maxActiveSessions, maxIdleSwap,
              minIdleBackup 모두를 -1 로, saveOnRestart 는 false로 세팅한 후,
              Catalina 를 재시동합니다.
         -->
   <!--
         <Manager className="org.apache.catalina.session.PersistentManager"
             debug="0"
             saveOnRestart="true"
             maxActiveSessions="-1"
             minIdleSwap="-1"
             maxIdleSwap="-1"
             maxIdleBackup="-1">
               <Store className="org.apache.catalina.session.FileStore"/>
         </Manager>
   -->
         <Environment name="maxExemptions" type="java.lang.Integer"
                     value="15"/>
         <Parameter name="context.param.name" value="context.param.value"
                    override="false"/>
         <Resource name="jdbc/EmployeeAppDb" auth="SERVLET"
                   type="javax.sql.DataSource"/>
         <ResourceParams name="jdbc/TestDB">
           <parameter><name>user</name><value>sa</value></parameter>
           <parameter><name>password</name><value></value></parameter>
           <parameter><name>driverClassName</name>
             <value>org.hsql.jdbcDriver</value></parameter>
           <parameter><name>driverName</name>
             <value>jdbc:HypersonicSQL:database</value></parameter>
         </ResourceParams>
         <Resource name="mail/Session" auth="Container"
                   type="javax.mail.Session"/>
         <ResourceParams name="mail/session">
           <parameter>
             <name>mail.smtp.host</name>
             <value>localhost</value>
           </parameter>
         </ResourceParams>
       </Context>

     </Host>

  <Host name="webmail.starit.net">
         <Context path="" docBase="/usr/local/tomcat/webapps/webmail" debug="0" reloadable="true">

 
  <!-- 자원정의 (Resource Definitions)
   웹어플리케이션 배치 디스크립터의 <resource-ref>와 <resource-env-ref> 엘리먼트의
   JNDI 탐색(lookup)에 대해 반환될 자원의 특성도 선언 가능합니다.
   그리고 어떤 자원에 대해서 객체팩토리를 사용하고(Tomcat이 아직 모르는 경우)
   그 객체팩토리를 설정하는데 사용할 프로퍼티를 설정하려면, 반드시 그 자원과 같은
   이름으로 자원 파라미터(Resource Parameters)를 추가로 정의해야 합니다.
  -->

  <!--
   예를 들어 다음과 같이 자원정의가 가능합니다:
 
    <Context ...>
     ...
     <Resource name="jdbc/EmployeeDB" auth="Container"
      type="javax.sql.DataSource"
     description="Employees Database for HR Applications"/>
     ...
   </Context>

   이것은 웹어플리케이션 배치 디스크립터(/WEB-INF/web.xml)에
   다음의 엘리먼트를 포함시킨 것과 동일합니다:

       <resource-ref>
     <description>Employees Database for HR Applications</description>
     <res-ref-name>jdbc/EmployeeDB</res-ref-name>
     <res-ref-type>javax.sql.DataSource</res-ref-type>
     <res-auth>Container</res-auth>
   </resource-ref>
  -->

  <!-- 속성값
   name :: (반드시설정) 생성할 자원의 이름. java:comp/env 컨텍스트에 대한 상대적인 이름입니다.

   type :: (반드시설정) 웹어플리케이션이 이 자원에 대해 탐색(lookup)을 실행할 때 기대하는 완전한 Java 클래스명.

   auth :: 해당 자원관리자에 인증(sign on)할 때, 웹어플리케이션 프로그램의 코드상에서 직접 할지,
   또는 컨테이너가 직접 어플리케이션의 행위(behalf)에 따라 할지를 지정합니다.
   이 속성의 값은 반드시 Application 또는 Container 중 하나여야 합니다.
   이 속성은, 웹어플리케이션이 웹어플리케이션 배치 디스크립터에서 <resource-ref> 엘리먼트를
   사용하는 경우에는 반드시 필요합니다.
   그러나 <resource-env-ref>를 대신 사용하는 경우에는 선택사항입니다.
   
   description :: (선택사항)이 자원에 대한 사람이 읽기 쉬운 간단한 설명
   
   scope :: 이 자원관리자를 통해 얻어진 연결(connection)의 공유를 허가할 것인지 지정합니다.
   이 속성의 값은 반드시 Shareable 또는 Unshareable 중 하나여야 합니다.
   지정하지 않으면 연결은 공유가능(shareable)이 됩니다.

  -->
  <!-- JNDI setting for jdbc -->
   <Resource name="jdbc/webmail" auth="Container"
      type="javax.sql.DataSource">
  </Resource>

  <!--
   사용할 자원 factory implementation 의 자바 클래스명과 자원 factory 를
   설정하는 데 사용되는 JavaBeans 프로퍼티를 설정합니다.

   이 엘리먼트는 웹어플리케이션에서 해당 자원의 이름에 대해 JNDI 탐색을 수행할 때,
   객체를 반환하는데 사용할 자원관리자(또는 객체팩토리)를 설정하는 역할을 합니다.
   $CATALINA_HOME/conf/server.xml의 <Context>나 <DefaultContext> 엘리먼트 내의
   <Resource> 엘리먼트로 지정된 모든 자원 이름, 그리고/또는 웹어플리케이션
   배치 디스크립터에서 <resource-ref> 나 <resource-env-ref> 엘리먼트에서
   선언된 모든 자원 이름에 대해서는 반드시 자원 파라미터(resource parameters)를
   정의해야 그 자원에 성공적으로 액세스할 수 있습니다.

   자원 파라미터는 이름으로 정의되며, 정확하게 어떤 파라미터 이름들의 집합을 지원하는가는
   당신이 사용하고 있는 자원관리자(또는 객체팩토리)에 따라 달라집니다.
   즉 해당 팩토리 클래스의 JavaBeans 프로퍼티 중 설정가능한(settable) 프로퍼티의 이름과
   일치해야 합니다. JNDI 구현체는 지정한 팩토리 클래스의 인스턴스에 대해 JavaBeans의
   모든 해당 속성 설정메소드를 호출함으로써 모든 설정을 마친 다음에야,
   이 팩토리 인스턴스를 JNDI lookup() 호출을 통해 사용가능하도록 할 것입니다.

   만약 특정 자원 타입에 대해 팩토리 클래스의 Java 클래스명을 지정할 필요가 있다면,
   <ResourceParams> 엘리먼트 내의 <parameter> 항목에 factory라는 이름을 사용하면 됩니다.

  -->

  <!-- 속성값
   name :: 설정할 자원의 이름이며, java:comp/env 컨텍스트에 대한 상대적인 이름이 됩니다.
   이 이름은 $CATALINA_HOME/conf/server.xml 내에 <Resource> 엘리먼트로 정의된 자원,
   그리고/또는 웹어플리케이션 배치 디스크립터 내에 <resource-ref> 또는 <resource-env-ref>로
   참조되는 자원의 이름과 반드시 일치해야 합니다.
   ※ 반드시 설정
  -->
  <ResourceParams name="jdbc/webmail">
    <parameter>
   <name>factory</name>
   <value>org.apache.commons.dbcp.BasicDataSourceFactory</value>
    </parameter>

  <!-- maximum nuber of DB connections in DB 0 for no limit -->
    <parameter>
   <name>maxActive</name>
   <value>100</value>
    </parameter>

  <!-- set 0 for no limit -->
    <parameter>
   <name>maxIdle</name>
   <value>30</value>
    </parameter>

    <parameter>
   <name>maxWait</name>
   <value>10000</value>
    </parameter>

    <parameter>
   <name>username</name>
   <value>staritadm</value>
    </parameter>

    <parameter>
   <name>password</name>
   <value>pe999</value>
    </parameter>

    <parameter>
   <name>driverClassName</name>
   <value>org.gjt.mm.mysql.Driver</value>
    </parameter>

    <parameter>
   <name>url</name>
   <value>jdbc:mysql://localhost:3306/webmail?autoReconnect=true</value>
    </parameter>
  </ResourceParams>

  <!--
   이 엘리먼트는 어떤 전역 JNDI 자원으로의 링크를 생성하는데 사용합니다.
   그 연결명에 대하여 JNDI 탐색을 실행하면 링크된 전역자원이 반환됩니다.
  -->

  <!-- 속성값
   name :: 생성할 자원링크의 이름이며, java:comp/env에 대한 상대적인 이름입니다.
   ※ 반드시 설정
   
   global :: 전역 JNDI 컨텍스트내의 링크된 전역자원의 이름.
   ※ 반드시 설정

   type ::  이 자원링크에 대해 탐색을 실행할 때 웹어플리케이션이 기대하는 완전한 Java 클래스명.
   ※ 반드시 설정

  -->
  <ResourceLink name="linkToGlobalResource" global="simpleValue" type="java.lang.Integer"/>


  <!-- 컨텍스트 파라미터 (Context Parameter)
   이 엘리먼트 안에 <Parameter> 엘리먼트 들을 중첩시키면, 웹어플리케이션에서
   서블릿-컨텍스트 초기화 파라미터(servlet context initialization parameters)로
   이용가능한 파라미터이름-값 들을 설정할 수 있습니다.
   
   예를 들어 다음과 같이 하면 초기화 파라미터를 생성할 수 있습니다:
   
   <Context ...>
     ...
     <Parameter name="companyName" value="My Company, Incorporated"
      override="false"/>
     ...
   </Context>

   
   이는 웹어플리케이션 배치 디스크립터(/WEB-INF/web.xml) 안에
   다음과 같은 엘리먼트를 포함시키는 것과 동일합니다:
     
    <context-param>
     <param-name>companyName</param-name>
     <param-value>My Company, Incorporated</param-value>
   </context-param>
   
   
   -- 속성값 --

   name :: (반드시 설정) 생성할 컨텍스트 초기화 파라미터의 이름.

   value :: (반드시 설정) 웹어플리케이션에서 ServletContext.getInitParameter()을
   호출할 때 반환할 파라미터 값.

   override :: 웹어플리케이션 배치 디스크립터에서 여기에서 지정한 초기화 파라미터와
   같은 이름의 <context-param>를 지정했을 때 그 파라미터 값의 덮어쓰기(override)를
   허용하지 않으려면 false로 설정합니다. 디폴트값은 true입니다.

   description :: 이 컨텍스트 초기화 파라미터에 대한 간략한 설명이며, 생략가능
  -->
 


  <!-- 환경항목(Environment Entries)
   
   웹어플리케이션에서 환경항목자원(environment entry resources)으로 사용할 수
   있도록 항목의 이름-값 들을 설정할 수 있습니다. 이 설정은 <Environment> 항목을
   이 엘리먼트 내에 중첩시키면 됩니다. 예를 들어 아래와 같이 환경항목을 생성할 수 있습니다:

       <Context ...>
     ...
     <Environment name="maxExemptions" value="10"
      type="java.lang.Integer" override="false"/>
     ...
   </Context>
    

   이는 웹어플리케이션 배치 디스크립터(/WEB-INF/web.xml)에서
   다음의 엘리먼트를 포함시킨 것과 동일합니다:
     
    <env-entry>
     <env-entry-name>maxExemptions</param-name>
     <env-entry-value>10</env-entry-value>
     <env-entry-type>java.lang.Integer</env-entry-type>
   </env-entry>

    
   그러나 이 값을 커스터마이즈하기 위해 배치 디스크립터를 변경할 필요는 없습니다.

   
   <Environment> 엘리먼트에서는 다음과 같은 속성들을 사용할 수 있습니다:
   
   description :: (선택사항)이 환경항목에 대한 사람이 읽기 쉬운 간단한 설명
   
   name :: (반드시 설정) 생성할 환경항목의 이름. java:comp/env 컨텍스트에 대한 상대적인 이름입니다.
   
   override :: 웹어플리케이션 배치 디스크립터에서 <env-entry>으로 같은 이름의
   환경항목을 지정하는 경우, 여기에서 지정한 값을 덮어쓰기(override) 하지 않도록
   하고 싶으면 false로 지정합니다. 이 값을 지정하지 않으면 덮어쓰기가 허용됩니다.
   
   type :: (반드시 설정) 이 환경항목에 대해 웹어플리케이션이 예상하는 완전한(fully qualified) Java 클래스명.
   반드시 웹어플리케이션 배치 디스크립터의 <env-entry-type>의 규칙에 맞는 값이어야 합니다.
   그 규칙에 맞는 값 들은: java.lang.Boolean,
           java.lang.Byte,
           java.lang.Character,
           java.lang.Double,
           java.lang.Float,
           java.lang.Integer,
           java.lang.Long,
           java.lang.Short,
           java.lang.String 입니다.
   
   value :: (반드시 설정) 웹어플리케이션이 JNDI 컨텍스트로부터 요청해서 반환 받을 환경항목의 값.
   이 값은 반드시 위의 type에 정의된 Java type으로 변환 가능해야 합니다.
   
  -->
  </Context>
</Host>

   </Engine>

  </Service>

  <!-- MOD_WEBAPP 커넥터는 apache 1.3 과 서블릿 컨테이너로 Tomcat 4.0 을 연결하는
      데 쓰입니다. WebApp 모듈 배포판에 포함된 어떻게 만드는지에 대해 설명하는
      README.txt 파일을 읽어보십시오. (또는 "jakarta-tomcat-connectors/webapp"
      CVS repository 를 확인해 보십시오.)

      Apache 쪽에서 설정하려면, 먼저 "httpd.conf" 에 설정되어진 "ServerName" 과
      "Port" 지시자를 확인해야 합니다. 그리고, "httpd.conf" 파일 아래에 다음과
      같은 줄을 넣어줍니다:

        LoadModule webapp_module libexec/mod_webapp.so
        WebAppConnection warpConnection warp localhost:8008
        WebAppDeploy examples warpConnection /examples/

      이 후에 (필요하다면 Tomcat 을 재시동한 후) Apache 를 재시작하면 연결이 됩니
      다. Apache 를 통해서 "WebAppDeploy" 지시자에 있는 모든 어플리케이션들이 실
      행하는 것을 보실 수 있습니다.
  -->

  <!-- Apache-Connector Service 설정하기 -->
  <Service name="Tomcat-Apache">

   <Connector className="org.apache.catalina.connector.warp.WarpConnector"
    port="8008" minProcessors="5" maxProcessors="75"
    enableLookups="true"
    acceptCount="10" debug="0"/>

   <!-- "localhost" 를 Apache "ServerName" 에 설정된 값으로 대치해주십시오 -->
   <Engine className="org.apache.catalina.connector.warp.WarpEngine"
    name="Apache" debug="0" appBase="webapps">

     <!-- 하위 레벨에서 설정되지 않았다면 Global logger -->
     <Logger className="org.apache.catalina.logger.FileLogger"
             prefix="apache_log." suffix=".txt"
             timestamp="true"/>

     <!-- 이 Realm 이 여기 있기 때문에, 전체적으로 이 Realm 이 공유됩니다. -->
     <Realm className="org.apache.catalina.realm.MemoryRealm" />

   </Engine>

  </Service>

</Server>


<!-- Context 컴포넌트내에서만 정의가 가능한 Loader

Loader 엘리먼트는 웹애플리케이션에 필요한 Java 클래스와 자원을 적재하는데 사용할
웹애플리케이션 클래스로더를 나타냅니다. 이 클래스로더는 반드시 서블릿 스펙의 요구사항을
반드시 따라야 하며, 아래와 같은 위치로부터 클래스들을 적재합니다:

웹애플리케이션 내 /WEB-INF/classes 디렉토리에서.
웹애플리케이션 내 /WEB-INF/lib 디렉토리의 JAR 파일들로부터.
모든 웹애플리케이션이 전역적으로 이용가능하도록 Catalina가 허용한 자원들로부터.

Loader 엘리먼트는 Context 컴포넌트 내에서만 정의가 가능하지만, 생략할 수도 있습니다.
만약 생략한다면, 디폴트 Loader 설정이 자동으로 생성되며, 대부분의 경우 이 Loader로 충분합니다.

Catalina에서 구현된 클래스로더 계층구조에 대해 더 깊이 알고 싶다면 FIXME - Reference를 참고하십시오.


-- 속성값 --

className :: 사용할 Java 구현체 클래스의 이름.
이 클래스는 반드시 org.apache.catalina.Loader 인터페이스를 구현해야 합니다.
지정하지 않으면 표준값(아래에 정의됩니다)이 사용됩니다.

delegate :: 이 클래스로더가 표준 Java2 위임모델을 따르게 하고 싶다면 true로 지정합니다.
즉 이렇게 지정하면, 클래스를 적재할 때 웹애플리케이션 내부를 검색하기 전에 먼저
부모 클래스로더에서 클래스를 찾으려 시도합니다. false(디폴트)로 지정하면 요청된 클래스나
자원을 찾을 때 부모 클래스로더에 묻기 전에 웹애플리케이션 내부를 먼저 검색합니다.

reloadable :: true로 지정하면, Catalina는 /WEB-INF/classes/와 /WEB-INF/lib 안
클래스 들의 변경여부를 감시하다가, 변경이 발견되면 웹애플리케이션을 자동으로 재적재합니다.
이 기능은 개발중에는 매우 유용하지만 상당한 실행부하가 발생하므로, 실제 운영할 용도로
애플리케이션을 배치(deploy)할 때는 사용하지 않도록 합니다.
그러나 이미 배치가 끝난 애플리케이션이라도 Manager 웹애플리케이션을 이용하면
필요할 때 재적재 하도록 할 수 있습니다.

NOTE - 이 프로퍼티에 대한 값은 이 Loader 엘리먼트가 정의된 Context 컴포넌트에
설정된 reloadable 속성값을 물려받습니다만,
Loader에 이 reloadable을 정의하면 Context의 값은 무시됩니다.


Standard Implementation
Loader의 표준구현체 클래스는 org.apache.catalina.loader.WebappLoader 입니다.
이 클래스는 위에 나열한 공통속성 외에도 다음과 같은 추가적인 속성을 제공합니다:

checkInterval :: 클래스와 자원의 변경을 확인할 시간간격을 초단위로 나타냅니다.
이 값은 reloadable이 true인 경우에만 의미를 갖습니다. 지정하지 않으면 15초로 설정됩니다.

debug :: 이 Engine이 해당 Logger에 디버깅 로그를 출력하는 상세수준을 의미합니다.
숫자가 높을 수록 더 자세한 출력을 생성합니다. 지정하지 않으면 0으로 설정됩니다.

loaderClass :: 사용할 java.lang.ClassLoader 구현체 클래스의 Java 클래스명입니다.
지정하지 않으면 org.apache.catalina.loader.WebappClassLoader로 설정됩니다.

workDir :: 이 Context에서 사용할 임시 디렉토리에 대한 경로명입니다.
이 디렉토리는 관련 웹어플리케이션의 서블릿들이 임시로 읽기-쓰기 작업을 하는 용도로 사용합니다.
웹어플리케이션의 서블릿 들은 이름이 javax.servlet.context.tempdir인 서블릿-컨텍스트
속성(타입은 java.io.File)을 통해 이 디렉토리를 볼 수 있으며, 이 내용은 서블릿 스펙에
기술되어 있습니다. 지정하지 않은 경우에는 적절한 디렉토리가
$CATALINA_HOME/work 아래에 제공됩니다.
