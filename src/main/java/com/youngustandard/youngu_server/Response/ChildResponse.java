package com.youngustandard.youngu_server.Response;

import com.youngustandard.youngu_server.Child.ChildDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildResponse {
    private String result;
    private String mbr_id;
    private List<ChildDTO> child_list;
}
