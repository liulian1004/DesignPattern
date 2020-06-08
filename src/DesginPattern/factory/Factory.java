package DesginPattern.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Factory pattern: based on different input param, return corresponding implementations.
 * */
public class Factory {
    public static void main(String[] arg) {
      good go = new good();

        System.out.println(go.toString());
        System.out.println("go: " + go);

        S1 s1 = S1.getInstance(Type.car);
        S1 s2 = S1.getInstance(Type.car);
        System.out.println(s1==s2);

        S1 s3 = S1.getInstance(Type.food);
        System.out.println(s3!=s1);
//        S1 s4 = new S1();
//
//        System.out.println(s4);
//        System.out.println(s4.i);
//        System.out.println(s4.k);
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();

//        System.out.println("Factory pattern:");
//        car c1 = F1.produce(cartype.byd);
//        System.out.println(c1 instanceof F1.byd);
//        car c2 = F1.produce(cartype.ford);
//        System.out.println(c2 instanceof F1.ford);
//        car c3 = F1.produce(cartype.tesla);
//        System.out.println(c3 instanceof F1.tesla);
//        System.out.println(!(c3 instanceof F1.ford));
//
//        car c4 = F1.produce(cartype.byd);
//        System.out.println(c1 == c4);
//
//        car c5 = F1.produce(cartype.tesla);
//        System.out.println(c3!=c5);
        car c1 = F1.produce(cartype.tesla);
        System.out.println("Test F1 tesla:" + c1.toString() );

//        base1 b = new base1();
        base1 b1 = new c1();
        base1 b2 = new c2();
//        c1 c = new b1(); X
    }
}

abstract class base1 {}

class c1 extends base1 {}
class c2 extends base1 {}

// singleton review
enum Type {
    car,
    food,
    clothes,
}

interface car {}  // public

enum cartype { // public
    ford,
    tesla,
    byd,
}

class F1 { // public
//    private static byd bydCar;
    // make sure you have only one byd instance using final.
    private final static byd bydCar = new byd();
    private final static tesla[] tesla = new tesla[]{new tesla(),new tesla(),new tesla()};


//    private final static int test = 1;

    static class ford implements car {}
    static class tesla implements car {}
    static class byd implements car {}
//    private final car car;
//    private final cartype cartype;
//    private F1(cartype type) {
//        car = F
//        cartype = type;
//    }

    // only F1.produce() provide service to outside
    // package, car constructor is not exposed to outside package.
    // encapsulation.
    public static car produce(cartype type) {
        // Only for byd we always return the same instance.
        if (type == cartype.byd) {
//            if(bydCar == null) {
//                bydCar = new byd();
//            }
            return bydCar;
        }
        if (type == cartype.tesla) {
            int i  = (int)(Math.random()*tesla.length) ;
            return tesla[i];
        }
        if (type == cartype.ford) {
            return new ford();
        }
        return null;
//    F1 instance = new F1(type);
//    car c = instance.car;
//    return c;
    }
}

// If the requested type is different, get a new instance, otherwise always give me the same instance.
class S1 extends Object {
    // Override toString() so that anytime when
    // system needs to convert instance to string,
    // it is not the default implementation
    // package_name.class_name@memory_address.
    @Override
    public String toString() {
        return "S1{" +
                "i=" + i +
                ", k=" + k +
                '}';
    }

    // private final Type type;

    // Empty constructor still makes new instance,
    // it is just that all attributes are default value.
    private  S1(/*Type type*/){
        // this.type = type;
    }
    // Type is not related to S1,
    // it is only used for identifying
    // if new instance is necessary.
    private int i = 4;
    private int k = 1;

    private static S1 instance;
    private static Type type;

    // synchronized for multi-threading.
    public static synchronized S1 getInstance(Type type) {
        if(instance == null) {
//            instance = new S1(type);
            instance = new S1();
            S1.type = type;
            // Pay attention to the way static var is referenced.
        }else if(S1.type != type) {
            instance = new S1();
            S1.type = type;
        }
        return instance;
    }
}

class good{

}
class Cat {}

class fork  {}

class auto {}

class F2 {
    public static synchronized Object getInstance(int type) {

    return null;
    }
}
