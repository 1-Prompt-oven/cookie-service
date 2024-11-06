package com.promptoven.cookieservice.member.cookie.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CookieCreateRequestVo {

    private int quantity;
    private int changeAmount;
    private String transactionType;
    private String memberUUID;

    @Builder
    public CookieCreateRequestVo(int quantity, int changeAmount, String transactionType, String memberUUID) {
        this.quantity = quantity;
        this.changeAmount = changeAmount;
        this.transactionType = transactionType;
        this.memberUUID = memberUUID;
    }
}
