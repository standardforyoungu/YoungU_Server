package com.youngustandard.youngu_server.User;

import java.util.List;

public interface UserRepository {
    List<ChildDTO> find_Children(String mbr_id);

    MemberDTO get_UserInfo(String mbrId);

    ChildDTO find_Max_Child_Id(ChildDTO childDTO);

    int insert_ChildInfo(ChildDTO childDTO);

    int delete_ChildInfo(ChildDTO childDTO);

    int update_ChildInfo(ChildDTO childDTO);
}
