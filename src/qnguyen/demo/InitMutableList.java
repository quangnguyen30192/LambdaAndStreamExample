package qnguyen.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by qnguyen on 4/27/2017.
 */
public class InitMutableList {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(Collections.singletonList("s"));
        List<String> list2 = new ArrayList<>(Arrays.asList("s"));
        List<String> list3 = Stream.of("a").collect(Collectors.toList());

        Iterator<String> a = null;
        Iterable<String> b = () -> a;
        StreamSupport.stream(b.spliterator(), false).collect(Collectors.toList());

    }
}
