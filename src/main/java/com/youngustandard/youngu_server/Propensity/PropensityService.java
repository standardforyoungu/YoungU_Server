package com.youngustandard.youngu_server.Propensity;

import java.util.List;

public interface PropensityService {
    List<PropensityDTO> get_Questions();

    void save_Result(List<String> resultList);
}
