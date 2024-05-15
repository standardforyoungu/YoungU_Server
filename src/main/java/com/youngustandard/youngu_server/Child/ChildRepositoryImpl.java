package com.youngustandard.youngu_server.Child;

import com.youngustandard.youngu_server.Mapper.ChildMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChildRepositoryImpl implements ChildRepository{
    private ChildMapper childMapper;
    public ChildRepositoryImpl(ChildMapper childMapper){
        this.childMapper=childMapper;
    }
    @Override
    public List<ChildDTO> find_Children(String mbr_id) {
        return childMapper.find_children(mbr_id);
    }
}
