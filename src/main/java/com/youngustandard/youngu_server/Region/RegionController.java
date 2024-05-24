package com.youngustandard.youngu_server.Region;

import com.youngustandard.youngu_server.Response.RegionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {
    private static HttpHeaders httpHeaders = new HttpHeaders();
    private RegionService regionService;
    public RegionController(RegionService regionService){
        this.regionService=regionService;
    }

    @GetMapping("/youngustandard/regn")
    public ResponseEntity<RegionResponse> find_all_regn(){
        List<RegionDTO> regionDTOS = regionService.find_all_regn();
        RegionResponse regionResponse = new RegionResponse();
        regionResponse.setResult("Success");
        regionResponse.setRegion_list_size(regionDTOS.size());
        regionResponse.setRegion_list(regionDTOS);
        return new ResponseEntity<>(regionResponse,httpHeaders, HttpStatus.OK);
    }
}
