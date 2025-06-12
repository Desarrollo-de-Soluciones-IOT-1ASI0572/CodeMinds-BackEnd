package com.codeminds.edugo.shared.domain.model.bus;

public interface DomainEventPublisher {
    void publish(Object event);
}
