package my.examples.minichatting.ThreadExam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-03
 * Github : https://github.com/YeoHoonYun
 */
public class Box{
    List<String> stringList;

    public Box() {
        this.stringList = new ArrayList<>();
    }

    public synchronized void Box1() {
        for(int i = 0; i< 10; i++){
            System.out.print("A");
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void Box2(){
        for(int i = 0; i< 10; i++) {
            System.out.print("B");
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void Box3(){
        for(int i = 0; i< 10; i++) {
            System.out.print("C");
            try {
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
