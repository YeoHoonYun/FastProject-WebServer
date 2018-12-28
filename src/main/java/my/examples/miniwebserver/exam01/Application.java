package my.examples.miniwebserver.exam01;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-28
 * Github : https://github.com/YeoHoonYun
 */
public class Application {
    public static void main(String[] args) {
        WebServer webServer = new WebServer(9000);
        webServer.run();
    }
}
