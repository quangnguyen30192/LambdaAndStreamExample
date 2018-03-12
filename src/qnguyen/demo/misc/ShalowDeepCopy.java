package qnguyen.demo.misc;

public class ShalowDeepCopy {

    private static class Student {
        private int age;

        public Student(int age) {
            this.age = age;
        }

        /**
         * Getter for property 'age'.
         *
         * @return Value for property 'age'.
         */
        public int getAge() {
            return this.age;
        }

        /**
         * Setter for property 'age'.
         *
         * @param age Value to set for property 'age'.
         */
        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Student student1 = new Student(3);

        //shallow copy
        Student student2 = student1;
        System.out.println("Shallow copy: copied object age: ");
        System.out.println(student2.getAge());
        student1.setAge(5);
        System.out.println("Shallow copy: copied object age after original object modified: ");
        System.out.println(student2.getAge());


        //deep copy
        Student student3 = new Student(student1.getAge());
        System.out.println("Deep copy: copied object age: ");
        System.out.println(student3.getAge());
        student1.setAge(4);
        System.out.println("Deep copy: copied object age after original object modified: ");
        System.out.println(student3.getAge());
    }
}
