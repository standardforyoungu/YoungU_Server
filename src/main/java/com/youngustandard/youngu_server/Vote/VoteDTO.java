package com.youngustandard.youngu_server.Vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    private List<List<String>> info;
}
