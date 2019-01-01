package my.examples.minichatting.exam01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User {
    private String name;
    private Boolean room;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private Socket socket = null;
    private boolean admin;

    public User(Socket socket) {
        this.socket = socket;
        room = false;
        admin = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRoom() {
        return room;
    }

    public void setRoom(Boolean room) {
        this.room = room;
    }

    public void writeOutput(String string){
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(string);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void readInput(){
        try {
            in = new DataInputStream(socket.getInputStream());
            in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
