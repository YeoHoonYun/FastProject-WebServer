package my.examples.minichatting.ThreadExam;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-03
 * Github : https://github.com/YeoHoonYun
 */
public class BoxMain {
    public static void main(String[] args) {
        Box box = new Box();
        Thread t1 = new Thread(new BoxThread(box, 1));
        Thread t2 = new Thread(new BoxThread(box, 2));
        Thread t3 = new Thread(new BoxThread(box, 3));
        t1.start();
        t2.start();
        t3.start();
        System.out.println("end!");

    }
}
