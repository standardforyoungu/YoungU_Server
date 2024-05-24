package com.youngustandard.youngu_server.Login;

import java.util.HashMap;

public interface LoginRepository {
    int cnt_user_num(String id);

    int add_User(LoginDTO loginDTO);

    int add_User_Secret(LoginDTO loginDTO);

    void delete_YS_MEMBER(String mbrId);

    void delete_YS_REVIEW(String mbrId);

    void delete_YS_TEST_RESULT(String mbrId);
}
