package com.youngustandard.youngu_server.Vote;

import com.youngustandard.youngu_server.Response.DefaultResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
public class VoteController {
    private VoteService voteService;

    private static HttpHeaders httpHeaders = new HttpHeaders();
    public VoteController(VoteService voteService){
        this.voteService=voteService;
    }

    @PostMapping("/youngustandard/vote")
    public ResponseEntity<DefaultResponse> save_Vote_Result(@RequestBody VoteDTO voteDTO){
        voteService.save_Vote_Result(voteDTO);
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setResult("Success");
        defaultResponse.setMessage("투표해주셔서 감사합니다.");
        return new ResponseEntity<>(defaultResponse,httpHeaders, HttpStatus.OK);
    }
}
