package com.youngustandard.youngu_server.Propensity;

import com.youngustandard.youngu_server.User.ChildDTO;

import java.util.List;

public interface PropensityService {
    List<PropensityDTO> get_Questions();

    //검사 결과 저장
    int save_Result(ResultDTO resultDTO);

    ////다음번 테스트 검사 결과 번호 찾기
    ResultDTO find_next_test_rslt_no(ResultDTO resultDTO);

    PrpnsDataDTO find_propensity(String prppns_data);
}
