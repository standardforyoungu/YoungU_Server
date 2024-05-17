package com.youngustandard.youngu_server.Propensity;

import com.youngustandard.youngu_server.Response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;

@RestController

public class PropensityController {
    private static HttpHeaders httpHeaders = new HttpHeaders();

    private final PropensityService propensityService;
    public PropensityController(PropensityService propensityService){
        this.propensityService = propensityService;
    }

    @GetMapping("/youngustandard/propensity")
    public ResponseEntity<QuestionResponse> search_Question(){
        List<PropensityDTO> propensityDTOS = propensityService.get_Questions();
        QuestionResponse questionResponse = new QuestionResponse();

        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        questionResponse.setResult("Success");
        questionResponse.setQuestion_list(propensityDTOS);

        return new ResponseEntity<>(questionResponse,httpHeaders, HttpStatus.OK);
    }
}
