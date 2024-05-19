package com.youngustandard.youngu_server.Propensity;

import java.util.List;

public interface PropensityRepository {
    List<PropensityDTO> get_Question_List();

    int save_Result(ResultDTO resultDTO);

    ResultDTO find_next_test_rslt_no(ResultDTO resultDTO);//다음번 테스트 검사 결과 번호 찾기
}
