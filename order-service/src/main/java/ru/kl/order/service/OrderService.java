package ru.kl.order.service;

import ru.kl.order.model.OrderDetails;

public interface OrderService {

    OrderDetails create(String clientId, String idempotencyKey, Long amount);
}
