package com.raj.demo.customcollector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollector implements Collector<Emp, Map<Integer,Emp>, Integer> {

    public static void run() {
        List<Emp> emps = List.of(new Emp(1), new Emp(2), new Emp(3));
        Integer collect = emps.stream().collect(MyCollector.toList());
        IO.println(collect);
    }

    public static MyCollector toList() {
        return new MyCollector();
    }

    @Override
    public Supplier<Map<Integer,Emp>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<Integer,Emp>, Emp> accumulator() {
        return (map, emp) -> map.put(emp.i(),emp);
    }

    @Override
    public BinaryOperator<Map<Integer,Emp>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    @Override
    public Function<Map<Integer,Emp>,Integer> finisher() {
        return Map::size;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
