package com.youngustandard.youngu_server.Login;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

public interface LoginService {
    String getRedirectURL();
    LoginDTO getToken(String authorization_code) throws Exception;

    LoginDTO getUserInfo(LoginDTO loginDTO) throws Exception;

    boolean find_User(String id);

    void add_User(LoginDTO loginDTO);

    String create_JWT(String token,String flag, String id);
    HashMap<String,Object> decode_JWT(String token);

    void withdraw_user(String mbrId);

    int logout(String accessToken) throws IOException;
}
