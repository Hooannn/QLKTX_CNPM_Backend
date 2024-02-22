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
        return regionRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new HttpException("Không tìm thấy dãy phòng với mã phòng này", HttpStatus.NOT_FOUND));
    }

    public List<Region> findAll() {
        return regionRepository.findAllByDeletedIsFalse();
    }

    public Region create(CreateRegionDto createRegionDto) {
        if (regionRepository.existsById(createRegionDto.getId()) || regionRepository.existsByName(createRegionDto.getName())) {
            throw new HttpException("Dãy phòng hoặc tên phòng đã tồn tại", HttpStatus.BAD_REQUEST);
        }

        var region = Region.builder()
                .id(createRegionDto.getId())
                .name(createRegionDto.getName())
                .sex(createRegionDto.getSex())
                .build();

        return regionRepository.save(region);
    }

    public void delete(String id) {
        var region = findById(id);
        if (!region.getRooms().isEmpty()) {
            throw new HttpException("Không thể xóa dãy phòng này vì có phòng trong dãy phòng", HttpStatus.BAD_REQUEST);
        }
        region.setDeleted(true);
        regionRepository.save(region);
    }

    public List<Region> lookUpById(String keyword) {
        return regionRepository.findByIdContainingIgnoreCaseAndDeletedIsFalse(keyword);
    }
}
