package com.ht.qlktx.modules.booking.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingRequestDto {
    @NotNull(message = "Thời gian đặt phòng không được để trống")
    @JsonProperty("booking_time_id")
    private Long bookingTimeId;

    @NotEmpty(message = "Mã phòng không được để trống")
    @JsonProperty("room_id")
    private String roomId;
}
