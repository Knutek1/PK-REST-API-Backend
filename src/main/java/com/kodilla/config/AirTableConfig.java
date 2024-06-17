package com.kodilla.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AirTableConfig {


    @Value("${airtable.api.endpoint.record}")
    private String airTableEndpointRecord;
    @Value("${airtable.api.endpoint.meta}")
    private String airTableEndpointMeta;
    @Value("${airtable.app.token}")
    private String airTableToken;

}
