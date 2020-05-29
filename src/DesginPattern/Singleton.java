package DesginPattern;

import java.util.ArrayList;
import java.util.List;


public class Singleton {
    public static void main(String[] args) {
//        Singleton2 a = Singleton2.getInstance();
//        Singleton2 b = Singleton2.getInstance();
//        System.out.println(a.equals(b));

        ICar car1 = new Ford();
        ICar car3 = new Ford();
        ICar car4 = new Ford();
        ICar car2 = new BYD();
        Singleton2 a = Singleton2.getInstance(car1);
        Singleton2 b = Singleton2.getInstance(car1);
        Singleton2 f = Singleton2.getInstance(car3);
        Singleton2 g = Singleton2.getInstance(car4);
        System.out.println(a.equals(b)); // true
        System.out.println(a.equals(f)); // true
        System.out.println(a.equals(g)); // true
        Singleton2 c = Singleton2.getInstance(car2);
        System.out.println(a.equals(c)); // false
        Singleton2 d = Singleton2.getInstance(car2);
        System.out.println(c.equals(d)); // true

//        new Thread(() ->{
//            System.out.println(Thread.currentThread().toString());
//            Singleton2 a = Singleton2.getInstance();
//        }).start();
//
//        new Thread(() ->{
//            System.out.println(Thread.currentThread().toString());
//            Singleton2 b = Singleton2.getInstance();
//        }).start();
//
//        System.out.println(Thread.currentThread().toString());
//        Singleton2 c = Singleton2.getInstance();
    }
}

interface ICar {
}

class Ford implements ICar {
}

class BYD implements ICar {
}

/**
 * Only one instance of Singleton class at any time.
 * */
class Singleton2 {

    private final ICar car;
//    private Singleton2() {
//        car = null;
//    }

    private Singleton2 (ICar car) {
        this.car = car;
    }

    private static Singleton2 instance;


    // synchronized : 用于多现成，只初始化一次
//    public static synchronized  Singleton2 getInstance() {
//        if (instance == null) {
//            instance = new Singleton2();
//        }
//        return instance;
//    }

    //pratice:
    //有条件的singleton
    public static Singleton2 getInstance(ICar car) {
        if (instance == null) {
            instance = new Singleton2(car);
        }else if(!instance.car.getClass().equals(car.getClass()) ) {
            instance = new Singleton2(car);
        }
        return instance;
    }


}

