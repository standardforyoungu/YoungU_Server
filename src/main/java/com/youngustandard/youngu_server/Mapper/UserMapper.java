package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.User.ChildDTO;
import com.youngustandard.youngu_server.User.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<ChildDTO> find_children(String mbr_id);

    MemberDTO get_UserInfo(String mbrId);

    ChildDTO find_Max_Child_Id(ChildDTO childDTO);

    int insert_ChildInfo(ChildDTO childDTO);

    int delete_ChildInfo(ChildDTO childDTO);

    int update_ChildInfo(ChildDTO childDTO);
}
