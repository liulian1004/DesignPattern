package DesginPattern;

import java.util.*;

public class ObjectPool {
    public static Data test = null;
    public static void main(String[] strings) {
        Pool pools= new Pool(3);
//        Data d1 = new Data();
//        System.out.println("d1 " + d1);
//        Data d2 = new Data();
//        System.out.println("d2 " + d2);
        System.out.println("next available: " + pools.nextAvailable());
        System.out.println("next available: " + pools.nextAvailable());
        System.out.println("next available: " + pools.nextAvailable());
        //Data ran = pools.random();
//        System.out.println("released data: " + ran);
//        pools.release(ran);
        System.out.println("next available: " + pools.nextAvailable());
        System.out.println("next available: " + pools.nextAvailable());
    }
}

class Data {
   // private static  Data data = null;
    Data(){};
//    public static Data getInstance() {
//        return data = new Data();
//    }
}

//constructor: size,
class Pool {

    private Queue<Data> queue;
    private Set<Data> used = new HashSet<>();
    public static Data getData(){
        Data data = new Data();
        return data;

    }
    public Pool(int size) {
        queue = new ArrayDeque<>(size);
        //写进的是同一个对象
        //Arrays.fill(pools,getData());
        //写数据用这个方法
//        for(int i =0;i < size; i++) {
//            pools[i] = getData();
//        }
        //读数据用这个方法
//        for(Data d: pools){
//            System.out.println(d);
//        }
        for(int i = 0; i < size; i++) {
            queue.offer(getData());
        }

    }
//    public Data random(){
//        int ran = (int)(Math.random() * queue.size());
//        return pools[ran];
//    }

    public Data nextAvailable() {
        // TODO: return the next available data instance and mark it as used, null if no data instance is available.
        if(queue.isEmpty()) {
            return null;
        }
        Data res = queue.poll();
        used.add(res);
        return  res;
    }

    public synchronized void release(Data d) {
        // TODO: mark data instance as released.
       if(used.contains(d)) {
           used.remove(d);
           queue.offer(d);
       }
    }
}
