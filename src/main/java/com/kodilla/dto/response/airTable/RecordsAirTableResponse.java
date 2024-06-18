package com.kodilla.dto.response.airTable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kodilla.service.ZonedDateTimeDeserializer;
import lombok.Getter;

import java.util.List;

@Getter
public class RecordsAirTableResponse {

    private List<Record> records;
    @Getter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Record {
        private String id;
        @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
        private String createdTime;
        private Fields fields;
    }
    @Getter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Fields {
        @JsonProperty("Jakość")
        private String jakosc;

        @JsonProperty("Pomiar AQIUS")
        private int pomiarAQIUS;

        @JsonProperty("Miasto")
        private String miasto;

        @JsonProperty("Start date")
        @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
        private String startDate;

        @JsonProperty("Temperatura")
        private int temperatura;
    }
}
