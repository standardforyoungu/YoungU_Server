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

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private static HttpHeaders httpHeaders = new HttpHeaders();

    //@GetMapping("/youngustandard/user")
    @GetMapping("/youngustandard/user")
    // 유저 정보 조회
    @AuthorizeCheck
    public ResponseEntity<MemberDTO> search_UserInfo(String mbr_id){

        MemberDTO memberDTO = userService.get_UserInfo(mbr_id);
        if(memberDTO == null){
            throw new NotFoundException("해당 유저의 정보를 찾을 수 없습니다.");
        }
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        memberDTO.setResult("Success");
        return new ResponseEntity<>(memberDTO,httpHeaders,HttpStatus.OK);
    }

    //@GetMapping("/youngustandard/user/child")
    @GetMapping("/youngustandard/user/child")
    //특정 유저의 아이 전체 조회
    @AuthorizeCheck
    public ResponseEntity<ChildResponse> search_AllChildInfo(String mbr_id){

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

    @PostMapping("/youngustandard/user/child")
    @AuthorizeCheck
    public ResponseEntity<DefaultResponse> insert_ChildInfo(@Valid @RequestBody ChildDTO childDTO, String mbr_id){
       int next_child_id;
        if(!(childDTO.getChl_sex().equals("F") || childDTO.getChl_sex().equals("M"))){
           throw new ViolateRuleException("아이는 여아 혹은 남아입니다.");
       }
        MemberDTO memberDTO = userService.get_UserInfo(mbr_id);
        if(memberDTO == null){
            throw new NotFoundException("해당 유저의 정보를 찾을 수 없습니다.");
        }
        childDTO.setMbr_id(mbr_id);

        if(userService.find_Max_Child_Id(childDTO)==null){
            next_child_id=1;
        }
        else{
            next_child_id = userService.find_Max_Child_Id(childDTO).getChl_id()+1;

        }

        if(next_child_id>3){
            throw new ViolateRuleException("아이 정보는 최대 3개까지 저장가능합니다. 아이 정보를 삭제한 후 다시 시도해주시기 바랍니다.");
        }
        childDTO.setChl_id(next_child_id);

        int procced_insert = userService.insert_ChiildInfo(childDTO);
        if(procced_insert<1){
            throw new ViolateRuleException("잠시 후 다시 시도해 주시기 바랍니다.");
        }

        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setResult("Success");
        defaultResponse.setMessage("아이 정보를 성공적으로 저장하였습니다.");
        return new ResponseEntity<>(defaultResponse,httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/youngustandard/user/child")
    @AuthorizeCheck
    public ResponseEntity<DefaultResponse> delete_ChildInfo(@RequestBody ChildDTO childDTO, String mbr_id){
        MemberDTO memberDTO = userService.get_UserInfo(mbr_id);
        if(memberDTO == null){
            throw new NotFoundException("해당 유저의 정보를 찾을 수 없습니다.");
        }
        childDTO.setMbr_id(mbr_id);
        int process_result = userService.delete_Child_Info(childDTO);
        if(process_result<1){
            throw new NotFoundException("해당 아이의 정보를 찾을 수 없습니다. 다시 시도해 주세요.");
        }
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setResult("Success");
        defaultResponse.setMessage("아이 정보를 성공적으로 삭제하였습니다.");
        return new ResponseEntity<>(defaultResponse,httpHeaders, HttpStatus.OK);
    }
    @PatchMapping("/youngustandard/user/child")
    @AuthorizeCheck
    public ResponseEntity<DefaultResponse> update_ChildInfo(@RequestBody ChildDTO childDTO, String mbr_id){
        MemberDTO memberDTO = userService.get_UserInfo(mbr_id);
        if(memberDTO == null){
            throw new NotFoundException("해당 유저의 정보를 찾을 수 없습니다.");
        }
        childDTO.setMbr_id(mbr_id);
        int proceed_result = userService.update_Child_Info(childDTO);
        if(proceed_result<1){
            throw new NotFoundException("아이의 정보를 찾을 수 없습니다. 다시 시도해 주시기 바랍니다.");
        }
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setResult("Success");
        defaultResponse.setMessage("아이 정보를 성공적으로 수정하였습니다.");
        return new ResponseEntity<>(defaultResponse,httpHeaders, HttpStatus.OK);
    }
}
