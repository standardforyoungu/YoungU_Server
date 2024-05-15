package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.Login.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LoginMapper {
    int get_User_num(String id);

    int add_User(LoginDTO loginDTO);

    int add_User_Secret(LoginDTO loginDTO);
}
