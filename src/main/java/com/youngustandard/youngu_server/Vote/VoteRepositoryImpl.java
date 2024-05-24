package com.youngustandard.youngu_server.Vote;

import com.youngustandard.youngu_server.Mapper.VoteMapper;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class VoteRepositoryImpl implements VoteRepository{
    private VoteMapper voteMapper;
    public VoteRepositoryImpl(VoteMapper voteMapper){
        this.voteMapper=voteMapper;
    }
    @Override
    public int save_Vote_Result(ConcurrentHashMap<String, Integer> map) {
        return voteMapper.save_Vote(map);
    }
}
