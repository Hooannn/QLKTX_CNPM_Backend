package com.ht.qlktx.modules.region;

import com.ht.qlktx.entities.Region;
import com.ht.qlktx.modules.region.dtos.CreateRegionDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/api/v1/regions")
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    public ResponseEntity<Region> create(@Valid @RequestBody CreateRegionDto createRegionDto) {
        var region = regionService.create(createRegionDto);
        return ResponseEntity.created(null).body(region);
    }

    @GetMapping
    public ResponseEntity<List<Region>> findAll() {
        var regions = regionService.findAll();
        return ResponseEntity.ok(regions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> findById(@PathVariable String id) {
        var region = regionService.findById(id);
        return ResponseEntity.ok(region);
    }
}
