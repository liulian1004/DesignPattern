package DesginPattern;

/**
 * Allows you to stack multiple deliverables from different vendors without inheritance.
 * */
public class Decorator {

    public static void main(String[] args) {
        Washer w1 = Washing.getInstance();
        Washer w2 = Washing.getInstance();
        System.out.println(w1.equals(w2));
        WashStore washStore = new WashStore(w1);
        Cloth c1 = washStore.washingCloth();
        BeijingChef chef = new BeijingChef();
        Dish dish = new Dish();
        GuangDongChef chef2 = new GuangDongChef();
//        chef.cook(dish);
        //chef2.cook(dish);
        SichuanChef sc = new SichuanChef();
        GDChef gd = new GDChef();
        BJChef bj = new BJChef(gd, sc);
        bj.cook(dish);
    }
}


class Cloth {
}

interface Washer {
    Cloth wash();
}

 class Washing implements Washer {
    private static final Washer w = new Washing();

    @Override
    public Cloth wash() {
        return new Cloth();
    }

    public static Washer getInstance() {
        return w;
    }
}

 class WashStore {
//    static Washer w = Washing.getInstance();
    private Washer w;
    WashStore(Washer w) {
        this.w = w;
    }
    public /*static*/ Cloth washingCloth(/*Washer w*/) {
        return w.wash();
    }
}

class Dish {}

interface Chef {
    void cook(Dish dish);
}

class SichuanChef implements Chef {
    @Override
    public void cook(Dish dish) {
        System.out.println("make it spicy");
    }
}

class GDChef implements Chef {
    private Chef sc;
    private Chef bj;

    @Override
    public void cook(Dish dish) {
        System.out.println("make it sweet");
    }
}

class BJChef implements Chef {
    private Chef gd;
    private Chef sc;

    BJChef(Chef gd, Chef sc) {
        this.gd = gd;
        this.sc = sc;
    }

    @Override
    public void cook(Dish dish) {
        gd.cook(dish);
        sc.cook(dish);
        System.out.println("make it salt");
    }
}

class GuangDongChef extends SichuanChef{
    @Override
    public void cook(Dish dish) {
        super.cook(dish);
        System.out.println("make it sweet");
    }
}

class BeijingChef extends GuangDongChef{
    @Override
    public void cook(Dish dish) {
//        super.cook(dish);
        System.out.println("make it salt");
        super.cook(dish);
    }
}