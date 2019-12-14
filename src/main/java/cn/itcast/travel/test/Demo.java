package cn.itcast.travel.test;

public class Demo {
    public static void main(String[] args) {
        try {
            int a = 3/0;
        }catch (Exception e) {
            System.err.println("错误");
        }
        System.out.println("dfdf");


    }
}
