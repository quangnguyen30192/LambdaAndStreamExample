package qnguyen.demo;

import java.util.stream.IntStream;

public class HighOrderFunction {

    // isPrime
    private boolean isPrime(int number) {
        return number > 1 && IntStream.range(2, number)
                                      .noneMatch(i -> number % i == 0);
    }
}
