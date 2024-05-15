package com.youngustandard.youngu_server.Config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizeAop {

    //Before 어노테이션으로 @LoginCheck가 달린 메소드 시작 전에 Advice가 적용되도록 포인트 컷 해줌
    @Before("@annotation(com.youngustandard.youngu_server.Config.AuthorizeCheck)")
    public void AuthorizeCheck(){
        // 1. Access_token, Refresh_toekn 만료여부 확인
        //  1-1. 둘 다 만료라면 -> 405 에러 발생(unauthorized)
        //  1-2. Access_token만 만료라면
        //      1-2-1. Refresh_Token을 검증(서버 DB에 저장되어있는 Refresh token 일치성 확인)
        //      1-2-2. Access_token 재발급
        //  1-3. Refresh_token만 만료라면 -> Access_Token을 검증하여 Refresh_token 재발급
        //  1-4. 둘 다 유효하다면 -> 정상처리
    }
}
