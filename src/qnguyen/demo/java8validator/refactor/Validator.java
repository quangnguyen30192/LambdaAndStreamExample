package qnguyen.demo.java8validator.refactor;

@FunctionalInterface
public interface Validator<P> {

    ValidationResult test(P param);

    default Validator<P> and(Validator<P> other) {
        return param -> {
            final ValidationResult thisValidator = this.test(param);
            return thisValidator.isValid() ? other.test(param) : thisValidator;
        };
    }

    default Validator<P> or(Validator<P> other) {
        return param -> {
            final ValidationResult thisValidator = this.test(param);
            return thisValidator.isValid() ? thisValidator : other.test(param);
        };
    }
}
