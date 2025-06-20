package com.latch.domain.customer.model;

public class Customer {
  /*
   * national id e.g. CNP
   * */
  private final String id;
  private final String name;

  public Customer(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
