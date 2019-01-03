package my.examples.minichatting.ThreadExam;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-03
 * Github : https://github.com/YeoHoonYun
 */
public class BoxThread implements Runnable{
    private int num;
    private Box box;

    public BoxThread(int num, Box box) {
        this.num = num;
        this.box = box;
    }

    @Override
    public void run() {
        if(num == 1){
            box.Box1();
        }
        else if(num ==2){
            box.Box2();
        }
        else if(num == 3){
            box.Box3();
        }

    }
}
