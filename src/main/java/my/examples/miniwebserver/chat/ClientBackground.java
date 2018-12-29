package my.examples.miniwebserver.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {
    private Socket socket;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private ClientGui gui;

    public void connet() {
        try {
            socket = new Socket("localhost", 7777);
            System.out.println("서버 연결됨.");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("안녕하세요. 나는 클라이언트입니다.\n");

            String msg = in.readUTF();
            System.out.println("서버로 부터의 메세지 : " + msg);
            while(in!=null){
                msg = in.readUTF();
                gui.appendMsg(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
    public ClientGui getGui() {
        return gui;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public void setGui(ClientGui gui) {
        this.gui = gui;
    }

    public static void main(String[] args) {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connet();
    }

    public void sendmessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
