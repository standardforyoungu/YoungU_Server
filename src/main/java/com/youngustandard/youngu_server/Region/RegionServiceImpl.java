package com.youngustandard.youngu_server.Region;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService{
    private RegionRepository regionRepository;
    public RegionServiceImpl(RegionRepository regionRepository){
        this.regionRepository=regionRepository;
    }
    @Override
    public List<RegionDTO> find_all_regn() {
        System.out.println("regionRepository = " +regionRepository);
        return regionRepository.find_all_regn();
    }
}
