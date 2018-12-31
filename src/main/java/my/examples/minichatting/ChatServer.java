package my.examples.minichatting;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-31
 * Github : https://github.com/YeoHoonYun
 */
public class ChatServer {
    private int port;
    private ChatHouse chatHouse;

    public ChatServer(int port){
        this.port = port;
        chatHouse = new ChatHouse();
    }

    public void run(){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            while(true) {
                Socket socket = serverSocket.accept();
                ChatServerHandler chatServerHandler
                        = new ChatServerHandler(socket, chatHouse);
                chatServerHandler.start();
            }
        }catch (Exception ex){
            System.out.println("오류 발생.");
        }finally {
            try{ serverSocket.close(); }catch(Exception ignore){}
        }
    }
}
