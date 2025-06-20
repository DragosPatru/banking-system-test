package com.latch.domain.event;

/**
 * TODO: implementations usually provided by frameworks like SpringBoot or messaging systems like
 * Kakfa or an JPA repository saving into table (sharing the same transaction with the normal
 * operation - outbox pattern) depending on the requirements
 */
public interface DomainEventPublisher {

  void publish(DomainEvent event);
}
