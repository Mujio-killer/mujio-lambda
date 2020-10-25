package com.mujio.stream;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Stream的主要方法：
 *
 * collect(toList())	通过 Stream 生成一个列表
 * map	将流中的一个值转换成一个新的值
 * filter	过滤 Stream 中的元素
 * flatMap	将多个 Stream 连接成一个 Stream
 * max	求最大值
 * min	求最小值
 * reduce	从一组值中生成一个新的值
 */

public class StreamDemo {

    /**
    * filter 用于从集合中过滤出符合某些条件的元素
    * collect toList用于将过滤出来的元素组合成list，也可传入其他收集器需实现 Collector
    */
    public void filter_collectDemo() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = list.stream().filter((value) -> value > 2).collect(toList());
        result.forEach((value) -> System.out.print(value));
    }

    /**
     * map 用于操作k,v集合，将一个值转换成另一个值，参数为Function<T, R>
     */
    public void map_collectDemo() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<String> result = list.stream().map(value -> String.format("String:%s", value)).collect(toList());
        result.forEach(System.out::print);
    }

    /**
     * flatmap 将多个 Stream 连接成一个 Stream 参数为Function< T, Stream<R>>
     * 定义一个 List 对象，将这个对象中的每一个 String 都分割成一个字母并生成一个新的 List 对象
     */
    public void flatmap_collectDemo() {
        List<String> list = Arrays.asList("abc", "def", "ghi");
        List<Character> result = list.stream().flatMap(value -> {
            char[] chars = value.toCharArray();
            Character[] characters = new Character[chars.length];
            for(int i = 0; i < characters.length; i++){
                characters[i] = chars[i];
            }
            return Stream.of(characters);
        }).collect(toList());
        result.forEach(System.out::println);
    }

    /**
     * 根据compare实现，取极值
     */
    public void min_maxDemo() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3);
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        System.out.println(list.stream().min(comparator).get());
        System.out.println(list.stream().max(comparator).get());
    }

    /**
     * map()和filter()都是Stream的转换方法，而Stream.reduce()则是Stream的一个聚合方法
     * 参数为：T identity, BinaryOperator<T> accumulator
     */
    public void reduceDemo() {
        int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(10, (acc, n) -> acc + n);
        System.out.println(sum); // 45
    }

    public void reduceDemo1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int sum = 0;
        for (int i = 10; i < list.size() ; i++) {
            sum += i;
        }
    }
    public void reduceDemo2() {
        // 按行读取配置文件:
        List<String> props = Arrays.asList("profile=native", "debug=true", "logging=warn", "interval=500");
        Map<String, String> map = props.stream()
                // 把k=v转换为Map[k]=v:
                .map(kv -> {
                    String[] ss = kv.split("\\=", 2);
                    return  Collections.singletonMap(ss[0], ss[1]);
                })
                // 把所有Map聚合到一个Map:
                .reduce(new HashMap<String, String>(), (m, kv) -> {
                    m.putAll(kv);
                    return m;
                });
        // 打印结果:
        map.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
    }


}
