# MiniWebServer
## 2018-12-28
### MiniWebServer 업데이트
* my.examples.miniwebserver
* 사용자가 브라우저를 통하여 html를 랜더링하는 과정
    - 브라우저가 서버에 접속
    - 브라운저가 GET 방식으로 데이터를 보낸다.
         + 요청라인 + 헤더정보:헤더값 + 빈줄 + body
    - 서버는 요청 정보를 받아들여서 해당 path에 자원을 반응한다.
    - 상태코드 + 헤더이름:헤더값 + 빈줄 + 내용
    - 브라우저와 서버의 연결이 끊어진다.
    - 사용자가 원하는 화면을 보여주는 걸 랜더링
    - 여러요청을 처리하기 위해서 핸들러와 쓰레드를 만들어서 역할을 분리
* 템플릿 메소드 패턴

------------------------------------------------------
## 2018-12-29
### MiniWebServer 추가 내용 정리
* HTTP 프로토콜 요청과 응답 메시지 구조
![HTTP 요청 구조](https://4.bp.blogspot.com/-2x_75s1GrKg/WWhJHtTXK-I/AAAAAAAASl4/kc1Sc73R2OYdA7gJC0sV4rHRqzRx3JUyACLcBGAs/s1600/36.jpg "HTTP 요청 구조") 
    - 요청라인 / HTTP 헤더 정보 / 헤더 /메시지 바디 (헤더와 메시지 바디 사이에는 한줄로 헤더와 바디를 구분한다.
    
![HTTP 요청 예시](https://4.bp.blogspot.com/-LtT5Y2MOtfQ/WWhJ7m9VERI/AAAAAAAASl8/GWSs4qKxdVA0pVQwgBPgXbqZbbps2SdBQCLcBGAs/s1600/37.jpg "HTTP 요청 예시") 
    - 웹서버를 JAVA로 짤때 GET / HTTP/1.1부분을 분리하여 가지고 온다.
```
            String requestLine = in.readLine();
            String[] s = requestLine.split(" ");
            System.out.println(s[0] + s[1] + s[2]);
            String httpMethod = s[0];
            String httpPath = s[1];
            if(httpPath.equals("/"))
                httpPath = "/index.html";
            String filePath = baseDir + httpPath;
```
