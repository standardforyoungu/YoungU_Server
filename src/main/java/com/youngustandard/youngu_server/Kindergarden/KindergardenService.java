package com.youngustandard.youngu_server.Kindergarden;

import com.youngustandard.youngu_server.Propensity.PrpnsDataDTO;

import java.util.List;

public interface KindergardenService {

    List<KindergardenDTO> getKindergarden(String regn_cd,int offset);
    int row_cnum_YS_REGN(String regn_cd);

    List<KindergardenDTO> find_recommend_list(PrpnsDataDTO prpnsDataDTO);
}
