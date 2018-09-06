package qnguyen.demo.designpattern.composite.employehierarchy;

public class Demo {
    public static void main(String[] args) {
        Employee quang = new Employee("Quang");
        Employee denis = new Employee("Denis");
        Employee pete = new Employee("Pete");
        Employee bob = new Employee("Bob");

        Supervisor tien = new Supervisor("Tien");
        tien.supervise(quang);
        tien.supervise(denis);
        tien.supervise(bob);

        Supervisor minh = new Supervisor("minh");
        minh.supervise(pete);


        Supervisor boss = new Supervisor("boss");
        boss.supervise(minh);
        boss.supervise(tien);

        boss.doTask();
    }
}
