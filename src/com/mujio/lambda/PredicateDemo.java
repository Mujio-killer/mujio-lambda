package com.mujio.lambda;

import java.util.ArrayList;

/**
 * 判断型：有参有返
 * boolean test（T t）
 */

public class PredicateDemo {
    public void lambda() {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.removeIf(t -> t / 3 == 0);
    }

    public void common() {

    }
}
