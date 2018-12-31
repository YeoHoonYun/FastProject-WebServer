package my.examples.minichatting;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-31
 * Github : https://github.com/YeoHoonYun
 */
public class ChatServerMain {
    public static void main(String[] args){
        ChatServer chatServer = new ChatServer(9999);
        chatServer.run();
    }
}
