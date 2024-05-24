package com.youngustandard.youngu_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
public class ex {
    @GetMapping("/")
    public String mainPage(){
        return "Main Page";
    }
    @GetMapping("/test")
    public String test(){

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println(base64Key);

        return "서버 연결 완료-cicd-origin/develop";
    }
}
