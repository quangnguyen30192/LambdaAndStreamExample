package qnguyen.demo.java8validator.refactor;

import java.util.Optional;
import java.util.function.Supplier;

public class ValidationResult {

    private boolean isValid;

    private ValidationResult(boolean isValid) {
        this.isValid = isValid;
    }

    public static ValidationResult ok() {
        return new ValidationResult(true);
    }

    public static ValidationResult fail() {
        return new ValidationResult(false);
    }

    public boolean isValid() {
        return this.isValid;
    }

    public Optional<String> messageIfInvalid(String message) {
        return isValid ? Optional.empty() : Optional.of(message);
    }

    public <X extends Throwable> ValidationResult ifInvalidThrows(Supplier<X> handler) throws X {
        if (isValid) {
            return this;
        }

        throw handler.get();
    }


}

