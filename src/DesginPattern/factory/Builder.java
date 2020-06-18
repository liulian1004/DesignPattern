package DesginPattern.factory;

import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * Split the construction process of a complicated instance, of which
 * each element is difficult to build, and could be built in heterogeneous context.
 *
 * Decoupling component collection and instance construction process.
 * */
public class Builder {
    public static void main(String[] args) {
//        B1.UserBuilder user = new B1.UserBuilder();
//        user.userName("111");
//        user.password("123");
//        System.out.println(user.toString());
        System.out.println("B1.class.equals(B1.Builder.class):");
        System.out.println(B1.class.equals(B1.Builder.class) );
        // Basic BUILDER use case
        B1.Builder builder = new B1.Builder();
        builder.setUserName("111");
        builder.setPassword("123");
        builder.setDob(1l);
        builder.setAddress("addr");
        B1 b1 = builder.build();
        // Question: Why not use constructor directly?
        // B1 b2 = new B1(un, pw, dob, addr);
        // B1 b2 = new B1(un, pw);
        System.out.println("b1:"+b1);

        final B1.Builder builder2 = new B1.Builder();
        new Thread(() -> {
            // Perform some network to retrieve username
            // pass some time
            builder2.setUserName("1234");
        }).start();
        new Thread(() -> {
            // perform some db operation to retrieve password
            // pass some time
            builder2.setPassword("pw");
        }).start();
        // synchronized previous threads
        B1 b2 = builder2.build();
    }
}

class B1 {
    // container subject can only read, no write. best practice.
    private final String userName;
    private final String password;
    private final long dob;
    private final String address;

    @Override
    public String toString() {
        return "B1{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                '}';
    }
    //    public B1(String un, String pw) {
//        userName = un;
//        password = pw;
//    }

//    private B1(UserBuilder user) {
//        this.userName = UserBuilder.userName;
//        this.password = UserBuilder.password;
//        this.dob = UserBuilder.dob;
//        this.address = UserBuilder.address;
//    }

    //constructor为private
private B1(Builder user) {
    this.userName = user.userName;
    this.password = user.password;
    this.dob = user.dob;
    this.address = user.address;
}


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public long getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }
    // builder can only write, not read. best practice.
    public static class Builder{

    // No claimed constructor, system will setup default constructor.

//        public static String userName;
//        private static String password;
//        private static long dob = 0l;
//        private static String address = null;
        private String userName;
        private String password;
        private long dob = 0l; //optional ，先给一个默认值
        private String address = null;//optional ，先给一个默认值

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setDob(long dob) {
            this.dob = dob;
            return this;
        }
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Generated the corresponding subject instance.
         * */
        public B1 build() throws IllegalArgumentException{
            if(userName == null || password == null) {
                throw new IllegalArgumentException("You have to fill this info");
            }
            return new B1(this);//this: builder自己的filed
        }
//        public UserBuilder builder(){
//            if(userName == null || password == null) {
//                throw new IllegalArgumentException("You have to fill this info");
//            }
//            return new UserBuilder();
//        }
    }
}
