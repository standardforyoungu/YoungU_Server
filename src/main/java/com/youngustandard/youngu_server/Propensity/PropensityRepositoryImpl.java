package com.youngustandard.youngu_server.Propensity;

import com.youngustandard.youngu_server.Mapper.PropensityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropensityRepositoryImpl implements PropensityRepository{
    private PropensityMapper propensityMapper;
    public PropensityRepositoryImpl(PropensityMapper propensityMapper){
        this.propensityMapper=propensityMapper;
    }
    @Override
    public List<PropensityDTO> get_Question_List() {
        return propensityMapper.get_Question_List();
    }

    @Override
    //검사 결고 저장하기
    public int save_Result(ResultDTO resultDTO) {
        return propensityMapper.save_Result(resultDTO);
    }

    @Override
    //다음번 테스트 검사 결과 번호 찾기
    public ResultDTO find_next_test_rslt_no(ResultDTO resultDTO) {
        return propensityMapper.find_next_test_rslt_no(resultDTO);
    }
}
