package com.latch.domain.financialmonitoring;

import com.latch.domain.financialmonitoring.model.MonitoredCustomer;
import java.time.Instant;

public class FinancialMonitoringServiceImpl implements FinancialMonitoringService {

  private final FinancialMonitoringRepository repository;

  public FinancialMonitoringServiceImpl(FinancialMonitoringRepository repository) {
    this.repository = repository;
  }

  /** must be @Transactional */
  @Override
  public void startMonitoring(String customerId) {
    // @throws DuplicateKeyException in case of duplicates (by primary key)
    repository.insert(new MonitoredCustomer(customerId, Instant.now()));
  }

  /** must be @Transactional */
  @Override
  public void stopMonitoring(String customerId) {
    repository.deleteByCustomerId(customerId);
  }

  @Override
  public boolean isMonitored(String customerId) {
    return repository.findByCustomerId(customerId).isPresent();
  }
}
