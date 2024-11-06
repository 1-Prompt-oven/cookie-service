package com.promptoven.cookieservice.member.cookie.infrastructure;

import com.promptoven.cookieservice.global.common.CursorPage;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieGetRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.out.CookieGetResponseDto;

public interface CookieRepositoryCustom {
    CursorPage<CookieGetResponseDto> getCookiesByCriteria(CookieGetRequestDto requestDto);
}
