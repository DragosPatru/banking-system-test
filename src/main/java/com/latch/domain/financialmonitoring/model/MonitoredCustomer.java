package com.latch.domain.financialmonitoring.model;

import java.time.Instant;

public record MonitoredCustomer(String id, Instant createdAt) {}
