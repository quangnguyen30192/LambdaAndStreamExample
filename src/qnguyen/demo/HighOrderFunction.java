package qnguyen.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HighOrderFunction {

    // isPrime
    private boolean isPrime(int number) {
        return number > 1 && IntStream.range(2, number)
                                      .noneMatch(i -> number % i == 0);
        //  instead of using for loop and then some garbage variable
    }

    // line counts contains search words
    private long linesCount(String filePath, String searchWord) throws IOException {
        return Files.lines(Paths.get(filePath))
                    .filter(line -> line.contains(searchWord))
                    .count();
        // instead of using stringbuffer and then for loop, convert the byte to string, then consider whether the line contains search word
    }

    // grouping
    // group scores by name
    private Map<Integer, List<String>> grouping(Map<String, Integer> scores) {
        return scores.keySet().stream()
                     .collect(Collectors.groupingBy(scores::get));
        // instead of create a empty map<int, list<String>> push the score of the first key of scores as the key and the value
        // is a List containing 1 element is the name
        // check the 2nd key of scores, if the score exists in the new map -> get the list and add one more name, otherwise
        // create a new key with the value is a list containing 1 element is the name
        ///... fucking verbose. now we have a concise and clear code
    }
}
