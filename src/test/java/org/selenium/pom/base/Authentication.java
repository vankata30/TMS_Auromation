package org.selenium.pom.base;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Authentication {
    @JsonProperty("AccessToken")
    private String accessToken;

    @JsonProperty("IdToken")
    private String idToken;

    @JsonProperty("RefreshToken")
    private String refreshToken;
}
