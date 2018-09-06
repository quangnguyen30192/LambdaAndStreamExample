package qnguyen.demo.designpattern.composite.employehierarchy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Supervisor extends Employee implements IWork {

    private List<Employee> employees = new LinkedList<>();

    public Supervisor(String name) {
        super(name);
    }

    public void supervise(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void doTask() {
        final String employees = this.employees.stream()
                .map(Employee::getName).collect(Collectors.joining(", "));
        System.out.println(this.getName() + " manages " + employees);
        this.employees.forEach(Employee::doTask);
    }
}
