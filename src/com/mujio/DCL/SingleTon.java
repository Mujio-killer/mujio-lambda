package com.mujio.DCL;

/**
 * @Description: DCL实现单例的最好方法
 * @Author: GZY
 * @Date: 2020/8/12
 */

public class SingleTon {

    private static class SingleTonHolder {
        static SingleTon instance;

        public static SingleTon getInstance() {

            return instance == null ? new SingleTon() : instance;
        }
    }

    public static SingleTon getInstance() {
        return SingleTonHolder.getInstance();
    }
}
