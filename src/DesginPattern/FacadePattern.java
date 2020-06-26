package DesginPattern;

/***/
// TODO: read through https://github.com/gxwangdi/CodePattern/blob/master/DesignPatterns/src/edu/gxwangdi/design/patterns/FacadePattern.java
// and figure out the point for facade, complement the FacadePattern javadoc.
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

abstract class Organ {}

// TODO： implement adapter patter, heterogeneous food instance into mouth, homogeneous something for stomach out
//  of mouth and into stomach.
class Mouth extends Organ {
    public void takeIn(FFood f) {
        chew(f);
    }

    private void chew(FFood f) {}
    public void tone(){}
}

class Stomach extends Organ {
    public void dignose(FFood f) {
        fenmi(f);
    }
    private void fenmi(FFood f) {

    }
}

class Throat extends Organ {
    public void speak(){
        System.out.println("Hello world.");
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
        //
        mouth.tone();
      throat.speak();
    }
}