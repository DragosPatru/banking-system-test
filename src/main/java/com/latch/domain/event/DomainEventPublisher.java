package com.latch.domain.event;

/**
 * TODO: implementations are usually provided by frameworks like SpringBoot <br>
 * or <br>
 * messaging systems like Kakfa can be integrated <br>
 * or <br>
 * an JPA repository saving into a table in the same database (sharing the same transaction with the
 * normal operation - outbox pattern) depending on the requirements
 */
public interface DomainEventPublisher {

  void publish(DomainEvent event);
}
