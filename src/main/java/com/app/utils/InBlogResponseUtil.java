package com.app.utils;

import com.app.utils.exceptions.InBlogAppException;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

public class InBlogResponseUtil {

    private InBlogResponseUtil() {
    }

    /**
     * throw exception with result code and MDC request id
     *
     * @param errorMsg
     * @param requestId
     */
    public static InBlogAppException throwApplicationException(String errorMsg, String requestId)
            throws InBlogAppException {
        throw new InBlogAppException(
                errorMsg,
                HttpStatus.BAD_REQUEST,
                requestId);
    }

}