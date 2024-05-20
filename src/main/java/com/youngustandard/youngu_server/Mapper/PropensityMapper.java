package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.Propensity.PropensityDTO;
import com.youngustandard.youngu_server.Propensity.PrpnsDataDTO;
import com.youngustandard.youngu_server.Propensity.ResultDTO;
import com.youngustandard.youngu_server.User.ChildDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropensityMapper {
    List<PropensityDTO> get_Question_List();

    int save_Result(ResultDTO resultDTO); // 검사결과 저장하기

    ResultDTO find_next_test_rslt_no(ResultDTO resultDTO); //다음번 테스트 검사 결과 번호 찾기

    PrpnsDataDTO find_propensity(ChildDTO childDTO);
}
