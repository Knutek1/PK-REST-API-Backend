package com.kodilla.dto.request.airTable;

import lombok.Getter;

@Getter
public enum AqiUsStatus {

    NIEZDROWA_DLA_OSOB_WRAZLIWYCH("Niezdrowa dla osób wrażliwych"),
    SREDNIA("Średnia"),
    DOBRA("Dobra"),
    NIEZDROWA("Niezdrowa");

    private final String status;
    AqiUsStatus(String status){
        this.status = status;
    }

}

