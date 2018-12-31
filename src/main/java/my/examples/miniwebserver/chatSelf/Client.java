package my.examples.miniwebserver.chatSelf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-30
 * Github : https://github.com/YeoHoonYun
 */
public class Client {
    Socket socket = null;
    String ip = null;
    int port = 0;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void Connect() {
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            this.socket = new Socket(ip, port);
            System.out.println("서버 접속");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("클라이언트가 접속하였습니다.");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 8000);
        client.Connect();
    }
}

class ClientThead extends Thread{
    Socket socket;

    public ClientThead(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

        }catch (Exception e){

        }finally {
//            try {
//                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
    }
}