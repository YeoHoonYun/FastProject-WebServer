package my.examples.miniwebserver.chatSelf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-30
 * Github : https://github.com/YeoHoonYun
 */
public class Server {
    ServerSocket serverSocket = null;
    Socket socket = null;

    public void setting(){
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            serverSocket = new ServerSocket(8000);
            System.out.println("서버 대기중");
            socket = serverSocket.accept();
            System.out.println("서버 접속완료");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            System.out.println(in.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        Server server = new Server();
        server.setting();

    }
}

class ServerThread extends Thread{
    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
        }catch (Exception e){
//            try {
//                in.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            try {
//                out.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
        }

    }
}