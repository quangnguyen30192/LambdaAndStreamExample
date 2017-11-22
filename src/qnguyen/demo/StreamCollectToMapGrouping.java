package qnguyen.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

enum Gender {
    MALE,
    FEMALE
}

/**
 * Created by QuangNguyen on 12/06/2016.
 */
public class StreamCollectToMapGrouping {
    public static void main(String[] args) {
        List<Person> persons = createPersons();

        // create a map where the key is name and value is that person
        final Map<String, Person> map1 = persons.stream()
                                                   .collect(Collectors.toMap(person -> String.format("%s-%s",
                                                                                                     person.getName(),
                                                                                                     person.getId()),
                                                                             Function.identity())); // Person -> Person

        // create a map where the key is name and value is all people of that name (it's like grouping)
        final Map<String, List<Person>> map2 = persons.stream()
                                                         .collect(Collectors.groupingBy(Person::getName));
        // please do in this way :) otherwise you must do couple things making code messy

        // create a map where the key is name and value is all the age of people with that name
        final Map<String, List<Integer>> map3 = persons.stream()
                                                          .collect(Collectors.groupingBy(Person::getName,
                                                                                         Collectors.mapping(Person::getAge,
                                                                                                            Collectors.toList())));


    }
    public static final List<Person> createPersons() {
        return Arrays.asList(new Person("Jeni", Gender.FEMALE, 18, "1411"),
                             new Person("May", Gender.FEMALE, 20, "1151"),
                             new Person("Thomas", Gender.MALE, 24, "1131"),
                             new Person("May", Gender.MALE, 22, "1211"),
                             new Person("Mike", Gender.FEMALE, 33, "1121"),
                             new Person("Tom", Gender.MALE, 11, "1111"));
    }
}

class Person {
    private String name;
    private Gender gender;
    private int age;
    private String id;

    public Person(String name, Gender gender, int age, String id) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
