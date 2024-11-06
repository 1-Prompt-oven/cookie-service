package com.promptoven.cookieservice.member.cookie.application;

import com.promptoven.cookieservice.global.common.CursorPage;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieCreateRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieGetRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.out.CookieGetResponseDto;
import com.promptoven.cookieservice.member.cookie.infrastructure.CookieRepository;
import com.promptoven.cookieservice.member.cookie.infrastructure.CookieRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {

    private final CookieRepository cookieRepository;
    private final CookieRepositoryCustom cookieRepositoryCustom;

    @Override
    public void createCookie(CookieCreateRequestDto requestDto) {
        cookieRepository.save(CookieCreateRequestDto.toDocument(requestDto));
    }

    @Transactional(readOnly = true)
    @Override
    public CursorPage<CookieGetResponseDto> getCookiesByMemberUuid(CookieGetRequestDto requestDto) {
        return cookieRepositoryCustom.getCookiesByCriteria(requestDto);
    }

}
