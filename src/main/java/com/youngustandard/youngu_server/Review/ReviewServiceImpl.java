package com.youngustandard.youngu_server.Review;

import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository){
        this.reviewRepository=reviewRepository;
    }
    @Override
    public int save_Review(ReviewDTO reviewDTO) {
        return reviewRepository.save_Review(reviewDTO);
    }

    @Override
    public int find_Review_YN(String mbrId) {
        return reviewRepository.find_Review_YN(mbrId);
    }
}
