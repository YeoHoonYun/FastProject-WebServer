package my.examples.miniwebserver;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-28
 * Github : https://github.com/YeoHoonYun
 */
public class Application {
    public static void main(String[] args) {
        WebServer server = new WebServer(9000);
        server.run();
    }
}
