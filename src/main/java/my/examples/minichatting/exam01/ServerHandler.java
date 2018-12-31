package my.examples.minichatting.exam01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler extends Thread{
    Socket socket = null;
    RoomManger roomManger;

    public ServerHandler(Socket socket, RoomManger roomManger) {
        this.socket = socket;this.roomManger = roomManger;
    }

    @Override
    public void run() {
        User user = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            user = new User(socket);
            user.setIn(new DataInputStream(socket.getInputStream()));
            user.setOut(new DataOutputStream(socket.getOutputStream()));

            in = user.getIn();
            out = user.getOut();

            out.writeUTF("\n별명을 입력하세요 : ");
            user.setName(in.readUTF());
            out.flush();
            out.writeUTF("\n\""+user.getName() + "\"로 설정 되었습니다.");

            while(true){
                String content = in.readUTF();
                if(!user.isRoom()) {
                    if (content.indexOf("/help") == 0) {
                        out.writeUTF("\n--------명령어 도움말--------");
                        out.writeUTF("/create 방이름 : 방 생성 하기");
                        out.writeUTF("/join 방이름 : 방 참여 하기");
                        out.writeUTF("/list : 방 리스트 출력하기");
                        out.writeUTF("/place");
                        out.flush();
                    } else if (content.indexOf("/create") == 0) {
                        String title = content.split(" ")[1];
                        Room room = new Room(title);
                        roomManger.roomAdd(room);
                        room.userAdd(user);
                        out.writeUTF(title + "방이 생성되었습니다.");
                        user.setRoom(true);
                    } else if (content.indexOf("/join") == 0) {
                        String title = content.split(" ")[1];
                        Room room = roomManger.searchRoom(title);
                        room.userAdd(user);
                        out.writeUTF(room.getName()+"방에 접속하였습니다.");
                        user.setRoom(true);
                    } else if (content.indexOf("/list") == 0) {
                        int i = 0;
                        for (Room room : roomManger.getRoomList()) {
                            out.writeUTF("\n"+i + " : " + room.getName());
                        }
                    } else {
                        System.out.println(in.readUTF());
                    }
                }
                else{
                    String name = roomManger.searchRoom(user).getName();
                    out.writeUTF(name+" 대화방 안에 있습니다.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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
}
