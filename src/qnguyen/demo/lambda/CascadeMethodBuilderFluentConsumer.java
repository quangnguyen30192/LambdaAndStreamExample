package qnguyen.demo.lambda;

import java.util.function.Consumer;

/**
 * Created by QuangNguyen on 19/06/2016.
 */
public class CascadeMethodBuilderFluentConsumer {
    public static void main(String[] args) {
        // no longer use mailer.from
        //               mailer.to ...
        // such a mess

        // use cascade method
        System.out.println("Use cascade method");
        new Mailer().from("quangbnn@mgm-tp.com")
                    .to("app@gmail.com")
                    .body("aaaaa")
                    .send();

        System.out.println();
        System.out.println();
        System.out.println();


        // or use Consumer method
        System.out.println("use consumer method");
        Mailer.send(mailer -> mailer.from("quangbnn@mgm-tp.com")
                                    .to("app@gmail.com")
                                    .body("aaaaa"));
    }
}

class Mailer {
    static void send(Consumer<Mailer> consumer) {
        consumer.accept(new Mailer());
        System.out.println("Sending...");
    }

    Mailer from(String address) {
        System.out.println("from = " + address);
        return this;
    }

    Mailer to(String address) {
        System.out.println("to = " + address);
        return this;
    }

    Mailer body(String content) {
        System.out.println("body = " + content);
        return this;
    }

    void send() {
        System.out.println("Sending...");
    }
}