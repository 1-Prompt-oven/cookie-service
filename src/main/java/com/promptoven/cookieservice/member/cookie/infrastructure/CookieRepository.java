package com.promptoven.cookieservice.member.cookie.infrastructure;

import com.promptoven.cookieservice.common.domain.Cookie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CookieRepository extends MongoRepository<Cookie, String> {

    @Query(value = "{}", sort = "{ 'approvedAt': -1 }")
    Optional<Cookie> findTopByOrderByApprovedAtDesc();
}
