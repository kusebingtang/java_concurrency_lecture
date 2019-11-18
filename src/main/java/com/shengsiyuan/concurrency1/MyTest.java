package com.shengsiyuan.concurrency1;


/**
 * Object.wait()和Thread.sleep()的区别
 * Thread的sleep()的doc：
 * Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds,
 * subject to the precision and accuracy of system timers and schedulers. The thread does not lose ownership of any monitors.
 * <p>
 * 让当前线程睡眠用户指定的毫秒数，当前线程不会丢失锁的拥有权。
 * <p>
 * 而Object的wait方法调用之后会释放锁。
 */
public class MyTest {

//    public static void main(String[] args) throws InterruptedException {
//        Object object = new Object();
//        object.wait();
//    }

    /**
     * Exception in thread "main" java.lang.IllegalMonitorStateException
     * at java.lang.Object.wait(Native Method)
     * at java.lang.Object.wait(Object.java:502)
     * at com.shengsiyuan.concurrency1.MyTest.main(MyTest.java:7)
     */


    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        synchronized (object) {
            object.wait();
        }
    }

    /*
     javap -c MyTest
警告: 文件 ./MyTest.class 不包含类 MyTest
Compiled from "MyTest.java"
public class com.shengsiyuan.concurrency1.MyTest {
  public com.shengsiyuan.concurrency1.MyTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]) throws java.lang.InterruptedException;
    Code:
       0: new           #2                  // class java/lang/Object
       3: dup
       4: invokespecial #1                  // Method java/lang/Object."<init>":()V
       7: astore_1
       8: aload_1
       9: dup
      10: astore_2
      11: monitorenter                       //synchronized开始的时候，获取监视器
      12: aload_1
      13: invokevirtual #3                  // Method java/lang/Object.wait:()V
      16: aload_2
      17: monitorexit                       //监视器的退出。
      18: goto          26
      21: astore_3
      22: aload_2
      23: monitorexit
      24: aload_3
      25: athrow
      26: return
    Exception table:
       from    to  target type
          12    18    21   any
          21    24    21   any
}

     */
}
