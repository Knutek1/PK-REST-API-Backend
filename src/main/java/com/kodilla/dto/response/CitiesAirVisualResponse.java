package com.kodilla.dto.response;

import java.util.List;

public record CitiesAirVisualResponse(List<City> data) {
    public record City(String city) {
    }
}
