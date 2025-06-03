package com.gonzalodev.saiyajinstore.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppUrlProperties {

    @Value("${app.backend.base-url}")
    private String backendBaseUrl;

    @Value("${app.frontend.base-url}")
    private String frontendBaseUrl;
}
