package DesginPattern;
/**
 * FoodMaker is the service render that delegates requests( API: buildNoodle, API:buildFruit)
 * to executors(class NodleMaker, class FruitMaker)
 * The service is provided by executors.
 * */
public class Composit {

    public static void main(String[] args) {
        FoodMaker fm = new FoodMaker();
        fm.setFruitMaker(new FruitMaker());
        fm.setNoodleMaker(new NoodleMaker());
        fm.setSoupMaker(new SoupMaker());
        fm.buildNoodle();
        fm.buildFruit();
        Food soup = fm.buildSoup();

    }
}

class Food{}

class Noodle extends Food{}
class NoodleMaker implements buildFood {
    @Override
    public Food buildFood() {
        return new Noodle();
    }
}
//class NoodleMaker extends FruitMaker {
//    public Noodle buildNoodle(){
//        //
//        return null;
//    }
//}

class Fruit extends Food{}
class FruitMaker implements buildFood {
    @Override
    public Food buildFood() {
        return new Fruit();
    }
}
//class FruitMaker {
//    public Fruit buildFruit(){return null;}
//}

class FriedNoodleMaker implements buildFood {
    @Override
    public Food buildFood() {
        Noodle noodle = new Noodle();
        fry(noodle);
        return noodle;
    }

    private void fry(Food food) {}
}
//class FriedNoodleMaker {
//    public Noodle buildNoodle() {
//        // fry noodle
//        return null;
//    }
//}

interface buildFood {
    Food buildFood();
}

class Soup extends Food{}
class SoupMaker implements buildFood {
    @Override
    public Food buildFood() {
        return new Soup();
    }
}

class FoodMaker {
    private buildFood fruitMaker;
    private buildFood noodleMaker;
    private buildFood friedNoodleMaker;
    private buildFood soupMaker;

    public FoodMaker(buildFood fm, buildFood nm, buildFood fnm) {
        fruitMaker = fm;
        noodleMaker = nm;
        friedNoodleMaker = fnm;
    }

    public FoodMaker() {}

    public void setNoodleMaker(buildFood noodleMaker) {
        this.noodleMaker = noodleMaker;
    }

    public void setFruitMaker(buildFood fruitMaker) {
        this.fruitMaker = fruitMaker;
    }

    public void setFriedNoodleMaker(buildFood friedNoodleMaker) {
        this.friedNoodleMaker = friedNoodleMaker;
    }

    public void setSoupMaker(buildFood soupMaker) {
        this.soupMaker = soupMaker;
    }

    public void buildSoupNoodle() {
        // decorator pattern
    }

    public Food buildNoodle() {
        if (noodleMaker == null) {
            return null;
        }
        return noodleMaker.buildFood();
    }

    public Food buildFruit() {
        if (fruitMaker == null) {
            return null;
        }
        return fruitMaker.buildFood();
    }

    public Food buildFriedNoodle() {
        if (friedNoodleMaker == null) {
            return null;
        }
        return friedNoodleMaker.buildFood();
    }
    public Food buildSoup() {
        if(soupMaker == null) {
            return null;
        }
        return soupMaker.buildFood();
    }
}

class StreetStand {
    private buildFood fruitMaker;
    private buildFood soupMaker;
    public StreetStand(){

    }

    public void setMakeFruit(buildFood makeFruit) {
        this.fruitMaker = makeFruit;
    }

    public void setSoupMaker(buildFood makeSoup) {
        this.soupMaker = makeSoup;
    }
    public Food buildFruit(){
        if(fruitMaker == null) {
            return null;
        }
        return fruitMaker.buildFood();
    }
    public Food buildSoup(){
        if(soupMaker == null) {
            return null;
        }
        return soupMaker.buildFood();
    }
}