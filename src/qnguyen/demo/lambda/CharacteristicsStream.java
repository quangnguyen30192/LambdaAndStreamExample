package qnguyen.demo.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by QuangNguyen on 12/06/2016.
 */
public class CharacteristicsStream {
    public static void main(String[] args) {
        // its characteristics are sized, ordered, distinct, sorted
        // streams could be sized or un-sized (infinite streams), order or un-ordered, distinct or non-distinct, sorted or non-sorted
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

        numbers.stream()
               .filter(e -> e % 2 == 0)
               .distinct()
               .sorted()
               .forEach(System.out::println);

    }
}
