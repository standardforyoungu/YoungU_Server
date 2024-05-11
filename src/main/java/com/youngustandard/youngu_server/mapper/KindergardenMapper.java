package com.youngustandard.youngu_server.mapper;

import com.youngustandard.youngu_server.kindergarden.KindergardenDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Mapper
public interface KindergardenMapper {
    List<KindergardenDTO> getKindergarden_list(HashMap<String, Object> param);

}
