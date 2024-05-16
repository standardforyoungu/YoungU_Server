package com.youngustandard.youngu_server.User;

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
    public int update_Child_Info() {
        return 0;
    }

    @Override
    public int delete_Child_Info() {
        return 0;
    }

    @Override
    public MemberDTO get_UserInfo(String mbrId) {
        return userRepository.get_UserInfo(mbrId);
    }

    @Override
    public int find_Max_Child_Id(String mbrId) {
        return userRepository.find_Max_Child_Id(mbrId);
    }

    @Override
    public int insert_ChiildInfo(ChildDTO childDTO) {
        return userRepository.insert_ChildInfo(childDTO);
    }
}
