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
        Food f = new Noodle();
        System.out.println("f: "+f);
        fm.buildNoodle(f);
        System.out.println("Noodle:" + f.food.toString());
        fm.buildFruit(new Food());
        fm.buildSoup(new Food());
        fm.setSoupNoodleMaker(new SoupNoodleMaker(new SoupMaker(), new NoodleMaker()));
        Food food = new soupNoodle(); // food.class().getSimpleName();
        fm.buildSoupNoodle(food);
        System.out.println("food:" + food.food.toString());
    }
}

class Food{
    StringBuilder food = new StringBuilder();
}

class Noodle extends Food{}
class NoodleMaker implements buildFood {
    @Override
    public void buildFood(final Food f) {
//        f = new Noodle();
        f.food.append("add noodle");
        f.food = new StringBuilder();
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
    public void buildFood(Food f) {
        f.food.append("add fruit");
    }
}
//class FruitMaker {
//    public Fruit buildFruit(){return null;}
//}

class FriedNoodleMaker implements buildFood {
    @Override
    public void buildFood(Food f) {
        fry(f);
        f.food.append("noodle.");
    }

    private void fry(Food food) {
        food.food.append("fry ");
    }
}
//class FriedNoodleMaker {
//    public Noodle buildNoodle() {
//        // fry noodle
//        return null;
//    }
//}

interface buildFood {
    void buildFood(Food f);
}

class Soup extends Food{}
class SoupMaker implements buildFood {


    @Override
    public void buildFood(Food f) {
        f.food.append("add soup");
    }
}
class soupNoodle extends Food{};
//
class SoupNoodleMaker implements buildFood {

    buildFood soupMaker;
    buildFood noodleMaker;
    public SoupNoodleMaker(buildFood soupMaker, buildFood noodleMaker) {
        this.soupMaker=soupMaker;
        this.noodleMaker=noodleMaker;
    }

    @Override
    public void buildFood(Food f) {
        soupMaker.buildFood(f);
        noodleMaker.buildFood(f);
    }
}

class FoodMaker {
    private buildFood fruitMaker;
    private buildFood noodleMaker;
    private buildFood friedNoodleMaker;
    private buildFood soupMaker;
    private buildFood soupNoodleMaker;

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

    public void setSoupNoodleMaker(buildFood  soupNoodleMaker){
        this.soupNoodleMaker = soupNoodleMaker;
    }

    public void buildSoupNoodle(Food f) {
        // decorator pattern
        if(soupNoodleMaker == null){
            return ;
        }
        soupNoodleMaker.buildFood(f);

    }

    public void buildNoodle(Food f) {
        if (noodleMaker == null) {
            return;
        }
        noodleMaker.buildFood(f);
    }

    public void buildFruit(Food f) {
        if (fruitMaker == null) {
            return ;
        }
        fruitMaker.buildFood(f);
    }

    public void buildFriedNoodle(Food f) {
        if (friedNoodleMaker == null) {
            return ;
        }
        friedNoodleMaker.buildFood(f);
    }
    public void buildSoup(Food f) {
        if(soupMaker == null) {
            return ;
        }
        soupMaker.buildFood(f);
    }
    public void soupNoodle(Food f) {
        if(soupNoodleMaker == null) {
            return ;
        }
        soupNoodleMaker.buildFood(f);
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
    public void buildFruit(Food f){
        if(fruitMaker == null) {
            return ;
        }
        fruitMaker.buildFood(f);
    }
    public void buildSoup(Food f){
        if(soupMaker == null) {
            return ;
        }
        soupMaker.buildFood(f);
    }
}