package com.youngustandard.youngu_server.Propensity;

import com.youngustandard.youngu_server.Exception.NotFoundException;
import com.youngustandard.youngu_server.Exception.ViolateRuleException;
import com.youngustandard.youngu_server.User.ChildDTO;
import com.youngustandard.youngu_server.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PropensityServiceImpl implements PropensityService{

    private PropensityRepository propensityRepository;
    private UserRepository userRepository;
    public PropensityServiceImpl(PropensityRepository propensityRepository, UserRepository userRepository){
        this.propensityRepository=propensityRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<PropensityDTO> get_Questions() {
        return propensityRepository.get_Question_List();
    }

    @Override
    //검사 결과 저장.
    public int save_Result(ResultDTO resultDTO) {
        List<String> result_list = resultDTO.getResult_list();
        String test_result = "";
        int window_Size = 5;
        for(int i = 0 ; i < result_list.size() ; i += window_Size){
            List<String> sub_result_list = result_list.subList(i,i+window_Size);
            test_result += findMostFrequentResult(sub_result_list);
        }

        resultDTO.setTest_rslt(test_result);
        //검사결과에도 저장.
        //아이 정보도 update 해줘야함.
        int proceed_result = propensityRepository.save_Result(resultDTO);
        if(proceed_result<1){
            throw new ViolateRuleException("잠시 후 다시 시도해주시기 바랍니다.");
        }
        proceed_result = userRepository.update_Child_Result(resultDTO);
        if(proceed_result<1){
            throw new ViolateRuleException("잠시 후 다시 시도해주시기 바랍니다.");
        }
        return proceed_result;
    }

    @Override
    //다음번 테스트 검사 결과 번호 찾기
    public ResultDTO find_next_test_rslt_no(ResultDTO resultDTO) {
        return propensityRepository.find_next_test_rslt_no(resultDTO);
    }

    @Override
    public PrpnsDataDTO find_propensity(ChildDTO childDTO) {
        return propensityRepository.find_propensity(childDTO);
    }

    //가장 많이 출현하는 성향 찾는 함수
    private String findMostFrequentResult(List<String> subResultList) {
        String result = null;
        int frequent_Num = 0;
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(String element : subResultList){
            hashMap.put(element,hashMap.getOrDefault(element,0)+1);
        }
        for(String key : hashMap.keySet()){
            if(frequent_Num<hashMap.get(key)){
                frequent_Num = hashMap.get(key);
                result = key;
            }
        }
        return result;
    }
}
