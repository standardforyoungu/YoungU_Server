package com.youngustandard.youngu_server.User;

import com.youngustandard.youngu_server.Mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private UserMapper userMapper;
    public UserRepositoryImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    @Override
    public List<ChildDTO> find_Children(String mbr_id) {
        return userMapper.find_children(mbr_id);
    }

    @Override
    public MemberDTO get_UserInfo(String mbrId) {
        return userMapper.get_UserInfo(mbrId);
    }

    @Override
    public int find_Max_Child_Id(String mbrId) {
        return userMapper.find_Max_Child_Id(mbrId);
    }

    @Override
    public int insert_ChildInfo(ChildDTO childDTO) {
        return userMapper.insert_ChildInfo(childDTO);
    }
}
