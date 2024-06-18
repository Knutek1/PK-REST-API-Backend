package com.kodilla.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AirVisualConfig {

        @Value("${airvisual.api.endpoint.prod}")
        private String airVisualApiEndpoint;
        @Value("${airvisual.app.key}")
        private String airVisualAppKey;

}
