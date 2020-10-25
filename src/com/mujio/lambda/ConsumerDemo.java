package com.mujio.lambda;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 消费型接口：有参无返
 * void accept（T t）
 */

public class ConsumerDemo {
    public void lambda() {
        TreeSet<Emp> set = new TreeSet<>((o1, o2) -> Integer.compare(o1.age, o2.age));

        set.add(new Emp("小王", 21, 1.80));
        set.add(new Emp("小李", 19, 1.81));
        set.add(new Emp("小军", 29, 1.70));

        set.stream().forEach(System.out::println);
    }

    public void common() {
        TreeSet<Emp> set = new TreeSet<>(new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                return o1.age-o2.age;
            }
        });

        set.add(new Emp("小王", 21, 1.80));
        set.add(new Emp("小李", 19, 1.81));
        set.add(new Emp("小军", 29, 1.70));

        set.stream().forEach(System.out::println);
    }


}
