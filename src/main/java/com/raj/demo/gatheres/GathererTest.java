package com.raj.demo.gatheres;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class GathererTest {
    public static void run() {
        Stream<String> letterStream = Stream.of("a","b","c","d");
        //letterStream.gather(Gatherers.fold(()->"",(acc,elem)->acc+elem)).forEach(IO::println);
        letterStream.gather(Gatherers.windowFixed(3)).forEach(IO::println);
    }
}
