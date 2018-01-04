package hello;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.JPAExpressions;
@Transactional
public class CustomerRepositoryQdslImpl implements CustomerRepositoryQdsl {

    @PersistenceContext
    private EntityManager em;

    @Override
    // @Transactional("customer")
    public Customer save(final Customer cust) {
        em.persist(cust);
        return cust;
    }

    @Override
    public List<Customer> findAll() {
        final JPAQuery<Customer> query = new JPAQuery<>(em);
        final QCustomer customer = QCustomer.customer;

        return query.from(customer).fetch();
    }

    @Override
    public List<Customer> findByFirstNameAndAgeLessThan(String firstName, int ltAge) {
        final JPAQuery<Customer> query = new JPAQuery<>(em);
        final QCustomer customer = QCustomer.customer;

        return query.from(customer)
        .where(customer.firstName.eq(firstName)
            .and(customer.age.lt(ltAge)))
        .fetch();
    }

    @Override
    public List<Customer> findAllWithOrder() {
        final JPAQuery<Customer> query = new JPAQuery<>(em);
        final QCustomer customer = QCustomer.customer;

        return query.from(customer)
        .orderBy(customer.age.asc(), customer.firstName.desc())
        .fetch();
    }

    @Override
    public List<Customer> findByMaxAge() {
        final JPAQuery<Customer> query = new JPAQuery<>(em);
        final QCustomer customer = QCustomer.customer;

        return query.from(customer)
            .where(customer.age.eq(JPAExpressions.select(customer.age.max()).from(customer)))
            .fetch();
    }
}