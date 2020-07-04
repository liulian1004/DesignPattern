package DesginPattern;

public class TemplatePattern {
    public static void main(String[] ags) {

    }
}

abstract class Creature {
    private int b = 1;
    abstract String speak();

    abstract void eat();

    abstract void run();

    void laugh() {
        System.out.println("laugh");
    }
}

class Snake extends Creature {

    @Override
    String speak() {
        return null;
    }

    @Override
    void eat() {

    }

    @Override
    void run() {

    }
}

class Bear extends Creature {

    @Override
    String speak() {
        return null;
    }

    @Override
    void eat() {

    }

    @Override
    void run() {

    }
}

interface Beautiful {
    int a = 0;
    void decorate();
    default void removeMarkup() {
        System.out.println("wipe out markup.");
    }
}

class Girl implements Beautiful {

    @Override
    public void decorate() {

    }
}