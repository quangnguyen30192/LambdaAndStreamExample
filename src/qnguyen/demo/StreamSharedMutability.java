package qnguyen.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by QuangNguyen on 12/06/2016.
 */
public class StreamSharedMutability {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);
        // double the even numbers and get into the list

        // wrong way
        List<Integer> doubleEvens = new ArrayList<>();
        numbers.stream()
               .filter(num -> num % 2 == 0)
               .map(num -> num * 2)
               .forEach(num -> numbers.add(num));
        // => mutability is ok, sharing is good but shared mutability is devils work.(will become a bad practice in multi-threading)

        // right way
        List<Integer> doubleEvenRight = numbers.stream()
                                               .filter(num -> num % 2 == 0)
                                               .map(num -> num * 2)
                                               .collect(Collectors.toList());
    }
}
