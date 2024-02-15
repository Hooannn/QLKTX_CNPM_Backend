package com.ht.qlktx.modules.room.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.qlktx.enums.RoomStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRoomDto {
    private String id;

    @JsonProperty("region_id")
    private String regionId;

    @JsonProperty("type_id")
    private Long typeId;

    private RoomStatus status;
}
