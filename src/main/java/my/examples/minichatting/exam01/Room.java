package my.examples.minichatting.exam01;

import java.util.*;

public class Room {
    private String name;
    private List<User> userList;

    public Room(String name) {
        this.name = name;
        this.userList = Collections.synchronizedList(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void userAdd(User user) {
        this.userList.add(user);
    }
}

