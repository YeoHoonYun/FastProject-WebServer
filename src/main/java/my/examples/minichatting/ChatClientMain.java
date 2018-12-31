package my.examples.minichatting;

/**
 * Created by cjswo9207u@gmail.com on 2018-12-31
 * Github : https://github.com/YeoHoonYun
 */
public class ChatClientMain {
    public static void main(String[] args){
        ChatClient chatClient = new ChatClient("127.0.0.1", 9999);
        chatClient.run();
    }
}
