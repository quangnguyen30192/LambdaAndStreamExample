package qnguyen.demo.misc;

public class StatePatternDemo {
    public static void main(String[] args) {
        Bike bike = new Bike();
        bike.gearDown();
        bike.gearUp();
        bike.gearUp();
        bike.gearUp();
        bike.gearUp();
        bike.gearDown();
        bike.gearDown();
        bike.gearDown();
    }
}

class Bike {

    private GearState gearState;

    public Bike() {
        this.gearState = new FirstGearState(this);
    }

    public void gearUp() {
        gearState.gearUp();
    }

    public void gearDown() {
        gearState.gearDown();
    }

    public void setGearState(GearState gearState) {
        this.gearState = gearState;
    }
}

abstract class GearState {

    protected Bike bike;

    public GearState(Bike bike) {
        this.bike = bike;
    }

    abstract void gearUp();
    abstract void gearDown();
}

class FirstGearState extends GearState {

    public FirstGearState(Bike bike) {
        super(bike);
    }

    @Override
    void gearUp() {
        System.out.println("Moving Up from FirstGear to SecondGear");
        bike.setGearState(new SecondGearState(bike));
    }

    @Override
    void gearDown() {
        System.out.println("Already in the FirstGear - cannot go lower");
    }
}


class SecondGearState extends GearState {

    public SecondGearState(Bike bike) {
        super(bike);
    }

    @Override
    void gearUp() {
        System.out.println("Moving Up from SecondGear to ThirdGear");
        bike.setGearState(new ThirdGearState(bike));
    }

    @Override
    void gearDown() {
        System.out.println("Already in the SecondGear to FirstGear");
        bike.setGearState(new FirstGearState(bike));
    }
}


class ThirdGearState extends GearState {

    public ThirdGearState(Bike bike) {
        super(bike);
    }

    @Override
    void gearUp() {
        System.out.println("Already in the ThirdGear, can not go higher");
    }

    @Override
    void gearDown() {
        System.out.println("Already in the FirstGear - cannot go lower");
        bike.setGearState(new SecondGearState(bike));
    }
}