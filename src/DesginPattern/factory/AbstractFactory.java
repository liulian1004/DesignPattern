package DesginPattern.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AbstractFactory {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
    }
}

interface Component {
//    boolean isExpensive();
}
class Tire implements Component {
   Tire() {}
}
class Glass implements Component {
    Glass(){}
}
class Engine implements Component {
    Engine(){}
}


interface Producable {
    Component produce();
}

class f1 implements Producable {

    private static final Component tire = new Tire();

    public static Component productTire() {
        return tire;
    }

    public f1 (){};
    @Override
    public Component produce() {
        return tire;
    }
}

class f2 implements Producable {
    private static final Component glass = new Glass();

    public static Component productGlass() {
        return glass;
    }

    public f2 (){};

    @Override
    public Component produce() {
        return glass;
    }
}

class f3 implements Producable {
    private static final Component engine = new Engine();

    public static Component productEngine() {
        return engine;
    }

    public f3 (){
    }
    static final Producable engineFactory = new f3();

    @Override
    public Component produce() {
        return engine;
    }
}

enum FactoryType{
    tire,
    glass,
    engine,
}
/**
 * Based on input param, return corresponding factory that particularly produce that component.
 *
 * follow up: for engine, always return the same factory, for tire, it is ok to return a new factory every time.
 *
 * follow up2: setup a glass factory repo, as qualified candidates, for glass, return instance from the repo.
 * */
class  f4 {

    private List<Producable> glassFactories = Collections.singletonList(new f2());

    public Producable produce(FactoryType type) {
        switch (type) {
            case tire:
                if (type == FactoryType.tire) {
                    return new f1();
                }
                break;
            case glass:
                if (type == FactoryType.glass) {
                    //TODO: randomly pick something from gFactoryArray to return each time.
                    int i = (int)(Math.random()*glassFactories.size());
                    return glassFactories.get(i);
                }
                break;
            case engine:
                if (type == FactoryType.engine) {
                    return f3.engineFactory;
                }
                break;
        }
        return null;
    }
}

/**
 * Based on input param, return corresponding component.
 * */
class f5{
   public Component method(FactoryType type){
       if(type == FactoryType.engine) {
           return new Engine();
       }
       if(type == FactoryType.glass) {
           return new Glass();
       }
       if(type == FactoryType.tire) {
           return new Tire();
       }
       return null;
   }

}
