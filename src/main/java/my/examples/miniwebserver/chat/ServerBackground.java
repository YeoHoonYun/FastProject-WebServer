package my.examples.miniwebserver.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBackground {
    private ServerSocket serverSocket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    private ServerGui gui;

    public ServerGui getGui() {
        return gui;
    }

    public void setGui(ServerGui gui) {
        this.gui = gui;
    }

    public void setting(){
        try{
            serverSocket = new ServerSocket(7777);
            System.out.println("대기중 .....");
            Socket socket = serverSocket.accept();
            System.out.println(socket.getInetAddress() + "에서 접속하였습니다.");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            String msg = in.readUTF();
            System.out.println("클라이언트로 부터의 메세지 : " + msg);

            gui.appendMsg(msg);

            while(in!=null){
                msg = in.readUTF();
                gui.appendMsg(msg);
            }


        }catch (Exception e){

        }finally {
//            try {
//                serverSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
        }
    }

    public static void main(String[] args) {
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
