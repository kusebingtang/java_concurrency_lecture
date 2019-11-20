package com.shengsiyuan.concurrency3;

public class MyTest3 {

    public static synchronized void method() {
        System.out.println("hello world");
    }

}
/**
 public static synchronized void method();
 descriptor: ()V
 flags: (0x0029) ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED  //相比非静态方法多了一个标志 ACC_STATIC
 Code:
 stack=2, locals=0, args_size=0
 0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
 3: ldc           #3                  // String hello world
 5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 8: return
 LineNumberTable:
 line 6: 0
 line 7: 8
*/