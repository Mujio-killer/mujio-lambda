package com.mujio.DCL;
/**
* @Description: 普通安全单例
* @Author: GZY
* @Date: 2020/8/12
*/

public class SimpleSingleTon {

    private static SimpleSingleTon instance;

    private SimpleSingleTon(){}

    // 普通单例模式在多线程下不安全，故给获取单例的时候加锁，可以保证线程安全，但是牺牲了性能
    // 优化见 SingleTon
    public static synchronized SimpleSingleTon getInstance() {
        if (instance == null) {
            instance = new SimpleSingleTon();
        }
        return instance;
    }
}
