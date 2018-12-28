package my.examples.miniwebserver.exam01;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.logging.SocketHandler;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-28
 * Github : https://github.com/YeoHoonYun
 */
public class WebServer {
    private int port;
    public WebServer(int port) {
        this.port = port;
    }

    public void run() {
        // 소켓 생성
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            //서버 연결 대기
            while(true) {
                System.out.println("서버 대기 중입니다.");
                Socket socket = serverSocket.accept();
                // 서버연결
                System.out.println("서버에 연결합니다.");
                WebHandler webHandler = new WebHandler(socket);
                webHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class WebHandler extends Thread{
    Socket socket = null;

    public WebHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintStream out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            String stringLine = null;
            while((stringLine=in.readLine()) != null){
                System.out.println(stringLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}