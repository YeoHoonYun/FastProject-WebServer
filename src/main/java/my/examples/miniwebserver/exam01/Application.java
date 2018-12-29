package my.examples.miniwebserver.exam01;

public class Application {
    public static void main(String[] args) {
        WebServer webServer = new WebServer(8000);

        webServer.run();
    }
}
