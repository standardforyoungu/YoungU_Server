package com.youngustandard.youngu_server.Child;

import com.youngustandard.youngu_server.Response.ChildResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;
    @GetMapping("/youngustandard/child/{mbr_id}")
    public ResponseEntity<ChildResponse> search_Child(@PathVariable String mbr_id){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        //TODO mbr_id 유효한 id 인지 확인

        ChildResponse childResponse =new ChildResponse();
        childResponse.setResult("Success");
        childResponse.setMbr_id(mbr_id);
        childResponse.setChild_list(childService.find_Children(mbr_id));

        return new ResponseEntity<>(childResponse,httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/youngustandard/child/{mbr_id}")
    public void insert_Child(@RequestBody ChildDTO childDTO, @PathVariable String mbr_id){
        System.out.println("childDTO = " + childDTO);
        System.out.println("mbr_id = " + mbr_id);
    }
}
