package hello;

import java.util.List;

public interface CustomerRepositoryQdsl {
    public Customer save(Customer cust);

    public List<Customer> findAll();

    public List<Customer> findAllWithOrder();

    public List<Customer> findByFirstNameAndAgeLessThan(String firstName, int ltAge);

    public List<Customer> findByMaxAge();
}
