package com.latch.domain.financialmonitoring;

public interface FinancialMonitoringService {

  void startMonitoring(String customerId); // customer

  void stopMonitoring(String customerId); // customer

  boolean isMonitored(String customerId);
}
