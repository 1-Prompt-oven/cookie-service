package com.promptoven.cookieservice.member.cookie.dto.out;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.common.domain.PaymentType;
import com.promptoven.cookieservice.member.cookie.vo.out.CookieGetResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CookieGetResponseDto {

    private String id;
    private Long paymentId;
    private String memberUuid;
    private Integer cookieAmount;
    private LocalDateTime approvedAt;
    private PaymentType paymentType;
    private Integer quantity;

    public static CookieGetResponseDto fromEntity(Cookie cookie) {
        return CookieGetResponseDto.builder()
                .id(cookie.getId())
                .paymentId(cookie.getPaymentId())
                .memberUuid(cookie.getMemberUuid())
                .cookieAmount(cookie.getCookieAmount())
                .approvedAt(cookie.getApprovedAt())
                .paymentType(cookie.getPaymentType())
                .quantity(cookie.getQuantity())
                .build();
    }

    public static CookieGetResponseVo toVo(CookieGetResponseDto dto) {
        return CookieGetResponseVo.builder()
                .id(dto.getId())
                .paymentId(dto.getPaymentId())
                .memberUuid(dto.getMemberUuid())
                .cookieAmount(dto.getCookieAmount())
                .approvedAt(dto.getApprovedAt())
                .paymentType(dto.getPaymentType())
                .quantity(dto.getQuantity())
                .build();
    }
}
