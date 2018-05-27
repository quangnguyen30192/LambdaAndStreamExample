package qnguyen.demo.expwrapper;

public class RuntimeExceptionWrapper<T> {
    public static <T> T wrap(RuntimeExceptionWrappable<T> wrapper) {
        try {
            return wrapper.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
