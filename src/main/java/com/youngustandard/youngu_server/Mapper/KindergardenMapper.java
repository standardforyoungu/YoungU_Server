package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.Kindergarden.KindergardenDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface KindergardenMapper {
    List<KindergardenDTO> getFstKindergarden_list(String regn_cd);
    List<KindergardenDTO> getKindergarden_list(HashMap<String, Object> param);
    int count_row_num_YS_REGN(int regn_cd);
}
