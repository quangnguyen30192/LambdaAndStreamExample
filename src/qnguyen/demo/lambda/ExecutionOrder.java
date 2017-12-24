package qnguyen.demo.lambda;

import java.util.stream.Stream;

/**
 * Created by nguyenquang on 6/23/17.
 */
public class ExecutionOrder {

    public static void main(String[] args) {
        Stream.of("a", "b", "c", "d")
        .filter( str -> {
            System.out.println("filter" + str);
            return str.startsWith("a");
        })
        .map( str -> {
            System.out.println("map: " + str);
            return str.toUpperCase();
        })
        .forEach(System.out::println);
    }
}
