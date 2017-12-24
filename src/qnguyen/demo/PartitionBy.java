package qnguyen.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionBy {

    private static final List<Developer> DEVELOPERS = Arrays.asList(
            new Developer(20, Arrays.asList("Java", "Python")),
            new Developer(25, Arrays.asList("Java", "AngularJS", "Angular2", "Scala", "JavaScript")),
            new Developer(30, Arrays.asList("Java", "Swift")),
            new Developer(35, Arrays.asList("Swift", "Python")),
            new Developer(40, Arrays.asList("C#", "Java")));

    // list all programming languages using by developers who are older than 30
    public static void main(String[] args) {

        // by using filter way 
        DEVELOPERS.stream()
                  .filter(developer -> developer.getAge() > 30)
                  .flatMap(developer -> developer.getLanguages().stream())
                  .collect(Collectors.toSet())
                    .forEach(System.out::println);

        // by partition
        final Map<Boolean, List<Developer>> devMaps = DEVELOPERS.stream()
                                                                .collect(Collectors.partitioningBy(developer -> developer.getAge() > 30));
        System.out.println("For developers older than 30: they are using:  ");
        devMaps.get(Boolean.TRUE).stream()
               .flatMap(developer -> developer.getLanguages().stream())
               .collect(Collectors.toSet());

        System.out.println("For developers younger than 30: they are using:  ");
        devMaps.get(Boolean.FALSE).stream()
               .flatMap(developer -> developer.getLanguages().stream())
               .collect(Collectors.toSet());
    }

}

class Developer{

    private int age;
    private List<String> languages;

    public Developer(int age, List<String> languages) {
        this.age = age;
        this.languages = languages;
    }

    /**
     * Getter for property 'age'.
     *
     * @return Value for property 'age'.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Getter for property 'languages'.
     *
     * @return Value for property 'languages'.
     */
    public List<String> getLanguages() {
        return this.languages;
    }
}
