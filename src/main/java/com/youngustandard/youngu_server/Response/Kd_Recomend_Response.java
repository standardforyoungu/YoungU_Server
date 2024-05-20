package com.youngustandard.youngu_server.Response;

import com.youngustandard.youngu_server.Kindergarden.KindergardenDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kd_Recomend_Response {
    private String result;
    private List<KindergardenDTO> engl_kd_clas_list;
}
