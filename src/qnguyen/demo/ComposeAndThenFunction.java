package qnguyen.demo;

import java.util.function.Function;

/**
 * Created by QuangNguyen on 19/06/2016.
 */
public class ComposeAndThenFunction {
    public static void main(String[] args) {
        Function<Integer, Integer> increase = e -> e + 1;
        Function<Integer, Integer> doubleIt = e -> e * 2;

        // doubleIt.apply(increase.apply(3)); dont do this
        System.out.println(increase.andThen(doubleIt)
                                   .apply(3)); // 8

        System.out.println(increase.compose(doubleIt)
                                   .apply(3)); // 7
        /*
         the difference between compose and andThen is the order they execute the functions.
         While the compose function executes the caller last and the parameter first, the andThen
         executes the caller first and the parameter last.
         */
    }
}
