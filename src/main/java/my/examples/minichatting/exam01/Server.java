package my.examples.minichatting.exam01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start(){
        RoomManger roomManger = new RoomManger();
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);

            System.out.println("연결을 대기합니다.");
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("클라이언트가 연결되었습니다.");
                Room room = new Room("Lobby");
                roomManger.roomAdd(room);

                ServerHandler serverHandler = new ServerHandler(socket, roomManger);
                serverHandler.start();
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

    public static void main(String[] args) {
        Server server = new Server(8000);
        server.start();
    }
}
