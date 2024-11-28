package com.promptoven.cookieservice.member.cookie.infrastructure;

import com.promptoven.cookieservice.common.domain.Cookie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends MongoRepository<Cookie, String> {
}
