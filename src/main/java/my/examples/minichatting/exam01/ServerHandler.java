package my.examples.minichatting.exam01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler extends Thread{
    private Socket socket = null;
    private RoomManger roomManger;

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
            out.writeUTF("\""+user.getName() + "\"로 설정 되었습니다.");
            while(true){
                if(!user.isRoom()) {
                    while(true) {
                        out.writeUTF("내용(/help 도움말 | /quit 종료) : ");
                        out.flush();
                        String content = in.readUTF();
                        if (content.indexOf("/help") == 0) {
                            out.writeUTF("--------명령어 도움말--------");
                            out.writeUTF("/help : 명령어 보기");
                            out.writeUTF("/quit : 프로그램 종료하기");
                            out.writeUTF("/create 방이름 : 방 생성 하기");
                            out.writeUTF("/join 방이름 : 방 참여 하기");
                            out.writeUTF("/list : 방 리스트 출력하기");
                            out.flush();
                        } else if (content.indexOf("/create") == 0) {
                            try{
                                String title = content.split(" ")[1];
                                Room room = new Room(title);
                                roomManger.roomAdd(room);
                                roomManger.exitUser(roomManger.searchRoom("Lobby"), user);
                                room.userAdd(user);
                                out.writeUTF(title + "방이 생성되었습니다.");
                                user.setAdmin(true);
                                user.setRoom(true);
                                break;
                            }catch (Exception e){
                                out.writeUTF("잘못된 값입니다. 방제목을 입력하세요.");
                                out.flush();
                                break;
                            }
                        } else if (content.indexOf("/join") == 0) {
                            try{
                                String title = content.split(" ")[1];
                                Room room = roomManger.searchRoom(title);
                                roomManger.exitUser(roomManger.searchRoom("Lobby"), user);
                                room.userAdd(user);
                                out.writeUTF(room.getName() + "방에 접속하였습니다.");
                                user.setRoom(true);
                                break;
                            }catch (Exception e){
                                out.writeUTF("잘못된 값입니다. 방제목을 입력하세요.");
                                out.flush();
                                break;
                            }
                        } else if (content.indexOf("/list") == 0) {
                            int i = 0;
                            for (Room room : roomManger.getRoomList()) {
                                out.writeUTF(i + " : " + room.getName());
                                i++;
                            }

                        }else if (content.indexOf("/quit") == 0) {
                            out.writeUTF("프로그램을 종료합니다.");
                            out.flush();
                            in.close();
                            out.close();
                            socket.close();
                        }
                        else {
                            roomManger.roomChat(roomManger.searchRoom("Lobby"), user.getName() + " : " + content);
                        }
                    }
                }
                else if(user.isRoom()) {
                    Room room = roomManger.searchRoom(user);
                    String name = room.getName();
                    String text = name + "방에 "+ user.getName()+ "입장하였습니다.";
                    roomManger.roomChat(room, text);
                    while (true) {
                        if(!user.isRoom()){
                            break;
                        }
                        out.writeUTF("내용(/help 도움말) : ");
                        out.flush();
                        String content = in.readUTF();
                        if (content.indexOf("/help") == 0) {
                            out.writeUTF("--------명령어 도움말--------");
                            out.flush();
                            out.writeUTF("/users : 사용자 리스트");
                            out.flush();
                            out.writeUTF("/exit : 방 나가기");
                            out.flush();
                            if(user.getAdmin()){
                                out.writeUTF("/kick : 사용자 강퇴");
                                out.flush();
                            }
                        } else if (content.indexOf("/users") == 0) {
                            int i = 0;
                            out.writeUTF(name + "방 사용자 List");
                            out.flush();
                            for (User u : room.getUserList()) {
                                out.writeUTF(i + " : " + u.getName());
                                out.flush();
                                i++;
                            }
                        } else if (content.indexOf("/exit") == 0) {
                            String exitText = user.getName() + "가 "+room.getName()+"에서 나옵니다.";
                            roomManger.roomChat(room, exitText);
                            roomManger.exitUser(room, user);
                            roomManger.searchRoom("Lobby").userAdd(user);
                            user.setAdmin(false);
                            break;
                        } else if (user.getAdmin() & content.indexOf("/kick") == 0) {
                            out.writeUTF("강퇴할 사람의 번호를 입력하세요.");
                            out.flush();
                            int i = 0;
                            out.writeUTF(name + "방 사용자 List");
                            out.flush();
                            for (User u : room.getUserList()) {
                                out.writeUTF(i + " : " + u.getName());
                                out.flush();
                                i++;
                            }
                            out.writeUTF("강퇴시킬 사람의 번호 : ");
                            out.flush();
                            int kickText = Integer.parseInt(in.readUTF());
                            try {
                                User user1 = room.getUserList().get(kickText);
                                out.writeUTF(user1.getName() + "님을 강퇴하였습니다.");
                                out.flush();
                                String exitText = user1.getName() + "가 " + room.getName() + "에서 강퇴당했습니다.";
                                roomManger.roomChat(room, exitText);
                                roomManger.exitUser(room, user1);
                                user1.setAdmin(false);
                            }catch (Exception e){
                                out.writeUTF("잘못된 값입니다. 방제목을 입력하세요.");
                                out.flush();
                            }

                        }
                        else {
                            roomManger.roomChat(room, user.getName() + " : " + content);
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
