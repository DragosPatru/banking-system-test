package com.latch.domain.customer;

import com.latch.domain.customer.model.Customer;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * must be @Transactional
     * */
    @Override
    public Customer createCustomer(Customer customer) {
        return repository.insert(customer);
    }

    /**
     * must be @Transactional
     * */
    @Override
    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
