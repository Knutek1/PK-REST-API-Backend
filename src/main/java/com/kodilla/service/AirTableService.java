package com.kodilla.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kodilla.config.AirTableConfig;
import com.kodilla.dto.request.airTable.AddRecordsAirTableRequest;
import com.kodilla.dto.request.airTable.AqiUsStatus;
import com.kodilla.dto.response.airTable.BasesAirTableResponse;
import com.kodilla.dto.response.airTable.RecordsAirTableResponse;
import com.kodilla.dto.response.airTable.TablesAirTableResponse;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirTableService {
    private final OkHttpClient httpClient = new OkHttpClient();
    private final AirTableConfig airTableConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getUserId() throws IOException {
        Request request = new Request.Builder()
                .url(airTableConfig.getAirTableEndpointRecord() + "/meta/whoami")
                .header("Authorization", "Bearer " + airTableConfig.getAirTableToken())
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public BasesAirTableResponse getBases() throws IOException {
        Request request = new Request.Builder()
                .url(airTableConfig.getAirTableEndpointMeta())
                .header("Authorization", "Bearer " + airTableConfig.getAirTableToken())
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return objectMapper.readValue(response.body().string(), BasesAirTableResponse.class);
        }
    }

    public TablesAirTableResponse getTables(String baseId) throws IOException {
        Request request = new Request.Builder()
                .url(airTableConfig.getAirTableEndpointMeta() + "/" + baseId + "/tables")
                .header("Authorization", "Bearer " + airTableConfig.getAirTableToken())
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY).readValue(response.body().string(), TablesAirTableResponse.class);
        }
    }

    public RecordsAirTableResponse getRecords(String baseId, String tableId) throws IOException {
        Request request = new Request.Builder()
                .url(airTableConfig.getAirTableEndpointRecord() + "/" + baseId + "/" + tableId)
                .header("Authorization", "Bearer " + airTableConfig.getAirTableToken())
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return objectMapper.readValue(response.body().string(), RecordsAirTableResponse.class);
        }
    }

    public RecordsAirTableResponse postRecords(String baseId, String tableId, AddRecordsAirTableRequest addRecordsAirTableRequest) throws IOException {
        JsonNode jsonNode = objectMapper.valueToTree(addRecordsAirTableRequest);

        if (jsonNode.has("records")) {
            for (JsonNode recordNode : jsonNode.get("records")) {
                if (recordNode.has("fields")) {
                    ObjectNode fieldsNode = (ObjectNode) recordNode.get("fields");
                    int pomiarAQIUS = fieldsNode.get("Pomiar AQIUS").asInt();
                    String jakosc;
                    if(pomiarAQIUS < 51)
                        jakosc = AqiUsStatus.DOBRA.getStatus();
                    else if (pomiarAQIUS<101)
                        jakosc = AqiUsStatus.SREDNIA.getStatus();
                    else if (pomiarAQIUS<151)
                        jakosc = AqiUsStatus.NIEZDROWA_DLA_OSOB_WRAZLIWYCH.getStatus();
                    else
                        jakosc = AqiUsStatus.NIEZDROWA.getStatus();
                    fieldsNode.put("Jakość", jakosc);
                }
            }
        }

        String jsonBody = objectMapper.writeValueAsString(jsonNode);
        RequestBody requestBody = RequestBody.create(jsonBody, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(airTableConfig.getAirTableEndpointRecord() + "/" + baseId + "/" + tableId)
                .header("Authorization", "Bearer " + airTableConfig.getAirTableToken())
                .post(requestBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return objectMapper.readValue(response.body().string(), RecordsAirTableResponse.class);
        }
    }
}
