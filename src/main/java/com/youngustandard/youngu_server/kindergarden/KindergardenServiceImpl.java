package com.youngustandard.youngu_server.kindergarden;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class KindergardenServiceImpl implements KindergardenService{

    private KindergardenRepository kindergardenRepository;
    public KindergardenServiceImpl(KindergardenRepository kindergardenRepository){
        this.kindergardenRepository=kindergardenRepository;
    }
    @Override
    public List<KindergardenDTO> getKindergarden(String regn_cd, int offset) {

        HashMap<String,Object> param = new HashMap<>();
        param.put("regn_cd",regn_cd);
        param.put("offest",offset);
        //List<KindergardenDTO> kindergardenDTOList = kindergardenRepository.getKindergarden(param);
        return kindergardenRepository.getKindergarden(param);
    }
}
