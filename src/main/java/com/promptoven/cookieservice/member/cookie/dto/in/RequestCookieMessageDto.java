package com.promptoven.cookieservice.member.cookie.dto.in;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.common.domain.PaymentType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestCookieMessageDto {

    private Long paymentId;

    private String memberUuid;

    private Integer cookieAmount;

    private LocalDateTime approvedAt;

    public static Cookie toDocument(Long paymentId, String memberUuid, Integer cookieAmount, LocalDateTime approvedAt, Integer currentQuantity) {
        return Cookie.builder()
                .paymentId(paymentId)
                .memberUuid(memberUuid)
                .cookieAmount(cookieAmount)
                .approvedAt(approvedAt)
                .paymentType(PaymentType.CHARGE)
                .quantity(currentQuantity + cookieAmount)
                .build();
    }
}
