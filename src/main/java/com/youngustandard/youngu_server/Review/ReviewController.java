package com.youngustandard.youngu_server.Review;

import com.youngustandard.youngu_server.Config.AuthorizeCheck;
import com.youngustandard.youngu_server.Exception.NotFoundException;
import com.youngustandard.youngu_server.Response.DefaultResponse;
import com.youngustandard.youngu_server.Response.ReviewYNResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
public class ReviewController {
    private static HttpHeaders httpHeaders = new HttpHeaders();
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }

    @GetMapping("/youngustandard/review/{mbr_id}")
    //@AuthorizeCheck
    //리뷰 작성 여부
    public ResponseEntity<ReviewYNResponse> Review_YN(@PathVariable String mbr_id){
        int review_count = reviewService.find_Review_YN(mbr_id);
        ReviewYNResponse reviewYNResponse = new ReviewYNResponse();
        reviewYNResponse.setResult("Success");
        reviewYNResponse.setReview_cnt(review_count);
        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(reviewYNResponse,httpHeaders, HttpStatus.OK);
    }
    @PostMapping("/youngustandard/review")
    public ResponseEntity<DefaultResponse> save_Review(@RequestBody ReviewDTO reviewDTO){
        int review_count = reviewService.find_Review_YN(reviewDTO.getMbr_id());
        if(review_count > 0 ){
            throw new NotFoundException("이미 리뷰 저장을 했어요.");
        }
        int proceed_result = reviewService.save_Review(reviewDTO);

        httpHeaders.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setResult("Success");
        defaultResponse.setMessage("정성스럽게 작성해주신 리뷰가 등록되었습니다. 감사합니다.");
        return new ResponseEntity<>(defaultResponse,httpHeaders, HttpStatus.OK);
    }

}
