package com.youngustandard.youngu_server.User;

import com.youngustandard.youngu_server.Propensity.ResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<ChildDTO> find_Children(String mbr_id) {
        return userRepository.find_Children(mbr_id);
    }

    @Override
    public int save_Child_Info() {
        return 0;
    }

    @Override
    public int update_Child_Info(ChildDTO childDTO) {
        return userRepository.update_ChildInfo(childDTO);
    }

    @Override
    public int delete_Child_Info(ChildDTO childDTO) {
        return userRepository.delete_ChildInfo(childDTO);
    }

    @Override
    public MemberDTO get_UserInfo(String mbrId) {
        return userRepository.get_UserInfo(mbrId);
    }

    @Override
    public ChildDTO find_Max_Child_Id(ChildDTO childDTO) {
        return userRepository.find_Max_Child_Id(childDTO);
    }

    @Override
    public int insert_ChiildInfo(ChildDTO childDTO) {
        return userRepository.insert_ChildInfo(childDTO);
    }

    @Override
    public int find_specific_child(ResultDTO resultDTO) {
        return userRepository.find_specific_child(resultDTO);
    }
}
