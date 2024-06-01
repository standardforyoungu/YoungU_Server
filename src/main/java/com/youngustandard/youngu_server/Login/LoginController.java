package com.youngustandard.youngu_server.Login;

import com.youngustandard.youngu_server.Config.AuthorizeCheck;
import com.youngustandard.youngu_server.Response.DefaultResponse;
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
        System.out.println("URL = " + URL);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    //@GetMapping("/youngustandard/login/oauth2/callback")
    //@ResponseBody
    //@RequestBody LoginDTO aloginDTO
    @RequestMapping(value="/youngustandard/login/oauth2/callback", method = {RequestMethod.POST})
    public ResponseEntity<Object> loginCallback( @RequestBody LoginDTO aloginDTO) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        //accessToken 이랑 refreshToken 발급
        System.out.println(" =1 " );
        LoginDTO loginDTO = loginService.getToken(aloginDTO.getCode());
        //LoginDTO loginDTO = loginService.getToken(code);
        //accessToken으로 사용자 정보 받으러 가기
        System.out.println(" =2" );
        loginDTO = loginService.getUserInfo(loginDTO);
        System.out.println(" =3 " );
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

        String jwt_access_toekn = loginService.create_JWT(loginDTO.getAccess_token(),"AT", loginDTO.getMbr_id());

        String jwt_refresh_toekn = loginService.create_JWT(loginDTO.getRefresh_token(),"RT", loginDTO.getMbr_id());
        loginResponse.setAccess_token(jwt_access_toekn);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        System.out.println(" =4 " );
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

    //회원탈퇴
    @RequestMapping(value="/youngustandard/withdraw", method = {RequestMethod.DELETE})
    @AuthorizeCheck
    public ResponseEntity<DefaultResponse> withdraw_User(@PathVariable String mbr_id){
        loginService.withdraw_user(mbr_id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setResult("Success");
        defaultResponse.setMessage("안녕히 가세요.");

        return new ResponseEntity<>(defaultResponse,headers, HttpStatus.OK);
    }
    @RequestMapping(value="/youngustandard/logout", method = {RequestMethod.GET})
    @AuthorizeCheck
    public void logout(){
        //헤더에서 access_token 뽑아내고
        String access_token = null;
        loginService.logout(access_token);
    }
}
