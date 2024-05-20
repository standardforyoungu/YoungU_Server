package com.youngustandard.youngu_server.Kindergarden;

import com.youngustandard.youngu_server.Propensity.PrpnsDataDTO;
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
        param.put("offset",offset);
        return kindergardenRepository.getKindergarden(param);
    }

    @Override
    public int row_cnum_YS_REGN(String regn_cd) {
        return kindergardenRepository.getRowNum_YS_REGN(Integer.parseInt(regn_cd));
    }

    @Override
    public List<KindergardenDTO> find_recommend_list(PrpnsDataDTO prpnsDataDTO) {
        HashMap<String,String> map = new HashMap<>();
        map.put("prmr_lrn_mthd", prpnsDataDTO.getPrmr_lrn_mthd());
        map.put("post_lrn_mthd", prpnsDataDTO.getPost_lrn_mthd());
        return kindergardenRepository.find_recommend_list(map);
    }
}
