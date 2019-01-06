package my.examples.minichatting.exam02;

import java.io.*;
import java.net.Socket;

public class Client {
    private String ip;
    private int port;

    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void connect(){
        Socket socket = null;
        DataInputStream  in = null;
        DataOutputStream out = null;
        BufferedReader br = null;
        try {
            socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                out.writeUTF(br.readLine());
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
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost",8000);
        client.connect();
    }
}
