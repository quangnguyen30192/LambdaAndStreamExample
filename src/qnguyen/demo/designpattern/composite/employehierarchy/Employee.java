package qnguyen.demo.designpattern.composite.employehierarchy;

public class Employee implements IWork {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public void doTask() {
        System.out.println("Employee: " + this.name + " is working");
    }

    public String getName() {
        return name;
    }
}
