package com.promptoven.cookieservice.member.cookie.dto.in;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.common.domain.PaymentType;
import com.promptoven.cookieservice.member.cookie.vo.in.CookieCreateRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CookieCreateRequestDto {

    private String memberUuid;
    private Integer cookieAmount;
    private LocalDateTime approvedAt;

    public static CookieCreateRequestDto toDto(CookieCreateRequestVo vo) {
        return CookieCreateRequestDto.builder()
                .memberUuid(vo.getMemberUuid())
                .cookieAmount(vo.getCookieAmount())
                .approvedAt(vo.getApprovedAt())
                .build();
    }

    public static Cookie toDocument(CookieCreateRequestDto dto, Integer currentQuantity) {
        return Cookie.builder()
                .memberUuid(dto.getMemberUuid())
                .cookieAmount(dto.getCookieAmount())
                .approvedAt(dto.getApprovedAt())
                .paymentType(PaymentType.USE)
                .quantity(currentQuantity - dto.getCookieAmount())
                .build();
    }
}
