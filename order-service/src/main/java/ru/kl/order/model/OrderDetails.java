package ru.kl.order.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "order_details")
public class OrderDetails {

    @Id
    private String id;

    private String clientId;

    private long amount;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime insTime;

    public OrderDetails(String clientId, long amount) {
        this.id = UUID.randomUUID().toString();
        this.clientId = clientId;
        this.amount = amount;
        this.insTime = LocalDateTime.now();
    }

    public int generateHashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $clientId = this.getClientId();
        result = result * PRIME + ($clientId == null ? 43 : $clientId.hashCode());
        final long $amount = this.getAmount();
        result = result * PRIME + (int) ($amount >>> 32 ^ $amount);
        return result;
    }
}
