package com.promptoven.cookieservice.member.cookie.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CookieGetRequestDto {

    private String memberUuid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String lastId;
    private Integer pageSize;

    @Builder
    public CookieGetRequestDto(String memberUuid, LocalDateTime startDate, LocalDateTime endDate, String lastId, Integer pageSize) {
        this.memberUuid = memberUuid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lastId = lastId;
        this.pageSize = pageSize;
    }
}
