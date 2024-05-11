package com.youngustandard.youngu_server.kindergarden;

import java.util.HashMap;
import java.util.List;

public interface KindergardenRepository {

    public List<KindergardenDTO> getKindergarden(HashMap<String,Object> param);
}
