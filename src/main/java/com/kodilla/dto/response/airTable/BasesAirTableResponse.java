package com.kodilla.dto.response.airTable;

import java.util.List;

public record BasesAirTableResponse (List <Base> bases){
    public record Base(String id, String name, String permissionLevel){

    }
}

