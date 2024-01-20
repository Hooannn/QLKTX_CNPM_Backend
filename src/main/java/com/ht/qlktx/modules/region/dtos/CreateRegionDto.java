package com.ht.qlktx.modules.region.dtos;

import com.ht.qlktx.enums.Sex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRegionDto {
    private String id;
    private Sex target;
    private String type;
}
