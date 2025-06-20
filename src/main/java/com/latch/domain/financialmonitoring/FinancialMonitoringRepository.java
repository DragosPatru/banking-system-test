package com.latch.domain.financialmonitoring;

import com.latch.domain.customer.model.Customer;
import com.latch.domain.exception.DuplicateKeyException;
import com.latch.domain.financialmonitoring.model.MonitoredCustomer;

import java.util.Optional;

public interface FinancialMonitoringRepository {

  Optional<MonitoredCustomer> findByCustomerId(String customerId);

  /**
   * @throws DuplicateKeyException in case of duplicates
   */
  Customer insert(MonitoredCustomer monitoredCustomer);

  void deleteByCustomerId(String customerId);
}
