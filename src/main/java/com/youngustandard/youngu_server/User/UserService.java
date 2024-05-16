package com.youngustandard.youngu_server.User;

import java.util.List;

public interface UserService {
    //조회
    List<ChildDTO> find_Children(String mbr_id);
    //저장
    int save_Child_Info();
    //수정
    int update_Child_Info();
    //삭제
    int delete_Child_Info(ChildDTO childDTO);

    MemberDTO get_UserInfo(String mbrId);

    int find_Max_Child_Id(String mbrId);

    int insert_ChiildInfo(ChildDTO childDTO);
}
