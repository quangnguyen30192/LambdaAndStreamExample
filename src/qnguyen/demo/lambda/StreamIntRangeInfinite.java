package qnguyen.demo.lambda;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by QuangNguyen on 03/06/2016.
 */
public class StreamIntRangeInfinite {
    public static void main(final String[] args) throws IOException {

        final List<String> list = Arrays.asList("Oracle", "quang", "minh");
        final List<Integer> listLength = list.stream()
                                             .map(String::length)
                                             .collect(Collectors.toList()); // 6 5 4
        final int sum1 = listLength.stream()
                                   .reduce(0, (a, b) -> a + b); // 15
        System.out.println("sum1 = " + sum1);
        final int sum2 = listLength.stream()
                                   .reduce(0, Integer::sum); // 15
        final int max = listLength.stream()
                                  .reduce(Integer::max)
                                  .get(); // 15

        final int max2 = list.stream()
                             .mapToInt(String::hashCode)
                             .sum();

        final IntStream intStream = IntStream.range(10, 30)
                                             .filter(val -> val % 2 == 0);
        intStream.forEach(System.out::print);

        final Stream<Integer> numFromVal = Stream.of(1, 2, 3, 4, 5);
        final int[] numbers = {1, 2, 3, 4, 5};
        final IntStream streamArr = Arrays.stream(numbers);

//        Stream<String> lines = Files.lines(Paths.get("yourfile.txt"), Charset.defaultCharset());

        final Stream<Integer> infinite1 = Stream.iterate(0, n -> n + 2); // infinite streams
        infinite1.limit(30)
                 .forEach(System.out::println);

        final Stream<Integer> infinite2 = Stream.generate(() -> 1);
        infinite2.limit(30)
                 .forEach(System.out::println);


    }
}
