package com.kodilla.service;

import com.kodilla.config.AirVisualConfig;
import com.kodilla.dto.response.airVisual.CitiesAirVisualResponse;
import com.kodilla.dto.response.airVisual.CityDataAirVisualResponse;
import com.kodilla.dto.response.airVisual.StatesAirVisualResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class AirVisualService {

    private final RestTemplate restTemplate;
    private final AirVisualConfig airVisualConfig;

    public StatesAirVisualResponse getAirVisualStates() {

        String uri = UriComponentsBuilder.fromHttpUrl(airVisualConfig.getAirVisualApiEndpoint() + "/states")
                .queryParam("country", "Poland")
                .queryParam("key", airVisualConfig.getAirVisualAppKey())
                .toUriString();

        return restTemplate.getForObject(uri, StatesAirVisualResponse.class);
    }

    public CityDataAirVisualResponse getAirVisualsNearestCityData() {

        String uri = UriComponentsBuilder.fromHttpUrl(airVisualConfig.getAirVisualApiEndpoint() + "/nearest_city")
                .queryParam("key", airVisualConfig.getAirVisualAppKey())
                .toUriString();

        return restTemplate.getForObject(uri, CityDataAirVisualResponse.class);
    }

    public CitiesAirVisualResponse getAirVisualCities(String state) {

        String uri = UriComponentsBuilder.fromHttpUrl(airVisualConfig.getAirVisualApiEndpoint() + "/cities")
                .queryParam("state", URLEncoder.encode(state, StandardCharsets.UTF_8))
                .queryParam("country", "Poland")
                .queryParam("key", airVisualConfig.getAirVisualAppKey())
                .toUriString();

        return restTemplate.getForObject(uri, CitiesAirVisualResponse.class);
    }

    public CityDataAirVisualResponse getAirVisualCityData(String city, String state) {

        String uri = UriComponentsBuilder.fromHttpUrl(airVisualConfig.getAirVisualApiEndpoint() + "/city")
                .queryParam("state", URLEncoder.encode(state, StandardCharsets.UTF_8))
                .queryParam("city", URLEncoder.encode(city, StandardCharsets.UTF_8))
                .queryParam("country", "Poland")
                .queryParam("key", airVisualConfig.getAirVisualAppKey())
                .toUriString();

        return restTemplate.getForObject(uri, CityDataAirVisualResponse.class);
    }
}

