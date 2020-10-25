package com.mujio.lambda;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * 供给型接口：无参有返
 * T get()
 */

public class SupplierDemo {
    public void lambda() {
        Stream<Double> test = Stream.generate(() -> Math.random());

        test.forEach(System.out::println);
    }

    public void common() {

    }
}
