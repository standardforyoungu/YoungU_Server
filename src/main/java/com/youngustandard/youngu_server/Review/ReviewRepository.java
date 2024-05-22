package com.youngustandard.youngu_server.Review;

public interface ReviewRepository {
    int save_Review(ReviewDTO reviewDTO);

    int find_Review_YN(String mbrId);
}
