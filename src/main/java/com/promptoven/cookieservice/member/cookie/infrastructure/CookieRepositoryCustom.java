package com.promptoven.cookieservice.member.cookie.infrastructure;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.global.common.CursorPage;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieGetRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.out.CookieGetResponseDto;

import java.util.Optional;

public interface CookieRepositoryCustom {
    CursorPage<CookieGetResponseDto> getCookiesByCriteria(CookieGetRequestDto requestDto);

    Optional<Cookie> findTopByOrderByApprovedAtDesc(String memberUuid);
}
