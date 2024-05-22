package com.youngustandard.youngu_server.Review;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String mbr_id;
    @NotNull(message = "리뷰를 정성스럽게 작성해주세요.")
    private String mbr_rvw;
    @NotNull(message = "리뷰 평점을 부탁드릴게요!")
    @Range(min=1,max=5,message = "1~5점에 해당하는 평점을 선택해주세요!")
    private int mbr_star;
}
