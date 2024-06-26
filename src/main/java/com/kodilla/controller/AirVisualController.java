package com.kodilla.controller;

import com.kodilla.dto.response.airVisual.CitiesAirVisualResponse;
import com.kodilla.dto.response.airVisual.CityDataAirVisualResponse;
import com.kodilla.dto.response.airVisual.StatesAirVisualResponse;
import com.kodilla.service.AirVisualService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/Poland")
@RequiredArgsConstructor
public class AirVisualController {

    private final AirVisualService airVisualService;

    @GetMapping("/states")
    public ResponseEntity<StatesAirVisualResponse> getAirVisualStates() {
        return ResponseEntity.ok(airVisualService.getAirVisualStates());
    }

    @GetMapping("/nearest_city")
    public ResponseEntity<CityDataAirVisualResponse> getAirVisualNearestCityData() {
        return ResponseEntity.ok(airVisualService.getAirVisualsNearestCityData());
    }

    @GetMapping("/cities")
    public ResponseEntity<CitiesAirVisualResponse> getAirVisualCities(@RequestParam String state){
        return ResponseEntity.ok(airVisualService.getAirVisualCities(state));
    }

    @GetMapping("/city_data")
    public ResponseEntity<CityDataAirVisualResponse> getAirVisualCityData(@RequestParam String city, @RequestParam String state){
        return ResponseEntity.ok(airVisualService.getAirVisualCityData(city,state));
    }
}
