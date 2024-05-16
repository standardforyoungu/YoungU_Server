package com.youngustandard.youngu_server.Login;

import com.youngustandard.youngu_server.Response.LoginResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;

@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService =  loginService;
    }

    @GetMapping("/youngustandard/login")
    public ResponseEntity<LoginDTO> login(){
        String URL = loginService.getRedirectURL();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(URL));
        headers.setContentType(MediaType.APPLICATION_JSON);
        //TODO 해결해야할 문제..
        //TODO client_id와 redirect_uri 부분 암호화 필요?
        System.out.println("URL = " + URL);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/youngustandard/login/oauth2/callback")
    //@ResponseBody
    //@RequestBody LoginDTO aloginDTO
    public ResponseEntity<Object> loginCallback(@RequestParam String code) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        //accessToken 이랑 refreshToken 발급
        //LoginDTO loginDTO = loginService.getToken(aloginDTO.getCode());
        LoginDTO loginDTO = loginService.getToken(code);
        //accessToken으로 사용자 정보 받으러 가기
        loginDTO = loginService.getUserInfo(loginDTO);

        //사용자 id로 신규유저인지 기존유저인지 확인하기
        boolean exists = loginService.find_User(loginDTO.getMbr_id());
        //디비에 존재하지 않는다면 false
        //존재한다면 true
        if(!exists){
            //신규유저
            //회원가입 진행하기 userInfo값으로
            loginService.add_User(loginDTO);
            loginResponse.setExist_yn("no");
        }
        else{
            loginResponse.setExist_yn("yes");
        }
        loginResponse.setResult("Success");
        //access token, refresh token을 jwt로 만들기
        System.out.println("loginDTO = " + loginDTO.getAccess_token());
        String jwt_access_toekn = loginService.create_JWT(loginDTO.getAccess_token(),"AT", loginDTO.getMbr_id());
        System.out.println("jwt_access_toekn = " + jwt_access_toekn);
        String jwt_refresh_toekn = loginService.create_JWT(loginDTO.getRefresh_token(),"RT", loginDTO.getMbr_id());
        loginResponse.setAccess_token(jwt_access_toekn);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));

        //access_token 쿠키에 담아보내기
        ResponseCookie cookie = ResponseCookie.from("refresh_token",jwt_refresh_toekn)
                .domain("localhost")
                .httpOnly(true)
                .secure(false)
                .maxAge(Duration.ofDays(61))
                .sameSite("Strict")
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginResponse);
    }
}
