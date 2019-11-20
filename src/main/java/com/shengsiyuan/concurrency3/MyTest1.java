package com.shengsiyuan.concurrency3;

/**
 * 当我们使用synchronized关键字来修饰代码块时，
 * 字节码层面上是通过monitorenter和monitorexit指令来实现的锁的获取与释放动作。
 */
public class MyTest1 {

    private Object object = new Object();

    public void method(){
        synchronized (object){
            System.out.println("hello world");
        }
    }

    public void method2(){
        synchronized (object){
            System.out.println("welcome");
        }
    }

    public void method3(){
        synchronized (object){
            System.out.println("hello world");
            throw new RuntimeException();
        }
    }
}

/*
 javap -c MyTest1                                                                                                                                                                                                                                                                                ✔  414  14:13:15
Compiled from "MyTest1.java"
public class com.shengsiyuan.concurrency3.MyTest1 {
  public com.shengsiyuan.concurrency3.MyTest1();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: new           #2                  // class java/lang/Object
       8: dup
       9: invokespecial #1                  // Method java/lang/Object."<init>":()V
      12: putfield      #3                  // Field object:Ljava/lang/Object;
      15: return

  public void method();
    Code:
       0: aload_0
       1: getfield      #3                  // Field object:Ljava/lang/Object;
       4: dup
       5: astore_1
       6: monitorenter                //===>进入监视器（获取锁）
       7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
      10: ldc           #5                  // String hello world
      12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      15: aload_1
      16: monitorexit                //====>退出监视器（释放锁）
      17: goto          25
      20: astore_2
      21: aload_1
      22: monitorexit               //====>防止异常的情况下保证锁一定能释放掉
      23: aload_2
      24: athrow
      25: return
    Exception table:
       from    to  target type
           7    17    20   any
          20    23    20   any
}
 */

/*
    public void method3();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: getfield      #3                  // Field object:Ljava/lang/Object;
         4: dup
         5: astore_1
         6: monitorenter                      //====>进入监视器
         7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        10: ldc           #5                  // String hello world
        12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        15: new           #8                  // class java/lang/RuntimeException
        18: dup
        19: invokespecial #9                  // Method java/lang/RuntimeException."<init>":()V
        22: athrow
        23: astore_2
        24: aload_1
        25: monitorexit                     //====>退出监视器，因为肯定会抛出异常，所以只有这一个monitorexit。
        26: aload_2
        27: athrow
      Exception table:
         from    to  target type
             7    26    23   any
      LineNumberTable:
        line 24: 0
        line 25: 7
        line 26: 15
        line 27: 23
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      28     0  this   Lcom/shengsiyuan/concurrency3/MyTest1;
      StackMapTable: number_of_entries = 1
        frame_type = 255
    offset_delta = 23
    locals = [ class com/shengsiyuan/concurrency3/MyTest1, class java/lang/Object ]
    stack = [ class java/lang/Throwable ]
 */