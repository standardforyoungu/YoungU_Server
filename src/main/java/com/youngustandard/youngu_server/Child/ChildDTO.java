package com.youngustandard.youngu_server.Child;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {
    int chl_id;
    String chl_nck_nm;
    String chl_sex;
    String chl_age;
    String chl_mbti;
}
