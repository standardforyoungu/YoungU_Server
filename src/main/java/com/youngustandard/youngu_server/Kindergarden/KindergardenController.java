package com.youngustandard.youngu_server.Kindergarden;

import com.youngustandard.youngu_server.Exception.NotFoundException;
import com.youngustandard.youngu_server.Propensity.PropensityService;
import com.youngustandard.youngu_server.Propensity.PrpnsDataDTO;
import com.youngustandard.youngu_server.Response.Kd_Clas_Response;
import com.youngustandard.youngu_server.Response.Kd_Recomend_Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KindergardenController {

    private final KindergardenService kindergardenService;
    private final PropensityService propensityService;

    //영어 유치부 리스트 조회
    @GetMapping("/youngustandard/{regn_cd}/{city_cd}/{page_num}")
    public ResponseEntity<Object> find_kindergardens(@PathVariable String regn_cd,@PathVariable String city_cd, @PathVariable String page_num){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        HashMap<String, Integer> map = new HashMap<>();
        map.put("regn_cd",Integer.parseInt(regn_cd));
        map.put("city_cd",Integer.parseInt(city_cd));
        //없는 지역코드일 경우 에러 발생
        int count_row = kindergardenService.row_cnum_YS_REGN(map);

        if(count_row <= 0){
            throw new NotFoundException("없는 지역입니다.");
        }
        int current_page_num = Integer.parseInt(page_num);  //현재페이지
        int last_page_num = count_row / 10 + 1;             //마지막페이지

        //잘못된 페이지넘버가 들어온 경우
        //현재페이지가 0보다 작거나 마지막페이지 번호보다 큰 경우
        if(current_page_num<=0 || current_page_num > last_page_num){
            throw new NotFoundException("다시 조회바랍니다.");
        }

        int offset = (current_page_num-1) *10 ;

        Kd_Clas_Response kdClasResponse = new Kd_Clas_Response();
        kdClasResponse.setResult("Success");
        kdClasResponse.setLast_page_num(last_page_num);
        kdClasResponse.setCurrent_page(Integer.parseInt(page_num));
        kdClasResponse.setEngl_kd_clas_list(kindergardenService.getKindergarden(regn_cd,city_cd,offset));

        return new ResponseEntity<>(kdClasResponse,httpHeaders, HttpStatus.OK);
    }
    //추천 영어 유치부 조회
    @GetMapping("/youngustandard/recommend/{prpns_data}")
    public ResponseEntity<Kd_Recomend_Response> search_Recommend_list(@PathVariable String prpns_data){
        PrpnsDataDTO prpnsDataDTO = propensityService.find_propensity(prpns_data);
        if(prpnsDataDTO == null){
            throw new NotFoundException("해당 성향을 찾을 수 없어요. 다시 시도해주시기 바랍니다.");
        }

        List<KindergardenDTO> kindergardenDTOList = kindergardenService.find_recommend_list(prpnsDataDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Kd_Recomend_Response kdRecomendResponse  = new Kd_Recomend_Response();
        kdRecomendResponse.setResult("Success");
        kdRecomendResponse.setEngl_kd_clas_list(kindergardenDTOList);

        return new ResponseEntity<>(kdRecomendResponse,httpHeaders, HttpStatus.OK);
    }
}
