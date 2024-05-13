package com.youngustandard.youngu_server.Child;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService{
    private ChildRepository childRepository;
    public ChildServiceImpl(ChildRepository childRepository){
        this.childRepository=childRepository;
    }
    @Override
    public List<ChildDTO> find_Children(String mbr_id) {
        return childRepository.find_Children(mbr_id);
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
}
