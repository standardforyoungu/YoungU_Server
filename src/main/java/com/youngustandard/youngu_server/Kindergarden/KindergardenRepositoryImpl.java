package com.youngustandard.youngu_server.Kindergarden;

import com.youngustandard.youngu_server.Mapper.KindergardenMapper;
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

    @Override
    public int getRowNum_YS_REGN(int regn_cd) {
        return kindergardenMapper.count_row_num_YS_REGN(regn_cd);
    }
}
