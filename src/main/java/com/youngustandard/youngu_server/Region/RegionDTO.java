package com.youngustandard.youngu_server.Region;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {
    private int regn_cd;
    private int city_cd;
    private String regn_nm;
    private String city_nm;
}
