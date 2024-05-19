package com.youngustandard.youngu_server.Login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youngustandard.youngu_server.Exception.NotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class KakaoLoginServiceImpl implements LoginService{

    @Value("${kakao.restAPIkey}")
    private String KAKAO_CLIENT_ID;
    @Value("${kakao.redirectUri}")
    private String KAKAO_REDIRECT_URL;
    @Value("${kakao.location}")
    private String KAKAO_LOCATION;
    @Value("${kakao.requestURL}")
    private String KAKAO_GET_ACCESS_TOKEN_URL;
    @Value("${jwt.secretKey}")
    private String jwt_secret_key;
    @Override
    public String getRedirectURL() {
        return KAKAO_LOCATION+"&client_id="+KAKAO_CLIENT_ID+"&redirect_uri="+KAKAO_REDIRECT_URL;
    }
    private LoginRepository loginRepository;
    public KakaoLoginServiceImpl(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }
    @Override
    public LoginDTO getToken(String authorization_code) throws Exception {
        String access_token = "";
        String refresh_token = "";
        URL url = new URL(KAKAO_GET_ACCESS_TOKEN_URL);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true); //post 요청을 위해 기본값이 false인 것을 true로 변환

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id="+KAKAO_CLIENT_ID);
        sb.append("&redirect_uri="+KAKAO_REDIRECT_URL);
        sb.append("&code="+authorization_code);
        bufferedWriter.write(sb.toString());
        bufferedWriter.flush();

        int response_code=conn.getResponseCode();
        //TODO response_code가 200이 아니라면 예외처리해야함
//        if(response_code != 200){
//            throw new NotFoundException("잠시 후 다시 시도해주시기 바랍니다.");
//        }
        //요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line ="";
        String result = "";
        while((line = bufferedReader.readLine())!=null){
            result += line;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
        });
        System.out.println("jsonMap = " + jsonMap);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccess_token(jsonMap.get("access_token").toString());
        loginDTO.setRefresh_token(jsonMap.get("refresh_token").toString());


        bufferedReader.close();;
        bufferedWriter.close();

        return loginDTO;
    }

    @Override
    public LoginDTO getUserInfo(LoginDTO loginDTO) throws Exception{
        String postURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(postURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + loginDTO.getAccess_token());

            int responseCode = conn.getResponseCode();
            //TODO response_code가 200이 아니라면 예외처리해야함
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            JsonElement element = JsonParser.parseString(result.toString());
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            //JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String id = element.getAsJsonObject().get("id").getAsString();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();

            String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
            String thumb_image = properties.getAsJsonObject().get("thumbnail_image").getAsString();

            loginDTO.setMbr_id(id);
            loginDTO.setMbr_nck_nm(nickname);
            loginDTO.setPrf_img(profile_image);
            loginDTO.setThumb_img(thumb_image);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return loginDTO;
    }

    @Override
    public boolean find_User(String id) {
        //유저 존재 여부기를 찾는 함수
        int user_cnt = loginRepository.cnt_user_num(id);
        if(user_cnt == 0 ){
            return false;
        }
        return true;
    }

    @Override
    public void add_User(LoginDTO loginDTO) {
        //회원가입 진행
        loginRepository.add_User(loginDTO);
        loginRepository.add_User_Secret(loginDTO);
    }

    @Override
    public String create_JWT(String token,String flag, String id) {
        if(flag.equals("AT")){
            return Jwts.builder()
                    .setHeaderParam("type","youngu-jwt")
                    .claim("id",id)
                    .claim("toekn",token)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+59*60*6*1000))
                    .signWith(SignatureAlgorithm.HS256,jwt_secret_key)
                    .compact();
        }
        else{
            return Jwts.builder()
                    .setHeaderParam("type","youngu-jwt-rt")
                    .claim("id",id)
                    .claim("toekn",token)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+59*60*24*60*1000))
                    .signWith(SignatureAlgorithm.HS256,jwt_secret_key)
                    .compact();
        }
    }

    @Override
    public HashMap<String, Object> decode_JWT(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        HashMap<String,Object> d_map=new HashMap<>();
        String payload = token.split("\\.")[1];
        
        String decoded_payload = new String(decoder.decode(payload));
        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(decoded_payload);
        JsonObject jsonObject = (JsonObject) obj;
        d_map.put("id",jsonObject.get("id"));
        d_map.put("exp", jsonObject.get("exp"));
        return d_map;
    }

}
