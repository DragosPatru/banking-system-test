package com.latch.domain.customer;

import com.latch.domain.customer.model.Customer;
import com.latch.domain.exception.DuplicateKeyException;
import java.util.Optional;

public interface CustomerRepository {

  Optional<Customer> findById(String customerId);

  /**
   * @throws DuplicateKeyException in case of duplicates
   */
  Customer insert(Customer customer);

  void deleteById(String customerId);
}
