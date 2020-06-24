package DesginPattern;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Proxy {
    public static void main(String[] args) throws InterruptedException{
        PProxy proxy = new PProxy();
        Ipdata fake = proxy.produceDate();
        Thread.sleep(3000);
        fake.getCalled();
        Thread.sleep(3000);
        fake.getCalled();
        fake.getCalled();
    }
}

// TODO: an interface PData, implement getCalled() function.
interface Ipdata{
    void getCalled();
}

// TODO: a fake implementation for PData, that the creation is easy
class FakeImpl implements  Ipdata, Pobserve{
    private Ipdata real;
    private IFakeReplaceble proxy;

    FakeImpl(){
        System.out.println("fake implementation is easy and cheap.");
    }

    @Override
    public void getCalled() {
        if (real != null) {
            real.getCalled();
            return;
        }
        System.out.println("FakeImpl.getCalled()");
        Ipdata real = onChange(this);
        if (real != null) {
            this.real = real;
            real.getCalled();
        }
    }

    /**
     * @param fake pass in fakeImpl.
     * @return the corresponding realImpl.
     * */
    @Override
    public Ipdata onChange(Ipdata fake) {
        // pass in the fake, get the corresponding real
        if (proxy!=null) {
            return proxy.getAlternative(fake);
        }
        return null;
    }

    @Override
    public void register(IFakeReplaceble proxy) {
        this.proxy = proxy;
    }
}
// TODO: a real implementation for PData, that the creation is difficult and expensive.

class RealImpl implements  Ipdata {
   RealImpl(){
       try {
           Thread.sleep(5000);
           System.out.println("Spend 5 sec for complicated instant construction process.");
           System.out.println("real implementation is expensive and difficult");
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

    @Override
    public void getCalled() {
        System.out.println("realImpl.getCalled()");
    }
}

// TODO: Build a factory, that upon request, always return a fake Data object.
// TODO: implement observer pattern, when fakeImpl.getcalled(),  PProxy gets notified.
interface Pobserve{
    Ipdata onChange(Ipdata data);

    void register(IFakeReplaceble proxy);
}
//class ObserveImpl implements Pobserve{
//    Ipdata realImpl;
//    @Override
//    public void onChange() {
//        realImpl = new RealImpl();
//        realImpl.getCalled();
//
//    }
//}

interface IFakeReplaceble {
    Ipdata getAlternative(Ipdata data);
}

class PProxy implements IFakeReplaceble{
//    Pobserve ob;
//    void registerOb(){
//        ob = new ObserveImpl();
//    }
    private final Map<Ipdata, Ipdata> fakeToReal = new HashMap<>();
//    Ipdata fackimpl;
//    Ipdata realimpl;
    PProxy(){}
//    private Ipdata productReal(Ipdata fackimpl) {
//        if(fackimpl == null ) {
//            return null;
//        }
//        realimpl = new RealImpl();
//        return realimpl;
//    }
// TODO: whenever PProxy receive request to return a fake Data, produce a real data in async manner.
    Ipdata produceDate(){
        final Ipdata fake = new FakeImpl();
        fakeToReal.put(fake, null);
        // When there is a change, this is the guy that should get notified.
        if (fake instanceof Pobserve) {
            ((Pobserve)fake).register(this);
        }
        // new Thread().start();
        new Thread(()->{
            Ipdata real = new RealImpl(); // long and expensive
            fakeToReal.put(fake, real);
        }).start();
        // ->
        return fake;
//        if(fackimpl.getCalled()) {
//            ob.onChange();
//
//        }
//        return fackimpl;
    }

    @Override
    public Ipdata getAlternative(Ipdata fake) {
        if (fakeToReal.containsKey(fake)) {
            return fakeToReal.get(fake);
        }
        return null;
    }
}