package qnguyen.demo.java8validator.refactor;

import java.util.Objects;

public class ValidationUtil {

    public static final <P> Validator<P> notNull(Class<P> clazz) {
        return GenericValidator.from(Objects::nonNull);
    }
    public static final Validator<String> NOT_EMPTY_STRING = GenericValidator.fromNegate(String::isEmpty);

    public static final Validator<String> stringSizeBetween(int min, int max) {
        return  GenericValidator.<String>from(s -> s.length() > min)
                .and(GenericValidator.from(s -> s.length() < max));
    }

    public static final Validator<Integer> integerNotZero() {
        return GenericValidator.from(s -> s != 0);
    }

    public static final Validator<Integer> integerBetween(int min, int max) {
        return GenericValidator.<Integer>from(s -> s > min)
                .and(GenericValidator.from(s -> s < max));
    }


}
