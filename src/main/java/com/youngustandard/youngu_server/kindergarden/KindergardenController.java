package com.youngustandard.youngu_server.kindergarden;

import com.youngustandard.youngu_server.response.Kd_Clas_Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class KindergardenController {

    private final KindergardenService kindergardenService;
    @GetMapping("/youngustandard/{regn_cd}/{engl_kdgn_id}")
    public ResponseEntity<Object> find_kindergardens(@PathVariable String regn_cd, @PathVariable String engl_kdgn_id){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        Kd_Clas_Response kdClasResponse = new Kd_Clas_Response();

        //예외 발생 처리
        kdClasResponse.setResult("Success");
        kdClasResponse.setEngl_kd_clas_list(kindergardenService.getKindergarden(regn_cd,Integer.parseInt(engl_kdgn_id)));

        return new ResponseEntity<>(kdClasResponse,httpHeaders, HttpStatus.OK);
    }
}
