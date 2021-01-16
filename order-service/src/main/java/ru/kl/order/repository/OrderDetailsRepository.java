package ru.kl.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kl.order.model.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {
}
