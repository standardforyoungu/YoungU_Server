package com.youngustandard.youngu_server.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewYNResponse {
    private String result;
    private int review_cnt;
}
