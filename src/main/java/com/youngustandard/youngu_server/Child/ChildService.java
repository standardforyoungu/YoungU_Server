package com.youngustandard.youngu_server.Child;

import java.util.List;

public interface ChildService {
    //조회
    List<ChildDTO> find_Children(String mbr_id);
    //저장
    int save_Child_Info();
    //수정
    int update_Child_Info();
    //삭제
    int delete_Child_Info();

}
