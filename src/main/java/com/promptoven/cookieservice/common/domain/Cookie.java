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

    private int quantity;
    private int changeAmount;
    private LocalDateTime transactionDate;
    private String transactionType;
    private String memberUUID;

    @Builder
    public Cookie(String id, int quantity, int changeAmount, LocalDateTime transactionDate, String transactionType, String memberUUID) {
        this.id = id;
        this.quantity = quantity;
        this.changeAmount = changeAmount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.memberUUID = memberUUID;
    }
}
