package com.youngustandard.youngu_server.Vote;

import java.util.concurrent.ConcurrentHashMap;

public interface VoteRepository {

    int save_Vote_Result(ConcurrentHashMap<String, Integer> map);
}
