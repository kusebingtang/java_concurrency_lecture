package com.shengsiyuan.concurrency2;


/*
结论
如果一个对象它里边有若干个synchronized方法，那么在某一时刻，
只有一个线程能进入这里边的某一个synchronized方法，而其他线程想要进入其他的任意synchronized方法也要等待。
如果synchronized修饰static的方法，那么锁住的就是class对象。
 */
public class MyThreadTest2 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        Thread t1 = new Thread1(myClass);
        MyClass myClass2 = new MyClass();
        Thread t2 = new Thread2(myClass2);

        t1.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

}

class MyClass{

    public synchronized void hello(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public synchronized void world(){
        System.out.println("world");
    }
}

class Thread1 extends Thread{
    private MyClass myClass;

    public  Thread1(MyClass myClass){
        this.myClass = myClass;
    }

    @Override
    public void run(){
        this.myClass.hello();
    }

}


class Thread2 extends Thread{
    private MyClass myClass;

    public  Thread2(MyClass myClass){
        this.myClass = myClass;
    }

    @Override
    public void run(){
        this.myClass.world();
    }

}