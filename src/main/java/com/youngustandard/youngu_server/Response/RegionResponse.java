package com.youngustandard.youngu_server.Response;

import com.youngustandard.youngu_server.Region.RegionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponse {
    private String result;
    private int region_list_size;
    private List<RegionDTO> region_list;
}
