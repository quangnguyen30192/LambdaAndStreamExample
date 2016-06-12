package qnguyen.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by QuangNguyen on 12/06/2016.
 */
public class StreamGeneralMethod {
    public static void main(String[] args) {
        List<Integer> vals = Arrays.asList(1, 2, 3, 4);
        totalValues(vals, x -> x % 2 == 0);
    }

    public static int totalValues(List<Integer> list, Predicate<Integer> criteria) {
        return list.stream()
                   .filter(criteria)
                   .reduce(0, Math::addExact);
    }
}
