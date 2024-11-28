package com.promptoven.cookieservice.member.cookie.application;

import com.promptoven.cookieservice.common.domain.Cookie;
import com.promptoven.cookieservice.member.cookie.dto.in.RequestCookieMessageDto;
import com.promptoven.cookieservice.member.cookie.infrastructure.CookieRepository;
import com.promptoven.cookieservice.member.cookie.infrastructure.CookieRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CookieRepositoryCustom cookieRepositoryCustom;
    private final CookieRepository cookieRepository;

    @KafkaListener(topics = "${payment-cookie-create-event}", groupId = "kafka-payment-purchase-service")
    public void consumeCreate(RequestCookieMessageDto message) {

        Optional<Cookie> optionalCookie = cookieRepositoryCustom.findTopByOrderByApprovedAtDesc(message.getMemberUuid());

        Integer lastQuantity = optionalCookie.map(Cookie::getQuantity).orElse(0);

        cookieRepository.save(RequestCookieMessageDto.toDocument(
                message.getPaymentId(),
                message.getMemberUuid(),
                message.getCookieAmount(),
                message.getApprovedAt(),
                lastQuantity));
    }
}
