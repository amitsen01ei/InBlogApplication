package com.app.utils.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class InBlogAppException extends RuntimeException {

    private static final long serialVersionUID = 1436995162658277359L;

    private String requestId;

    private transient Object[] args;

    @Builder.Default
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public InBlogAppException(String errorMsg, HttpStatus status, Object... args) {
        super("Error Message=" + errorMsg);
        this.status = status;
        this.requestId = null;
        this.args = args;
    }
}
