package com.promptoven.cookieservice.member.cookie.dto.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CookieGetRequestDto {

    private String memberUuid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String lastId;
    private Integer pageSize;
}
