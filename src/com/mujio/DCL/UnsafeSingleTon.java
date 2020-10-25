package com.mujio.DCL;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description: 常犯的DCL错
 * @Author: GZY
 * @Date: 2020/8/13
 */

public class UnsafeSingleTon {
    private static UnsafeSingleTon instance;

    private UnsafeSingleTon() {}

    public static UnsafeSingleTon getInstance() {
        if (instance == null) {
            synchronized (UnsafeSingleTon.class) {
                if (instance == null) {
                    // 主要问题出在这里
                    // 对象创建过程：
                    // 1. 类加载检查
                    // 2. 分配内存（2.1 若内存规整则用指针碰撞来分配内存 2.2 不规整利用jvm的空闲列表来分配 2.3 多线程下利用CAS+失败重试保证内存的同步分配或直接在线程私有空间中TLAB分配）
                    // 3. 初始化（初始化零值，也可能发生在TLAB中） 4. 设置对象头 5. 执行<init> 方法，这里才真正赋我们设定的值
                    // 当new到初始化阶段时，其他线程判断instance不为null，直接拿去用，则会访问到不完整的实例。
                    instance = new UnsafeSingleTon();
                }
            }
        }
//        AbstractQueuedSynchronizer aqs = 90
        return instance;
    }
}
