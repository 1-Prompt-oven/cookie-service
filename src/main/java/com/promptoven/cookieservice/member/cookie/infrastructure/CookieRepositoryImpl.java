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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CookieRepositoryImpl implements CookieRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public CursorPage<CookieGetResponseDto> getCookiesByCriteria(CookieGetRequestDto requestDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("memberUUID").is(requestDto.getMemberUuid()));

        // 날짜 필터링 조건 추가
        if (requestDto.getStartDate() != null && requestDto.getEndDate() != null) {
            query.addCriteria(Criteria.where("transactionDate").gte(requestDto.getStartDate()).lte(requestDto.getEndDate()));
        }

        // Cursor 기반 조건 추가
        if (requestDto.getLastId() != null) {
            query.addCriteria(Criteria.where("_id").gt(requestDto.getLastId()));
        }

        // 정렬 및 페이징 설정
        query.with(Sort.by(Sort.Direction.ASC, "_id"));
        query.limit(requestDto.getPageSize() + 1); // +1 to check if there is a next page

        List<Cookie> cookies = mongoTemplate.find(query, Cookie.class);

        boolean hasNext = cookies.size() > requestDto.getPageSize();
        if (hasNext) {
            cookies.remove(cookies.size() - 1); // remove the extra element
        }

        List<CookieGetResponseDto> cookieDtos = cookies.stream()
                .map(CookieGetResponseDto::fromEntity)
                .collect(Collectors.toList());

        String nextCursor = hasNext ? cookies.get(cookies.size() - 1).getId().toString() : null;

        return new CursorPage<>(cookieDtos, nextCursor, hasNext, requestDto.getPageSize(), 0);
    }
}

