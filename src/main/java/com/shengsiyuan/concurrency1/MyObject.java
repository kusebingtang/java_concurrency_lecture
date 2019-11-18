package com.shengsiyuan.concurrency1;

public class MyObject {

    private int counter;

    public  synchronized  void increase(){
        if(0 != this.counter){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.counter++;
        System.out.println(this.counter);
        notify();
    }

    public synchronized  void  decrease(){
        if(this.counter == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.counter--;
        System.out.println(this.counter);
        notify();
    }
}