package my.examples.minichatting.exam02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int ip;
    public Server(int i) {
        this.ip = i;
    }

    public void start(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            serverSocket = new ServerSocket(ip);
            System.out.println("서버 대기");
            socket = serverSocket.accept();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("소켓 접속");
            while(true) {
                System.out.println(in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8000);
        server.start();
    }
}
