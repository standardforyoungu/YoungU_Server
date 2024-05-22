package com.youngustandard.youngu_server.Review;

public interface ReviewService {
    int save_Review(ReviewDTO reviewDTO);

    int find_Review_YN(String mbrId);
}
