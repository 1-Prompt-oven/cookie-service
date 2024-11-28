package com.promptoven.cookieservice.member.cookie.application;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.global.common.CursorPage;
import com.promptoven.cookieservice.global.common.response.BaseResponseStatus;
import com.promptoven.cookieservice.global.error.BaseException;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieCreateRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieGetRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.out.CookieGetResponseDto;
import com.promptoven.cookieservice.member.cookie.infrastructure.CookieRepository;
import com.promptoven.cookieservice.member.cookie.infrastructure.CookieRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {

    private final CookieRepository cookieRepository;
    private final CookieRepositoryCustom cookieRepositoryCustom;

    @Override
    public void createCookieUsage(CookieCreateRequestDto requestDto) {

        Optional<Cookie> optionalCookie = cookieRepositoryCustom.findTopByOrderByApprovedAtDesc(requestDto.getMemberUuid());

        // 값이 없으면 기본값 0 사용
        Integer lastQuantity = optionalCookie.map(Cookie::getQuantity).orElse(0);

        // 쿠키 부족 예외 처리
        if (lastQuantity < requestDto.getCookieAmount()) {
            throw new BaseException(BaseResponseStatus.INSUFFICIENT_COOKIES);
        }

        // 새로운 쿠키 저장
        cookieRepository.save(CookieCreateRequestDto.toDocument(requestDto, lastQuantity));
    }


    @Transactional(readOnly = true)
    @Override
    public CursorPage<CookieGetResponseDto> getCookiesByMemberUuid(CookieGetRequestDto requestDto) {
        return cookieRepositoryCustom.getCookiesByCriteria(requestDto);
    }
}
