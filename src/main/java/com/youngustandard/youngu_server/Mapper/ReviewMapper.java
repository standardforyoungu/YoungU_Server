package com.youngustandard.youngu_server.Mapper;

import com.youngustandard.youngu_server.Review.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    int save_Review(ReviewDTO reviewDTO);

    int find_Review_YN(String mbrId);
}
