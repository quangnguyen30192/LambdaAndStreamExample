package qnguyen.demo;

import java.util.stream.Stream;

/**
 * Created by QuangNguyen on 12/06/2016.
 */
public class InfiniteStream {
    /*
      Sum up
       - What is lambda expression ?
       - Using lamda expression
       - How it fits into the java philosophy
       - A peek at method references:
            + parameter as an argument
            + parameter as an argument to static method
            + parameter as a target
            + 2 parameters as arguments
            + 2 parameters, one as target and one as parameter
       - Function composition
       - Parallelizing it
       - lambdas are cool, but streams are awesome
       - Stream is as abstraction
       - non mutating pipeline
       - Revisiting stream operations
           + filter
           + map
           + reduce
           + specialized reduces
           + sum
       - collect:
           + to list
           + to set
           + to map
       - groupingBy
       - groupingBy and mapping
       - Efficient of streams
       - Lazy evaluations
       - Characteristics of a stream: sized, ordered, distinct, sorted
       - infinite stream



     */
    public static void main(String[] args) {
        // Given number k and a count n, find the total of double of n even numbers starting with k
        // , where sqrt of each number > 20

        int k = 121;
        int n = 51;
        System.out.println(compute(k, n));

    }

    /**
     * do with best way that makes programmer feeling he has a god's strength
     *
     * @param k
     * @param n
     *
     * @return
     */
    private static int compute(int k, int n) {
        return Stream.iterate(k, e -> e + 1) // unbounded, lazy
                     .filter(e -> e % 2 == 0) // unbounded, lazy
                     .filter(e -> Math.sqrt(e) > 20) // unbounded, lazy
//                     .map(e -> e * 2)
//                     .limit(n)
//                    .reduce(0, Integer::sum);
//                    .reduce(0, (total, e) -> total + e);
                     .mapToInt(e -> e * 2) // // unbounded, lazy
                     .limit(n) // bounded, lazy
                     .sum();
    }

    /**
     * do with stupid and silly way that makes programmer facing with many bugs:
     *
     * @param k
     * @param n
     *
     * @return
     */
    private static int computeStupid(int k, int n) {
        int index = k;
        int count = 0;
        int result = 0;
        while (count < n) { // =)) decide count <= n or count < n ? stupid and useless thinking
            if (index % 2 == 0 && Math.sqrt(index) > 20) {
                result += (index * 2);
                count++;
            }
            index++;
            // only decide ++ and count ++ in their appropriate places is too confusing.
        }
        return result;
    }
}
