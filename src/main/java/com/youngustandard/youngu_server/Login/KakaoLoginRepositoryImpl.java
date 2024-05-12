package com.youngustandard.youngu_server.Login;

import com.youngustandard.youngu_server.Kindergarden.KindergardenRepositoryImpl;
import com.youngustandard.youngu_server.Mapper.LoginMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class KakaoLoginRepositoryImpl implements LoginRepository{
    private LoginMapper loginMapper;
    public KakaoLoginRepositoryImpl(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }
    @Override
    public int cnt_user_num(String id) {
        return loginMapper.get_User_num(id);
    }

    @Override
    public int add_User(LoginDTO loginDTO) throws DuplicateKeyException {
        return loginMapper.add_User(loginDTO);
    }

    @Override
    public int add_User_Secret(LoginDTO loginDTO) {
        return loginMapper.add_User_Secret(loginDTO);
    }
}
