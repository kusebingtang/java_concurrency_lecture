package com.shengsiyuan.concurrency1;

public class MyObject {

    private int counter;

    public  synchronized  void increase(){
        while (0 != this.counter){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.counter++;
        System.out.println(this.counter);
        notifyAll();
    }

    public synchronized  void  decrease(){
        while(this.counter == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.counter--;
        System.out.println(this.counter);
        notifyAll();
    }
}