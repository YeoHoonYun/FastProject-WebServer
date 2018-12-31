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
            roomManger.searchRoom("Lobby").userAdd(user);
            user.setIn(new DataInputStream(socket.getInputStream()));
            user.setOut(new DataOutputStream(socket.getOutputStream()));

            in = user.getIn();
            out = user.getOut();

            out.writeUTF("별명을 입력하세요 : ");
            user.setName(in.readUTF());
            out.flush();
            out.writeUTF("\n\""+user.getName() + "\"로 설정 되었습니다.");
            while(true){
                if(user.isRoom()) {
                    out.writeUTF("내용(/help 도움말 | /quit 종료) : ");
                    out.flush();
                    while(true) {
                        String content = in.readUTF();
                        if (content.indexOf("/help") == 0) {
                            out.writeUTF("\n--------명령어 도움말--------");
                            out.writeUTF("/create 방이름 : 방 생성 하기");
                            out.writeUTF("/join 방이름 : 방 참여 하기");
                            out.writeUTF("/list : 방 리스트 출력하기");
                            out.flush();
                        } else if (content.indexOf("/create") == 0) {
                            String title = content.split(" ")[1];
                            Room room = new Room(title);
                            roomManger.roomAdd(room);
                            roomManger.exitUser(roomManger.searchRoom("Lobby"), user);
                            room.userAdd(user);
                            out.writeUTF(title + "방이 생성되었습니다.");
                            user.setRoom(true);
                            break;
                        } else if (content.indexOf("/join") == 0) {
                            String title = content.split(" ")[1];
                            Room room = roomManger.searchRoom(title);
                            roomManger.exitUser(roomManger.searchRoom("Lobby"), user);
                            room.userAdd(user);
                            out.writeUTF(room.getName() + "방에 접속하였습니다.");
                            user.setRoom(true);
                            break;
                        } else if (content.indexOf("/list") == 0) {
                            int i = 0;
                            for (Room room : roomManger.getRoomList()) {
                                out.writeUTF("\n" + i + " : " + room.getName());
                            }

                        }else if (content.indexOf("/quit") == 0) {
                            System.out.println("프로그램을 종료합니다.");
                            break;
                        }
                        else {
                            for(User user1 : roomManger.searchRoom("Lobby").getUserList()){
                                if(user1.isRoom()){
                                    user1.getOut().writeUTF(user.getName() + " : " + content);
                                    user1.getOut().flush();
                                }
                            }
                        }
                    }
                }
                else if(!user.isRoom()) {
                    String name = roomManger.searchRoom(user).getName();
                    out.writeUTF(name + "방에 입장하였습니다.");
                    out.flush();
                    out.writeUTF("내용(/help 도움말) : ");
                    out.flush();
                    while (true) {
                        String content = in.readUTF();
                        if (content.indexOf("/help") == 0) {
                            out.writeUTF("\n--------명령어 도움말--------");
                            out.flush();
                            out.writeUTF("/users : 사용자 리스트");
                            out.flush();
                            out.writeUTF("/exit : 방 나가기");
                            out.flush();
                        } else if (content.indexOf("/users") == 0) {
                            int i = 0;
                            out.writeUTF(name + "방 사용자 List");
                            out.flush();
                            for (User u : roomManger.searchRoom(user).getUserList()) {
                                out.writeUTF(i + " : " + u.getName());
                                out.flush();
                                i++;
                            }
                        } else if (content.indexOf("/exit") == 0) {
                            System.out.println(user.getName() + "가 "+roomManger.searchRoom(user)+"에서 나옵니다.");
                            roomManger.exitUser(roomManger.searchRoom(user), user);
                            roomManger.joinUser(roomManger.searchRoom("Lobby"), user);
                            user.setRoom(false);
                            break;
                        } else {
                            for (User user1 : roomManger.searchRoom(user).getUserList()) {
                                user1.getOut().writeUTF(user.getName() + " : " + content);
                                user1.getOut().flush();
                            }
                        }
                    }
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
