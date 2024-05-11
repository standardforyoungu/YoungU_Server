package com.youngustandard.youngu_server.kindergarden;

import java.util.List;

public interface KindergardenService {
    public List<KindergardenDTO> getKindergarden(String regn_cd,int offset);

}
