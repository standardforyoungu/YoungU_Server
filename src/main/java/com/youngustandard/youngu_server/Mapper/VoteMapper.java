package com.youngustandard.youngu_server.Mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.concurrent.ConcurrentHashMap;

@Mapper
public interface VoteMapper {
    int save_Vote(ConcurrentHashMap<String, Integer> map);
}
