package com.youngustandard.youngu_server.Propensity;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropensityServiceImpl implements PropensityService{

    private PropensityRepository propensityRepository;
    public PropensityServiceImpl(PropensityRepository propensityRepository){
        this.propensityRepository=propensityRepository;
    }
    @Override
    public List<PropensityDTO> get_Questions() {
        return propensityRepository.get_Question_List();
    }

    @Override
    public void save_Result(List<String> resultList) {

    }
}
