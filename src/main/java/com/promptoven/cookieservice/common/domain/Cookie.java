package com.promptoven.cookieservice.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(collection = "cookie")
public class Cookie {

    @Id
    private String id;

    private Long paymentId;
    private String memberUuid;
    private Integer cookieAmount;
    private LocalDateTime approvedAt;
    private PaymentType paymentType;
    private Integer quantity;

    @Builder
    public Cookie(String id, Long paymentId, String memberUuid, Integer cookieAmount, LocalDateTime approvedAt, PaymentType paymentType, Integer quantity) {
        this.id = id;
        this.paymentId = paymentId;
        this.memberUuid = memberUuid;
        this.cookieAmount = cookieAmount;
        this.approvedAt = approvedAt;
        this.paymentType = paymentType;
        this.quantity = quantity;
    }
}
