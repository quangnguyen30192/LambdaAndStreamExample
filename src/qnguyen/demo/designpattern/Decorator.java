package qnguyen.demo.designpattern;

public class Decorator {
    public static void main(String[] args) {
        IPizza basicPizza = new BasicPizza();
        System.out.println(basicPizza.producePizza()); //Hello I'm the pizza

        IPizza tomatoPizza = new TomatoDecorator(new BasicPizza());
        System.out.println(tomatoPizza.producePizza()); // Hello I'm the pizza with tomato

        IPizza peoperTomatoPizza = new PepperDecorator(new TomatoDecorator(new BasicPizza()));
        System.out.println(peoperTomatoPizza.producePizza()); // Hello I'm the pizza with tomato with pepper
    }
}

interface IPizza {
    String producePizza();
}

class BasicPizza implements IPizza {

    @Override
    public String producePizza() {
        return "Hello I'm the pizza";
    }
}

class TomatoDecorator implements IPizza {

    private IPizza iPizza;

    TomatoDecorator(IPizza iPizza) {
        this.iPizza = iPizza;
    }

    @Override
    public String producePizza() {
        return iPizza.producePizza() + " with tomato";
    }
}

class ChesseDecorator implements IPizza {

    private IPizza iPizza;

    ChesseDecorator(IPizza iPizza) {
        this.iPizza = iPizza;
    }

    @Override
    public String producePizza() {
        return iPizza.producePizza() + " with cheese";
    }
}

class PepperDecorator implements IPizza {

    private IPizza iPizza;

    PepperDecorator(IPizza iPizza) {
        this.iPizza = iPizza;
    }

    @Override
    public String producePizza() {
        return iPizza.producePizza() + " with pepper";
    }
}