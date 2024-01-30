package qnguyen.demo.misc;

class Vehicle {
    private String fuelType;
    private String colour;

    // Standard Getter methods..
    public Vehicle(VehicleBuilder builder) {
        this.colour = builder.colour;
        this.fuelType = builder.fuelType;
    }

    public static class VehicleBuilder {

        protected String fuelType;
        protected String colour;

        public VehicleBuilder fuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public VehicleBuilder colour(String colour) {
            this.colour = colour;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}

class Car extends Vehicle {

    private String make;
    private String model;

    // Standard Getter methods..

    public Car(CarBuilder builder) {
        super(builder);
        this.make = builder.make;
        this.model = builder.model;
    }

    public static class CarBuilder extends VehicleBuilder {

        protected String make;
        protected String model;

        public CarBuilder make(String make) {
            this.make = make;
            return this;
        }

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

public class BuilderPatternInheritance {
    public static void main (String[] args) {
        /**
         * This has compilation error as colour() returns Vehicle, and model() is absent in model
         * Let check solution in BuilderPatternInheritanceSimpleSolution and BuilderPatternInheritanceTypeSafe
         * and see what it does to let model() returns the CarBuilder instead of VehicleBuilder()
         */
        // final Car car = new Car.CarBuilder().colour("red").model("F");
    }
}
