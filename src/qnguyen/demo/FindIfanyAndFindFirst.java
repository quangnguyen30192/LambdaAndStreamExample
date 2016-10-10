package qnguyen.demo;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Quang Nguyen on 9/30/2016.
 */


class Customer {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

public class FindIfanyAndFindFirst {

    private List<Customer> customers;

    public FindIfanyAndFindFirst(List<Customer> customers) {
        this.customers = customers;
    }

    /*
        the same potential bug: they are built on the implicit assumption that there can only be one customer with any given ID.
     */
    public Optional<Customer> findCustomer(String customerId) {
        return customers.stream().filter(customer -> customer.getId().equals(customerId))
                        .findFirst();
    }

    // solution 1
/*
    public Optional<Customer> findOnlyCustomer(String customerId) {
        boolean foundCustomer = false;
        Customer resultCustomer = null;
        for (Customer customer : customers)
            if (customer.getId().equals(customerId))
                if (!foundCustomer) {
                    foundCustomer = true;
                    resultCustomer = customer;
                } else {
                    throw new DuplicateCustomerException();
                }
              // as reduce function does
        return foundCustomer
                ? Optional.of(resultCustomer)
                : Optional.empty();
    }
*/
    public Optional<Customer> findCustomerSolved(String customerId) {
        return customers.stream().filter(customer -> customer.getId().equals(customerId))
                        .reduce(toOnlyElement());
    }

    private BinaryOperator<Customer> toOnlyElement() {return toOnlyElementThrowing(IllegalArgumentException::new);}

    public static <T, E extends RuntimeException> BinaryOperator<T> toOnlyElementThrowing(Supplier<E> exception) {
        return (element, otherElement) -> {
            throw exception.get();
        };
    }

    // solution 2
    public Optional<Customer> findCustomerSolved2(String customerId) {
        Customer collect = customers.stream().filter(customer -> customer.getId().equals(customerId))
                                    .collect(singletonCollector());
        return Optional.ofNullable(collect);
    }

    public static <T> Collector<T, ?, T> singletonCollector() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    // guava has a solution
    // It is also worth noting that Guava has a similar functionality for iterators, namely Iterables::getOnlyElement
}
