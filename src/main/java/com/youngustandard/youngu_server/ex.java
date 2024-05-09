package com.youngustandard.youngu_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ex {
    @GetMapping("/test")
    public String test(){
        return "서버 연결 완료-cicd-origin/develop";
    }
}
