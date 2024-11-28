package com.promptoven.cookieservice.member.cookie.vo.out;

import com.promptoven.cookieservice.common.domain.PaymentType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CookieGetResponseVo {

    private String id;
    private Long paymentId;
    private String memberUuid;
    private Integer cookieAmount;
    private LocalDateTime approvedAt;
    private PaymentType paymentType;
    private Integer quantity;
}
