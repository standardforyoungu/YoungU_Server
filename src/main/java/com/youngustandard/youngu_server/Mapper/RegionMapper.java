package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.Region.RegionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionMapper {
    List<RegionDTO> find_all_regn();
}
