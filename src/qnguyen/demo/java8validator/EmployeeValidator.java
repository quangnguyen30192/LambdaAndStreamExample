package qnguyen.demo.java8validator;

public interface EmployeeValidator {
    
    void validate(Employee employee) throws EmployeeException;
}
