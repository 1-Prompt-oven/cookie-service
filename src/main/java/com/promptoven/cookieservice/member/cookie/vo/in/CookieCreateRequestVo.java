package com.promptoven.cookieservice.member.cookie.vo.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CookieCreateRequestVo {

    private String memberUuid;
    private Integer cookieAmount;
    private LocalDateTime approvedAt;
}
