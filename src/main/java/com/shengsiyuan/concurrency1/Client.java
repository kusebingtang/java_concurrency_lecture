package com.shengsiyuan.concurrency1;

public class Client {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        IncreaseThread increaseThread = new IncreaseThread(myObject);
        IncreaseThread increaseThread2 = new IncreaseThread(myObject);
        DecreaseThread decreaseThread = new DecreaseThread(myObject);
        DecreaseThread decreaseThread2 = new DecreaseThread(myObject);

        increaseThread.start();
        decreaseThread.start();
        increaseThread2.start();
        decreaseThread2.start();

    }
}