package my.examples.minichatting.exam01;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RoomManger {
    private List<Room> roomList;

    public RoomManger(){
        roomList = Collections.synchronizedList(new ArrayList<>());
    }

    public void roomAdd(Room room){
        this.roomList.add(room);
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Room searchRoom(String name){
        for(Room r : roomList){
            if(r.getName().equals(name)){
                return r;
            }
        }
        return null;
    }

    public Room searchRoom(User name){
        for(Room r : roomList){
            for(User user : r.getUserList()){
                if(user == name){
                    return r;
                }
            }
        }
        return null;
    }

    public void exitUser(Room room, User name){
        Iterator<User> iterator = room.getUserList().iterator();
        while(iterator.hasNext()){
            if(iterator.next() == name){
                iterator.remove();
            }
        }
        if(room.getUserList().size()==0){
            roomList.remove(room);
        }
    }
    public void joinUser(Room room, User user){
        room.userAdd(user);
    }

    public void talkUser(Room room, String s){
        for(User user : room.getUserList()){
            user.writeOutput(s);
        }
    }
}
