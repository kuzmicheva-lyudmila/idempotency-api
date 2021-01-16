package ru.kl.order.model;

import lombok.Value;

@Value
public class IdempotencyKey {

    String id;

    int hashCode;

    String orderId;

    public IdempotencyKey(String id, int hashCode, String orderId) {
        this.id = id;
        this.hashCode = hashCode;
        this.orderId = orderId;
    }
}
