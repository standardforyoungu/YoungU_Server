package com.youngustandard.youngu_server.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropensityResponse {
    private String result;
    private String message;
    private int chl_id;
}
