package qnguyen.demo;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
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
        persons.stream()
               .collect(Collectors.toMap(person -> String.format("%s-%s", person.getName(), person.getId()),
                                         person -> person));

        // create a map where the key is name and value is all people of that name (it's like grouping)
        persons.stream()
               .collect(Collectors.groupingBy(Person::getName));
        // please do in this way :) otherwise you must do couple things making code messy

        // create a map where the key is name and value is all the age of people with that name
        persons.stream()
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

@Data
@AllArgsConstructor
class Person {
    private String name;
    private Gender gender;
    private int age;
    private String id;
}
