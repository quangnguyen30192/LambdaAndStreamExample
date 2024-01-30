package qnguyen.demo.misc;

class Vehicle {

    private String colour;
    private String fuelType;

    public Vehicle(Builder builder) {
        this.colour = builder.colour;
        this.fuelType = builder.fuelType;
    }

    // Standard getter methods..
    public static class Builder<T extends Builder> {

        protected String colour;
        protected String fuelType;

        T self() {
            return (T) this;
        }

        /***
         * Noticeably, the methods fuelType() and colour() in the inner Builder class
         * are returning a generic type. This kind of implementation facilitates fluent
         * style coding or method chaining. This is a design pattern well known by the
         * name Curiously Recurring Template Pattern(CRTP).
         *
         */
        public T colour(String colour) {
            this.colour = colour;
            return self();
        }

        public T fuelType(String fuelType) {
            this.fuelType = fuelType;
            return self();
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}

class Car extends Vehicle {

    private String make;
    private String model;

    // Standard Getters..
    public Car(Builder<?> builder) {
        super(builder);
        this.make = builder.make;
        this.model = builder.model;
    }

    public static class Builder<T extends Builder<T>> extends Vehicle.Builder<T> {

        protected String make;
        protected String model;

        public T make(String make) {
            this.make = make;
            return self();
        }

        public T model(String model) {
            this.model = model;
            return self();
        }

        @Override
        public Car build() {
            return new Car(this);
        }
    }
}

class ElectricCar extends Car {
    private String batteryType;

    public String getBatteryType() {
        return batteryType;
    }

    /**
     * we’ve used the raw generic type ? for declaring the inner
     * classes Car.Builder<?> and ElectricCar.Builder<?>. This is because we need to
     * ensure that method calls such as carBuilder.colour() and
     * carBuilder.fuelType() return Car.Builder instead of its parent
     * Vehicle.Builder.
     */
    public ElectricCar(Builder<?> builder) {
        super(builder);
        this.batteryType = builder.batteryType;
    }

    public static class Builder<T extends Builder<T>> extends Car.Builder<T> {
        protected String batteryType;

        public T batteryType(String batteryType) {
            this.batteryType = batteryType;
            return self();
        }

        @Override
        public ElectricCar build() {
            return new ElectricCar(this);
        }
    }
}

public class BuilderPatternInheritanceTypeSafe {
    public static void main(String[] args) {
        /**
         * now model() is available as colour() returns CarBuilder instead of VehicleBuilder
         */
        Car car = new Car.Builder<>().colour("red").model("F").build();
    }
}
