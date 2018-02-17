package qnguyen.demo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * in java generic - PECS stands for Producer extends Consumer super, apply only on collection context
 */
public class PECS {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Cat());
        animals.add(new Dog());
        animals.add(new Animal());

        producerExample(animals);
        superExample(animals);

    }

    /**
     * at compile time level, support both adding and retrieving
     *
     * @param animals
     */
    static void fullySupport(List<Animal> animals) {
        final Animal animal = animals.get(0);
        animals.add(new Animal());
    }

    /**
     * it's called producer because it only supports retrieve value
     *
     * @param animals
     */
    static void producerExample(List<? extends Animal> animals) {
//        animals.add(new Cat());   can not add event it's element
        animals.add(null); // only add null
        final Animal animal = animals.get(0);// only get value from list
    }

    /**
     * it's called consumer because it only supports adding element
     *
     * @param animals
     */
    static void superExample(List<? super Animal> animals) {
        animals.add(new Dog()); // subtype can be added
//      animals.add(new Object());  can not add object event it's supertype
        // final Animal object = animals.get(0); CAN NOT get value as Animal or by its subtypes
        final Object object = animals.get(0); // only get value as Object
    }

    static class Animal {
        @Override
        public String toString() {
            return "I'm animal";
        }
    }

    static class Cat extends Animal {
        @Override
        public String toString() {
            return "I'm cat";
        }
    }

    static class Dog extends Animal {
        @Override
        public String toString() {
            return "I'm dog";
        }
    }
}
