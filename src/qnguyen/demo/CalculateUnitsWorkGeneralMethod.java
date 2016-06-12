package qnguyen.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by QuangNguyen on 05/06/2016.
 */
public class CalculateUnitsWorkGeneralMethod {

    public static void main(final String[] args) {
        List<Integer> vals = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        // sum = vals.stream().mapToInt(x -> x).sum();
        // find the double of the first even number greater than 3

        // way 1:
        int result = 0;
        for (Integer val : vals) {
            if (val > 3 && val % 2 == 0) {
                result = val * 2;
                break;
            }
        }
        System.out.println(result); // cost 8 units work

        // basic
        System.out.println(vals.stream()
                               .filter(CalculateUnitsWorkGeneralMethod::isGreaterThan3) // e -> e > 3
                               .filter(CalculateUnitsWorkGeneralMethod::isDiviableBy2) // e -> e % 2 == 0
                               .map(CalculateUnitsWorkGeneralMethod::mapToDoubleInt) // e -> e * 2
                               .findFirst());

        // conclusion: the stream will not apply all pipeline action to all elements at the same time but run every element for all pipe line action
        // it's as the same as the traditional way that we do above, it's called lazy evaluation
        // please run main function to see the console log.

        // the most efficient people is the most lazy people
        // streams works efficiently, not when working faster, but when you don't work at all
        // streams works efficiently only if the function don't have side effect (system.out.prinlt as I do)
    }

    public static final int upgradedVersion(List<Integer> vals) {
        Optional<Integer> rs = vals.stream()
                                   .filter(isGreaterThan().apply(3)) // equipvalent to x -> x > 3
                                   //  or  Predicate<Integer> isGreaterThan3 = x -> x > 3
                                   .filter(isDiviableBy().apply(2)) //  -> x % 2 == 0
                                   .findFirst()
                                   .map(mapTo().apply(2)); // x -> x * 2
        return rs.isPresent() ? rs.get() : -1;
    }

    private static Function<Integer, Predicate<Integer>> isGreaterThan() {return val -> number -> number > val;}

    private static Function<Integer, Predicate<Integer>> isDiviableBy() {return val -> number -> number % val == 0;}

    private static Function<Integer, Function<Integer, Integer>> mapTo() {return val -> number -> number * val;}

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
