package com.youngustandard.youngu_server.Region;

import com.youngustandard.youngu_server.Mapper.RegionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionRepositoryImpl implements RegionRepository{

    private RegionMapper regionMapper;
    public RegionRepositoryImpl(RegionMapper regionMapper){
        this.regionMapper = regionMapper;
    }
    @Override
    public List<RegionDTO> find_all_regn() {
        return regionMapper.find_all_regn();
    }
}
