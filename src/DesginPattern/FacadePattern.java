package DesginPattern;

/**
 * Facade is to simplify the system interface by providing a interface to subsystems
 * furthermore, you can do the change in the subsystems without changing in the client code, it reduces compilation dependencies
 * */
// TODO: read through https://github.com/gxwangdi/CodePattern/blob/master/DesignPatterns/src/edu/gxwangdi/design/patterns/FacadePattern.java
//
public class FacadePattern {
    public static void main(String[] args) {
        Human he = new Human(new Mouth(), new Stomach(),new Throat());
        FFood rice = new Rice();
        he.eat(rice);

        he.speak();
    }
}

abstract class FFood {}

class Rice extends FFood {}
class Liquid extends FFood{}

abstract class Organ {}

class  FAdapter{
    public  Liquid transfer(FFood f) {
        if(f != null) {
            return new Liquid();
        }
        return null;
    }
}
// TODO： implement adapter patter, heterogeneous food instance into mouth, homogeneous something for stomach out
//  of mouth and into stomach.
class Mouth extends Organ {
    public void takeIn(FFood f) {
        chew(f);
    }
    private void chew(FFood f) {}
    public void tone(){
        System.out.println("tone is working");
    }

}

class Stomach extends Organ {
    public void dignose(FFood f) {
        FAdapter fAdapter = new FAdapter();
        fenmi(fAdapter.transfer(f));
    }
    private void fenmi(FFood f) {
    }
}

class Throat extends Organ {
    public void speak(){
        System.out.println("speaking is working");
    }
}
//
class Human {
//Composite pattern
// 小弟是以下instance
    private final Mouth mouth;
    private final Stomach stomach;
    private final Throat throat;

    public Human(Mouth mouth, Stomach stomach, Throat throat) {
        this.mouth = mouth;
        this.stomach = stomach;
        this.throat = throat;
    }
//Decorator pattern
    public void eat(FFood food) {
        mouth.takeIn(food);
        stomach.dignose(food);
    }

    public void speak() {
        // TODO: mouth and throat move at the same time, and aggregate together to finish this action.

        Thread t = new Thread (){
            @Override
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throat.speak();
            }
        };
        t.start();
        mouth.tone();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}