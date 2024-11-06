package com.promptoven.cookieservice.global.error;

import com.promptoven.cookieservice.global.common.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage()); //
        this.status = status;
    }
}
