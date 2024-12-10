package com.promptoven.cookieservice.member.cookie.dto.in;

import com.promptoven.cookieservice.common.domain.PaymentType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CookieGetRequestDto {

    private String memberUuid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PaymentType paymentType;
    private String lastId;
    private Integer pageSize;
}
