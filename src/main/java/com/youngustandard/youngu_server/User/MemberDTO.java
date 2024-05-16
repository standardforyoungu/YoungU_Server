package com.youngustandard.youngu_server.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    String result;
    String mbr_id;
    String mbr_nck_nm;
    String prf_img;
    String thumb_img;
}
