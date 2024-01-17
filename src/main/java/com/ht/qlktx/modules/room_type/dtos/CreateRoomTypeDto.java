package com.ht.qlktx.modules.room_type.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateRoomTypeDto {
    private String name;

    @JsonProperty("max_people")
    private int maxPeople;

    private int price;
}
