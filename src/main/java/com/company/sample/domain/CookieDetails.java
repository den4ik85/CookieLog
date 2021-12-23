package com.company.sample.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@EqualsAndHashCode
public class CookieDetails {

    private String cookieId;
    private LocalDateTime timestamp;

}
