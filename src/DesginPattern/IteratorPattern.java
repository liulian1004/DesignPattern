package DesginPattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IteratorPattern {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(7);
        set.add(6);
        set.add(4);
        Collection<Integer> c = new Collection<>(set);
        Iterator<Integer> iterator = c.getIterator();
        // TODO: print all elements in collection class with iterator.
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // TODO: input two list, 0 max, 1 plus, 2 multiple, 3 divide, 4 min.
        List<Integer> l1 = new ArrayList<>();
        List<Float> l2 = new ArrayList<>();

    }

}

// List<>  ArrayList  LinkedList

class Collection<T> {
    Set<T> data;
    //private List<Integer> list;
    //private List<Float> list2;
    public Collection(Set<T> data) {
        //TODO: implement init
        data = new HashSet<>();
       // list = new ArrayList<>(data.size());
//        for (T t: data) {
//            list.add(t);
//        }
        //list = new ArrayList<>(data);
        //list2 = new ArrayList<>(data2);
    }

//    public Collection(List<Integer> list, List<Float> list2) {
//        this.list = list;
//        this.list2 = list2;
//    }
//    java.util.Iterator<Integer> i1 = list.iterator();
//
//    java.util.Iterator<Float> i2 = list2.iterator();

//    public float add() {
//        int a = (int) i1.next();
//        float b = (float) i2.next();
//        return (float)a + b;
//    }
//    public float max() {
//        int a = (int) i1.next();
//        float b = (float) i2.next();
//        return Math.max(a, (float)b);
//    }
//    public float time() {
//        int a = (int) i1.next();
//        float b = (float) i2.next();
//        return (float)a*b;
//    }

    public Iterator<T> getIterator() {
        // TODO: implement get iterator.

        Iterator<T> ite = null;
        return ite;
        // TODO: implement with IterImpl2
      //  Iterator<T, E> ite = new IterImpl2<T, E>(list.iterator(), list2.iterator());
       // return ite;
    }
}


interface Iterator<T> {
    public T next();
    public boolean hasNext();
}

interface EBase<T, E> {
    E multiply(T t);
}

class IterImpl2<T, E extends EBase> implements Iterator {
    java.util.Iterator<T> i;
    java.util.Iterator<E> i2;
    public IterImpl2(java.util.Iterator<T> i, java.util.Iterator<E> i2) {
        this.i = i;
        this.i2 = i2;
    }

    @Override
    public Object next() {
        return  i2.next().multiply(i.next());
    }

    @Override
    public boolean hasNext() {
        return i.hasNext() && i2.hasNext();
    }
}

class IterImpl<T> implements Iterator {
    List<T> list;

    public IterImpl (List<T> list) {
        this.list = list;
    }
    int i = 0;

    @Override
    public T next() {
      if(!hasNext()) {
          return null;
      }
      T res = list.get(i);
      i++;
      return res;
    }

    @Override
    public boolean hasNext() {
        return i < list.size();
    }

}