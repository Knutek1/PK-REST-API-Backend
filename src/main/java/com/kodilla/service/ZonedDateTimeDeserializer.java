package com.kodilla.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String utcDateTime = p.getText();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(utcDateTime, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        ZoneId localZoneId = ZoneId.of("Europe/Warsaw");
        ZonedDateTime localDateTime = zonedDateTime.withZoneSameInstant(localZoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        return localDateTime.format(formatter);
    }
}
