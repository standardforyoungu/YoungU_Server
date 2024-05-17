package com.youngustandard.youngu_server.Propensity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropensityDTO {
    private int test_qstn_id;
    private String test_qstn_cntnt;
    private String chc1_prpns;
    private String chc1_cntnt;
    private String chc2_prpns;
    private String chc2_cntnt;
}
