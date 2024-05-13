package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.Child.ChildDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChildMapper {
    List<ChildDTO> find_children(String mbr_id);
}
