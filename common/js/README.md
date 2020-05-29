A. 용어정의



○ Object

  개체, javascript에서 의미한 개체는 HTML 문서, 브라우저(윈도우) 같은 것을 의미, 메서드와 속성 

  ① date : Date는 시스템의 날짜와 시간을 얻기 위한 개체이다.

     -> 생성방법 Nameofobject = new Date();

  ② document : 이것은 해당 자바스크립트를 포함하고 있는 HTML 문서를 참조할 수 있는 개체

  ③ location : Location은 특정 URL을 지칭하는 개체이다.

     -> 사용방법 parent.location='index.html'

     ※ location이란 속성도 있는데 사용법이 서로 틀리다는 것을 기억하기 바란다.

  ④ window : window는 브라우저 화면을 참조할 수 있는 개체



○ Method

  어떤 개체로 하여금 어떻게 행동할지를 알려주는 하나의 명령(함수)

  - alert()      : alert는 window 개체 or 하이퍼텍스트링크 내에서 대화상자를 만들기 위해사용

  - getMonth()   : Date 개체의 메서드로 현재 월을 0~11 사이의 숫자로 반환

  - getDate()    : Date 개체의 메서드로 현재 날짜를 1~31 사이의 숫자로 반환

  - getYear()    : Date 개체의 메서드로 현재 연도를 0~99 사이의 숫자로 반환

  - getFullYear(): Date 개체의 메서드로 현재 연도를 네자리 숫자로 반환

  - getDay()     : Date 개체의 메서드로 현재 요일을 1~7 사이의 숫자로 반환

  - getHours()   : Date 개체의 메서드로 현재 시간을 0~23 사이의 숫자로 반환

  - getMinutes() : Date 개체의 메서드로 현재 분을 0~59 사이의 숫자로 반환

  - getSeconds() : Date 개체의 메서드로 현재 시간을 0~59 사이의 숫자로 반환

  - Write()      : Document 개체가 웹 페이지 상에 텍스트를 표시할 수 있도록 해주는 메서드

 


○ Property

  개체의 한 특징 또는 그 개체의 일부를 의미

  - bgColor     : Document 개체의 속성으로 HTML 문서의 배경색을 의미

  - parent      : Parent는 특별히 프레임과 함께 자주 사용되는 속성으로 특정 프레임을 가리킬 때사용된다. 만일 프레임 밖에서 사용된다면 브라우저 전체 윈도우를 가리키게 된다.

  - status       : Window 개체의 속성으로 브라우저 화면의 왼쪽 하단에 위치한 상태 표시줄을 의미



○ Error

  에러 또는 오류 메시지 창은 스크립트 안에 뭔가 문제가 있을 경우 발생한다. 크게 두 가지 종류가 있는데 구문(Syntax) 오류와 런타임(RunTIme) 오류이다.

  - 구문 오류(Syntax Error) : 스크립트 안에 잘못된 철자가 있거나 컴퓨터가 인식하기 어려운 텍스트가 있을 경우

  - 런타임 오류(Run-Time Error) : 런타임 오류는 잘못된 자바스크립트 명령어를 사용했을 때 발생

 


○ Event Handlers

  이벤트 핸들러는 HTML 코드에 내장되는 자바스크립트 명령어다. 사용자와 웹 페이지 사이의 상호 작용을 위해 HTML 코드와 함께 사용된다.

  - onBlur       : 사용자가 select, text, textarea 폼 요소에 있다가 그 요소에서 벗어나게 될 때 발생하는 이벤트 핸들러이다. 즉, 사용자가 그 아이템에 대한 포커스를 잃을 때 발생한다.

  - onChange     : 사용자가 select, text, textarea 폼 요소에 있는 텍스트를 변경한 후 그 요소를 떠날 때 발생

  - onClick      : 사용자가 링크와 같은 오브젝트를 클릭할 때 발생한다.

  - onFocus      : 사용자가 select, text, textarea 폼 요소를 선택할 때 발생

  - onLoad       : 웹 페이지가 열릴 때 발생하는 이벤트로 HTML의 BODY 태그 안에서 사용된다.

  - on_mouseOver  : 사용자가 링크 위에 마우스를 올려 놓았을 때 발생

  - onSelect     : 사용자가text, textarea폼 요소에 있는 텍스트의 일부 또는 전체를 선택할 때 발생

  - onSubmit     : Submit 버튼 폼 요소를 사용자가 clicked 때 발생

  - onUnLoad     : 사용자가 웹 페이지를 떠날 때 발생하는 이벤트로 HTML의 BODY 태그 안에서 사용



○ Comment

  주석은 더블 슬래쉬(//), /*와 */ 사이에 있는 모든 내용이 주석 처리 된다는 것



○ 콤마(comma : ",")

  자바스크립트 이벤트 핸들러를 동시에 여러 개 사용하고자 할 때 콤마(,)를 사용

 

 



B. 사용법

1. 윈도우 관련 함수

  window.alert('내용');   // 경고 창

  prompt('내용',초기값);  // 입력 값 받기

  confirm('내용');       // 확인, 취소

  print();              // 현 윈도우를 인쇄하고자 할 때



2. 소수점 처리 함수

  parseInt(변수);        소수점자리 그냥 버림

  parseFloat(변수);

  Math.ceil(변수);       소수점자리없앤정수(소수점자리 값 올림)

  Math.floor(변수);      소수점자리없앤정수(소수점자리 값 내림)

  Math.round(변수);      소수점자리없앤정수(소수점자리 값 반올림)



3. 변수 및 배열선언 및 초기화

  var test;

  var myArray = new Array();

  var myArray = new Array(5);

  var myArray = new Array('1','2','5','4','3');

  myArray[0] = '1';

  myArray[0][0] = '1';



4. 제어문

  ① if(비교값1) { 처리내용1 } elseif (비교값2) { 처리내용2 } else { 비교값3 };

  ② 논리연산자 && (AND), || (OR), ! (NOT)

  ③ 연산기호 ==,<,>,<=,>=,!=

  ④ switch (비교내용) {

       case '1':

        처리내용1;

        break;

       default:

        처리내용3; break;

    }

  ⑤ for(i=0;i<5;i++) { 처리내용 }

  ⑥ while(i<5) { 처리내용 };



5. 함수선언

  function 함수명(초기값) {

        처리내용;

        return 리턴값;

  }



  return 반환값;



6. 실제사용되는 경우

  isNaN(변수);           -> 숫자 false 문자 true

  length                -> 문자열의 길이

  myArray.length;        -> 변수길이

  myArray.sort();

  var myString = new String("나는 String 개체입니다");

  var lengthOfString = myString.length;



  charAt(위치값)          -> 문자열에서 한문자 선택

  charCodeAt(위치값)      -> 문자열에 한문자 코드 값

  myString = String.fromCharCode(65,66,67);  -> 문자 코드를 문자열로 변환 (ABC)



  indexOf('앞부분 부터 찾을문자');

  lastIndexOf('뒷부분 부터 찾을 문자')     -> 문자열의 위치를 반환, 없을 경우 -1반환

  substr(시작위치, 복사할문자수);          -> 문자열의 일부분 복사하기

  substring(시작위치, 끝위치);

myDate.toString();       -> 숫자를 문자열로 반환

myString.toLowerCase();   -> 문자열의 소문자 변환

myString.toUpperCase();   -> 문자열의 대문자 변환



Math.PI 파이값

Math.floor((Math.random() *  6) + 1);      -> 1~6사이에 난수를 발생한다.

Math.pow(제곱근, 변수)                    -> 제곱 Math.pow(4,2) = 16

Math.abs(변수);                          -> 절대값



concat() -> var TestArray = TestA.concat(TestB);    TesTA와 TestB에 배열을 결합

slice()  -> var slicedArray = TestA.slice(1,3);     TestA에 인덱스1에서 부터 인덱스 3까지를 가져온다.

join()  -> var TestString = TestA.join("");        TestA를 문자열로 변환 1 2 5 4 3...

sort()  -> TestA.sort();                          배열을 순서대로 정렬

reverse()-> TestA.reverse();                      배열을 역순으로 정렬



var myDate = new Date();

var myDate = new Date(949278000000);

var myDate = new Date("1 jan 2003");

var myDate = new Date(2000,0,31,15,35,20,20); 2000년 1월 31일 35분 20초 20밀리

getDate()       -> myDate.getDate();      날짜를 반환

getDay()        -> myDate.getDay();      요일을 정수로 반환

getMonth()       -> myDate.getMonth();     월을 정수로 반환

getFullYear()    -> myDate.getFullYear;    년을 네 자리 수로 반환

setDate()       -> myDate.setDate(27);    날짜를 설정

setMonth()       -> myDate.Month(1);      월을 설정

setFullYear()    -> myDate.setFullYear(2003); 년을 설정

myDate.getHours();       시간

myDate.getMinutes();      분

myDate.getSeconds();      초

myDate.getMilliseconds;   밀리



window.location;

window.location.replace(URL);

window.location.href(URL);

window.location.reload();

window.history; 

window.history.go(-1); 

window.history.go(1); 

window.history.go(3);

window.navigator



window.screen;

window.screen.height;     // 화면 높이

window.screen.width;      // 화면 너비

window.screen.colorDepth; // 사용가능한 색상 수

window.screen.pixelDepth; // 한 픽셀 당 비트수



window.document;

window.document.bgColor = "skyblue";

window.document.write('내용');

window.document.forms[인덱스]

window.document.form1.length;

window.document.images[이름명]

window.document.images[이름명].src = "값.gif"

window.document.images[이름명].value = "처리이름"

window.document.links[인덱스]

window.document.links[인덱스].onclick = "이름명";



window.defaultStatus = '하이'     -> 상태 표시줄 메시지-항상 보여주기 위한 상태표시줄 메시지

window.status = '하이'            -> 상태 표시줄 메시지



onLoad="처리내용";       // 웹페이지 열 때//body,frameset(여러 개의 프레임의 경우)

onunload="처리내용";     // 웹페이지 닫을 때//body,frameset

onresize="처리내용";     // 윈도우나 프레임의 크기를 바꾸었을 때



onclick="처리내용";      // 클릭했을 때

on_mouseup="처리내용";     // 마우스 버튼을 눌렀다 놓았을 때

on_mousedown="처리내용";   // 마우스 버튼을 눌렀을 때

on_mouseover="처리내용";   // 링크 위로 마우스가 지나갔을 때

on_mouseout="처리내용";    // 마우스가 링크나 특정 영역 안에 있다가 나갔을 때

on_mousemove="처리내용";   // 마우스를 움직였을 때

on_mouseup="처리내용";     // 마우스 버튼을 눌렀다 놓았을 때



onfocus="처리내용";      // 포커스를 맞출 때 발생한다.

onblur="처리내용";       // 포커스를 잃을 때

onreset="처리내용";      // 입력 양식에서 취소reset시켰을 때

onsubmit="처리내용";     // submit버튼을 누를 때

onselect="처리내용";     // 텍스트를 블럭 지정할 때

onchange="처리내용";     // 내용이 바뀔 때



onkeydown="처리내용";     // 키를 입력했을 때

onkeypress="처리내용";    // 키를 눌렀을 때

onkeyup="처리내용";      // 키를 눌렀다 놓았을 때



7. window 객체의 메서드

blur();         // 특정 객체의 포커스 없애기

click();         // 입력 양식이나 링크를 마우스로 클릭했을 때

focus(); -> txtAge.focus();       // 특정 개체에 포커스가 가도록 만들기

select(); -> txtAge.select();     // 입력 양식의 한 필드 블록 지정했을 때



onabort -> 이미지의 로딩을 취소할 때 발생한다.

on_error -> 페이지를 로딩하면서 오류가 생길 때 발생한다.(이미지(문서) 전송되는 도중에 네트워크 끊김, 시스템다운)



8. 개체별 속성, 이벤트, 메서드

① Button 개체

  Event          - onblur, onclick, onfocus, on_mousedown, on_mouseup

  Property      - form, name, type, value

  Method         - blur(), click(), focus()



② CheckBox 개체

  Event         - onblur, onclick, onfocus

  Property       - checked, defaultChecked, form, name, type, value





③ document개체(body, head, title 태그 안)

  Event         - onclick, ondblclick, onkeydown, onkeypress, onkeyup, on_mousedown, on_mouseup

  Property

    - alinkColor(링크를 클릭했을 때 색), vlinkColor(이전에 방문했던 링크 색), linkColor(문서에서 링크 색)

    - links(문서에 있는 링크들의 배열)

    - anchors(문서에 있는 표식들의 배열)

    - applets(문서에 있는 자바애플릿의 배열)

    - embeds(문서에 있는 플러그인들의 배열)

    - form_name

    - forms(문서에 있는 입력양식들의 배열)

    - images(문서에 있는 이미지들의 배열) ->.length로 배열 [0]...

    - bgColor(배경색), fgColor(문서의 전경색)

    - cookie(클라이언트의 pc에 저장된 정보)

    - domain(현재 문서의 도메인 이름)

    - lastModified(문서가 마지막으로 수정된 날짜)

    - referrer(링크로 현재 문서에 왔을 때 이전 문서의 url위치)

    - title(문서의 제목)

    - URL(문서의 URL주소)

  Method

  - close()(문서에 데이타를 출력하는 것을 마무리)

  - open()(문서에 데이타를 출력하기 위해 준비시키는 것)

  - write()(문서에 데이타 출력)

  - writeln()(문서에 데이터 출력(줄바꾸기 포함))



④ history 개체

  프로퍼티 - length

  메서드   - back(), forward(), go()



⑤ img 개체

  이벤트   - onabort, on_error, onload

  프로퍼티 - border, complete, height , hspace, lowsrc, name, src, vspace, width







--[ 기타 유용한 Tip ]--------------------------------------------------------------

window.document.form1.textarea1.value;

window.document.form1.radio1[인덱스].checked = true;

window.document.form1.radio1[인덱스].checked = false;

window.document.form1[인덱스].type == "checkbox"; -> form1에 checkbox형인 체크박스



var myNewOption = new Option("TheText","TheValue","TheTest");

document.form1.selectObject.option[0] = myNewOption; -> 항목설정

document.form1.selectObject.option[0] = null;       -> 항목제거

document.form1.selectObject.options[2].text == 수요일;

document.form1.selectObject.options[2] == null;

form1.secondDay.options[form1.secondDay.selectedIndex].value

form1.firstDay.options[nowDate.getDate() - 1].selected = true;



secondDate.valueOf(); -> 1970년 1월 1일부터 date개체에 저장한 시각까지의 시간을 밀리초단위로반환



window.name      -> 프레임의 윈도우 객체 이름

window.parent;   -> 프레임의 부모의 윈도우가 가지고 있는 window개체에 접근(프레임에서 현재프레임의 상위 프레임)

window.parent.location.href -> 현재윈도우의 부모의 주소

var formobject = window.parent.parent.fraMenu.document.form1;

formobject.choosePage.selectedIndex = linkIndex;



window.top; -> 최상위 프레임에 접근(현재 프레임의)

window.parent.parent.fraMenu.document.form1.choosePage.selectedIndex = linkIndex;

window.top.fraMenu.document.form1.choosePage.selectedIndex = linkIndex;

window.top.location.relace("myPagename.htm"); -> 최상위 페이지 수정

return window.top.addPage(window.location.href);



window.self; -> 현재 window 개체의 참조를 반환



window.open("파일명","target","width=크기,height=크기,left=위치,top=위치,directories=yn,copyHistory=yn,location=yn,resizable=yn,scrollbars=yn,status=yn,toolbar=yn"); -> 새창열기

width=크기       -> 새 윈도우의 폭

height=크기      -> 새 윈도우의 높이

left=위치       -> 윈도우의 위치를 왼쪽 끝을 기준

top=위치        -> 윈도우의 위치를 위쪽 끝을 기준

directories=yn   -> 디렉토리 버튼을 보여준다. (연결)익스플로러 연결도구모음, 익스플로러 전용yew||no

copyHistory=yn   -> 윈도우의 히스토리를 복사

location=yn      -> 주소 입력창

resizable=yn     -> 사용자가 윈도우의 크기를 조정 유무

scrollbars=yn    -> 스크롤바 사용 유무

status=yn       -> 상태 표시줄

toolbar=yn       -> 툴바





window.close;    -> 창닫기

window.opener;   -> 기존 윈도우의 window 개체의 참조를 반환한다.open()메서드로 윈도우를 열었을 때 호출한 윈도우

window.opener.document.bgcolor = "RED";

window.opener.document.form1.text1.value;



newWindow.resizeTo(350,200);

newWindow.moveTo(100,400);

newWindow.resizeBy(100,200);

newWindow.moveBy(20,50);



var myString = "A,B,C";

var myTextArray = myString.split(','); -> myTextArray[0]="A",myTextArray[1]="B",myTextArray[2]="C"

var myString = "본 행사는 5월 21일에 개최합니다";

var myCleanedUpString = myString.replace("5월","6월"); -> 본 행사는 6월 21일에 개최합니다

var myString = "1234567890";

alert (myString.search("7")); -> 6을 출력;

var myString = "1997, 1998, 1999, 2000, 2001, 2002";

myMatchArray = myString.match("2000");

alert(mymatchArray.length); -> myMatchArray = ('2000','2000','2001','2002')



RegExp() -> 정규식 생성자

var myRegExp =new RegExp("\\b'|'\\b");

/검색내용/gi -> g 전체를 검색, i 대소문자 구분하지 않음

\d -> 0부터 9사이의 숫자

\D -> 숫자가 아닌 모든 문자

\w -> A-Z,a-z,0-9,언더스코어 문자_와 같은 모든 단어문자

\W -> 단어 문자가 아닌 모든 문자

\s -> 탭,개행문자,캐리지 리턴, 폼 피드,수직 탭등 모든 공백 문자

\S -> 공백 문자가 아닌 모든 문자

.  -> 모든 단일 문자

[...] -> 중괄호 안에 있는 모든 문자중의 하나

[^...] -> 중괄호 안의 문자를 제외한 모든 문자

1-880-888-5474와 일치하는 정규식 -> /\d-\d\d\d-\d\d\d-\d\d\d\d/

{n} -> n개의 앞 요소와 일치한다.

{n,} -> n개 이상의 앞 요소와 일치한다.

{n,m} -> n개 이상 m개 이하의 앞 요소와 일치한다.

? -> 0개나 1개의 앞 요소와 일치한다.

+ -> 1개 이상의 앞 요소와 일치한다.

* -> 0개 이상의 앞 요소와 일치한다.

1-880-888-5474와 일치하는 정규식 -> /\d-\d{3}-\d{3}-\d{4}/

처음 /와 마지막 /는 이 사이에 있는 문자가 정규식임을 자바스크립트에게 알려준다.

/Paula?/ -> a가 있을 수 도 있고 없을 수도 있다.



^ -> 맨처음

$ -> 마지막

\b -> 단어 경계에 일치

\B -> 단어 경계가 아닌 위치에 일치



var myRegExp = /(\d{4}/g;

myString = myString.replace(myRegExp,"the year $1") -> the year 1999, the year 2000,...



한번실행후 끝

window.setTimeout("처리내용",시간);

  -> myTimerID = window.setTimeout("alert('시간 끝')",3000); 3초후 실행,고유한 식별아이디 반환

clearTimeout(myTimerID);



일정 시간 간격을 두고 연속적으로 실행

myTimerID = setInterval("myFunction()",5000);

clearInterval(myTimerID);

document.body.scrollWidth / 10; -> 윈도우의 내부 폭을 픽셀 단위로 알아내기



document.cookie = "UserName=홍길동;expires=Tue, 28 Dec 2010 00:00:00;";

var expireDate = new Date();

expdate.setTime(expireDate.getTime() + 1000 * 3600 * 24 * 30); // 30일

expireDate.setMonth(expireDate.getMonth()+6); -> 만기일 설정

document.cookie = "UserName=홍홍홍;expires=" + expireDate.toGMTString() + ";";

document.cookie = "UserName=홍홍홍;expires=" + expireDate.toGMTString() + ";path=/mystore;"; <- /mystore 디렉토리에서도 접근 가능



escape(변수);

unescape(변수);



setCookie("Name","Bob","","");

setCookie("Age","101","","");

alert(document.cookie);



var expireDate =new Date();

expireDate.setMonth(expireDate.getMonth()+12); -> 만기일 설정

setCookie("Name","Bob","/mystore",expireDate.toGMTString());



※ 기타 Tip-------------------------------------------------------------------------------------

document.all["mypara"];

mypara.innerText="hi~~~~~~~~~~";

mypara.style.color='red';

mypara.style.left = 값+"px";

mypara.innerHTML;        -> 인라인수준

div1.outerHTML;          -> 블록수준



beforeBegin      -> 시작 태그 바로 앞

afterBegin       -> 태그 바로 다음

beforeEnd        -> 종료 태그 바로 앞

afterEnd         -> 종료 태그 바로 다음

div1.insertAdjacentHTML("beforeBegin","");



이벤트

event.screenX

event.screenY

event.button -> 0안누름 1왼쪽누름 2오른쪽누름 3둘다누름...7



fromElement, srcElement, toElement



var xPos = parseInt( srcElement.offsetLeft );

var yPos = parseInt( srcElement.offsetTop );

srcElement.width;

srcElement.tagName;

srcElement.RollOver; 



※ 사용 예 -------------------------------------------------------------------------------------

<html><head>

<script language="javascript">

function display(form) {

  if(!form.subject.value) { alert("test"); };

  document.write("값을 입력하세요");

  alert("input~"); form.subject.focus();

}

</script>

</head>



<body><form method="post" action="a.php" onSubmit="return display(this)">

  <a href="#" onclick="display();">클릭하세요</a>   // 여러개의 함수 호출시 “;”를 사용해서 호출함.

</body></form>

</html>