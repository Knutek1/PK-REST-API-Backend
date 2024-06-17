package com.kodilla.dto.response.airTable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


public record TablesAirTableResponse(List<Table> tables) {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
        public record Table(String id, String name, List<Field> fields) {
        }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
        public record Field(String type, String id, String name, Options options) {
        }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
        public record Options(List<Choice> choices) {
        }
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public record Choice(String id, String name, String color) {
        }
    }