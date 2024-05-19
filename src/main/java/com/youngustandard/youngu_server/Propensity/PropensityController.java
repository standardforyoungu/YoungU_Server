package com.youngustandard.youngu_server.Propensity;

import com.youngustandard.youngu_server.Exception.NotFoundException;
import com.youngustandard.youngu_server.Exception.ViolateRuleException;
import com.youngustandard.youngu_server.Response.DefaultResponse;
import com.youngustandard.youngu_server.Response.QuestionResponse;
import com.youngustandard.youngu_server.User.UserService;
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
    private final UserService userService;
    public PropensityController(PropensityService propensityService, UserService userService){
        this.propensityService = propensityService;
        this.userService = userService;
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
    @PostMapping("/youngustandard/propensity/{mbr_id}")
    public ResponseEntity<DefaultResponse> save_Result(@PathVariable String mbr_id, @RequestBody ResultDTO resultDTO){
        //검사 결과 저장하고. 해당 검사 결과에 따른 내역 가지고 있어야함.
        //1. 해당 아이가 존재하는지 여부 파악(회원 아이디, 아이 아이디 로 아이 테이블 조회)
        //  1-1. 해당 아이가 없다면 에러 발생.
        //  1-2. 해당 아이가 있다면 2로 이동
        //2. 서비스로 넘겨서 수행
        DefaultResponse defaultResponse = new DefaultResponse();
        int next_test_rslt_no = 1;
        resultDTO.setMbr_id(mbr_id);
        int find_child = userService.find_specific_child(resultDTO);
        if(find_child == 0 ){
            throw new NotFoundException("해당 아이를 찾을 수 없습니다. 다시 시도해주시기 바랍니다.");
        }
        //다음번 테스트 검사 결과 번호 찾기
        ResultDTO resultDTO_1 = propensityService.find_next_test_rslt_no(resultDTO);
        if(resultDTO_1 != null){
            next_test_rslt_no = resultDTO_1.getTest_rslt_no()+1;
        }
        resultDTO.setTest_rslt_no(next_test_rslt_no);
        int proceed_result = propensityService.save_Result(resultDTO);
        if(proceed_result<1){
            throw new ViolateRuleException("잠시후 다시 시도해주시기 바랍니다.");
        }
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        defaultResponse.setResult("Success");
        defaultResponse.setMessage("검사 결과를 성공적으로 저장하였습니다.");

        return new ResponseEntity<>(defaultResponse,httpHeaders, HttpStatus.OK);
    }
}
