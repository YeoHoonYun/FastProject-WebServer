package my.examples.minichatting.exam01;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable{
    private String ip;
    private int port;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        Socket socket = null;
        DataOutputStream out = null;
        BufferedReader br = null;
        try {
            socket = new Socket(ip, port);
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new DataOutputStream(socket.getOutputStream());

            System.out.println("소켓을 연결합니다.");

            ClientHandler clientHandler = new ClientHandler(socket);
            clientHandler.start();

            while(true){
                String string = br.readLine();
                out.writeUTF(string);
                out.flush();
            }

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
        int port = 8000;
        if(args.length > 1){
            port = Integer.parseInt(args[0]);
        }

        Client client = new Client("localhost", port);
        new Thread(client).start();
    }
}
