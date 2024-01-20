package com.ht.qlktx.modules.rooms.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomDto {
    private String id;

    private int capacity;

    @JsonProperty("region_id")
    private String regionId;
}
