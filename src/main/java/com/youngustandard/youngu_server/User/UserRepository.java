package com.youngustandard.youngu_server.User;

import java.util.List;

public interface UserRepository {
    List<ChildDTO> find_Children(String mbr_id);

    MemberDTO get_UserInfo(String mbrId);

    int find_Max_Child_Id(String mbrId);

    int insert_ChildInfo(ChildDTO childDTO);

    int delete_ChildInfo(ChildDTO childDTO);
}
