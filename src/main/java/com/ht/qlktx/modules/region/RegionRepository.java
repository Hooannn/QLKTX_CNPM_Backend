package com.ht.qlktx.modules.region;

import com.ht.qlktx.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, String> {
    List<Region> findByIdContainingIgnoreCase(String keyword);
}
