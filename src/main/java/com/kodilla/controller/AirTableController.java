package com.kodilla.controller;

import com.kodilla.dto.request.airTable.AddRecordsAirTableRequest;
import com.kodilla.dto.response.airTable.BasesAirTableResponse;
import com.kodilla.dto.response.airTable.RecordsAirTableResponse;
import com.kodilla.dto.response.airTable.TablesAirTableResponse;
import com.kodilla.service.AirTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class AirTableController {
    private final AirTableService airTableService;
    @GetMapping("/user_id")
    public ResponseEntity<String> getUserId() throws IOException {
        return ResponseEntity.ok(airTableService.getUserId());
    }
    @GetMapping("/bases")
    public ResponseEntity<BasesAirTableResponse> getBases() throws IOException {
        return ResponseEntity.ok(airTableService.getBases());
    }
    @GetMapping("/tables/{baseId}")
    public ResponseEntity<TablesAirTableResponse> getTables(@PathVariable String baseId) throws IOException {
        return ResponseEntity.ok(airTableService.getTables(baseId));
    }
    @GetMapping("/records/{baseId}/{tableId}")
    public ResponseEntity<RecordsAirTableResponse> getRecords(@PathVariable String baseId, @PathVariable String tableId) throws IOException {
        return ResponseEntity.ok(airTableService.getRecords(baseId,tableId));
    }
    @PostMapping("/records/{baseId}/{tableId}")
    public ResponseEntity<RecordsAirTableResponse> postRecords(@PathVariable String baseId, @PathVariable String tableId,@RequestBody AddRecordsAirTableRequest recordsAirTableRequest) throws IOException {
        return ResponseEntity.ok(airTableService.postRecords(baseId,tableId,recordsAirTableRequest));
    }
}
