package com.app.utils;

import com.app.constants.enums.ResponseType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Builder
@AllArgsConstructor
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1546524853123L;

    @JsonProperty("type")
    private ResponseType responseType;

    @JsonProperty("message")
    private Collection<String> message;

    @JsonProperty("result")
    private Object result;

    @JsonProperty("error")
    private Object error;

    @JsonProperty("code")
    private String code;
}