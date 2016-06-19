package qnguyen.demo;

/**
 * Created by QuangNguyen on 19/06/2016.
 */
public class AutoCloseable {
    public static void main(String[] args) {
        try (FileBounder fileBounder = new FileBounder()) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}

class FileBounder implements java.lang.AutoCloseable {

    public FileBounder() {
        System.out.println("Create....");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closed... ");
    }
}
