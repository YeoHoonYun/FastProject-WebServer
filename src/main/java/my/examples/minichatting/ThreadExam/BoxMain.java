package my.examples.minichatting.ThreadExam;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-03
 * Github : https://github.com/YeoHoonYun
 */
public class BoxMain {
    public static void main(String[] args) {
        Thread Thread01 = new Thread(new BoxThread(1, new Box()));
        Thread Thread02 = new Thread(new BoxThread(2, new Box()));
        Thread Thread03 = new Thread(new BoxThread(3, new Box()));

        Thread01.start();
        Thread02.start();
        Thread03.start();

        System.out.println("end");

    }
}
