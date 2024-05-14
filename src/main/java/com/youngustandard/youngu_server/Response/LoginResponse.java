package com.youngustandard.youngu_server.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String result;
    private String exist_yn;
    private String access_token;
}
