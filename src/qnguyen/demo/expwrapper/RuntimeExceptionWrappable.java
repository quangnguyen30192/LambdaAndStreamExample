package qnguyen.demo.expwrapper;

@FunctionalInterface
public interface RuntimeExceptionWrappable<T> {
    T execute() throws Exception;
}
