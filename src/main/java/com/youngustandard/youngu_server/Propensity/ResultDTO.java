package com.youngustandard.youngu_server.Propensity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private String mbr_id;
    private int chl_id;
    private List<String> result_list;
    private String test_rslt;

    private int test_rslt_no;

}
