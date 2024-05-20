package com.youngustandard.youngu_server.Propensity;

import com.youngustandard.youngu_server.Exception.NotFoundException;
import com.youngustandard.youngu_server.Exception.ViolateRuleException;
import com.youngustandard.youngu_server.Response.DefaultResponse;
import com.youngustandard.youngu_server.Response.PropensityResponse;
import com.youngustandard.youngu_server.Response.QuestionResponse;
import com.youngustandard.youngu_server.User.ChildDTO;
import com.youngustandard.youngu_server.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
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

    @GetMapping("/youngustandard/propensity/question")
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
    @PostMapping("/youngustandard/propensity/result/{mbr_id}")
    public ResponseEntity<PropensityResponse> save_Result(@PathVariable String mbr_id, @RequestBody ResultDTO resultDTO){
        //검사 결과 저장하고. 해당 검사 결과에 따른 내역 가지고 있어야함.
        //1. 해당 아이가 존재하는지 여부 파악(회원 아이디, 아이 아이디 로 아이 테이블 조회)
        //  1-1. 해당 아이가 없다면 에러 발생.
        //  1-2. 해당 아이가 있다면 2로 이동
        //2. 서비스로 넘겨서 수행
        PropensityResponse propensityResponse = new PropensityResponse();
        int next_test_rslt_no = 1;
        resultDTO.setMbr_id(mbr_id);
        ChildDTO childDTO = userService.find_specific_child(resultDTO);
        if(childDTO == null ){
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
        propensityResponse.setResult("Success");
        propensityResponse.setMessage("검사 결과를 성공적으로 저장하였습니다.");
        propensityResponse.setChl_id(resultDTO.getChl_id());
        return new ResponseEntity<>(propensityResponse,httpHeaders, HttpStatus.OK);
    }

    //성향검사결과조회
    @GetMapping("/youngustandard/propensity/result/{mbr_id}/{chl_id}")
    public ResponseEntity<HashMap> search_Result(@PathVariable String mbr_id, @PathVariable String chl_id){
        //해당 아이의 성향을 가져와서
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setMbr_id(mbr_id);
        resultDTO.setChl_id(Integer.parseInt(chl_id));
        ChildDTO childDTO = userService.find_specific_child(resultDTO);
        if(childDTO == null ){
            throw new NotFoundException("해당 아이를 찾을 수 없습니다. 다시 시도해주시기 바랍니다.");
        }
        //해당 아이의 성향으로 그 성향 특징 가져오고
        PrpnsDataDTO prpnsDataDTO = propensityService.find_propensity(childDTO.getChl_mbti());
        //특징을 바탕으로 추천 영어 유치부를 가져온다
        if(prpnsDataDTO == null){
            throw new NotFoundException("해당 성향을 찾을 수 없어요. 다시 시도해주시기 바랍니다.");
        }
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("result","Success");
        hashMap.put("prpnsDataDTO",prpnsDataDTO);
        return new ResponseEntity<>(hashMap,httpHeaders, HttpStatus.OK);
    }

}
