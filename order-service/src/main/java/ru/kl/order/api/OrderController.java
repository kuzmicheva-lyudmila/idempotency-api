package ru.kl.order.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kl.order.model.OrderDetails;
import ru.kl.order.service.OrderService;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private static final String AUTH_CLIENT_HEADER = "X-Auth-Client";

    private static final String IDEMPOTENCY_KEY_HEADER = "Idempotency-Key";

    private final OrderService orderService;

    @PostMapping
    public OrderDetails create(
            @RequestHeader(value = AUTH_CLIENT_HEADER) String clientId,
            @RequestHeader(value = IDEMPOTENCY_KEY_HEADER) String idempotencyKey,
            @RequestBody Long amount
    ) {
        return orderService.create(clientId, idempotencyKey, amount);
    }
}
