package com.promptoven.cookieservice.member.cookie.dto.in;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.member.cookie.vo.in.CookieCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CookieCreateRequestDto {

    private int quantity;
    private int changeAmount;
    private String transactionType;
    private String memberUUID;

    @Builder
    public CookieCreateRequestDto(int quantity, int changeAmount, String transactionType, String memberUUID) {
        this.quantity = quantity;
        this.changeAmount = changeAmount;
        this.transactionType = transactionType;
        this.memberUUID = memberUUID;
    }

    public static CookieCreateRequestDto toDto(CookieCreateRequestVo vo) {
        return CookieCreateRequestDto.builder()
                .quantity(vo.getQuantity())
                .changeAmount(vo.getChangeAmount())
                .transactionType(vo.getTransactionType())
                .memberUUID(vo.getMemberUUID())
                .build();
    }

    public static Cookie toDocument(CookieCreateRequestDto dto) {
        return Cookie.builder()
                .quantity(dto.getQuantity())
                .changeAmount(dto.getChangeAmount())
                .transactionDate(LocalDateTime.now())
                .transactionType(dto.getTransactionType())
                .memberUUID(dto.getMemberUUID())
                .build();
    }
}
