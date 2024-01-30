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

        /**
         * the child builder classes override the chaining methods of all the base
         * builder classes in the hierarchy, this is just for type safety. Hence, there
         * is no compilation error during
         * method chaining to set the attribute values.
         */
        @Override
        public CarBuilder colour(String colour) {
            this.colour = colour;
            return this;
        }

        @Override
        public CarBuilder fuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

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

public class BuilderPatternInheritanceSimpleSolution {
    public static void main(String[] args) {
        /*
         * E.g a child class of Car such as ElectricCar, we must override all the methods of
         * CarBuilder and VehicleBuilder in ElectricCarBuilder. Hence, it’s not a very
         * efficient implementation.
         */
        final Car car = new Car.CarBuilder().colour("red").fuelType("F").make("").model("").build();
    }
}
