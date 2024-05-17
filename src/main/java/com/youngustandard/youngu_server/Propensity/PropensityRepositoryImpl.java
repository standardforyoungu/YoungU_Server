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
}
