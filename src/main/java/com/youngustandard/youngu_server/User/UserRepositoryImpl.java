package com.youngustandard.youngu_server.User;

import com.youngustandard.youngu_server.Mapper.UserMapper;
import com.youngustandard.youngu_server.Propensity.ResultDTO;
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
    public ChildDTO find_Max_Child_Id(ChildDTO childDTO) {
        return userMapper.find_Max_Child_Id(childDTO);
    }

    @Override
    public int insert_ChildInfo(ChildDTO childDTO) {
        return userMapper.insert_ChildInfo(childDTO);
    }

    @Override
    public int delete_ChildInfo(ChildDTO childDTO) {
        return userMapper.delete_ChildInfo(childDTO);
    }

    @Override
    public int update_ChildInfo(ChildDTO childDTO) {
        return userMapper.update_ChildInfo(childDTO);
    }

    @Override
    public ChildDTO find_specific_child(ResultDTO resultDTO) {
        return userMapper.find_specific_child(resultDTO);
    }

    @Override
    public int update_Child_Result(ResultDTO resultDTO) {
        return userMapper.update_Child_Result(resultDTO);
    }

    @Override
    public int count_children(ChildDTO childDTO) {
        return userMapper.count_children(childDTO);
    }
}
