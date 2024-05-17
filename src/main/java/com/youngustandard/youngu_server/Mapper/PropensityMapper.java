package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.Propensity.PropensityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropensityMapper {
    List<PropensityDTO> get_Question_List();
}
