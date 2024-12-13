package com.promptoven.cookieservice.member.cookie.infrastructure;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.global.common.CursorPage;
import com.promptoven.cookieservice.member.cookie.dto.in.CookieGetRequestDto;
import com.promptoven.cookieservice.member.cookie.dto.out.CookieGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CookieRepositoryImpl implements CookieRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<CookieGetResponseDto> getCookiesByCriteria(CookieGetRequestDto requestDto) {
        Query query = new Query();

        // memberUUID 필터 추가
        query.addCriteria(Criteria.where("memberUuid").is(requestDto.getMemberUuid()));

        // 날짜 범위 필터 추가
        if (requestDto.getStartDate() != null && requestDto.getEndDate() != null) {
            query.addCriteria(Criteria.where("approvedAt").gte(requestDto.getStartDate()).lte(requestDto.getEndDate()));
        }

        // PaymentType 필터 추가
        if (requestDto.getPaymentType() != null) {
            query.addCriteria(Criteria.where("paymentType").is(requestDto.getPaymentType()));
        }

        // Cursor 조건 (approvedAt과 _id를 함께 사용)
        if (requestDto.getLastId() != null) {
            Cookie lastCookie = mongoTemplate.findById(requestDto.getLastId(), Cookie.class);
            if (lastCookie != null) {
                query.addCriteria(new Criteria().orOperator(
                        Criteria.where("approvedAt").lt(lastCookie.getApprovedAt()),
                        Criteria.where("approvedAt").is(lastCookie.getApprovedAt()).and("_id").gte(lastCookie.getId())
                ));
            }
        }

        // 정렬 기준: approvedAt 내림차순, _id 내림차순
        query.with(Sort.by(
                Sort.Order.desc("approvedAt"),
                Sort.Order.desc("_id")
        ));
        query.limit(requestDto.getPageSize() + 1); // 페이지 크기 +1 (다음 페이지 존재 여부 확인)

        List<Cookie> cookies = mongoTemplate.find(query, Cookie.class);

        // 다음 페이지 여부 판단
        boolean hasNext = cookies.size() > requestDto.getPageSize();
        String nextCursor = null;

        if (hasNext) {
            // 다음 페이지가 있다면 초과 데이터를 제거하고 nextCursor를 설정
            Cookie lastCookie = cookies.remove(cookies.size() - 1); // 마지막 요소 제거
            nextCursor = lastCookie.getId(); // 다음 페이지의 시작점이 될 ID
        }

        // DTO 변환
        List<CookieGetResponseDto> cookieDtos = cookies.stream()
                .map(CookieGetResponseDto::fromEntity)
                .collect(Collectors.toList());

        return new CursorPage<>(cookieDtos, nextCursor, hasNext, requestDto.getPageSize(), 0);
    }

    @Override
    public Optional<Cookie> findTopByOrderByApprovedAtDesc(String memberUuid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("memberUuid").is(memberUuid));
        query.with(Sort.by(Sort.Direction.DESC, "approvedAt"));
        query.limit(1);
        return Optional.ofNullable(mongoTemplate.findOne(query, Cookie.class));
    }
}
