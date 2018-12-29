package my.examples.miniwebserver.exam01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class client {
    ServerSocket serverSocket = null;

    public void setting(){
        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("대기합니다.");
            Socket socket = serverSocket.accept();
            System.out.println("연결되었습니다.");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        client client = new client();
        client.setting();

    }
}
