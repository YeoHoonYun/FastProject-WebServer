package my.examples.miniwebserver;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-28
 * Github : https://github.com/YeoHoonYun
 */
public class WebServer {
    // 브라우저가 서버에 접속 -> 항상 대기를 해야함.
    private int port;

    public WebServer(int port) {
        this.port = port;
    }

    public void run() {
        // 접속을 대기하고 있다.(무한 반복)

        // 별도의 일을 처리를 하게 해주는 상대를 핸들러

        // 쓰레드 부분
        // 누군가 접속을 하면, 서버는 한줄씩 읽어 들인다. (빈줄나올때까지)
        // path에 해당하는 리소스를 찾는다.
        // 리소스가 있을 경우에, 상태코드를 보낸다. (첫줄)
        // 헤더들을 보낸다. (body의 길이, body가 무슨 mime type)
        // 빈줄을 보낸다.
        // body를 보낸다.
    }
}
