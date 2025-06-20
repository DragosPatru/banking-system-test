package com.latch.domain.customer;

import com.latch.domain.customer.model.Customer;

public interface CustomerService {

  Customer createCustomer(Customer customer);

  void deleteCustomer(String customerId);
}
