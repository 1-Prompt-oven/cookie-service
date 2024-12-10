package com.promptoven.cookieservice.member.cookie.presentation;

import com.promptoven.cookieservice.common.domain.PaymentType;
import com.promptoven.cookieservice.global.common.CursorPage;
import com.promptoven.cookieservice.global.common.response.BaseResponse;
import com.promptoven.cookieservice.member.cookie.application.CookieService;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieCreateRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieGetRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.out.CookieGetResponseDto;
import com.promptoven.cookieservice.member.cookie.vo.in.CookieCreateRequestVo;
import com.promptoven.cookieservice.member.cookie.vo.out.CookieGetResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "쿠키 Member API", description = "쿠키 관련 API endpoints")
@RequestMapping("/v1/member/cookie")
public class CookieController {

    private final CookieService cookieService;

    @Operation(summary = "쿠키 사용 하기", description = "쿠키 사용 하기")
    @PostMapping
    public BaseResponse<Void> createCookieUsage(@RequestBody CookieCreateRequestVo requestVo) {

        cookieService.createCookieUsage(CookieCreateRequestDto.toDto(requestVo));

        return new BaseResponse<>();
    }


    @Operation(summary = "회원별 쿠키 사용내역 조회", description = "회원 UUID로 쿠키 사용내역 조회")
    @GetMapping
    public BaseResponse<CursorPage<CookieGetResponseVo>> getCookiesByMemberUuid(
            @RequestParam String memberUuid,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) String lastId,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        // DTO 생성
        CookieGetRequestDto requestDto = CookieGetRequestDto.builder()
                .memberUuid(memberUuid)
                .startDate(startDate)
                .endDate(endDate)
                .paymentType(paymentType)
                .lastId(lastId)
                .pageSize(pageSize)
                .build();

        CursorPage<CookieGetResponseDto> cookiesDtoPage = cookieService.getCookiesByMemberUuid(requestDto);

        // DTO 리스트를 VO 리스트로 변환
        List<CookieGetResponseVo> cookiesVoList = cookiesDtoPage.getContent().stream()
                .map(CookieGetResponseDto::toVo)
                .toList();

        // CursorPage의 정보를 유지하면서 VO 리스트로 새로운 CursorPage 생성
        CursorPage<CookieGetResponseVo> cookiesVoPage = new CursorPage<>(
                cookiesVoList,
                cookiesDtoPage.getNextCursor(),
                cookiesDtoPage.getHasNext(),
                cookiesDtoPage.getPageSize(),
                cookiesDtoPage.getPage()
        );

        return new BaseResponse<>(cookiesVoPage);
    }

    @Operation(summary = "쿠키 추가 테스트", description = "쿠키 추가 테스트")
    @PostMapping("/test")
    public BaseResponse<Void> createCookieUsageTest(@RequestBody CookieCreateRequestVo requestVo) {

        cookieService.createCookie(CookieCreateRequestDto.toDto(requestVo));

        return new BaseResponse<>();
    }
}
