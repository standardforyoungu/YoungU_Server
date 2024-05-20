package com.youngustandard.youngu_server.Kindergarden;

import java.util.HashMap;
import java.util.List;

public interface KindergardenRepository {

    public List<KindergardenDTO> getKindergarden(HashMap<String,Object> param);
    public int getRowNum_YS_REGN(int regn_cd);

    List<KindergardenDTO> find_recommend_list(HashMap<String, String> map);
}
