package com.kodilla.dto.response.airVisual;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kodilla.service.ZonedDateTimeDeserializer;
import lombok.Getter;

@Getter
public class CityDataAirVisualResponse {

    private Data data;

    @Getter
    public static class Data {
        private String city;
        private String state;
        private String country;
        private Current current;
    }

    @Getter
    public static class Current {
        private Pollution pollution;
        private Weather weather;
    }

    @Getter
    public static class Pollution {
        @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
        private String ts;
        private int aqius;
        private String mainus;
    }

    @Getter
    public static class Weather {
        @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
        private String ts;
        private int tp;
        private int pr;
        private int hu;
        private double ws;
        private int wd;
        private String ic;
    }
}
