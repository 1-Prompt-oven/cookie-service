package com.promptoven.cookieservice.member.cookie.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CookieGetResponseVo {

    private String id;
    private int quantity;
    private int changeAmount;
    private LocalDateTime transactionDate;
    private String transactionType;
    private String memberUUID;

    @Builder
    public CookieGetResponseVo(String id, int quantity, int changeAmount, LocalDateTime transactionDate, String transactionType, String memberUUID) {
        this.id = id;
        this.quantity = quantity;
        this.changeAmount = changeAmount;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.memberUUID = memberUUID;
    }
}
