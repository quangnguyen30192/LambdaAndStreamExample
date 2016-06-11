package qnguyen.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * Created by QuangNguyen on 05/06/2016.
 */
public class StreamDemo2 {


    public static void main(final String[] args) {
        List<Integer> vals = Arrays.asList(1, 2, 7, 3, 5, 4, 6);
        cal1(vals);
        totalValues(vals, x -> x % 2 == 0);
    }

    public static void cal1(List<Integer> vals) {
        // sum = vals.stream().mapToInt(x -> x).sum();
        // find the double of the first even number greater than 3

        Predicate<Integer> isGreaterThan3 = x -> x > 3;
        Function<Integer, Predicate<Integer>> isGreaterThan = val -> number -> number > val;
        // in case of applying for greater than 3

        Optional<Integer> rs = vals.stream()
                                   .filter(isGreaterThan.apply(3)) // equipvalent to x -> x > 3 in labmda or StreamDemo2::isGreaterThan3
                                   .filter(StreamDemo2::isDiviableBy2) //  -> x % 2 == 0
                                   .findFirst()
                                   .map(StreamDemo2::mapToDoubleInt); // x -> x * 2
        System.out.println(rs);
    }

    public static int totalValues(List<Integer> list, Predicate<Integer> criteria) {
        return list.stream()
                   .filter(criteria)
                   .reduce(0, Math::addExact);
    }

    public static boolean isPrime(final int val) {
        if (val == 0) {
            throw new IllegalArgumentException("Wrong argument");
        }

        return val > 1
               && IntStream.range(2, val)
                           .noneMatch(index -> val % index == 0);
    }
    private static boolean isDiviableBy2(Integer integer) {
        System.out.println("isDiviableBy2 " + integer);
        return integer % 2 == 0;
    }

    private static Integer mapToDoubleInt(Integer integer) {
        System.out.println("mapToDoubleInt " + integer);
        return integer * 2;
    }

    private static boolean isGreaterThan3(Integer integer) {
        System.out.println("isGreaterThan3 " + integer);
        return integer > 3;
    }
}
