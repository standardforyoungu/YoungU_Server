package com.youngustandard.youngu_server.kindergarden;

import com.youngustandard.youngu_server.mapper.KindergardenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class KindergardenRepositoryImpl implements KindergardenRepository{

    private KindergardenMapper kindergardenMapper;
    public KindergardenRepositoryImpl(KindergardenMapper kindergardenMapper){
        this.kindergardenMapper = kindergardenMapper;
    }

    @Override
    public List<KindergardenDTO> getKindergarden(HashMap<String, Object> param) {
        return kindergardenMapper.getKindergarden_list(param);
    }
}
