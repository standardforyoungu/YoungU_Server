package com.youngustandard.youngu_server.User;

import com.youngustandard.youngu_server.Config.AuthorizeCheck;
import com.youngustandard.youngu_server.Exception.NotFoundException;
import com.youngustandard.youngu_server.Exception.ViolateRuleException;
import com.youngustandard.youngu_server.Response.ChildResponse;
import com.youngustandard.youngu_server.Response.DefaultResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static HttpHeaders httpHeaders = new HttpHeaders();

    //@GetMapping("/youngustandard/user")
    @GetMapping("/youngustandard/user/{mbr_id}")
    // 유저 정보 조회
    //@AuthorizeCheck
    public ResponseEntity<MemberDTO> search_User(@PathVariable String mbr_id){

        MemberDTO memberDTO = userService.get_UserInfo(mbr_id);
        if(memberDTO == null){
            throw new NotFoundException("해당 유저의 정보를 찾을 수 없습니다.");
        }
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        memberDTO.setResult("Success");
        return new ResponseEntity<>(memberDTO,httpHeaders,HttpStatus.OK);
    }

    //@GetMapping("/youngustandard/user/child")
    @GetMapping("/youngustandard/user/{mbr_id}/child")
    //특정 유저의 아이 전체 조회
    //@AuthorizeCheck
    public ResponseEntity<ChildResponse> search_AllChild(@PathVariable String mbr_id){

        MemberDTO memberDTO = userService.get_UserInfo(mbr_id);
        if(memberDTO == null){
            throw new NotFoundException("해당 유저의 정보를 찾을 수 없습니다.");
        }

        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        ChildResponse childResponse =new ChildResponse();
        childResponse.setResult("Success");
        childResponse.setMbr_id(mbr_id);
        childResponse.setChild_list(userService.find_Children(mbr_id));

        return new ResponseEntity<>(childResponse,httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/youngustandard/user/{mbr_id}/child")
    public ResponseEntity<DefaultResponse> insert_Child(@Valid @RequestBody ChildDTO childDTO, @PathVariable String mbr_id){
       if(!(childDTO.getChl_sex().equals("F") || childDTO.getChl_sex().equals("M"))){
           throw new ViolateRuleException("아이는 여아 혹은 남아입니다.");
       }
        MemberDTO memberDTO = userService.get_UserInfo(mbr_id);
        if(memberDTO == null){
            throw new NotFoundException("해당 유저의 정보를 찾을 수 없습니다.");
        }
        int next_child_id = userService.find_Max_Child_Id(mbr_id)+1;
        if(next_child_id>3){
            throw new ViolateRuleException("아이 정보는 최대 3개까지 저장가능합니다. 아이 정보를 삭제한 후 다시 시도해주시기 바랍니다.");
        }
        childDTO.setChl_id(next_child_id);
        childDTO.setMbr_id(mbr_id);
        int procced_insert = userService.insert_ChiildInfo(childDTO);
        if(procced_insert<1){
            throw new ViolateRuleException("잠시 후 다시 시도해 주시기 바랍니다.");
        }

        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setResult("Success");
        return new ResponseEntity<>(defaultResponse,httpHeaders, HttpStatus.OK);
    }
}
