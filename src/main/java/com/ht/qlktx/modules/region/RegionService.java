package com.ht.qlktx.modules.region;

import com.ht.qlktx.config.HttpException;
import com.ht.qlktx.entities.Region;
import com.ht.qlktx.modules.region.dtos.CreateRegionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    public Region findById(String id) {
        return regionRepository.findById(id).orElseThrow(() -> new HttpException("Region not found", HttpStatus.NOT_FOUND));
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region create(CreateRegionDto createRegionDto) {
        var region = Region.builder()
                .id(createRegionDto.getId())
                .target(createRegionDto.getTarget())
                .type(createRegionDto.getType())
                .build();

        return regionRepository.save(region);
    }
}
