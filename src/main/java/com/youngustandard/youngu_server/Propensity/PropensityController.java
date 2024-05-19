package com.youngustandard.youngu_server.Propensity;

import com.youngustandard.youngu_server.Exception.NotFoundException;
import com.youngustandard.youngu_server.Response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

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

    //성향검사 완료하기 버튼 클릭 시
    //성향검사 저장
    @PostMapping("/youngustandard/propensity")
    public void save_Result(@RequestBody Map<String, List<String>> results){
        //
        List<String> result_list = results.get("result_list");
        System.out.println(result_list);
//        if(result_list.size()!=20){
//            throw new NotFoundException("다시 시도해주시기 바랍니다.");
//        }
        propensityService.save_Result(result_list);
    }
}
