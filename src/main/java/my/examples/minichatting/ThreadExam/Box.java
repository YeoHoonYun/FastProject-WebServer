package my.examples.minichatting.ThreadExam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjswo9207u@gmail.com on 2019-01-03
 * Github : https://github.com/YeoHoonYun
 */
public class Box{
    private List<String> list;

    public Box(){
        list = new ArrayList<>();
    }

    public void methodA(){
        synchronized (list){
            for(int i = 1; i< 1000; i++){
                list.add(i + "");
            }
        }
    }

    public void methodB(){
        synchronized (list){
            for(int i = 1; i < 1000; i++){
                try{
                    list.remove(0);
                } catch (Exception ignore){

                }
            }
        }
    }

    public void methodC(){
        synchronized (list){
            for(int x = 0; x < 1000; x++){
                int size = list.size();
                for(int i = 0; i < size; i++){
                    System.out.println(list.get(i));
                }
            }
        }
    }
}
