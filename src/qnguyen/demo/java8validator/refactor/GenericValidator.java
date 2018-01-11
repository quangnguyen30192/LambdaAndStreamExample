package qnguyen.demo.java8validator.refactor;

import java.util.function.Predicate;

public class GenericValidator<P> implements Validator<P> {

    private Predicate<P> predicate;


    private GenericValidator(Predicate<P> predicate) {
        this.predicate = predicate;
    }

    public static <P> GenericValidator<P> from(Predicate<P> predicate) {
        return new GenericValidator<>(predicate);
    }
    
    public static <P> GenericValidator<P> fromNegate(Predicate<P> predicate) {
        return new GenericValidator<>(predicate.negate());
    }

    @Override
    public ValidationResult test(P param) {
        return predicate.test(param) ? ValidationResult.ok() : ValidationResult.fail();
    }
}
