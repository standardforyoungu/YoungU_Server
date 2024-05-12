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
    private String refresh_token;
    private String mbr_id;
    private String mbr_nck_nm;
    private String prf_img;
    private String thumb_img;
}
