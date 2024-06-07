package com.kodilla.dto.response;

import java.util.List;

public record StatesAirVisualResponse(List<State> data) {
    public record State(String state) {
    }
}
