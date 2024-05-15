package com.youngustandard.youngu_server.Login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LoginDTO {
    private String code;
    private String access_token;
    private String refresh_token;
    private String mbr_id;
    private String mbr_nck_nm;
    private String prf_img;
    private String thumb_img;
}
