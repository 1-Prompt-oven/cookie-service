package com.promptoven.cookieservice.member.cookie.dto.out;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.member.cookie.vo.out.CookieGetResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CookieGetResponseDto {

    private String id;
    private int quantity;
    private int changeAmount;
    private LocalDateTime transactionDate;
    private String transactionType;
    private String memberUUID;

    public static CookieGetResponseDto fromEntity(Cookie cookie) {
        return CookieGetResponseDto.builder()
                .id(cookie.getId())
                .quantity(cookie.getQuantity())
                .changeAmount(cookie.getChangeAmount())
                .transactionDate(cookie.getTransactionDate())
                .transactionType(cookie.getTransactionType())
                .memberUUID(cookie.getMemberUUID())
                .build();
    }

    public static CookieGetResponseVo toVo(CookieGetResponseDto dto) {
        return CookieGetResponseVo.builder()
                .id(dto.getId())
                .quantity(dto.getQuantity())
                .changeAmount(dto.getChangeAmount())
                .transactionDate(dto.getTransactionDate())
                .transactionType(dto.getTransactionType())
                .memberUUID(dto.getMemberUUID())
                .build();
    }
}
