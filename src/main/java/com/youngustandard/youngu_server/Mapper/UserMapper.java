package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.User.ChildDTO;
import com.youngustandard.youngu_server.User.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<ChildDTO> find_children(String mbr_id);

    MemberDTO get_UserInfo(String mbrId);

    int find_Max_Child_Id(String mbrId);

    int insert_ChildInfo(ChildDTO childDTO);
}
