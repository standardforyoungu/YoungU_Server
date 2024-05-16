package com.youngustandard.youngu_server.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {
    String mbr_id;
    int chl_id;
    @NotBlank(message = "아이 닉네임을 입력해 주세요.")
    @Size(min = 1, max = 10, message = "아이 닉네임은 최대 10자리입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "아이 닉네임은 한글,영어,숫자만 사용 가능해요.")
    String chl_nck_nm;
    @NotBlank(message = "아이 성별을 입력해 주세요.")
    String chl_sex;
    @NotNull(message = "아이 나이를 입력해 주세요.")
    @Range(min=4,max=7,message = "아이 나이는 4세부터 7세 사이까지 입니다.")
    int chl_age;
    String chl_mbti;
}
