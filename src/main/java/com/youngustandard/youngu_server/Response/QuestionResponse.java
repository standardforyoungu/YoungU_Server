package com.youngustandard.youngu_server.Response;

import com.youngustandard.youngu_server.Propensity.PropensityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private String result;
    private List<PropensityDTO> question_list;
}
