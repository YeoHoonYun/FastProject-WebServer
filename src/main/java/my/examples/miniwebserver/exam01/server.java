package my.examples.miniwebserver.exam01;

import java.io.IOException;
import java.net.Socket;

public class server {
    Socket socket = null;

    public void connet(){
        try {
            socket = new Socket("127.0.0.1",8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        server s = new server();
        s.connet();
    }
}
