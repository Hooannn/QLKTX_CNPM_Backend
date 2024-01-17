package com.ht.qlktx.modules.rooms.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomDto {
    private String name;

    @JsonProperty("region_id")
    private String regionId;

    @JsonProperty("room_type_id")
    private String roomTypeId;
}
