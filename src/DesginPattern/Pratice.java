package DesginPattern;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pratice {
    /**
     *
     *
     *
     * **/
    public static void main(String[] args) {
        Pc pc = new Pc(9);
        List<Object> l1= Arrays.asList(8,"Letter",2020,true);
        List<Object> l2 = Arrays.asList("Click");
        DataAdaptor dataadaptor= new DataAdaptor();
        DataSource d1 = dataadaptor.product(InputType.CharacterClass, l1);
        DataSource d2 = dataadaptor.product(InputType.Click,l2);
        pc.disk.addData(d1);
        pc.disk.addData(d2);
        List<Object> l3= Arrays.asList(8,"again",2020,true);
        DataSource d3 = dataadaptor.product(InputType.CharacterClass, l3);
        pc.disk.addData(d3);
        List<Idata> list = pc.disk.checkData();
        for(int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getFullContent());
        }

    }
}


    class Pc {
        public final Disk disk;
        public Pc(int memory) {
            this.disk = new Disk(memory);
        }

    }
    interface Idata{
        public String getContent();
        public int getSize();
        public String getFullContent();
    }
    class DataSource implements Idata {
       private int size;
       private String content;
       private String getFullContent;
       public DataSource(dataSourceBuilder builder) {
           size =builder.getSize();
           content = builder.getContent();
           getFullContent = builder.toString();
       }

        @Override
        public String getContent() {
            return content;
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public String getFullContent() {
            return getFullContent;
        }

        public static class dataSourceBuilder {
            private String content = null;
            private int time = -1;
            private boolean readable;
            private int size = 0;

            public void setContent(String content) {
                this.content = content;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public void setReadable(boolean readable) {
                this.readable = readable;
            }

            public void setSize(int size) {
                this.size = size;
            }
            public DataSource builder(){
                return new DataSource(this);
            }

            private int getSize() {
                return size;
            }

            private String getContent() {
                return content;
            }

            @Override
            public String toString() {
                return "dataSourceBuilder{" +
                        "content='" + content + '\'' +
                        ", time=" + time +
                        ", readable=" + readable +
                        ", size=" + size +
                        '}';
            }
        }


    }
    enum InputType{
        Click,
        CharacterClass;
    }
    interface Iproduct {
        public DataSource product(InputType type, List<Object> list);
    }
    class DataAdaptor implements Iproduct {
        @Override
        public DataSource product(InputType type, List<Object> list) {
            DataSource.dataSourceBuilder builder = new DataSource.dataSourceBuilder();
            if(type == InputType.CharacterClass) {
                //8,"Letter",2020,true
                builder.setSize((Integer) list.get(0));
                builder.setContent((String) list.get(1));
                builder.setTime((Integer) list.get(2));
                builder.setReadable((Boolean) list.get(3));

            }else {
                builder.setContent((String) list.get(0));
            }
            return new DataSource(builder);
        }

    }

    class Disk {
        int cap;
        int size;
        private List<Idata> dataList;
        private Observation ob;
        public Disk(int cap) {
            this.cap = cap;
            size = 0;
            dataList = new ArrayList<>();
            ob = new Observation();
        }

        public void addData(DataSource data) {
            View view = new View();
            if(size + data.getSize() > cap) {
               view.fail();
               return;
            }
            dataList.add(data);
            size +=  data.getSize();
            view.success();
            ob.onChange(data);
        }
        public List<Idata> checkData(){
            return dataList;
        }

    }
    class View {
        public void showContent(String content) {
            System.out.println(content + " appear on the screen");
        }
        public void success(){
            System.out.println("Data add successful");
        }
        public void fail(){
            System.out.println("Disk has not enough space");
        }
    }
    interface obser{
        void onChange(DataSource data);
    }
    class Observation implements obser{

        @Override
        public void onChange(DataSource data) {
            View view = new View();
            view.showContent(data.getContent());

        }
    }
