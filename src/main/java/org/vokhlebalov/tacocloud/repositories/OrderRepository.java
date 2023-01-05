package org.vokhlebalov.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import org.vokhlebalov.tacocloud.entities.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
