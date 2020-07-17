package DesginPattern;

import java.util.ArrayList;
import java.util.List;

interface executable {
    void execute();
}

enum Ctype {
    Cford("ford"),
    Cbyd("byd"),
    CTesla("tesla");

    private final String type;

    Ctype(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

class Code implements executable {

    @Override
    public void execute() {
        System.out.println(this.toString());
    }
}

public class CommandPattern {

    public static void main(String[] args) {
//        Ccar ford = new Cford();
//        Cfactory cfactory = new Cfactory();
//        cfactory.hireEngineContractor(Ctype.Cford,ford);
//        cfactory.hireTireContractor(Ctype.Cford,ford);
        ICommandShell commandFactory = new CommandFactory();
        Code c = new Code();
        Code d = new Code();
        commandFactory.execute(c);
        commandFactory.execute(d);
        commandFactory.turndown();
        commandFactory.turndown();
        commandFactory.turndown();
    }

    //TODO(1): implement a shell command history, the thing I demo in command line.
    static class CommandFactory implements ICommandShell {
        private int index = 0;
        List<Code> list;

        CommandFactory() {
            list = new ArrayList<>();
        }

        @Override
        public void execute(Code c) {
            list.add(c);
            System.out.println("Execute this code ");
            c.execute();
        }

        @Override
        public void turndown() {
            if (index < list.size()) {
                Code c = list.get(index++);
                execute(c);
            } else {
                index = 0;
                turndown();
            }
        }
    }

    interface ICommandShell {
        void execute(Code c);

        void turndown();
    }



    interface Ccar {
    }

    static class Cford implements Ccar {
    }

    class Cbyd implements Ccar {
    }

    class CTesla implements Ccar {
    }



    interface assemble {
        void assembleTire();

        void assembleEngine();
    }

    class TireMaster implements assemble {
        @Override
        public void assembleTire() {
            System.out.println("I can only build tire and assemble.");
        }

        @Override
        public void assembleEngine() {
            // nothing.
        }
    }

    class EngineMaster implements assemble {

        @Override
        public void assembleTire() {
            // nothing
        }

        @Override
        public void assembleEngine() {
            System.out.println("I can only build tire and assemble.");
        }
    }

    //abstraction for an action
    interface Command {
        void execute(Ctype ctype, Ccar ccar);
    }

    class TireContractor implements Command {

//        private final Ctype ctype;
//        private final Ccar ccar;
        private boolean getMaterial;

        // Constructor or other public function to collect necessary prerequsites
        TireContractor(/*Ctype ctype, Ccar ccar*/) {
//            this.ctype = ctype;
//            this.ccar = ccar;
        }

        void collectXiangjiao(/*put all the ingredients here necessary to perform the job. */) {
            // TODO: collect ingredient for tire.
            getMaterial = true;
        }

        // execute() actually make it happen.
        @Override
        public void execute(Ctype ctype, Ccar ccar) {
            // TODO: Validate all prerequisites are ready.
            if (getMaterial) {
                System.out.println("build and assemble tire based on param " + ctype);
            } else {
                System.out.println("material not ready");
            }

        }
    }

    class EngineContractor implements Command {
//        Ctype ctype;
//        Ccar ccar;
        private boolean getOil;

//        EngineContractor(Ctype ctype, Ccar ccar) {
//            this.ctype = ctype;
//            this.ccar = ccar;
//        }

        EngineContractor() {
        }

        void collectOil(/*put all the ingredients here necessary to perform the job. */) {
            getOil = true;
        }

        @Override
        public void execute(Ctype ctype, Ccar ccar) {
            if (getOil) {
                System.out.println("build and assemble engine based on param " + ctype);
            } else {
                System.out.println("oil not ready!");
            }
        }
    }

    /*
     * interface Runnable {
     *   void run() {}
     * }
     * */

    class Cfactory {
        // TODO: composite pattern, get TireMaster, EngineMaster to help you assemble a car.
        private Command tireContractor;
        private Command engineContractor;

//        private List<Command> contractors;

        public void hireTireContractor(Command tireContractor) {
            this.tireContractor = tireContractor;
        }

        public void hireEngineContractor(Command engineContractor) {
            this.engineContractor = engineContractor;
        }

        public void collectMaterial(/*collect all necessary material and dispatch to all contractors accordingly*/) {
            if (tireContractor instanceof TireContractor) {
                ((TireContractor)tireContractor).collectXiangjiao();
            }
            if (engineContractor instanceof EngineContractor) {
                ((EngineContractor)engineContractor).collectOil();
            }
        }

        public Ccar produce(Ctype ctype) {
            if (tireContractor == null || engineContractor == null) {
                System.out.println("The factory is not ready");
                return null;
            }
            Ccar mainFrame = null;
            switch (ctype) {
                case Cford:
                    mainFrame = new Cford();
                    break;
                case Cbyd:
                    mainFrame = new Cbyd();
                    break;
                case CTesla:
                    mainFrame = new CTesla();
                    break;
                default:
                    System.out.println("unhandled ctype:" + ctype);

            }
            tireContractor.execute(/*Ctype.Cford*/ctype, mainFrame);
            engineContractor.execute(ctype, mainFrame);
//            for (Command c : contractors) {
//                c.execute(ctype, mainFrame);
//            }
            return mainFrame;
        }

//        void hireTireContractor(Ctype ctype, Ccar ccar) {
//            TireContractor tireContractor = new TireContractor(ctype, ccar);
//            tireContractor.collectXiangjiao();
//            tireContractor.execute();
//        }

//        void hireEngineContractor(Ctype ctype, Ccar ccar) {
//            EngineContractor engineContractor = new EngineContractor(ctype, ccar);
//            engineContractor.collectOil();
//            engineContractor.execute();
//        }
    }
}