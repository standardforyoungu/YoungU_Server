package com.youngustandard.youngu_server.Review;

import com.youngustandard.youngu_server.Mapper.ReviewMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository{
    private ReviewMapper reviewMapper;
    public ReviewRepositoryImpl(ReviewMapper reviewMapper){
        this.reviewMapper=reviewMapper;
    }
    @Override
    public int save_Review(ReviewDTO reviewDTO) {
        return reviewMapper.save_Review(reviewDTO);
    }

    @Override
    public int find_Review_YN(String mbrId) {
        return reviewMapper.find_Review_YN(mbrId);
    }
}
