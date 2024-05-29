package com.youngustandard.youngu_server.Config;

import com.youngustandard.youngu_server.Login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Aspect
@Component
public class AuthorizeAop {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private LoginService loginService;
    public AuthorizeAop(LoginService loginService){
        this.loginService=loginService;

    }
    //Before 어노테이션으로 @LoginCheck가 달린 메소드 시작 전에 Advice가 적용되도록 포인트 컷 해줌
//    @Before("@annotation(com.youngustandard.youngu_server.Config.AuthorizeCheck)")
//    public void AuthorizeCheck(){
//        String access_value = request.getHeader("access_token");
//        HashMap<String,Object> at_map = loginService.decode_JWT(access_value);
//        String id = at_map.get("id").toString();
//
//        long nowTime = System.currentTimeMillis() / 1000;
//
//        if(nowTime < Long.parseLong(String.valueOf(at_map.get("exp"))) ){
//            //아직 만료시간이 남았음.
//            System.out.println(" true ");
//            Object resultObj =
//        }
//        else{
//            //갱신해야함.
//            System.out.println(" false ");
//        }
//
//        // 1. Access_token, Refresh_toekn 만료여부 확인
//        //  1-1. 둘 다 만료라면 -> 405 에러 발생(unauthorized)
//        //  1-2. Access_token만 만료라면
//        //      1-2-1. Refresh_Token을 검증(서버 DB에 저장되어있는 Refresh token 일치성 확인)
//        //      1-2-2. Access_token 재발급
//        //  1-3. Refresh_token만 만료라면 -> Access_Token을 검증하여 Refresh_token 재발급
//        //  1-4. 둘 다 유효하다면 -> 정상처리
//    }

    @Around("@annotation(com.youngustandard.youngu_server.Config.AuthorizeCheck)")
    public Object AuthorizeCheck(ProceedingJoinPoint jp) throws Throwable {
        String access_value = request.getHeader("access_token");
        HashMap<String,Object> at_map = loginService.decode_JWT(access_value);
        String id = at_map.get("id").toString();

        long nowTime = System.currentTimeMillis() / 1000;

        if(nowTime < Long.parseLong(String.valueOf(at_map.get("exp"))) ){
            //아직 만료시간이 남았음.
            System.out.println(" true ");

        }
        else{
            //갱신해야함.
            System.out.println(" false ");
        }
        Object resultObj = jp.proceed(new Object[]{id});
        return resultObj;
        // 1. Access_token, Refresh_toekn 만료여부 확인
        //  1-1. 둘 다 만료라면 -> 405 에러 발생(unauthorized)
        //  1-2. Access_token만 만료라면
        //      1-2-1. Refresh_Token을 검증(서버 DB에 저장되어있는 Refresh token 일치성 확인)
        //      1-2-2. Access_token 재발급
        //  1-3. Refresh_token만 만료라면 -> Access_Token을 검증하여 Refresh_token 재발급
        //  1-4. 둘 다 유효하다면 -> 정상처리
    }
}
