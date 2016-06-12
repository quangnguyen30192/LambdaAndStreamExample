package qnguyen.demo;

import java.util.stream.IntStream;

/**
 * Created by QuangNguyen on 12/06/2016.
 */
public class StreamRefactorTraditionalCheckPrime {
    public static boolean isPrime(final int val) {
        if (val == 0) {
            throw new IllegalArgumentException("Wrong argument");
        }

        return val > 1
               && IntStream.range(2, val)
                           .noneMatch(index -> val % index == 0);
    }
}
