package my.examples.minichatting.ThreadExam;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-03
 * Github : https://github.com/YeoHoonYun
 */
public class BoxThread implements Runnable{
    private Box box;
    private int num;

    public BoxThread(Box box, int num){
        this.box = box;
        this.num = num;
    }

    @Override
    public void run() {
        if(num == 1){
            box.methodA();
        }
        else if(num == 2) {
            box.methodB();
        }
        else if(num == 3) {
            box.methodC();
        }
    }
}
