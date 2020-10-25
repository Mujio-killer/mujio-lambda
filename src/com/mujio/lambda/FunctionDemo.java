package com.mujio.lambda;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能型接口:定返不定参
 * Function<T,R>：R apply（T t）
 */

public class FunctionDemo {
    public void lambda() {
        //定义一个function 输入是String类型，输出是 Emp 类型， Emp是一个类。
        Function<String, Emp> times2 = fun -> {
            Emp a = new Emp("小高",10,1.77);
            return a;
        };

        String[] testintStrings = {"1", "2", "3", "4"};

        //将String 的Array转换成map,调用times2函数进行转换
        Map<String, Emp> eventmap1 = Stream.of(testintStrings).collect(Collectors.toMap(inputvalue -> inputvalue, inputvalue -> times2.apply(inputvalue)));
    }

    public void common() {

    }
}
