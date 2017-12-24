package qnguyen.demo.lambda;

import java.util.function.Consumer;

/**
 * Created by QuangNguyen on 19/06/2016.
 */
public class ArroundMethod {
    public static void main(String[] args) {
        // with this way, we only focus on the method that we care,
        // no need to handle open and close manually
        Resource.use(resource -> resource.op1()
                                         .op2()
        );
    }
}


class Resource {

    private Resource() {
        System.out.println("Create....");
    }

    static void use(Consumer<Resource> functionBlock) {
        Resource resource = new Resource();
        resource.open();
        try {
            functionBlock.accept(resource);
        } catch (Exception e) {
            System.out.println("Catch " + e);
        } finally {
            resource.close();
        }
    }

    private void open() {
        System.out.println("Automatically open");
    }

    private void close() {
        System.out.println("Automatically closed");
    }

    Resource op1() {
        System.out.println("op1");
        return this;
    }

    Resource op2() {
        System.out.println("op2");
        return this;
    }
}

