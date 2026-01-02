package com.raj.demo.customcollector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * A custom collector that accumulates Emp objects into a Map (keyed by ID)
 * and returns the size of the map as the final result.
 */
public class MyCollector implements Collector<Emp, Map<Integer,Emp>, Integer> {

    /**
     * Runs a demonstration of the custom collector.
     */
    public static void run() {
        List<Emp> emps = List.of(new Emp(1), new Emp(2), new Emp(3));
        // Collects the stream using the custom collector and returns the count
        Integer collect = emps.stream().collect(MyCollector.toList());
        IO.println(collect);
    }

    /**
     * Static factory method to create an instance of MyCollector.
     * Note: Despite the name 'toList', this collector returns an Integer size.
     *
     * @return a new MyCollector instance
     */
    public static MyCollector toList() {
        return new MyCollector();
    }

    /**
     * Creates a new result container (a HashMap).
     */
    @Override
    public Supplier<Map<Integer,Emp>> supplier() {
        return HashMap::new;
    }

    /**
     * Incorporates a new data element into the result container.
     * Maps the Emp ID to the Emp object.
     */
    @Override
    public BiConsumer<Map<Integer,Emp>, Emp> accumulator() {
        return (map, emp) -> map.put(emp.i(),emp);
    }

    /**
     * Combines two result containers.
     * Used during parallel stream processing.
     */
    @Override
    public BinaryOperator<Map<Integer,Emp>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    /**
     * Performs the final transformation from the intermediate accumulation type (Map)
     * to the final result type (Integer).
     */
    @Override
    public Function<Map<Integer,Emp>,Integer> finisher() {
        return Map::size;
    }

    /**
     * Returns a Set of Collector.Characteristics indicating the characteristics of this Collector.
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }
}
