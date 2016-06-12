package qnguyen.demo;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

interface Password {
    void encode(Integer checkCode);
}

interface Username {

    String encode(String checkCode);

    default void a() {

    }

    default void b() {

    }
}

public class LambdaStream {

    public static User user = new User();

    public static void main(final String[] args) throws IOException {

        // variable capture
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // in case of not using {} => no need to return actually the value that the functional interface method assigned to,
        // just statement is enough
        // the signature of the referenced method needs to match the signature of functional interface
        list.sort((x, y) -> y - x); // comparator return int
        list.forEach(x -> System.out.print(x));
        list.forEach(System.out::println);
        list.forEach(Integer::doubleValue);

        final User user = new User();

        // in case of using {} => must have to return actually the value that the functional interface method assigned to
        user.use(x -> {
            x += 2;
            System.out.print(x);
            System.out.print(list);
        });

        user.useUsername(x -> {
            x += "aaa";
            return x; // dont do this, keep lamda expression is on only 1 line => if more, create a reference
        });

        // all instance method or static method will be converted into streams

        // pass by instance method
        final Function<String, Integer> mapper1 = x -> new Integer(x);
        final Function<String, Integer> mapper2 = Integer::new; // integer : instance variable

        final Function<String, String> mapper3 = x -> x.toLowerCase();
        final Function<String, String> mapper4 = String::toLowerCase; // String : instance variable not String class

        // using method reference when parameter is considered as a target
        final Function<User, String> mapper6 = x -> x.checkString();
        final Function<User, String> mapper5 = User::checkString; // User : instance variable not User class


        final Consumer<User> consumer5 = x -> x.checkUser();
        final Consumer<User> consumer7 = User::checkUser; // User : instance variable not User class
        final Consumer<User> consumer6 = User::checkString; // User : instance variable not User class: su dung nhu input

        // pass by static method
        // using method reference when parameter is argument of static method
        final Consumer<Integer> consumer1 = x -> System.out.print(x);
        final Consumer<Integer> consumer2 = System.out::print; // static method having one input param returns void
        final Consumer<Integer> consumer8 = x -> user.checkUser();
//        Consumer<Integer> consumer12 = user::checkString; // can not because of mismatch params
        // using method reference when parameter is argument of instance method
        final Consumer<Integer> consumer11 = x -> user.checkUserInt(x);
        final Consumer<Integer> consumer10 = user::checkUserInt;

        final Consumer<Integer> consumer9 = System.out::print; // static method having one input param returns void

        final Consumer<Integer> consumer4 = x -> User.check(x);
        final Consumer<Integer> consumer3 = User::check; // static method having one input param returns void

        final List<String> listString = new LinkedList<>(Arrays.asList("a", "b", "c"));
        listString.removeIf("a"::equalsIgnoreCase);

        final StringBuilder stringBuilder = new StringBuilder();
        listString.forEach(stringBuilder::append);
        System.out.print(stringBuilder.toString());

        final int sum = listString.stream()
                                  .filter(x -> x.equalsIgnoreCase("a") ||
                                               x.equalsIgnoreCase("b")) // => method reference ?
                                  .mapToInt(x -> "b".equalsIgnoreCase(x) ? 1 : 0)
                                  .sum();
        System.out.print(sum);

        System.out.print(LongStream.range(0, 10)
                                   .sum());
        LongStream.range(0, 10)
                  .forEach(System.out::println);

        final long length = "ABCD".chars()
                                  .count();

        final String workingDir = System.getProperty("user.dir");
        final Path path = FileSystems.getDefault()
                                     .getPath(workingDir);
        Files.list(path)
             .forEach(System.out::println);

        Files.walk(path)
             .forEach(System.out::println);

        System.out.print("===================");
        Files.find(path, 1000, (fileName, fileAtrribute) -> fileName.endsWith(".xml"))
             .forEach(paramPath -> {
                 try {
                     Files.lines(paramPath)
                          .forEach(System.out::println);
                 } catch (final IOException e) {
                     e.printStackTrace();
                 }
             });

        final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(integers.stream()
                                   .count());

        final Optional<Integer> min = integers.stream()
                                              .min((x, y) -> x - y);
        System.out.println("Min: " + min.get());

        final Optional<Integer> max = integers.stream()
                                              .max(Comparator.comparingInt(x -> x));
        System.out.println("Max: " + max.get());

        final Integer total = integers.stream()
                                      // the order is the same x, y => x + y => be able to apply method reference
                                      // if the order is y, x => x + y => you can not apply method reference
                                      //.mapToInt(x -> x).sum();
                                      //.reduce(0,  lamda (x, y) -> x + y)
                                      .reduce(0, Integer::sum);


        System.out.println("Total: " + total);

        integers.stream()
                //.map(x -> String.valueOf(x))
                .map(String::valueOf)
                // .reduce("", (totalString, x) -> totalString.concat(x));
                // using method reference when 2 params. 1 param is target and 1 param is argument and they are in the same order
                .reduce("", String::concat);

        // collect to other collection
        final Set<Integer> set = integers.stream()
                                         .collect(Collectors.toSet());
        final Integer[] intArr = integers.stream()
                                         .toArray(Integer[]::new);

        final Optional<Integer> first = integers.stream()
                                                .findFirst();
        final boolean result = integers.stream()
                                       .anyMatch(x -> x > 3);
        final Optional<Integer> any = integers.stream()
                                              .findAny();
        System.out.println("------------------" + any);
        // stateless intermediate operation
        integers.stream()
                .filter(x -> x < 4)
                .forEach(System.out::println); // 1 2 3 4

        integers.stream()
                .map(x -> x + 1)
                .forEach(System.out::println); // 2 3 4 5 6

        final IntSummaryStatistics stats = integers.stream()
                                                   .mapToInt(x -> x)
                                                   .summaryStatistics();
        System.out.println(stats);

        // stateful
        integers.stream()
                .distinct();
        integers.stream()
                .limit(3); //  1 2 3
        integers.stream()
                .skip(3);  // 4 5
        integers.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }
}

class User {
    static void check(final int check) {

    }
    void use(final Password password) {
        password.encode(2);
    }
    void useUsername(final Username username) {
        username.encode("aaa");
    }
    String checkString() {
        return null;
    }

    void checkUser() {
    }

    void checkUserInt(final Integer integer) {
    }
}