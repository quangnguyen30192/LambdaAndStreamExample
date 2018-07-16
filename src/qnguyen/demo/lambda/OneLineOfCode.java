package qnguyen.demo.lambda;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OneLineOfCode {
    public static void main(String[] args) {
    }

    private void createIntRange() {
        IntStream.range(0, 10); // do not include 10

        IntStream.rangeClosed(0, 10); // include 10 also

        // create infinite ordered stream
        Stream.iterate(0, i -> i + 2)
                .limit(10);

        IntStream.iterate(0, i -> i + 2)
                .limit(10);

        // create infinite unordered stream - supply by an supplier
        Stream.generate(() -> 1).limit(10);
    }

    // declare an array of integers from 0 .. 20 with even numbers
    private int[] intArr() {
        return IntStream.range(0, 10)
                .map(i -> i * 2)
                .toArray();
    }

    // declare a list of integers from 0 .. 20 with even numbers
    private List<Integer> listArr() {
        return IntStream.range(0, 10)
                .map(i -> i * 2)
                .boxed()
                .collect(Collectors.toList());
    }

    private void sumAListOfNumber() {
        IntStream.range(0, 10).sum();
        IntStream.range(0, 10).reduce(0, Integer::sum);
    }

    private void checkStringExists() {
        final String tweet = "The quick brown fox jumps over a lazy dog. #pangram http://www.rinkworks.com/words/pangrams.shtml";
        final List<String> keywords = Arrays.asList("brown", "fox", "dog", "pangram");

        keywords.stream().anyMatch(tweet::contains);
    }

    private void readInAFile() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"))) {
            bufferedReader.lines().collect(Collectors.joining("\n"));

            // or
            bufferedReader.lines().collect(Collectors.toList());

            //
            bufferedReader.lines().collect(Collectors.toCollection(LinkedList::new));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // or
        try (Stream<String> lines = Files.lines(new File("test.txt").toPath(), Charset.defaultCharset())) {
            lines.collect(Collectors.toList());
        }

    }

    private void partitionByInList() {
        final Map<Boolean, List<Integer>> collect = Stream.of(49, 58, 76, 82, 88, 90)
                .collect(Collectors.partitioningBy(number -> number > 60));

        final List<Integer> largerThan60 = collect.get(Boolean.TRUE);
        final List<Integer> lessThan60 = collect.get(Boolean.FALSE);
    }

    private void findMinMax() {
        // find min
        final Stream<Integer> integerStream = Stream.of(3, 2, 4, 5);
        integerStream.min(Integer::compare).orElse(0);
        integerStream.reduce(Integer::min).orElse(0);
        integerStream.mapToInt(Integer::intValue).min();
    }
}
