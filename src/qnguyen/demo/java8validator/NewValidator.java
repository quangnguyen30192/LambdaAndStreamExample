package qnguyen.demo.java8validator;

import static qnguyen.demo.java8validator.refactor.ValidationUtil.NOT_EMPTY_STRING;
import static qnguyen.demo.java8validator.refactor.ValidationUtil.integerBetween;
import static qnguyen.demo.java8validator.refactor.ValidationUtil.integerNotZero;
import static qnguyen.demo.java8validator.refactor.ValidationUtil.notNull;
import static qnguyen.demo.java8validator.refactor.ValidationUtil.stringSizeBetween;


public class NewValidator implements EmployeeValidator {

    @Override
    public void validate(Employee employee) throws EmployeeException {
        notNull(String.class).and(NOT_EMPTY_STRING)
                             .and(stringSizeBetween(2, 100))
                             .test(employee.getFirstName())
                             .ifInvalidThrows(() -> new EmployeeException("Please specify valid firstname"));

        notNull(String.class).and(NOT_EMPTY_STRING)
                             .and(stringSizeBetween(2, 100))
                             .test(employee.getLastName())
                             .ifInvalidThrows(() -> new EmployeeException("Please specify valid lastname"));

        notNull(String.class).and(NOT_EMPTY_STRING)
                             .and(stringSizeBetween(3, 100))
                             .test(employee.getEmail())
                             .ifInvalidThrows(() -> new EmployeeException("Please specify valid email"));

        notNull(Integer.class).and(integerNotZero())
                              .and(integerBetween(18, 60))
                              .test(employee.getAge())
                              .ifInvalidThrows(() -> new EmployeeException("Please specify valid age"));
    }
}
