package DesginPattern;

import java.util.HashSet;
import java.util.Set;
/**
 * model register the observer,and any change of model will trigger ,and update the view accordingly
 * */

public class Observer {
    public static void main(String[] args) {
        ob1 observer1 = new ob1impl();
        ob1 observer2 = new ob1impl();

        DataController repo = new DataController();
        repo.registerOb1(observer1);
        repo.registerOb1Instance2(observer2);

        //DataRepo repo = new DataRepo();
//        repo.registerOb1Instance2(observer2);
//        repo.accessDb(1);
//        repo.accessDb(-1);
//        repo.sendNetworkRequest(1);
//        repo.sendNetworkRequest(-1);
//
//        Observer observer = new Observer();
//        Observer ob = observer;


        LocalDataService local = new LocalDataService();
        RemoteDataService remote = new RemoteDataService();
//        local.registerob(observer1);
//        remote.registerOb(observer2);
        repo.setLocal(local);
        repo.setRemote(remote);
        repo.accessDb(1);
        repo.sendNetworkRequest(1);
        repo.sendNetworkRequest(-1);
        repo.accessDb(-1);
        //ob.tryCatchDemo(null);
        //System.out.println("after tryCatchDemo");
        // try statement
        // catch statement
        // finally statement
        // tryCatchDemo return statement
        // after tryCatchDemo

        //ob.tryCatchDemo(8);
        //System.out.println("after tryCatchDemo");
        // try statement
        // finally statement
        // tryCatchDemo return statement

    }

//    public int tryCatchDemo(Integer i) {
////        try {}
////        finally {}
//        try {
//            System.out.println("try statement");
//            if (i>10) {
//                return 10;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("catch statement");
//        } finally {
//            System.out.println("finally statement");
//        }
//        System.out.println("tryCatchDemo return statement");
//        return 0;
//    }
}

// regularly query

/**
 * Define observer behavior
 * */
interface ob1 {
    void onSuccess(int i);
//    void onError(Exception e);
    /**
     * This is JavaDoc. It will reflected on caller side.
     * @Deprected use onError(int) instead.
     */
    void onError(String s);

    /*
    * This comment is not javadoc, it is not reflected.
    */
    default void onError(int errorCode) {
        System.out.println("errorCode:" + errorCode);
    }
}

class ob1impl implements ob1 {
    @Override
    public void onSuccess(int i) {
        System.out.println(ob1impl.class.getSimpleName() + " succ: " + i);
    }

    @Override
    public void onError(String s) {
        System.out.println(ob1impl.class.getSimpleName() + " fail: " + s);
    }
}

class ob2impl implements ob1 {

    @Override
    public void onSuccess(int i) {

    }

    @Override
    public void onError(String s) {

    }
}

class RemoteDataService /*extends ob1impl*/ {
    private ob1 obInstance;
//    private Set<ob1> set = new HashSet<>();
//    RemoteDataService remoteDataService = new RemoteDataService();
    void registerOb(ob1 obInstance) {
        this.obInstance= obInstance;
    }
    public void perform(int req) {
        if(req >0) {
            if (obInstance != null)
            obInstance.onSuccess(req);
        }else {
            if (obInstance != null)
            obInstance.onError(RemoteDataService.class.getSimpleName() + " perform");
        }
    }
}

class LocalDataService /*extends ob1impl*/ {
  private ob1 obInstance;
    //    private Set<ob1> set = new HashSet<>();
   // localDataService = new LocalDataService();

    void registerob(ob1 obInstance) {
        this.obInstance = obInstance;
    }
    public void perform (int req
            /*This is used to simulate success or failure on caller level,
            ideally we only pass request params, like query criteria,
            timeout, caller permission, etc.*/) {
        // perform local data access request based on params.
        // get req as the reponse. req>0 means success, otherwise fail.
        if(req > 0) { // success case
            if (obInstance!=null) {
                obInstance.onSuccess(req);
//                for (ob1 o : set) {
//                    o.onSuccess(req);
//                }
            }

        }else{ // failure case
            if (obInstance!=null) {
                obInstance.onError(LocalDataService.class.getSimpleName() + ": perform ");
                //                for (ob1 o : set) {
                //                    o.onError("error");
                //                }
            }
        }
    }

}


class DataController {
    private ob1 ob1Instance;
    private final Set<ob1> ob1Set = new HashSet<>();
    private ob1 ob1Instance2;
    private final Set<ob1> ob2Set = new HashSet<>();

    private RemoteDataService remote;
    private LocalDataService local;

    public void setRemote(RemoteDataService remote) {
        this.remote = remote;
        this.remote.registerOb(ob1Instance);
    }

    public void setLocal(LocalDataService local) {
        this.local = local;
        this.local.registerob(ob1Instance2);
    }

    void registerOb1(ob1 ob1Instance) {
        if (ob1Instance != null) {
            ob1Set.add(ob1Instance);
        }
        this.ob1Instance = ob1Instance;
    }

    void registerOb1Instance2(ob1 ob1Instance2) {
        if (ob1Instance2 != null) {
            ob2Set.add(ob1Instance2);
        }
        this.ob1Instance2 = ob1Instance2;
    }

    public void sendNetworkRequest(int req) {
        // TODO: use remote.perform() to help finish this job.
        if(remote == null) {
            return;
        }
        remote.perform(req);
//        if (req >= 0) {
//            // succ
//            //in case observer has not initialed
////            if (ob1Instance!=null) {
////                ob1Instance.onSuccess(req);
////            }
//            for (ob1 ob : ob1Set) {
//                ob.onSuccess(req);
//            }
//        } else {
//            // fail
//            for (ob1 ob : ob1Set) {
//                ob.onError(DataController.class.getSimpleName() + ": sendNetworkRequest " + ob.getClass().getSimpleName());
//            }
////            ob1Instance.onError(DataRepo.class.getSimpleName() + ": sendNetworkRequest");
//        }
    }

    public void accessDb(int db) {
        if(local == null){
            return;
        }
        local.perform(db);
//        if (db >= 0) {
//            // succ
////            if (ob1Instance2 != null) {
////                ob1Instance2.onSuccess(db);
////            }
//            for(ob1 ob: ob1Set){
//                ob.onSuccess(db);
//            }
//        } else {
//            // fail
//            //ob1Instance2.onError(DataRepo.class.getSimpleName() + ": accessDb");
//            for(ob1 ob: ob1Set){
//                ob.onError(DataController.class.getSimpleName() + ": accessDb" + ob.getClass().getSimpleName());
//            }
//        }
    }

}
