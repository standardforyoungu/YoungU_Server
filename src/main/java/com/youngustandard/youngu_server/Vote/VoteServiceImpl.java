package com.youngustandard.youngu_server.Vote;

import com.youngustandard.youngu_server.Exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteServiceImpl implements VoteService{
    private VoteRepository voteRepository;
    public VoteServiceImpl(VoteRepository voteRepository){
        this.voteRepository=voteRepository;
    }
    @Override
    @Transactional
    public void save_Vote_Result(VoteDTO voteDTO) {
        for(List<String> a : voteDTO.getInfo()){
            ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
            map.put("regn_cd",Integer.parseInt(a.get(0)));
            map.put("city_cd",Integer.parseInt(a.get(1)));
            int proceed_result = voteRepository.save_Vote_Result(map);
            if(proceed_result<1){
                throw new NotFoundException("잠시 후 다시 시도해 주시기 바랍니다.");
            }
        }
    }
}
