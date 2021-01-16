package ru.kl.order.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.kl.order.model.IdempotencyKey;
import ru.kl.order.model.OrderDetails;
import ru.kl.order.repository.OrderDetailsRepository;
import ru.kl.order.service.OrderService;

import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class DefaultOrderService implements OrderService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final ConcurrentHashMap<String, IdempotencyKey> keys = new ConcurrentHashMap<>();

    public DefaultOrderService(
            OrderDetailsRepository orderDetailsRepository
    ) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public OrderDetails create(String clientId, String idempotencyKey, Long amount) {
        Assert.hasText(clientId, "The given client must not be empty");
        Assert.hasText(idempotencyKey, "The given idempotency key must not be empty");
        Assert.isTrue(amount != null && amount > 0, "The given amount must not be empty or zero");

        OrderDetails orderDetails = new OrderDetails(clientId, amount);

        if (keys.containsKey(idempotencyKey)) {
            IdempotencyKey key = keys.get(idempotencyKey);
            if (key.getHashCode() != orderDetails.generateHashCode()) {
                throw new IllegalArgumentException("The given idempotency key exist");
            }
            return orderDetailsRepository.findById(key.getOrderId()).orElseThrow();
        }

        keys.put(
                idempotencyKey,
                new IdempotencyKey(idempotencyKey, orderDetails.generateHashCode(), orderDetails.getId())
        );
        return orderDetailsRepository.save(orderDetails);
    }
}
